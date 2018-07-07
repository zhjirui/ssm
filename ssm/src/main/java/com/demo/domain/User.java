package com.demo.domain;


import org.hibernate.validator.constraints.NotEmpty;

public class User {

    @NotEmpty(message = "id不能为空")
    private String id;
    @NotEmpty(message = "姓名不能为空")
    private String username;
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}