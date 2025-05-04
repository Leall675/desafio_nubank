package spring.boot.desafio.desafio_nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ContatoResponseDto {
    private Long id;
    private String telefone;
    private String email;
    private Long clientesId;

    public ContatoResponseDto() {
    }

    public ContatoResponseDto(Long id, String telefone, String email, Long clientesId) {
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.clientesId = clientesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
