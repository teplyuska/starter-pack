package starter.pack.restapi.model.dto.account;

public class CreateAccountDTO {
    private Long id;
    private String email;

    public CreateAccountDTO() {
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
