package com.minlia.modules.rbac.service;

import com.google.common.collect.Sets;
import com.minlia.cloud.utils.ApiPreconditions;
import com.minlia.modules.rbac.dao.PermissionDao;
import com.minlia.modules.rbac.dao.RoleDao;
import com.minlia.modules.rbac.domain.Permission;
import com.minlia.modules.rbac.domain.Role;
import com.minlia.modules.security.code.SecurityApiCode;
import com.minlia.modules.security.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * Created by maitha.manyala on 10/6/15.
 */
@Service
public class PermissionCreationServiceImpl implements PermissionCreationService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;


    public Permission addPermission(String code, String label) {
        return addPermission(code,label,Sets.newHashSet());
    }



    public Permission addPermission(String code, String label,Set<Role> roles) {
        Permission permission = permissionDao.findByCode(code);
        if (permission == null) {
            permission = new Permission();
            permission.setCode(code);
            permission.setLabel(label);
            if(null!=roles&& roles.size()>0) {
                permission.setRoles(roles);
            }
            permission=permissionDao.save(permission);
        }
        return permission;
    }


    @Override
    public void initialAdminPermissions(Map<String,String> initialAdminPermissions) {
        //添加权限点组管理员角色
        final Role adminRole = roleDao.findByCode(SecurityConstant.ADMIN_ROLE_NAME);
//        ApiPreconditions.checkNotNull(adminRole, SecurityApiCode.ROLE_NOT_FOUND);
        if(null!=adminRole) {
            Set<Permission> permissionCreated= Sets.newHashSet();
            for(Map.Entry permission:initialAdminPermissions.entrySet()){
                Permission created=this.addPermission((String)permission.getKey(),(String)permission.getValue(),Sets.newHashSet(adminRole));
                permissionCreated.add(created);
            }
        }
    }
}
