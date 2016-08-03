package com.inin.testing.testservice.api.dto;

/**
 * Created by brianschultz on 11/2/15.
 */
public class User {
    String userName;
    String role;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUserName().equals(user.getUserName())) return false;
        return getRole().equals(user.getRole());

    }

    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }

    public String getUserName() {
        return userName;

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
