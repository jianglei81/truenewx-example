package org.truenewx.example.data.dao.misc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.data.query.QueryOrder;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.misc.ActionLog;

/**
 * 操作日志DAO实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Repository
public class ActionLogDaoImpl extends HibernateUnityDaoSupport<ActionLog, Long>
        implements ActionLogDao {

    @Override
    public QueryResult<ActionLog> query(final String managerKeyword, final Date beginTime,
            final Date endTime, final int pageSize, final int pageNo, final QueryOrder order) {
        final StringBuffer hql = new StringBuffer("from ActionLog where 1=1");
        final Map<String, Object> params = new HashMap<>();
        if (managerKeyword != null) {
            hql.append(" and (manager.username like :managerKeyword")
                    .append(" or manager.fullname like :managerKeyword)");
            params.put("managerKeyword", managerKeyword);
        }
        if (beginTime != null) {
            hql.append(" and createTime>=:beginTime");
            params.put("beginTime", beginTime);
        }
        if (endTime != null) {
            hql.append(" and createTime<=:endTime");
            params.put("endTime", endTime);
        }
        return query(getEntityName(), hql, params, pageSize, pageNo, order);
    }

}
