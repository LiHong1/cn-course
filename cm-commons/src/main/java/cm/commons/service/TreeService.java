package cm.commons.service;

import cm.commons.bean.TreeNode;

import java.util.List;

/**
 * 树服务
 *
 * @author lzc
 */
public interface TreeService extends BaseService<TreeNode> {

    /**
     * 获取所有树根节点
     *
     * @return
     */
    List<TreeNode> getAllRoots();

    /**
     * 获取子节点
     *
     * @param parentId
     * @return
     */
    List<TreeNode> getChildren(Long parentId);

    /**
     * 获取子节点
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
     * 创建树
     *
     * @param rootName
     * @return
     */
    Long createRoot(String rootName);

    /**
     * 树是否存在
     *
     * @param rootName
     * @return
     */
    boolean rootExist(String rootName);

    /**
     * 获取所有后代
     *
     * @param parent
     * @return
     */
    List<TreeNode> getDescendants(TreeNode parent);

    /**
     * 获取所有后代
     *
     * @param nodeId
     * @return
     */
    List<TreeNode> getDescendants(Long nodeId);

    /**
     * 获取根节点所有后代
     *
     * @param rootName
     * @return
     */
    List<TreeNode> getRootDescendants(String rootName);
}
