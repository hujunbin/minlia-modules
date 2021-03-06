package com.minlia.modules.rbac.backend.common.constant;


import com.minlia.cloud.code.ApiCode;

/**
 * Created by will on 6/17/17.
 * 安全相关API响应码
 */
public class SecurityApiCode {

    public SecurityApiCode() {
        throw new AssertionError();
    }

    /**
     * 定义安全模块代码基数为11000
     */
    public static final int BASED= ApiCode.BASED_ON;

    /**
     * 登录 100000 +
     */
    public static final int LOGIN_SUCCESS = 100101;
    public static final int LOGIN_NOT_REGISTRATION = 100102;
    public static final int LOGIN_QR_CODE = 100103;
    public static final int LOGIN_NOT_WXCODE = 100104;

    /**
     * 用户
     */
    public static final int USER_NOT_EXISTED = 100201;
    public static final int USER_REFERRAL_NOT_FOUND = 100202;

    /**
     * 角色
     */
    public static final int ROLE_ALREADY_EXISTED = 100301;
    public static final int ROLE_NOT_EXISTED = 100302;

    /**
     * 权限点
     */
    public static final int BASE_PERMISSION_CODE=100401;

    /**
     * 导航
     */
    public static final int NAVIGATION_NOT_EXISTS=100501;
    public static final int NAVIGATION_PARENT_NOT_EXISTS=100502;
    public static final int NAVIGATION_CAN_NOT_DELETE_HAS_CHILDREN=100503;

    /**
     * 验证码
     */
    public static final int BASE_SECURITY_CODE=BASED+0600;

}
