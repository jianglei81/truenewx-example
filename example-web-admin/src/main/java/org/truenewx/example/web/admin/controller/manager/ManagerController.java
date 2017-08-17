package org.truenewx.example.web.admin.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.example.service.manager.RoleService;
import org.truenewx.example.service.model.SubmitManager;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.web.rpc.server.annotation.RpcController;
import org.truenewx.web.rpc.server.annotation.RpcMethod;
import org.truenewx.web.validation.generate.annotation.ValidationGeneratable;

/**
 * 管理员管理
 *
 * @author jianglei
 * @since JDK 1.8
 */
@RpcController
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
        final QueryResult<Manager> result = this.managerService.findGeneral(keyword, pageSize,
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
    public String add(final SubmitManager model, final HttpServletRequest request)
            throws HandleableException {
        this.managerService.add(model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/manager/list");
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    @ValidationGeneratable(Manager.class)
    public ModelAndView toUpdate(@PathVariable("id") final int id) throws BusinessException {
        final ModelAndView mav = new ModelAndView("/manager/update");
        mav.addObject("manager", this.managerService.load(id));
        mav.addObject("roles", this.roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") final int id, final SubmitManager model)
            throws HandleableException {
        this.managerService.update(id, model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/manager/list");
    }

    @RequestMapping(value = "/{id}/password", method = RequestMethod.GET)
    @ValidationGeneratable(Manager.class)
    public ModelAndView toPassword(@PathVariable("id") final int id) {
        final ModelAndView mav = new ModelAndView("/manager/password");
        mav.addObject("id", id);
        return mav;
    }

    @RpcMethod
    public void resetPassword(final int id, final String password) {
        this.managerService.resetPassword(id, password);
    }

}
