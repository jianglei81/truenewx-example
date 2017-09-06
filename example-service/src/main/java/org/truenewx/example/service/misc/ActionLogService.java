package org.truenewx.example.service.misc;

import java.util.Date;

import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.misc.ActionLog;
import org.truenewx.service.Service;
import org.truenewx.support.log.service.ActionLogWriter;

/**
 * 操作日志服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface ActionLogService extends Service, ActionLogWriter<Integer> {

    QueryResult<ActionLog> query(String managerKeyword, Date beginTime, Date endTime, int pageSize,
            int pageNo);

}
