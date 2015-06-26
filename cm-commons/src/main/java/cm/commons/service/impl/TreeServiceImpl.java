package cm.commons.service.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.TreeNode;
import cm.commons.dao.TreeNodeDao;
import cm.commons.exception.BusinessException;
import cm.commons.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 树Service实现类
 *
 * @author lzc
 */
@Service
public class TreeServiceImpl extends BaseServiceImpl<TreeNode> implements TreeService {
    @Autowired
    private TreeNodeDao treeNodeDao;

//	@Autowired
//    public void setTreeNodeDao(TreeNodeDao treeNodeDao) {
//        this.treeNodeDao = treeNodeDao;
//        setBaseDao(treeNodeDao);
//    }


    @Transactional(readOnly = true)
    public List<TreeNode> getAllRoots() {
        return treeNodeDao.getAllRoots();
    }


    @Transactional(readOnly = true)
    public List<TreeNode> getChildren(Long parentId) {
        return treeNodeDao.getChildren(treeNodeDao.get(parentId));
    }


    @Transactional(readOnly = true)
    public List<TreeNode> getChildren(TreeNode parent) {
        return treeNodeDao.getChildren(parent);
    }


    @Transactional(readOnly = true)
    public TreeNode getRoot(String rootName) {
        return treeNodeDao.getRoot(rootName);
    }


    @Transactional(readOnly = true)
    public List<TreeNode> getDescendants(Long nodeId) {
        TreeNode node = treeNodeDao.get(nodeId);
        return getDescendants(node);
    }


    @Transactional(readOnly = true)
    public List<TreeNode> getRootDescendants(String rootName) {
        TreeNode rootNode = treeNodeDao.getRoot(rootName);
        return getDescendants(rootNode);
    }


    @Transactional(readOnly = true)
    public List<TreeNode> getDescendants(TreeNode parent) {
        List<TreeNode> result = new ArrayList<TreeNode>();

        appendChildren(result, parent);

        return result;
    }

    private void appendChildren(List<TreeNode> list, TreeNode parent) {
        List<TreeNode> children = parent.getChildren();
        list.addAll(children);
        for (TreeNode node : children) {
            appendChildren(list, node);
        }
    }


    @Transactional
    public Long createRoot(String rootName) {
        if (rootExist(rootName)) {
            throw new BusinessException(CommonsErrorCode.TREE_ROOT_EXISTS, "树根节点已经存在：rootName=" + rootName);
        }
        TreeNode rootNode = new TreeNode();
        rootNode.setName(rootName);
        rootNode.setCreatedDate(Calendar.getInstance());
        return (Long) treeNodeDao.save(rootNode);
    }


    @Transactional(readOnly = true)
    public boolean rootExist(String rootName) {
        return treeNodeDao.rootExist(rootName);
    }

}
