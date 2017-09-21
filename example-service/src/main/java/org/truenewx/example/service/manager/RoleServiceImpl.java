package org.truenewx.example.service.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.core.spring.transaction.annotation.WriteTransactional;
import org.truenewx.core.util.CollectionUtil;
import org.truenewx.data.model.SubmitModel;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.manager.RoleDao;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.model.SubmitRole;
import org.truenewx.service.unity.AbstractUnityService;

/**
 * 角色服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class RoleServiceImpl extends AbstractUnityService<Role, Integer> implements RoleService {

    @Autowired
    private RoleDao dao;

    @Override
    protected UnityDao<Role, Integer> getDao() {
        return this.dao;
    }

    @Override
    public List<Role> findAll() {
        return findByName(null);
    }

    @Override
    public List<Role> findByName(final String name) {
        return this.dao.findByName(name);
    }

    @Override
    public void validateName(final String name, final Integer id) throws BusinessException {
        if (this.dao.countByNameExceptId(name, id) > 0) {
            throw new BusinessException(ManagerExceptionCodes.ROLE_REPEAT_NAME, name);
        }
    }

    @Override
    protected Role beforeSave(final Integer id, final SubmitModel<Role> submitModel)
            throws HandleableException {
        if (submitModel instanceof SubmitRole) {
            final SubmitRole model = (SubmitRole) submitModel;
            final String name = model.getName();
            validateName(name, id);

            Role role;
            if (id == null) {
                role = new Role();
                role.setOrdinal(System.currentTimeMillis());
            } else {
                role = load(id);
            }
            role.setName(name);
            role.setRemark(model.getRemark());
            final Set<String> permissions = new HashSet<>();
            CollectionUtil.addAll(permissions, model.getPermissions());
            role.setPermissions(permissions);
            this.dao.save(role);
            return role;
        }
        return null;
    }

    @Override
    @WriteTransactional
    public Role move(final int id, final boolean down) {
        final Role role = find(id);
        if (role != null) {
            final long ordinal = role.getOrdinal();
            Role other;
            if (down) { // 下移
                other = this.dao.findClosestByMinOrdinal(ordinal);
            } else { // 上移
                other = this.dao.findClosestByMaxOrdinal(ordinal);
            }
            // 互换序号
            if (other != null) {
                role.setOrdinal(other.getOrdinal());
                this.dao.save(role);
                other.setOrdinal(ordinal);
                this.dao.save(other);
            }
        }
        return role;
    }

}
