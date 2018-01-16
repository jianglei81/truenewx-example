package org.truenewx.example.web.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.manager.ManagerService;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.web.rpc.server.annotation.RpcController;
import org.truenewx.web.rpc.server.annotation.RpcMethod;

/**
 * 当前登录管理员控制器
 *
 * @author jianglei
 * @since JDK 1.8
 */
@RpcController
@RequestMapping("/mine")
public class MineController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/profile")
    public ModelAndView profile() {
        final ModelAndView mav = new ModelAndView("/mine/profile");
        mav.addObject("manager", ProjectWebUtil.getManager());
        return mav;
    }

    @RpcMethod
    public void updateHeadImage(final String headImageUrl) {
        final Manager manager = ProjectWebUtil.getManager();
        this.managerService.updateHeadImageUrl(manager.getId(), headImageUrl);
        manager.setHeadImageUrl(headImageUrl);
    }

    @RpcMethod
    public void updateFullname(final String fullname) {
        final Manager manager = ProjectWebUtil.getManager();
        this.managerService.updateFullname(manager.getId(), fullname);
        manager.setFullname(fullname);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String toUpdatePassword() {
        return "/mine/password";
    }

    @RpcMethod
    public void updatePassword(final String oldMd5Password, final String newMd5Password)
            throws BusinessException {
        final int managerId = ProjectWebUtil.getManagerId();
        this.managerService.updatePassword(managerId, oldMd5Password, newMd5Password);
    }

}
