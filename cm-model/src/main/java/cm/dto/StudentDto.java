package cm.dto;

import cm.entity.Student;

import java.util.Date;

public class StudentDto {
    private String id;
    private String name;
    //0表示增加 1表示修改  2表示删除
    private Integer operate;
    private Date createdDate;
    private Date updatedDate;

    public StudentDto() {

    }

    public StudentDto(Student s) {
        // this.setId(BigInteger.valueOf(s.getId()));
        this.setName(s.getName());
        this.setOperate(s.getOperate());
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
