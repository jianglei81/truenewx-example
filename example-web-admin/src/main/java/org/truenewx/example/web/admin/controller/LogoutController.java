package org.truenewx.example.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.web.admin.util.ProjectWebUtil;

/**
 * 注销
 *
 * @author jianglei
 * @version 1.0.0 2015年9月16日
 * @since JDK 1.8
 */
@Controller
public class LogoutController {

    @RequestMapping("/logout")
    public String execute(final HttpServletRequest request, final HttpServletResponse response)
            throws BusinessException {
        ProjectWebUtil.getSubject(request, response).logout();
        return "redirect:/login";
    }

}
