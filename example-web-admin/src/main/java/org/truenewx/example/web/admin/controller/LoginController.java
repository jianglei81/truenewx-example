package org.truenewx.example.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.truenewx.core.Strings;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.web.exception.annotation.HandleableExceptionResult;
import org.truenewx.web.security.login.UsernamePasswordToken;
import org.truenewx.web.spring.servlet.mvc.Loginer;
import org.truenewx.web.validation.generate.annotation.ValidationGeneratable;

/**
 * LoginController
 *
 * @author jianglei
 * @version 1.0.0 2014年1月12日
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/login")
public class LoginController implements Loginer {

    @RequestMapping(method = RequestMethod.GET)
    @ValidationGeneratable(Manager.class)
    public String get() {
        return "/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    @HandleableExceptionResult("/login")
    public String post(@RequestParam("username") final String username,
            @RequestParam("password") final String password,
            @RequestParam(value = "next", required = false) final String next,
            final HttpServletRequest request, final HttpServletResponse response)
            throws HandleableException {
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        ProjectWebUtil.getSubject(request, response).login(token);

        if (StringUtils.isNotBlank(next) && next.startsWith(Strings.SLASH)) { // 必须斜杠开头以确保内部跳转
            return "redirect:" + next;
        }

        return "redirect:/person/index";
    }

    @Override
    public String login(final String loginName, final String password,
            final HttpServletRequest request, final HttpServletResponse response)
            throws HandleableException {
        return post(loginName, password, null, request, response);
    }

    @Override
    public boolean isLoginUrl(final String url) {
        return "/login".equals(url) || url.startsWith("/login?");
    }

}
