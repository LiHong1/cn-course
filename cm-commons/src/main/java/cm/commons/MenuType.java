package cm.commons;

/**
 * 菜单类型
 *
 * @author lzc
 */
public final class MenuType {
    // 会员菜单
    public static final Integer MEMBER = 1;
    public static final Long MEMBER_ROOT_ID = 100L;
    // 商户菜单
    public static final Integer MERCHANT = 2;
    public static final Long MERCHANT_ROOT_ID = 200L;
    // 会员产品菜单
    public static final Integer MEMBER_PRODUCT = 3;
    public static final Long MEMBER_PRODUCT_ROOT_ID = 300L;
    // 商户产品菜单
    public static final Integer MERCHANT_PRODUCT = 4;
    // 商户按钮菜单
    public static final Integer MERCHANT_BUTTON = 5;

    public static final Integer ADMIN = 5;
    // 后台管理员菜单
    public static final Long ADMIN_ROOT_ID = 500L;

    public static final Long ROOT_ID = 0L;

    /**
     * 私有构造器
     */
    private MenuType() {

    }
}
