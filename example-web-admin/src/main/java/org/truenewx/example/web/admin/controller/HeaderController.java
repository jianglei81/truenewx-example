package org.truenewx.example.web.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.example.web.admin.util.ProjectWebUtil;

/**
 * 页面头部访问Controller
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
public class HeaderController {

    @RequestMapping("/header")
    public ModelAndView execute(final HttpServletRequest request,
            final HttpServletResponse response) {
        final ModelAndView mav = new ModelAndView("header");
        mav.addObject("manager", ProjectWebUtil.getManager());
        return mav;
    }

}
