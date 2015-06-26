package cm.commons.bean;

import cm.commons.NonDeletableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单类
 *
 * @author leizhenchun
 */
@Entity
@Table(name = "T_C_MENU")
public class MenuItem extends NonDeletableDomainObject {
    /**
     *
     */
    private static final long serialVersionUID = 6663539359748421140L;
    // 名称
    private String name;
    // 菜单类型（会员，商户，产品）
    private Integer type;
    // URL
    private String url;
    // 显示次序
    private Integer order;
    // 上级菜单
    private Long parentId;
    // 是否有效
    private Boolean enabled;
    // 图标
    private String icon;
    // 菜单编号
    private String code;
    // 菜单状态
    private Integer status;
    // 是否显示
    private Boolean visible;
    // 父菜单
    private MenuItem parent;
    //菜单显示权限
    private String authority;

    /**
     * 是否有效
     *
     * @return
     */
    @Column(name = "IS_ENABLED")
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 是否有效
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 名称
     *
     * @return
     */
    @Column(name = "ITEM_NAME")
    public String getName() {
        return name;
    }

    /**
     * 名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 菜单类型（会员，商户，产品）
     *
     * @return
     */
    @Column(name = "ITEM_TYPE")
    public Integer getType() {
        return type;
    }

    /**
     * 菜单类型（会员，商户，产品）
     *
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * URL
     *
     * @return
     */
    @Column(name = "ITEM_URL")
    public String getUrl() {
        return url;
    }

    /**
     * URL
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 显示次序
     *
     * @return
     */
    @Column(name = "ITEM_ORDER")
    public Integer getOrder() {
        return order;
    }

    /**
     * 显示次序
     *
     * @param order
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 上级菜单
     *
     * @return
     */
    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级菜单
     *
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 图标
     *
     * @return
     */
    @Column(name = "ITEM_ICON")
    public String getIcon() {
        return icon;
    }

    /**
     * 图标
     *
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 菜单编号
     *
     * @return
     */
    @Column(name = "ITEM_CODE")
    public String getCode() {
        return code;
    }

    /**
     * 菜单编号
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 菜单状态
     *
     * @return
     */
    @Column(name = "ITEM_STATUS")
    public Integer getStatus() {
        return status;
    }

    /**
     * 菜单状态
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否显示
     *
     * @return
     */
    @Column(name = "IS_VISIBLE")
    public Boolean getVisible() {
        return visible;
    }

    /**
     * 是否显示
     *
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * 父菜单
     *
     * @return
     */

    public MenuItem getParent() {
        return parent;
    }

    /**
     * 父菜单
     *
     * @param parent
     */
    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public MenuItem(){

    }
    public MenuItem(String url, Integer order, String name, String authority) {
        super();
        this.url = url;
        this.order = order;
        this.name = name;
        this.authority = authority;
    }
}
