package org.truenewx.example.web.admin.controller.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.example.service.manager.RoleService;
import org.truenewx.example.service.model.SubmitManager;
import org.truenewx.web.validation.generate.annotation.ValidationGeneratable;

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
    @Autowired
    private RoleService roleService;

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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ValidationGeneratable(Manager.class)
    public ModelAndView toAdd() {
        final ModelAndView mav = new ModelAndView("/manager/add");
        mav.addObject("roles", this.roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final SubmitManager model,
            @RequestParam(value = "prev", required = false) final String prev)
            throws HandleableException {
        this.managerService.add(model);
        return "redirect:" + (StringUtils.isBlank(prev) ? "/manager/list" : prev);
    }

}
