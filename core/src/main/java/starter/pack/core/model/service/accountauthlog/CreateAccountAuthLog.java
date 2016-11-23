package starter.pack.core.model.service.accountauthlog;

import starter.pack.core.enums.AuthStatusEnum;

public class CreateAccountAuthLog {
    private Long accountId;
    private String ipAddress;
    private AuthStatusEnum status;

    public CreateAccountAuthLog() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public AuthStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuthStatusEnum status) {
        this.status = status;
    }
}
