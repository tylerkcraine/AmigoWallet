package com.infy.AmigoWallet.controller;

import com.infy.AmigoWallet.dto.AuthenticationRequest;
import com.infy.AmigoWallet.dto.ChangeUserRequest;
import com.infy.AmigoWallet.dto.PermissionDTO;
import com.infy.AmigoWallet.model.WalletUser;
import com.infy.AmigoWallet.service.JwtUtil;
import com.infy.AmigoWallet.service.PermissionService;
import com.infy.AmigoWallet.service.WalletUserDetailsService;
import com.infy.AmigoWallet.service.WalletUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "authentication")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final WalletUserDetailsService walletUserDetailsService;

    private final WalletUserService walletUserService;

    private final PermissionService permissionService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          WalletUserDetailsService walletUserDetailsService,
                          WalletUserService walletUserService,
                          JwtUtil jwtUtil,
                          PermissionService permissionService) {
        this.authenticationManager = authenticationManager;
        this.walletUserDetailsService = walletUserDetailsService;
        this.walletUserService = walletUserService;
        this.jwtUtil = jwtUtil;
        this.permissionService = permissionService;
    }

    @Operation(summary = "Used to register a new user")
    @PostMapping("/register")
    public String registerUser(@RequestBody AuthenticationRequest user) {
        walletUserService.saveUser(new WalletUser(user.getUsername(), user.getPassword()));
        return "Registration successful";
    }

    @PatchMapping("/change")
    public void changeUser(@RequestBody ChangeUserRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        walletUserService.changeUser(request, userDetails);
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = walletUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        Cookie jwtCookie = new Cookie("WORD_JWT", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        httpServletResponse.addCookie(jwtCookie);
    }

    @GetMapping("/signout")
    public void signOut(HttpServletResponse httpServletResponse) {
        Cookie removeCookie = new Cookie("WORD_JWT", "");
        removeCookie.setMaxAge(-1);
        removeCookie.setPath("/");
        httpServletResponse.addCookie(removeCookie);
    }

    @PutMapping("/role")
    public void addRole(@RequestBody PermissionDTO permissionDTO) {
        permissionService.addPermission(walletUserService.getUserByUsername(permissionDTO.getUsername()), permissionDTO.getPermission());
    }

    @DeleteMapping("/role")
    public void removeRole(@RequestBody PermissionDTO permissionDTO) {
        permissionService.removePermission(walletUserService.getUserByUsername(permissionDTO.getUsername()), permissionDTO.getPermission());
    }

    @GetMapping("/checkAdmin")
    public String adminCheck() {
        return "yes";
    }
}
