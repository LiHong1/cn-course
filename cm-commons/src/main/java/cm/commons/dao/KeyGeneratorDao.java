package cm.commons.dao;

/**
 * 主键生成器Dao接口
 *
 * @author lzc
 */
public interface KeyGeneratorDao {
    /**
     * 获取下一个Id
     *
     * @param tableName
     * @param incrementBy
     * @return
     */
    long getNextId(String tableName, long incrementBy);

    /**
     * 获取下一个Id
     *
     * @param tableName
     * @return
     */
    long getNextId(String tableName);
}
