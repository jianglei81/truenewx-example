package org.truenewx.example.data.model.misc;

import java.util.Date;

import org.truenewx.data.model.unity.AbstractUnity;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.support.log.data.model.Action;

/**
 * 抽象的操作日志
 *
 * @author jianglei
 * @since JDK 1.8
 */
abstract class AbstractActionLog extends AbstractUnity<Long> {

    private Manager manager;
    private Date createTime;
    private Action action;

    public Manager getManager() {
        return this.manager;
    }

    public void setManager(final Manager manager) {
        this.manager = manager;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

}
