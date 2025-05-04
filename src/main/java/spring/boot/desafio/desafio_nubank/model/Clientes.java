package spring.boot.desafio.desafio_nubank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    @OneToMany(mappedBy = "clientes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contatos> contatos = new ArrayList<Contatos>();


    public Clientes() {
    }

    public Clientes(String name, String cpf, List<Contatos> contatos) {
        this.name = name;
        this.cpf = cpf;
        this.contatos = contatos;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Contatos> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contatos> contatos) {
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
}
