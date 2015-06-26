package cm.commons;

/**
 * 会员模块错误码
 *
 * @author leizhenchun
 */
public final class CommonsErrorCode {
    //默认值
    public static final String DEFAULT = "1000";
    //领域对象不存在
    public static final String DOMAIN_OBJECT_NOT_EXIST = "1002";
    //领域对象已经存在
    public static final String DOMAIN_OBJECT_EXISTS = "1003";
    //错误码不存在
    public static final String ERROR_CODE_NOT_EXIST = "1004";
    //日志不存在
    public static final String OPERATION_LOG_NOT_EXIST = "1005";
    //短信记录不存在
    public static final String SHORT_MESSAGE_NOT_EXIST = "1006";
    //错误的邮箱格式
    public static final String EMAIL_ADDRESS_FORMAT_ERROR = "1010";
    //错误的电话号码格式
    public static final String PHONE_NUMBER_FORMAT_ERROR = "1011";
    //树节点不存在
    public static final String TREE_NODE_NOT_EXIST = "1012";
    //树节点已经存在
    public static final String TREE_NODE_EXISTS = "1013";
    //树根节点不存在
    public static final String TREE_ROOT_NOT_EXIST = "1014";
    //树根节点已经存在
    public static final String TREE_ROOT_EXISTS = "1015";
    //列表不存在
    public static final String LIST_NOT_EXIST = "1016";
    //列表已经存在
    public static final String LIST_EXISTS = "1017";
    //菜单项不存在
    public static final String MENU_ITEM_NOT_EXIST = "1018";
    //新闻不存在
    public static final String NEWS_NOT_EXIST = "1019";
    //幻灯片不存在
    public static final String SLIDE_NOT_EXIST = "1020";
    //产品不存在
    public static final String PRODUCT_NOT_EXIST = "1021";

    private CommonsErrorCode() {
        throw new AssertionError();
    }
}
