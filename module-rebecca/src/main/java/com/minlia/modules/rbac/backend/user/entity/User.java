package com.minlia.modules.rbac.backend.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.minlia.module.data.entity.AbstractEntity;
import lombok.*;

import java.util.Date;

/**
 * Created by will on 8/14/17.
 * TODO 不能使用Builder 会导致 Data Batis 出现 No TypeHander的错误 在Batis的扫描类中找到以UserBuilder开头的属性给排除掉
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({})
@JsonIgnoreProperties(value = {})
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"username"})
public class User extends AbstractEntity {

    /**
     * Global User Identification
     */
    private String guid;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 凭证是否有效
     */
    private Boolean credentialsExpired;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 锁定次数
     */
    private Integer lockLimit;

    /**
     * 锁定时间，多少分钟内不能登录
     */
    private Date lockTime;

//    @JsonProperty("lockTime")
//    public Long getLockTimeEpochDay(){
//        return null != lockTime ? lockTime.toEpochSecond(ZoneOffset.of("+8")) : null;
//    }

    /**
     * 是否启用
     */
    private Boolean enabled;

    @JsonProperty
    private Date lastLoginTime;

    private String lastLoginIp;

    /**
     * 推荐人
     */
    private String referral;

}