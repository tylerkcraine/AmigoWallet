package com.infy.AmigoWallet.service;

import com.infy.AmigoWallet.model.Permission;
import com.infy.AmigoWallet.model.WalletUser;
import com.infy.AmigoWallet.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void addPermission(WalletUser user, String permission) {
        Permission newPermission = new Permission(user, permission);
        permissionRepository.save(newPermission);
    }

    public void removePermission(WalletUser user, String permission) {
        List<Permission> permissions = permissionRepository.findPermissionByUser(user);
        for (Permission p : permissions) {
            if (Objects.equals(p.getAuthority(), permission)) {
                permissionRepository.delete(p);
            }
        }
    }

    public List<Permission> findByAuthority(String authority) {
        return permissionRepository.findPermissionByAuthority(authority);
    }

    public List<Permission> findByUserName(String username) {
        return permissionRepository.findPermissionByUserUsername(username);
    }
}
