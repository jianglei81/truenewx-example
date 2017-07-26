package org.truenewx.example.web.admin.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.manager.ManagerService;

/**
 * 管理员管理
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(
            @RequestParam(value = "keyword", required = false) final String keyword,
            @RequestParam(value = "pageSize",
                    required = false,
                    defaultValue = "20") final int pageSize,
            @RequestParam(value = "pageNo",
                    required = false,
                    defaultValue = "1") final int pageNo) {
        final ModelAndView mav = new ModelAndView("/manager/list");
        final QueryResult<Manager> result = this.managerService.findByKeyword(keyword, pageSize,
                pageNo);
        mav.addObject("result", result);
        return mav;
    }

}
