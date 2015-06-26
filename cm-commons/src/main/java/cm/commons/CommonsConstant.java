package cm.commons;

/**
 * 公共模块常数
 *
 * @author lzc
 */
public final class CommonsConstant {
    public static final Long SYSTEM_ID = 0L;

    public static final Integer INIT_STATUS = -1;
    public static final Integer UN_NOTIFY = 0;
    public static final Integer SUCCESS_STATUS = 2;
    public static final Integer FAILE_STATUS = 1;
    public static final Integer NOTIFY_OVER_STATUS = 3;
    public static final Integer MAX_NOTIFY_NUM = 4;
    public static final Integer ZERO_NOTIFY_NUM = 0;

    private CommonsConstant() {
        throw new AssertionError();
    }
}
