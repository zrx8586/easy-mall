package com.leapstack.wanglong.mall.service;

import com.leapstack.wanglong.mall.dao.domain.UmsAdmin;
import com.leapstack.wanglong.mbg.model.UmsPermission;

import java.util.List;

public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     * @return
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
