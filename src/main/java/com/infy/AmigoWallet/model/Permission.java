package com.infy.AmigoWallet.model;

import com.infy.AmigoWallet.validation.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WalletUser user;

    @Role
    private String authority;

    public Permission(WalletUser user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public Permission(Long id, WalletUser user, String permission) {
        this.id = id;
        this.user = user;
        this.authority = permission;
    }

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletUser getUser() {
        return user;
    }

    public void setUser(WalletUser user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
