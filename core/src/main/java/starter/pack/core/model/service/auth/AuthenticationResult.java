package starter.pack.core.model.service.auth;

import starter.pack.core.enums.AuthStatusEnum;
import starter.pack.core.model.data.Account;

public class AuthenticationResult {
    private Account account;
    private AuthStatusEnum authStatus;

    public AuthenticationResult() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AuthStatusEnum getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatusEnum authStatus) {
        this.authStatus = authStatus;
    }
}
