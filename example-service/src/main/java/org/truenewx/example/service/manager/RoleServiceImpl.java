package org.truenewx.example.service.manager;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.core.spring.transaction.annotation.WriteTransactional;
import org.truenewx.core.util.CollectionUtil;
import org.truenewx.data.model.SubmitModel;
import org.truenewx.data.model.unity.UnityUtil;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.manager.ManagerDao;
import org.truenewx.example.data.dao.manager.RoleDao;
import org.truenewx.example.data.model.manager.Manager;
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
    @Autowired
    private ManagerDao managerDao;

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
            final Collection<Manager> managers = role.getManagers();
            final int[] newManagerIds = model.getManagerIds();
            // 原管理员在新管理员中没有的，说明被移除了
            managers.removeIf(manager -> {
                final boolean removing = !ArrayUtils.contains(newManagerIds, manager.getId());
                if (removing) {
                    manager.getRoles().remove(role);
                }
                return removing;
            });
            // 此时管理员清单中现存的均为已包含在新管理员中的，需要添加新加的管理员
            Arrays.stream(newManagerIds)
                    .filter(managerId -> !UnityUtil.containsId(managers, managerId))
                    .mapToObj(managerId -> this.managerDao.find(managerId))
                    .filter(manager -> manager != null).forEach(manager -> {
                        managers.add(manager);
                        manager.getRoles().add(role);
                    });
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

    @Override
    public void delete(final Integer id) throws HandleableException {
        final Role role = find(id);
        if (role != null) {
            // 移除包含的管理员关系
            final Collection<Manager> managers = role.getManagers();
            managers.forEach(manager -> manager.getRoles().remove(role));
            managers.clear();

            this.dao.delete(role);
        }
    }

}
