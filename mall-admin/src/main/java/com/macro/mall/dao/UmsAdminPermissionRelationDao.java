package com.macro.mall.dao;

import com.macro.mall.model.UmsAdminPermissionRelation;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UmsAdminPermissionRelationDao
 * @Description 自定义用户权限关系管理dao
 * @Author AW
 * @Date 2020/8/6 000621:29
 * @Version V1.0
 **/
public interface UmsAdminPermissionRelationDao {

   int insertList(@Param("list") List<UmsAdminPermissionRelation> list);

}
