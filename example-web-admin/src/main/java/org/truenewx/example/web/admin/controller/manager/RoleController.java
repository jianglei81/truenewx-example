package org.truenewx.example.web.admin.controller.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.Strings;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.core.exception.HandleableException;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.manager.RoleService;
import org.truenewx.example.service.model.SubmitRole;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.support.log.web.annotation.LogExcluded;
import org.truenewx.web.menu.MenuResolver;
import org.truenewx.web.menu.model.MenuItem;
import org.truenewx.web.menu.model.MenuOperation;
import org.truenewx.web.rpc.server.annotation.RpcController;
import org.truenewx.web.rpc.server.annotation.RpcMethod;
import org.truenewx.web.rpc.server.annotation.RpcResult;
import org.truenewx.web.rpc.server.annotation.RpcResultFilter;
import org.truenewx.web.validation.generate.annotation.ValidationGeneratable;

/**
 * 角色管理
 *
 * @author jianglei
 * @since JDK 1.8
 */
@RpcController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;
    @Autowired
    private MenuResolver menuResolver;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "name", required = false) final String name) {
        final ModelAndView mav = new ModelAndView("/role/list");
        mav.addObject("roles", this.service.findByName(name));
        return mav;
    }

    @RpcMethod
    public void move(final int id, final boolean down) {
        this.service.move(id, down);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ValidationGeneratable(Role.class)
    public String toAdd() {
        return "/role/add";
    }

    @RpcMethod(
            result = @RpcResult(
                    filter = {
                            @RpcResultFilter(type = MenuItem.class,
                                    includes = { "caption", "role", "permission", "subs",
                                            "operations" }),
                            @RpcResultFilter(type = MenuOperation.class,
                                    includes = { "caption", "role", "permission" }) }))
    @LogExcluded
    public Iterable<MenuItem> getMenuItems() {
        return this.menuResolver.getFullMenu().getItems();
    }

    @RpcMethod
    @LogExcluded
    public void validateName(final String name, final Integer id) throws BusinessException {
        this.service.validateName(name, id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final SubmitRole model) throws HandleableException {
        this.service.add(model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/role/list");
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    @ValidationGeneratable(Role.class)
    public ModelAndView toUpdate(@PathVariable("id") final int id) throws BusinessException {
        final ModelAndView mav = new ModelAndView("/role/update");
        final Role role = this.service.load(id);
        mav.addObject("role", role);
        mav.addObject("permissions", StringUtils.join(role.getPermissions(), Strings.COMMA));
        return mav;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") final int id, final SubmitRole model)
            throws HandleableException {
        this.service.update(id, model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/role/list");
    }

}
