package org.truenewx.example.web.admin.security;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.truenewx.core.Strings;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.example.web.admin.util.ProjectConstants;
import org.truenewx.web.menu.MenuResolver;
import org.truenewx.web.menu.model.Menu;
import org.truenewx.web.security.authority.AuthorizationInfo;
import org.truenewx.web.security.authority.MenuAuthorizationInfo;
import org.truenewx.web.security.login.DefaultLoginInfo;
import org.truenewx.web.security.login.DefaultLogoutInfo;
import org.truenewx.web.security.login.LoginInfo;
import org.truenewx.web.security.login.LoginToken;
import org.truenewx.web.security.login.LogoutInfo;
import org.truenewx.web.security.realm.Realm;
import org.truenewx.web.util.WebUtil;

/**
 * 管理员校验领域
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Component
public class ManagerRealm implements Realm<Manager> {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private MenuResolver menuResolver;

    @Override
    public Class<Manager> getUserClass() {
        return Manager.class;
    }

    @Override
    public LoginInfo getLoginInfo(final LoginToken loginToken) throws HandleableException {
        final String username = (String) loginToken.getPrincipal();
        final String md5Password = (String) loginToken.getCredentials();
        final Manager manager = this.managerService.validateLogin(username, md5Password);
        final DefaultLoginInfo loginInfo = new DefaultLoginInfo(manager.cloneForSession());
        final Cookie usernameCookie = WebUtil.createCookie(ProjectConstants.COOKIE_USERNAME,
                username, Integer.MAX_VALUE, true, Strings.SLASH);
        loginInfo.addCookie(usernameCookie);
        return loginInfo;
    }

    @Override
    public AuthorizationInfo getAuthorizationInfo(final Manager manager) {
        final MenuAuthorizationInfo ai = new MenuAuthorizationInfo(true); // 需要缓存
        if (manager.isTop()) { // 顶级管理员使用特殊的顶级权限菜单
            ai.addRole("top");
        }
        // 添加管理员所属各角色的权限
        for (final Role role : manager.getRoles()) {
            for (final String permission : role.getPermissions()) {
                ai.addPermission(permission);
            }
        }
        // 加载菜单
        final Menu menu = this.menuResolver.getAuthorizedMenu(ai);
        ai.setMenu(menu);
        return ai;
    }

    @Override
    public LogoutInfo getLogoutInfo(final Manager manager) throws BusinessException {
        return new DefaultLogoutInfo(false);
    }

}
