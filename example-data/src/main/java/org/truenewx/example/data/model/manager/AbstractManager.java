package org.truenewx.example.data.model.manager;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.validator.constraints.NotBlank;
import org.truenewx.data.model.unity.AbstractUnity;
import org.truenewx.data.validation.constraint.NotContainsSpecialChars;

/**
 * 抽象管理员
 *
 * @author jianglei
 * @since JDK 1.8
 */
public abstract class AbstractManager extends AbstractUnity<Integer> {

    /**
     * 用户名
     */
    @NotBlank
    @NotContainsSpecialChars
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @NotContainsSpecialChars
    private String password;

    /**
     * 姓名
     */
    @NotBlank
    @NotContainsSpecialChars
    private String fullname;

    /**
     * 是否顶级管理员
     */
    private boolean top;

    /**
     * 是否禁用
     */
    private boolean disabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色集
     */
    private Collection<Role> roles = new HashSet<>(0);

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

    public boolean isTop() {
        return this.top;
    }

    public void setTop(final boolean top) {
        this.top = top;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Collection<Role> getRoles() {
        return this.roles;
    }

    protected void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }

}
