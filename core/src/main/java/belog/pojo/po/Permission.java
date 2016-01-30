package belog.pojo.po;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Beldon
 */
@Entity(name = "t_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 权限名称
     */
    @Column(name = "name", length = 225, nullable = false)
    private String name;

    /**
     * 权限值
     */
    @Column(name = "value", length = 225, nullable = false)
    private String value;

    /**
     * 权限父级
     */
    private Integer pid;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限对应的角色
     */
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
