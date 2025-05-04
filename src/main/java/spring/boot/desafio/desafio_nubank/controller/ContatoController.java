package spring.boot.desafio.desafio_nubank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.desafio.desafio_nubank.dto.ContatosDto;
import spring.boot.desafio.desafio_nubank.model.Clientes;
import spring.boot.desafio.desafio_nubank.model.Contatos;
import spring.boot.desafio.desafio_nubank.repository.ClientesRepository;
import spring.boot.desafio.desafio_nubank.repository.ContatosRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contato")
public class ContatoController {

    @Autowired
    private ContatosRepository contatosRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContatosDto dto) {
        Optional<Clientes> clientes = clientesRepository.findById(dto.getClientesId());
        if (clientes.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }
        Contatos contato = new Contatos();
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        contato.setClientes(clientes.get());
        contatosRepository.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }
}
