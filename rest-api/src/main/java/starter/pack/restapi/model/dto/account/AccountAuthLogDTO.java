package starter.pack.restapi.model.dto.account;

import starter.pack.core.enums.AuthStatusEnum;

import java.time.LocalDateTime;

public class AccountAuthLogDTO {
    private Long id;
    private String ipAddress;
    private AuthStatusEnum status;
    private LocalDateTime createdDate;

    public AccountAuthLogDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
