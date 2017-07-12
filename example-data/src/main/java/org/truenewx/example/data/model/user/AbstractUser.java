package org.truenewx.example.data.model.user;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.truenewx.data.model.unity.AbstractUnity;

/**
 *
 * 抽象用户
 *
 * @author jianlgei
 * @since JDK 1.8
 */
public abstract class AbstractUser extends AbstractUnity<Long> {

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 密码
     */
    @NotBlank
    private String password;
    /**
     * 姓名
     */
    @NotBlank
    private String fullname;

    /**
     * 性别
     */
    private Sex sex;

    /**
     * 是否禁用
     */
    private boolean disabled;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
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

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(final String fullname) {
        this.fullname = fullname;
    }

}
