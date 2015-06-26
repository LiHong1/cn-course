package cm.commons.bean;

import cm.commons.DomainObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 列表
 *
 * @author lzc
 */
@Entity
@Table(name = "T_C_LIST")
public class AllPurposeList extends DomainObject {
    /**
     *
     */
    private static final long serialVersionUID = -2664318699378474264L;
    // 列表名，唯一
    private String name;
    // 列表项
    private List<ListItem> items = new ArrayList<ListItem>();

    @Column(name = "LIST_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = {CascadeType.REMOVE}, targetEntity = ListItem.class)
    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(List<ListItem> items) {
        this.items = items;
    }

    /**
     * 增加列表项
     *
     * @param key
     * @param value
     */
    public void addItem(String key, String value) {
        ListItem item = new ListItem();
        item.setKey(key);
        item.setValue(value);
        item.setCreatedDate(Calendar.getInstance());

        items.add(item);
    }
}
