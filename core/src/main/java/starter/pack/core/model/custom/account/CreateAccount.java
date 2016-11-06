package starter.pack.core.model.custom.account;

public class CreateAccount {
    private Long id;
    private String email;

    public CreateAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
