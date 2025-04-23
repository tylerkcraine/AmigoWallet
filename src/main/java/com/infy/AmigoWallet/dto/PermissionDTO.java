package com.infy.AmigoWallet.dto;

import com.infy.AmigoWallet.validation.Role;

public class PermissionDTO {
    String username;
    @Role
    String permission;

    public PermissionDTO(String username, String permission) {
        this.username = username;
        this.permission = permission;
    }

    public PermissionDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
