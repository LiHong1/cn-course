package cm.commons.bean;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 *
 * @author lzc
 */
@Entity
@Table(name = "T_C_TREE_NODE")
public class TreeNode extends DomainObject {
    /**
     *
     */
    private static final long serialVersionUID = 9012756194524778135L;
    // 节点名称
    private String name;
    // 节点值
    private String value;
    // 父节点
    private TreeNode parent;
    // 子节点
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 父节点
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", updatable = false)
    public TreeNode getParent() {
        return parent;
    }

    /**
     * 父节点
     *
     * @param parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * 子节点
     *
     * @return
     */
    @OneToMany(cascade = {CascadeType.REMOVE}, targetEntity = TreeNode.class)
    public List<TreeNode> getChildren() {
        return children;
    }

    /**
     * 子节点
     *
     * @param children
     */
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Column(name = "NODE_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "NODE_VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
