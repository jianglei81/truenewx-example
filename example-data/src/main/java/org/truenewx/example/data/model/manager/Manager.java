package org.truenewx.example.data.model.manager;

import org.truenewx.core.annotation.Caption;
import org.truenewx.data.model.CloneableForSession;

/**
 * 管理员
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Caption("管理员")
public class Manager extends AbstractManager
        implements CloneableForSession<Manager>, Comparable<Manager> {

    @Override
    public Manager cloneForSession() {
        final Manager manager = new Manager();
        manager.setId(getId());
        manager.setUsername(getUsername());
        manager.setPassword(getPassword());
        manager.setFullname(getFullname());
        manager.setTop(isTop());
        manager.setDisabled(isDisabled());
        manager.setCreateTime(getCreateTime());
        for (final Role role : getRoles()) {
            manager.getRoles().add(role.cloneForSession());
        }
        return manager;
    }

    @Override
    public int compareTo(final Manager other) {
        return getUsername().compareTo(other.getUsername());
    }

}
