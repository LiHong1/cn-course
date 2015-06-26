package cm.entity;

import cm.commons.DomainObject;

import javax.persistence.*;

/**
 * 材料实体
 */
@Entity
@Table(name = "T_MATERIAL")
public class Material extends DomainObject {

    //材料路径
    private String materialPath;
    //页面展示的文件名
    private String name;
    //材料类型
    private Long type = 0L;// 0表示视频 1表是能够展示的资料 2不能够展示的其他资料
    //案例
    private Case cases;
    //对应关系
    private State state;
    //原始名字
    private String originalName;
    //后缀
    private String suffix;

    @Column(name = "SUFFIX")
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Column(name = "MATERIAL_PATH")
    public String getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TYPE")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID")
    public Case getCases() {
        return cases;
    }

    public void setCases(Case cases) {
        this.cases = cases;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name = "ORIGINAL_NAME")
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
