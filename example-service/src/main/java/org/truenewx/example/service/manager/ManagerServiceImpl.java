package org.truenewx.example.service.manager;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.core.Strings;
import org.truenewx.core.encrypt.Md5xEncrypter;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.core.spring.transaction.annotation.WriteTransactional;
import org.truenewx.data.model.SubmitModel;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.dao.manager.ManagerDao;
import org.truenewx.example.data.dao.manager.RoleDao;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.model.SubmitManager;
import org.truenewx.service.unity.AbstractUnityService;

/**
 * 管理员服务实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class ManagerServiceImpl extends AbstractUnityService<Manager, Integer>
        implements ManagerService {

    @Autowired
    private ManagerDao dao;
    @Autowired
    private RoleDao roleDao;

    private Md5xEncrypter encrypter = new Md5xEncrypter(17);

    @Override
    protected UnityDao<Manager, Integer> getDao() {
        return this.dao;
    }

    @Override
    public Manager validateLogin(final String username, final String md5Password)
            throws BusinessException {
        final Manager manager = this.dao.findByUsername(username);
        if (manager == null) { // 根据用户名找不到管理员
            throw new BusinessException(ManagerExceptionCodes.USERNAME_OR_PASSWORD_ERROR);
        }
        if (!this.encrypter.validateByMd5Source(manager.getPassword(), md5Password,
                manager.getId())) { // 密码错误
            throw new BusinessException(ManagerExceptionCodes.USERNAME_OR_PASSWORD_ERROR);
        }
        if (manager.isDisabled()) { // 管理员被禁用
            throw new BusinessException(ManagerExceptionCodes.DISABLED_MANAGER);
        }
        return manager;
    }

    @Override
    public QueryResult<Manager> findGeneral(final String keyword, final int pageSize,
            final int pageNo) {
        return this.dao.findByKeywordAndTop(keyword, false, pageSize, pageNo);
    }

    @Override
    public Manager updateFullname(final int id, final String fullname) {
        final Manager manager = find(id);
        if (manager != null) {
            manager.setFullname(fullname);
            this.dao.save(manager);
        }
        return manager;
    }

    @Override
    public Manager updatePassword(final int id, final String oldMd5Password,
            final String newMd5Password) throws BusinessException {
        final Manager manager = find(id);
        if (manager != null) {
            if (!this.encrypter.validateByMd5Source(manager.getPassword(), oldMd5Password,
                    manager.getId())) { // 密码错误
                throw new BusinessException(ManagerExceptionCodes.USERNAME_OR_PASSWORD_ERROR);
            }
            manager.setPassword(this.encrypter.encryptByMd5Source(newMd5Password, manager.getId()));
            this.dao.save(manager);
        }
        return manager;
    }

    @Override
    @WriteTransactional
    public Manager resetPassword(final int id, final String newMd5Password) {
        final Manager manager = find(id);
        if (manager != null) {
            manager.setPassword(this.encrypter.encryptByMd5Source(newMd5Password, manager.getId()));
            this.dao.save(manager);
        }
        return manager;
    }

    @Override
    public Manager add(final SubmitModel<Manager> submitModel) throws HandleableException {
        if (submitModel instanceof SubmitManager) {
            final SubmitManager model = (SubmitManager) submitModel;
            final String username = model.getUsername();
            if (this.dao.countByUsername(username) > 0) {
                throw new BusinessException(ManagerExceptionCodes.REPEAT_USERNAME, username)
                        .bind("username");
            }
            final Manager manager = new Manager();
            manager.setUsername(username);
            manager.setPassword(Strings.ASTERISK); // 密码暂时置为星号
            manager.setFullname(model.getFullname());
            manager.setCreateTime(new Date());
            applyRoles(manager, model.getRoleIds());
            this.dao.save(manager);
            // 有了id之后再用id做密钥进行密码加密
            final String encryptedPassword = this.encrypter.encryptByMd5Source(model.getPassword(),
                    manager.getId());
            manager.setPassword(encryptedPassword);
            this.dao.save(manager);
        }
        return null;
    }

    private void applyRoles(final Manager manager, final int[] roleIds) {
        final Collection<Role> roles = manager.getRoles();
        roles.clear();
        if (roleIds != null) {
            for (final int roleId : roleIds) {
                final Role role = this.roleDao.find(roleId);
                if (role != null) {
                    roles.add(role);

                    final Collection<Manager> managers = role.getManagers();
                    if (!managers.contains(manager)) {
                        managers.add(manager);
                        this.roleDao.save(role);
                    }
                }
            }
        }
    }

    @Override
    public Manager update(final Integer id, final SubmitModel<Manager> submitModel)
            throws HandleableException {
        if (submitModel instanceof SubmitManager) {
            final SubmitManager model = (SubmitManager) submitModel;
            final Manager manager = load(id);
            manager.setFullname(model.getFullname());
            applyRoles(manager, model.getRoleIds());
            this.dao.save(manager);
        }
        return null;
    }

}
