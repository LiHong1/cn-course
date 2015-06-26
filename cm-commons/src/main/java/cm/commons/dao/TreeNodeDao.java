package cm.commons.dao;

import cm.commons.bean.TreeNode;

import java.util.List;

/**
 * 通用树节点Dao接口
 *
 * @author lzc
 */
public interface TreeNodeDao extends BaseDao<TreeNode> {
    /**
     * 获取所有树根节点
     *
     * @return
     */
    List<TreeNode> getAllRoots();

    /**
     * 获取所有子节点
     *
     * @param parent
     * @return
     */
    List<TreeNode> getChildren(TreeNode parent);

    /**
     * 获取树根节点
     *
     * @param rootName
     * @return
     */
    TreeNode getRoot(String rootName);

    /**
     * 根节点是否存在
     *
     * @param rootName
     * @return
     */
    boolean rootExist(String rootName);
}
