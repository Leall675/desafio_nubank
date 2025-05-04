package spring.boot.desafio.desafio_nubank.dto;
import java.util.List;

public class ClientesDto {
    private String name;
    private String cpf;
    private List<ContatosDto> contatos;

    public ClientesDto() {
    }

    public ClientesDto(String name, String cpf, List<ContatosDto> contatos) {
        this.name = name;
        this.cpf = cpf;
        this.contatos = contatos;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ContatosDto> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatosDto> contatos) {
        this.contatos = contatos;
    }
}
