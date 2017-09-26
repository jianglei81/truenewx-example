package org.truenewx.example.data.model.manager;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.validator.constraints.NotBlank;
import org.truenewx.data.model.unity.AbstractUnity;
import org.truenewx.data.validation.constraint.NotContainsSpecialChars;

/**
 * 抽象角色
 *
 * @author jianglei
 * @since JDK 1.8
 */
public abstract class AbstractRole extends AbstractUnity<Integer> {

    /**
     * 名称
     */
    @NotBlank
    @NotContainsSpecialChars
    private String name;

    /**
     * 备注
     */
    @NotContainsSpecialChars
    private String remark;

    /**
     * 序号
     */
    private long ordinal;

    /**
     * 权限集
     */
    private Set<String> permissions;

    /**
     * 管理员集
     */
    private Collection<Manager> managers = new TreeSet<>();

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public long getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(final long ordinal) {
        this.ordinal = ordinal;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(final Set<String> permissions) {
        this.permissions = permissions;
    }

    public Collection<Manager> getManagers() {
        return this.managers;
    }

    protected void setManagers(final Collection<Manager> managers) {
        this.managers = managers;
    }

}
