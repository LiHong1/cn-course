package cm.entity;

import cm.commons.DomainObject;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * 案例
 */
@Entity
@Table(name = "T_CASE")
public class Case extends DomainObject {
    //标题
    private String title;
    //对应的班级课程
    private State state;
    //内容
    private String content;
    //材料
    private Set<Material> materials;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = true)
    @NotEmpty(message = "案例内容不能为空")
    @Length(max = 5000,message = "案例内容不能超过5000个字符")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "cases", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    @Column(name = "TITME")
    @NotEmpty(message = "标题不能为空")
    @Length(max = 100,message = "标题不能超过100个字符")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
