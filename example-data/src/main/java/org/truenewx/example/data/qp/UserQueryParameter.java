package org.truenewx.example.data.qp;

import org.truenewx.data.query.QueryParameterImpl;
import org.truenewx.example.data.model.user.Sex;

/**
 * 用户分页查询参数
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class UserQueryParameter extends QueryParameterImpl {

    private String keyword;
    private Sex sex;
    private Boolean disabled;

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

}
