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
import org.truenewx.data.query.Paging;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.example.service.manager.RoleService;
import org.truenewx.example.service.model.SubmitRole;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.support.log.web.annotation.LogExcluded;
import org.truenewx.web.menu.MenuResolver;
import org.truenewx.web.menu.model.MenuItem;
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
    private RoleService roleService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private MenuResolver menuResolver;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "name", required = false) final String name) {
        final ModelAndView mav = new ModelAndView("/role/list");
        mav.addObject("roles", this.roleService.findByName(name));
        return mav;
    }

    @RpcMethod
    public void move(final int id, final boolean down) {
        this.roleService.move(id, down);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ValidationGeneratable(Role.class)
    public ModelAndView toAdd() {
        final ModelAndView mav = new ModelAndView("/role/add");
        mav.addObject("selectableManagers", this.managerService.findGeneral(null, 20, 1));
        return mav;
    }

    @RpcMethod(result = @RpcResult(filter = @RpcResultFilter(type = MenuItem.class,
            includes = { "caption", "role", "permission", "subs" })))
    public Iterable<MenuItem> getMenuItems() {
        return this.menuResolver.getFullMenu().getItems();
    }

    @RpcMethod
    public void validateName(final String name, final Integer id) throws BusinessException {
        this.roleService.validateName(name, id);
    }

    @RpcMethod(result = @RpcResult(filter = {
            @RpcResultFilter(type = Manager.class, includes = { "id", "username", "fullname" }),
            @RpcResultFilter(type = Paging.class, includes = { "morePage", "pageNo" }) }))
    @LogExcluded
    public QueryResult<Manager> getSelectableManagers(final int pageNo, final Integer roleId) {
        if (roleId == null) {
            return this.managerService.findGeneral(null, 20, pageNo);
        } else {
            return this.managerService.findGeneralOutOfRole(roleId, 20, pageNo);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(final SubmitRole model) throws HandleableException {
        this.roleService.add(model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/role/list");
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    @ValidationGeneratable(Role.class)
    public ModelAndView toUpdate(@PathVariable("id") final int id) throws BusinessException {
        final ModelAndView mav = new ModelAndView("/role/update");
        final Role role = this.roleService.load(id);
        mav.addObject("role", role);
        mav.addObject("permissions", StringUtils.join(role.getPermissions(), Strings.COMMA));
        mav.addObject("selectableManagers", this.managerService.findGeneralOutOfRole(id, 20, 1));
        return mav;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable("id") final int id, final SubmitRole model)
            throws HandleableException {
        this.roleService.update(id, model);
        return "redirect:" + ProjectWebUtil.getPrevPrevUrl("/role/list");
    }

    @RpcMethod
    public int countManagers(final int id) {
        return this.managerService.countOfRole(id);
    }

    @RpcMethod
    public void del(final int id) throws HandleableException {
        this.roleService.delete(id);
    }

}
