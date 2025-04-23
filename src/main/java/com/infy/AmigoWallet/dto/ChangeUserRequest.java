package com.infy.AmigoWallet.dto;

public class ChangeUserRequest {

    private String oldUsername;
    private String newUserName;
    private String oldPassword;
    private String newPassword;

    public ChangeUserRequest(String oldUsername, String newUserName, String oldPassword, String newPassword) {
        this.oldUsername = oldUsername;
        this.newUserName = newUserName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangeUserRequest() {
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
