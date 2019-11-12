package com.itheima.Constant;

/**
 * @ Author     ：mmzs.
 * @ Date       ：Created in 20:49 2019/11/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class RedisMessageConstant {
    public static final String SENDTYPE_ORDER = "001";//用于缓存体检预约时发送的验证码
    public static final String SENDTYPE_LOGIN = "002";//用于缓存手机号快速登录时发送的验证码
    public static final String SENDTYPE_GETPWD = "003";//用于缓存找回密码时发送的验证码
}
