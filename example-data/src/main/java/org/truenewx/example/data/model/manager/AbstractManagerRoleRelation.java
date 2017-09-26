package org.truenewx.example.data.model.manager;

import org.truenewx.data.model.relation.AbstractRelation;

/**
 * 抽象的管理员-角色关系
 *
 * @author jianglei
 * @since JDK 1.8
 */
public abstract class AbstractManagerRoleRelation extends AbstractRelation<Integer, Integer> {

    private static final long serialVersionUID = 8680765513711237061L;

    private Manager manager;
    private Role role;

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(final Manager manager) {
        this.manager = manager;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

}
