package org.truenewx.example.web.admin.controller;

import java.util.ArrayList;
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
 * 面包屑控制器
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
public class BreadcrumbController {

    @RequestMapping("/breadcrumb")
    public ModelAndView execute(final HttpServletRequest request,
            final HttpServletResponse response) {
        final ModelAndView mav = new ModelAndView("breadcrumb");
        final Menu menu = ProjectWebUtil.getMenu();
        if (menu != null) {
            final HttpMethod method = HttpMethod.valueOf(request.getMethod());
            final String href = WebUtil.getRelativeRequestUrl(request);
            final List<Binate<Integer, MenuItem>> indexes = menu.indexesOf(href, method);
            if (indexes != null) {
                final List<MenuItem> items = new ArrayList<>();
                for (final Binate<Integer, MenuItem> binate : indexes) {
                    items.add(binate.getRight());
                }
                mav.addObject("items", items);
            }
        }
        return mav;
    }

}
