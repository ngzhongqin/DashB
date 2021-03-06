package com.dashb.framework.vo;

/**
 * Created by zhongqinng on 26/7/15.
 * User Value Object
 */
public class UserVO {
    private Long id;
    private String full_name;
    private String lanId;
    private String password_salt_hash;

    public UserVO(){

    }

    public UserVO(Long id,
                  String full_name,
                  String lanId,
                  String password_salt_hash){
        this.id=id;
        this.full_name = full_name;
        this.lanId = lanId;
        this.password_salt_hash = password_salt_hash;
    }

    public UserVO(String full_name,
                  String lanId,
                  String password_salt_hash){
        this.full_name = full_name;
        this.lanId = lanId;
        this.password_salt_hash = password_salt_hash;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLanId() {
        return lanId;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    public String getPassword_salt_hash() {
        return password_salt_hash;
    }

    public void setPassword_salt_hash(String password_salt_hash) {
        this.password_salt_hash = password_salt_hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
