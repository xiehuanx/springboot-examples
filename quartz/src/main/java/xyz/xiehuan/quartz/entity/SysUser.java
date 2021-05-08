package xyz.xiehuan.quartz.entity;





import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xie
 * @since 2018-09-01
 */

@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue
    private Integer id;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "SysUser{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
