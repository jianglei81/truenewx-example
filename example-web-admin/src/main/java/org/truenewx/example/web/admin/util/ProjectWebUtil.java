package org.truenewx.example.web.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.truenewx.core.spring.util.SpringUtil;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.web.menu.model.Menu;
import org.truenewx.web.security.authority.MenuAuthorizationInfo;
import org.truenewx.web.security.mgt.SubjectManager;
import org.truenewx.web.security.subject.Subject;
import org.truenewx.web.spring.context.SpringWebContext;
import org.truenewx.web.spring.util.SpringWebUtil;

/**
 * 工程WEB工具类
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ProjectWebUtil {

    private ProjectWebUtil() {
    }

    private static SubjectManager SUBJECT_MANAGER;

    private static SubjectManager getSubjectManager(final HttpServletRequest request) {
        if (SUBJECT_MANAGER == null) {
            final ApplicationContext context = SpringWebUtil.getApplicationContext(request);
            SUBJECT_MANAGER = SpringUtil.getFirstBeanByClass(context, SubjectManager.class);
        }
        return SUBJECT_MANAGER;
    }

    public static Subject getSubject(final HttpServletRequest request,
            final HttpServletResponse response) {
        return getSubjectManager(request).getSubject(request, response, Manager.class);
    }

    public static Subject getSubject(final HttpServletResponse response) {
        final HttpServletRequest request = SpringWebContext.getRequest();
        return getSubject(request, response);
    }

    public static Subject getSubject() {
        final HttpServletRequest request = SpringWebContext.getRequest();
        final HttpServletResponse response = SpringWebContext.getResponse();
        return getSubject(request, response);
    }

    public static Manager getManager() {
        return getSubject().getUser();
    }

    public static Integer getManagerId() {
        final Manager manager = getManager();
        return manager == null ? null : manager.getId();
    }

    public static Menu getMenu() {
        final MenuAuthorizationInfo sai = getSubject().getAuthorization();
        return sai == null ? null : sai.getMenu();
    }

}
