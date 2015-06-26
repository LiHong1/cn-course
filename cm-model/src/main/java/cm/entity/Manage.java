package cm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员
 */
@Entity
@Table(name = "T_MANAGE")
public class Manage extends Person implements Serializable {

}
