package models.login;

import java.util.Map;

public class LoginRequest {
    private String email;
    private String password;

    public static LoginRequest createLoginData(Map<String, String> entry) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(entry.get("email"));
        loginRequest.setPassword(entry.get("password"));
        return loginRequest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
