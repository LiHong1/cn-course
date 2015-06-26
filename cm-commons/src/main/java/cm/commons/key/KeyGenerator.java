package cm.commons.key;

/**
 * 主键生成器接口
 *
 * @author leizhenchun
 */
public interface KeyGenerator {

    /**
     * 下一个主键值
     *
     * @return
     */
    Long nextKey();
}
