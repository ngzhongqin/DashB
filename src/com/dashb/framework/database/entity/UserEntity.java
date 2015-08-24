package com.dashb.framework.database.entity;

import javax.persistence.*;

/**
 * Created by zhongqinng on 23/7/15.
 */
@Entity
@Table(name = "user", schema = "pssdash", catalog = "pssdash")
public class UserEntity {
    private String lanId;
    private String full_name;
    private String password_salt_hash;

    @Id
    @SequenceGenerator(name="user_id_seq",
            sequenceName="user_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="user_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    public Long getId(){return this.id;}
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "full_name")
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Basic
    @Column(name = "lanId")
    public String getLanId() {
        return lanId;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    @Basic
    @Column(name = "password_salt_hash")
    public String getPassword_salt_hash() {
        return password_salt_hash;
    }

    public void setPassword_salt_hash(String password_salt_hash) {
        this.password_salt_hash = password_salt_hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (lanId != null ? !lanId.equals(that.lanId) : that.lanId != null) return false;

        return true;
    }

}
