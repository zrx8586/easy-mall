package com.leapstack.wanglong.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @program: easy-mall
 * @description: 登陆参数
 * @author: Wang Long
 * @create: 2019-11-18
 **/

@Data
public class UmsAdminLoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
