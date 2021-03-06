package io.cde.account.domain.i18n;

/**
 * @author lcl
 */
public enum Error {
    /**
     * 用户已存在.
     */
    ACCOUNT_EXISTE(10),
    /**
     * 无效用户id.
     */
    INVALID_ACCOUNT_ID(11),
    /**
     * 用户名密码不匹配.
     */
    UNMATCHED_ACCOUNT_AND_PASSWORD(20),
    /**
     * 两次密码不一致.
     */
    UNMATCHED_PASSWORD1_AND_PASSWORD2(21),
    /**
     * 邮箱没有认证.
     */
    UNVERIFIED_EMAIL(30),
    /**
     * 用户没有关联该邮箱.
     */
    UNASSOCIATED_ACCOUNT_AND_EMAIL(40),
    /**
     * 电话号码没有认证.
     */
    UNVERIFIED_MOBILE(50),
    /**
     * 用户没有关联该电话号码.
     */
    UNASSOCIATED_ACCOUNT_AND_MOBILE(60),
    /**
     * 邮箱已经被使用过.
     */
    USED_EMAIL(70),
    /**
     * 电话已经被使用过.
     */
    USED_MOBILE(80),
    /**
     * 默认邮箱不能删除.
     */
    UNDELETABLE_DEFAULT_EMAIL(90),
    /**
     * 默认电话不能删除.
     */
    UNDELETABLE_DEFAULT_MOBILE(100),
    /**
     * 非法的用户名字符串.
     */
    ILLEGAL_ACCOUNT_NAME(110),
    /**
     * 非法的用户名字符串.
     */
    ILLEGAL_PASSWORD(111),
    /**
     * 非法的用户名字符串.
     */
    ILLEGAL_EMAIL(112),
    /**
     * 非法的用户名字符串.
     */
    ILLEGAL_MOBILE(113),
    /**
     * 缺少必要参数.
     */
    MISS_REQUIRED_PARAMETER(114);

    /**
     * 用来计算错误码的常量.
     */
    private static final int SERVICE_COUNT = 10000;

    /**
     * 错误码.
     */
    private int code;

    /**
     * 私有构造方法,根据错误编号和服务编号生成错误码.
     *
     * @param code 错误码
     */
    Error(final int code) {
        this.code = ServiceCode.CDE_ACCOUNT_SERVICE.getCode() * SERVICE_COUNT + code;
    }

    /**
     * 获取错误码.
     *
     * @return 返回错误码.
     */
    public int getCode() {
        return code;
    }
}
