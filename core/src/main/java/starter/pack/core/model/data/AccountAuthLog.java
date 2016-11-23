package starter.pack.core.model.data;

import org.hibernate.annotations.Type;
import starter.pack.core.enums.AuthStatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_auth_log")
public class AccountAuthLog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;

    @Column(name = "ip_address", nullable = false, length = 50)
    private String ipAddress;

    @Column(name = "status", nullable = false)
    @Type(type = "starter.pack.core.repository.util.NameEnumType")
    private AuthStatusEnum status;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public AccountAuthLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
