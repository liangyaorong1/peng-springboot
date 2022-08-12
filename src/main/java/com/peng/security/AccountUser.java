package com.peng.security;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class AccountUser implements UserDetails {

    private Long id;//用户编号
    private String username;//邮箱
    private String password;//密码

    private List<String> permissions;//用户权限集合
    // 不会被序列化
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;//用户权限集合

    public AccountUser(Long id, String username, String password, List<String> permissions) {
       this.id=id;
        this.username=username;
        this.password=password;
        this.permissions=permissions;
    }

    //权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null){
            return authorities;
        }
        authorities=permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
