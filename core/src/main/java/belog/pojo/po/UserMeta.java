package belog.pojo.po;


import javax.persistence.*;

/**
 * 存储用户的元数据
 *
 * @author Beldon
 */
@Entity(name = "t_usermeta")
public class UserMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "umeta_id", length = 20)
    private Long id;
    @Column(name = "umeta_key", length = 225)
    private String metaKey;

    @Column(name = "umeta_value")
    private String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
