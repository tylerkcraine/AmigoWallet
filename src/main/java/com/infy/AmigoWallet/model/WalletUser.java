package com.infy.AmigoWallet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class WalletUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    public WalletUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public WalletUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WalletUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletUser wordUser = (WalletUser) o;
        return Objects.equals(id, wordUser.id) && Objects.equals(username, wordUser.username) && Objects.equals(password, wordUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
