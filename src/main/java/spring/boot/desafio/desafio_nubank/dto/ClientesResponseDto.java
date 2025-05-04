package spring.boot.desafio.desafio_nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.desafio.desafio_nubank.model.Contatos;

import java.util.List;

public class ClientesResponseDto {
    private Long id;
    private String name;
    private String cpf;
    private List<ContatoResponseDto> contatos;

    public ClientesResponseDto() {
    }
    public ClientesResponseDto(Long id, String name, String cpf, List<ContatoResponseDto> contatos) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.contatos = contatos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContatoResponseDto> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoResponseDto> contatos) {
        this.contatos = contatos;
    }
}
