package com.infy.AmigoWallet.repository;

import com.infy.AmigoWallet.model.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordUserRepository extends JpaRepository<WalletUser, Long> {
    public WalletUser findUserByUsername(String username);
    public boolean existsByUsername(String username);
}
