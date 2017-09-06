package org.truenewx.example.data.dao.misc;

import java.util.Date;

import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.data.query.QueryOrder;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.misc.ActionLog;

/**
 * 操作日志DAO
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface ActionLogDao extends UnityDao<ActionLog, Long> {

    QueryResult<ActionLog> query(String managerKeyword, Date beginTime, Date endTime, int pageSize,
            int pageNo, QueryOrder order);

}
