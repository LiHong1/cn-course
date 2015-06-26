package cm.commons.dao.impl;

import cm.commons.CommonsErrorCode;
import cm.commons.bean.TreeNode;
import cm.commons.dao.TreeNodeDao;
import cm.commons.exception.BusinessException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点Dao实现类
 *
 * @author lzc
 */
@Repository
public class TreeNodeDaoImpl extends BaseDaoImpl<TreeNode> implements TreeNodeDao {

    @Override
    public TreeNode get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        TreeNode result = (TreeNode) session.get(TreeNode.class, id);
        if (result == null) {
            throw new BusinessException(CommonsErrorCode.TREE_NODE_NOT_EXIST, "树节点不存在：id=" + id);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TreeNode> getAllRoots() {
        Session session = sessionFactory.getCurrentSession();

        return (List<TreeNode>) session.createCriteria(TreeNode.class).add(Restrictions.isNull("parent")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TreeNode> getChildren(TreeNode parent) {
        Session session = sessionFactory.getCurrentSession();
        return (List<TreeNode>) session.createQuery("select tn.children from TreeNode tn where tn.id = ?")
                .list();
    }

    @Override
    public TreeNode getRoot(String rootName) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<TreeNode> result = session.createCriteria(TreeNode.class).add(Restrictions.isNull("parent"))
                .add(Restrictions.eq("name", rootName)).list();
        if (result.isEmpty()) {
            throw new BusinessException(CommonsErrorCode.TREE_ROOT_NOT_EXIST, TreeNode.class.toString()
                    + "根节点不存在：rootName=" + rootName);
        }
        return result.get(0);
    }

    @Override
    public boolean rootExist(String rootName) {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<TreeNode> result = session.createCriteria(TreeNode.class).add(Restrictions.isNull("parent"))
                .add(Restrictions.eq("name", rootName)).list();
        return result == null ? false : true;
    }

}
