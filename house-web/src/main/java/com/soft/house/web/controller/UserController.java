package com.soft.house.web.controller;

import com.soft.house.common.model.User;
import com.soft.house.common.result.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: UserController
 * @Description: 用户Controller
 * @Author: Jason Biao
 * @Date: 2019/8/13 15:19
 * @Version: 1.0
 **/
@Controller
public class UserController {

    /**
     * 注册提交流程：1.注册验证 2.发送邮件 3.验证失败重定向到注册页面
     * 是否为注册页获取：根据account对象为依据判断是否为注册页获取请求
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping("accounts/register")
    public String accountsRegister(User user,ModelMap modelMap){

        /** 用户为空跳转到注册页面 */
        if (StringUtils.isEmpty(user) || user.getName() == null){
            return "/user/accounts/register";
        }

        /** 用户表单验证 */
        ResultMsg resultMsg = UserHelper.validate(user);

        if (resultMsg.isSuccess()){
            return "/user/accounts/registerSubmit";
        }else {
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }

    }
}
