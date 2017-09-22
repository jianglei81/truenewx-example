package org.truenewx.example.service.model;

import org.truenewx.data.model.SubmitModel;
import org.truenewx.example.data.model.manager.Role;

/**
 * 角色提交模型
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class SubmitRole implements SubmitModel<Role> {

    private String name;
    private String remark;
    private String[] permissions;
    private int[] managerIds;

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

    public String[] getPermissions() {
        return this.permissions;
    }

    public void setPermissions(final String[] permissions) {
        this.permissions = permissions;
    }

    public int[] getManagerIds() {
        return this.managerIds;
    }

    public void setManagerIds(final int[] managerIds) {
        this.managerIds = managerIds;
    }

}
