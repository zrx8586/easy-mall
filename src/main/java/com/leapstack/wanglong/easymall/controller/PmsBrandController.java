package com.leapstack.wanglong.easymall.controller;

import com.leapstack.wanglong.easymall.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: easy-mall
 * @description: 品牌管理Controller
 * @author: Wang Long
 * @create: 2019-11-15
 **/

@RestController("/brand")
@Slf4j
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;
}
