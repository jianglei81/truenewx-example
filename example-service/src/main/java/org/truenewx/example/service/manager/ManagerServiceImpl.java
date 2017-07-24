package org.truenewx.example.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.core.encrypt.Md5xEncrypter;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.manager.ManagerDao;
import org.truenewx.example.data.model.manager.Manager;
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

}
