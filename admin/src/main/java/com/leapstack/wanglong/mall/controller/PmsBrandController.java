package com.leapstack.wanglong.mall.controller;

import com.leapstack.wanglong.common.api.CommonPage;
import com.leapstack.wanglong.common.api.CommonResult;
import com.leapstack.wanglong.mall.dao.domain.PmsBrand;
import com.leapstack.wanglong.mall.service.impl.PmsBrandServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: easy-mgb
 * @description: 品牌管理Controller
 * @author: Wang Long
 * @create: 2019-11-15
 **/

@RestController()
@RequestMapping("/brand")
@Slf4j
@Api(tags = "PmsBrandController", description = "商品品牌管理")
public class PmsBrandController {

    /**
     *
     * 给查询接口添加pms:brand:read权限
     * 给修改接口添加pms:brand:update权限
     * 给删除接口添加pms:brand:delete权限
     * 给添加接口添加pms:brand:create权限
     *
     * **/

    @Autowired
    private PmsBrandServiceImpl brandService;

    @ApiOperation("获取所有品牌列表")
    @GetMapping(value = "listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            log.info("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.info("createBrand failed:{}", pmsBrand);

        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            log.info("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.info("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            log.info("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            log.info("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

}
