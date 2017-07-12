package org.truenewx.example.data.model.manager;

import org.truenewx.data.model.CloneableForSession;

/**
 * 角色
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class Role extends AbstractRole implements CloneableForSession<Role> {

    @Override
    public Role cloneForSession() {
        final Role role = new Role();
        role.setId(getId());
        role.setName(getName());
        role.setRemark(getRemark());
        role.setOrdinal(getOrdinal());
        role.setPermissions(getPermissions());
        return role;
    }

}
