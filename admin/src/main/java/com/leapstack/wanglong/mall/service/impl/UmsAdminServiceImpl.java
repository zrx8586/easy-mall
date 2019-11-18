package com.leapstack.wanglong.mall.service.impl;

import com.leapstack.wanglong.mall.dao.domain.UmsAdmin;
import com.leapstack.wanglong.mall.dao.mapper.UmsAdminMapper;
import com.leapstack.wanglong.mall.dao.mapper.UmsAdminRoleRelationDao;
import com.leapstack.wanglong.mall.dao.mapper.UmsAdminRoleRelationMapper;
import com.leapstack.wanglong.mall.service.UmsAdminService;
import com.leapstack.wanglong.mall.utils.JwtTokenUtil;
import com.leapstack.wanglong.mbg.model.UmsAdminExample;
import com.leapstack.wanglong.mbg.model.UmsPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @program: easy-mall
 * @description: 后台管理员Service
 * @author: Wang Long
 * @create: 2019-11-17
 **/

@Service
@Slf4j
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin queryParam = new UmsAdmin();
        queryParam.setUsername(username);
        List<UmsAdmin> adminList = adminMapper.select(queryParam);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdminParam.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.error("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

}
