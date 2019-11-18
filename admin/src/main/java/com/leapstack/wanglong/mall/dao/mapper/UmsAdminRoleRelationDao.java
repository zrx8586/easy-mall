package com.leapstack.wanglong.mall.dao.mapper;

import com.leapstack.wanglong.mbg.model.UmsAdminRoleRelation;
import com.leapstack.wanglong.mbg.model.UmsPermission;
import com.leapstack.wanglong.mbg.model.UmsRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * Created by macro on 2018/10/8.
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    @Insert("INSERT INTO ums_admin_role_relation (admin_id, role_id) VALUES\n" +
            "        <foreach collection=\"list\" separator=\",\" item=\"item\" index=\"index\">\n" +
            "            (#{item.adminId,jdbcType=BIGINT},\n" +
            "            #{item.roleId,jdbcType=BIGINT})\n" +
            "        </foreach>")
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    @Select("select r.* " +
            "        from ums_admin_role_relation ar left join ums_role r on ar.role_id = r.id\n" +
            "        where ar.admin_id = #{adminId}")
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    @Select("select p.*\n" +
            "        from ums_admin_role_relation ar left join ums_role r on ar.role_id = r.id\n" +
            "            left join ums_role_permission_relation rp on r.id = rp.role_id\n" +
            "            left join ums_permission p on rp.permission_id=p.id\n" +
            "            where ar.admin_id = #{adminId} and p.id is not null")
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    @Select("SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            ums_admin_role_relation ar\n" +
            "            LEFT JOIN ums_role r ON ar.role_id = r.id\n" +
            "            LEFT JOIN ums_role_permission_relation rp ON r.id = rp.role_id\n" +
            "            LEFT JOIN ums_permission p ON rp.permission_id = p.id\n" +
            "        WHERE\n" +
            "            ar.admin_id = #{adminId}\n" +
            "            AND p.id IS NOT NULL\n" +
            "            AND p.id NOT IN (\n" +
            "                SELECT\n" +
            "                    p.id\n" +
            "                FROM\n" +
            "                    ums_admin_permission_relation pr\n" +
            "                    LEFT JOIN ums_permission p ON pr.permission_id = p.id\n" +
            "                WHERE\n" +
            "                    pr.type = - 1\n" +
            "                    AND pr.admin_id = #{adminId}\n" +
            "            )\n" +
            "        UNION\n" +
            "        SELECT\n" +
            "            p.*\n" +
            "        FROM\n" +
            "            ums_admin_permission_relation pr\n" +
            "            LEFT JOIN ums_permission p ON pr.permission_id = p.id\n" +
            "        WHERE\n" +
            "            pr.type = 1\n" +
            "            AND pr.admin_id = #{adminId}")
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
