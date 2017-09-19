package org.truenewx.example.web.admin.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 角色管理
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @RequestMapping("/list")
    public ModelAndView list() {
        final ModelAndView mav = new ModelAndView("/role/list");
        if (mav.hasView()) {
            throw new NullPointerException();
        }
        return mav;
    }

}
