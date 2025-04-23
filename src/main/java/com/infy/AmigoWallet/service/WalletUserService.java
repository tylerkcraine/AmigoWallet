package com.infy.AmigoWallet.service;

import com.infy.AmigoWallet.dto.ChangeUserRequest;
import com.infy.AmigoWallet.exception.IncorrectUserPassword;
import com.infy.AmigoWallet.exception.UserAlreadyExistException;
import com.infy.AmigoWallet.model.WalletUser;
import com.infy.AmigoWallet.repository.WordUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Transactional(rollbackOn = {UserAlreadyExistException.class, })
public class WalletUserService {

    private final WordUserRepository wordUserRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WalletUserService(WordUserRepository wordUserRepository, PasswordEncoder passwordEncoder) {
        this.wordUserRepository = wordUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(WalletUser user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        wordUserRepository.save(user);
    }

    public boolean userExists(String username) {
        return wordUserRepository.existsByUsername(username);
    }

    public WalletUser getUserByUsername(String username) {
        return wordUserRepository.findUserByUsername(username);
    }

    public void changeUser(ChangeUserRequest request, UserDetails userDetails) {
        boolean roleAdmin = userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> Objects.equals(a.getAuthority(), "ROLE_ADMIN"));

        if (roleAdmin) {
            if (userDetails.getUsername().equals(request.getOldUsername()) && !passwordEncoder.matches(request.getOldPassword(),userDetails.getPassword()))
                throw new IncorrectUserPassword("Incorrect username/password");

        } else {
            if (!userDetails.getUsername().equals(request.getOldUsername()) || !userDetails.getPassword().equals(request.getOldPassword()))
                throw new IncorrectUserPassword("Incorrect username/password");
        }

        WalletUser user = getUserByUsername(request.getOldUsername());
        user.setUsername(request.getNewUserName() == null ? user.getUsername() : request.getNewUserName());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
