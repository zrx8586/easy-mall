package com.leapstack.wanglong.mall.controller;

import com.leapstack.wanglong.common.api.CommonResult;
import com.leapstack.wanglong.mall.dao.domain.UmsAdmin;
import com.leapstack.wanglong.mall.dto.UmsAdminLoginParam;
import com.leapstack.wanglong.mall.service.UmsAdminService;
import com.leapstack.wanglong.mbg.model.UmsPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @program: easy-mall
 * @description: 后台用户管理
 * @author: Wang Long
 * @create: 2019-11-18
 **/

@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (null == umsAdmin){
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("登陆以后返回token")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping(value = "/permission/adminId")
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }



}
