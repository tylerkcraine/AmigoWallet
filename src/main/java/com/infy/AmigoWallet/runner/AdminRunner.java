package com.infy.AmigoWallet.runner;

import com.infy.AmigoWallet.model.WalletUser;
import com.infy.AmigoWallet.service.PermissionService;
import com.infy.AmigoWallet.service.WalletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminRunner implements CommandLineRunner {

    @Autowired
    private WalletUserService walletUserService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // added a default admin user on start up if one doesn't exist
    // recommended to change the password if in production
    @Override
    public void run(String... args) throws Exception {
        if (!walletUserService.userExists("admin") && permissionService.findByAuthority("ROLE_ADMIN").isEmpty()) {
            WalletUser admin = new WalletUser("admin", "password");
            walletUserService.saveUser(admin);
            permissionService.addPermission(admin, "ROLE_ADMIN");
        }
    }
}
