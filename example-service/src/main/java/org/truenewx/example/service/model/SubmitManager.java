package org.truenewx.example.service.model;

import org.truenewx.data.model.SubmitModel;
import org.truenewx.example.data.model.manager.Manager;

/**
 * 管理员提交模型
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class SubmitManager implements SubmitModel<Manager> {

    private String username;
    private String password;
    private String fullname;
    private int[] roleIds;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

    public int[] getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(final int[] roleIds) {
        this.roleIds = roleIds;
    }

}
