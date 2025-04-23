package com.infy.AmigoWallet.service;

import com.infy.AmigoWallet.model.Permission;
import com.infy.AmigoWallet.model.WalletUser;
import com.infy.AmigoWallet.repository.PermissionRepository;
import com.infy.AmigoWallet.repository.WordUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletUserDetailsService implements UserDetailsService {
    private final WordUserRepository userRepository;

    private final PermissionRepository permissionRepository;

    @Autowired
    public WalletUserDetailsService(WordUserRepository userRepository, PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WalletUser user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");

        List<Permission> permissions = permissionRepository.findPermissionByUser(user);
        return new User(user.getUsername(),user.getPassword(),permissions);
    }
}
