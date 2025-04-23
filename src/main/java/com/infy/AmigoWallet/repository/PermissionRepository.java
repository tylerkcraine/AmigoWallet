package com.infy.AmigoWallet.repository;

import com.infy.AmigoWallet.model.Permission;
import com.infy.AmigoWallet.model.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findPermissionByUser(WalletUser user);
    List<Permission> findPermissionByUserUsername(String username);
    List<Permission> findPermissionByAuthority(String authority);
}
