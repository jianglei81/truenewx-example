package org.truenewx.example.service.misc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.core.util.DateUtil;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.data.query.QueryOrder;
import org.truenewx.data.query.QueryResult;
import org.truenewx.data.query.SingleQueryOrder;
import org.truenewx.example.data.dao.misc.ActionLogDao;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.data.model.misc.ActionLog;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.service.unity.AbstractUnityService;
import org.truenewx.support.log.data.model.Action;

/**
 * 操作日志服务实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class ActionLogServiceImpl extends AbstractUnityService<ActionLog, Long>
        implements ActionLogService {

    @Autowired
    private ActionLogDao dao;

    @Override
    protected UnityDao<ActionLog, Long> getDao() {
        return this.dao;
    }

    @Override
    public void add(final Integer managerId, final Action action) {
        final Manager manager = getService(ManagerService.class).find(managerId);
        if (manager != null) {
            final ActionLog log = new ActionLog();
            log.setManager(manager);
            log.setAction(action);
            log.setCreateTime(new Date());
            this.dao.save(log);
        }
    }

    @Override
    public QueryResult<ActionLog> query(final String managerKeyword, Date beginTime, Date endTime,
            final int pageSize, final int pageNo) {
        beginTime = DateUtil.setTime(beginTime, 0, 0, 0, 0);
        endTime = DateUtil.setTime(endTime, 23, 59, 59, 999);
        final QueryOrder order = new SingleQueryOrder("createTime", true);
        return this.dao.query(managerKeyword, beginTime, endTime, pageSize, pageNo, order);
    }

}
