package com.minlia.modules.rbac.backend.password.service;

import com.minlia.cloud.code.ApiCode;
import com.minlia.cloud.utils.ApiPreconditions;
import com.minlia.module.captcha.service.CaptchaService;
import com.minlia.modules.rbac.backend.password.body.ChangePasswordByRawPasswordRequestBody;
import com.minlia.modules.rbac.backend.password.body.ChangePasswordBySecurityCodeRequestBody;
import com.minlia.modules.rbac.backend.password.body.ResetPasswordRequestBody;
import com.minlia.modules.rbac.backend.user.entity.User;
import com.minlia.modules.rbac.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordServiceImpl implements UserPasswordService {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 重置密码
     * @param body
     * @return
     */
    @Override
    public User resetPassword(ResetPasswordRequestBody body) {
        //验证验证码是否正确
        captchaService.validity(body.getUsername(), body.getCode());

//        User user = userMapper.queryByUsername(body.getUsername());
//        ApiPreconditions.is(user == null, ApiCode.NOT_FOUND,"该用户尚未注册");
//
//        return change(user,body.getNewPassword());
        return null;
    }

    @Override
    public User changePassword(ChangePasswordBySecurityCodeRequestBody body) {
        User user= SecurityContextHolder.getCurrentUser();

        //验证验证码是否正确
        captchaService.validity(user.getUsername(), body.getCode());

        return change(user,body.getNewPassword());
    }

    @Override
    public User changePassword(ChangePasswordByRawPasswordRequestBody body) {
        User user= SecurityContextHolder.getCurrentUser();

        Boolean bool = bCryptPasswordEncoder.matches(body.getRawPassword(),user.getPassword());
        ApiPreconditions.not(bool, ApiCode.INVALID_RAW_PASSWORD,"原密码错误，请确认！");

        return change(user,body.getNewPassword());
    }

    private User change(User user,String newPassword) {
        //设置新密码
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setEnabled(Boolean.TRUE);
        user.setCredentialsExpired(Boolean.FALSE);
        user.setLocked(Boolean.FALSE);
//        userMapper.update(user);
        return user;
    }

}
