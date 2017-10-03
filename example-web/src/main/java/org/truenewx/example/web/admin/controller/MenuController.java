package org.truenewx.example.web.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.tuple.Binate;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.web.menu.model.Menu;
import org.truenewx.web.menu.model.MenuItem;
import org.truenewx.web.util.WebUtil;

/**
 * 菜单
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
public class MenuController {

    @RequestMapping("/menu")
    public ModelAndView execute(final HttpServletRequest request,
            final HttpServletResponse response) {
        final ModelAndView mav = new ModelAndView("menu");
        final Menu menu = ProjectWebUtil.getMenu();
        if (menu != null) {
            mav.addObject("menu", menu);
            final HttpMethod method = HttpMethod.valueOf(request.getMethod());
            final String href = WebUtil.getRelativeRequestUrl(request);
            final List<Binate<Integer, MenuItem>> indexes = menu.indexesOf(href, method);
            if (indexes != null) {
                if (indexes.size() > 0) {
                    mav.addObject("level1ActiveIndex", indexes.get(0).getLeft());
                }
                if (indexes.size() > 1) {
                    mav.addObject("level2ActiveIndex", indexes.get(1).getLeft());
                }
            }
        }
        return mav;
    }

}
