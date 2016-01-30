package belog.pojo.po;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Beldon
 */
@Entity(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name", length = 225, nullable = false)
    private String name;

    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色对应的权限
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_roles_permissions",
            joinColumns = {@JoinColumn(name = "r_id")},
            inverseJoinColumns = {@JoinColumn(name = "p_id")}
    )
    private Set<Permission> permissions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
