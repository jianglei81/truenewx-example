package org.truenewx.example.data.dao.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.truenewx.core.Strings;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.example.data.model.manager.Role;

/**
 * 角色DAO实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Repository
public class RoleDaoImpl extends HibernateUnityDaoSupport<Role, Integer> implements RoleDao {

    @Override
    public List<Role> findByName(final String name) {
        final StringBuffer hql = new StringBuffer("from Role");
        final List<Object> params = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            hql.append(" where name like ?");
            params.add(Strings.PERCENT + name + Strings.PERCENT);
        }
        hql.append(" order by ordinal");
        return getHibernateTemplate().list(hql, params);
    }

    @Override
    public int countByNameExceptId(final String name, final Integer exceptedId) {
        final StringBuffer hql = new StringBuffer("select count(*) from Role where name = ?");
        final List<Object> params = new ArrayList<>();
        params.add(name);
        if (exceptedId != null) {
            hql.append(" and id <> ?");
            params.add(exceptedId);
        }
        return getHibernateTemplate().count(hql, params);
    }

    @Override
    public Role findClosestByMaxOrdinal(final long maxOrdinal) {
        final String hql = "from Role where ordinal < ? order by ordinal desc";
        final List<Object> params = Arrays.asList(maxOrdinal);
        return getHibernateTemplate().first(hql, params);
    }

    @Override
    public Role findClosestByMinOrdinal(final long minOrdinal) {
        final String hql = "from Role where ordinal > ? order by ordinal";
        final List<Object> params = Arrays.asList(minOrdinal);
        return getHibernateTemplate().first(hql, params);
    }

}
