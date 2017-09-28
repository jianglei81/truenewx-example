package org.truenewx.example.web.admin.interceptor;

import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.support.log.web.interceptor.AbstractActionLogInterceptor;
import org.truenewx.web.menu.model.Menu;

/**
 * 访问日志拦截器
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ActionLogInterceptor extends AbstractActionLogInterceptor<Integer> {

    @Override
    protected Integer getUserId() {
        return ProjectWebUtil.getManagerId();
    }

    @Override
    protected Menu getMenu() {
        return ProjectWebUtil.getMenu();
    }

}
