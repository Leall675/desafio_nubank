package spring.boot.desafio.desafio_nubank.dto;

public class ContatosDto {
    private String telefone;
    private String email;
    private Long clientesId;

    public ContatosDto() {}

    public ContatosDto(String telefone, String email, Long clientesId) {
        this.telefone = telefone;
        this.email = email;
        this.clientesId = clientesId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClientesId() {
        return clientesId;
    }

    public void setClientesId(Long clientesId) {
        this.clientesId = clientesId;
    }
}