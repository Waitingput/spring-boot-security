package cn.pzhu.springbootsecurity.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 账户实体类
 */
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private boolean locked;
    private boolean enabled;
    private List<Role> roles;

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    //用户所有角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority(role.getName()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    //是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //是否没锁定
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    //当前账户密码是否没过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //当前账户是否可用
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
