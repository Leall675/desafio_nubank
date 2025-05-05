package spring.boot.desafio.desafio_nubank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;
    private String email;


    @ManyToOne()
    @JoinColumn(name = "clientes_id")
    @JsonBackReference
    private Clientes clientes;

    public Contatos() {
    }

    public Contatos(String telefone, String email, Clientes clientes) {
        this.telefone = telefone;
        this.email = email;
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
