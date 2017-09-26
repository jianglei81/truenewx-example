package org.truenewx.example.data.model.manager;

/**
 * 管理员-角色关系
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ManagerRoleRelation extends AbstractManagerRoleRelation {

    private static final long serialVersionUID = 8167843662481139578L;

    @Override
    public Integer getLeftId() {
        final Manager manager = getManager();
        return manager == null ? null : manager.getId();
    }

    @Override
    public Integer getRightId() {
        final Role role = getRole();
        return role == null ? null : role.getId();
    }

}
