package cm.commons;

/**
 * 公共模块配置项
 *
 * @author lzc
 */
public final class CommonsConfigKey {

    public static final String AUTOID_INCREMENT = "C_AUTOID_INCREMENT";
    public static final int AUTOID_INCREMENT_DEF = 1;

    public static final String AUTOID_INCREMENT_FOR_LOG = "C_AUTOID_INCREMENT_FOR_LOG";
    public static final int AUTOID_INCREMENT_FOR_LOG_DEF = 100;

    private CommonsConfigKey() {
        throw new AssertionError();
    }
}
