package com.softuni.springworkshop.dao.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String email;
    private String git;
    private Set<Role> authorities;
    private Set<Homework> homeworks;

    public User() {
    }

    @Override
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Override
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    @Email
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "git", nullable = false)
    public String getGit() {
        return git;
    }

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @OneToMany(mappedBy = "author", targetEntity = Homework.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
