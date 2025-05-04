package spring.boot.desafio.desafio_nubank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.desafio.desafio_nubank.dto.ClientesDto;
import spring.boot.desafio.desafio_nubank.dto.ClientesResponseDto;
import spring.boot.desafio.desafio_nubank.dto.ContatoResponseDto;
import spring.boot.desafio.desafio_nubank.dto.ContatosDto;
import spring.boot.desafio.desafio_nubank.model.Clientes;
import spring.boot.desafio.desafio_nubank.service.ClientesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/cliente")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClientesResponseDto> salvarClientes(@RequestBody ClientesDto dto) {
        System.out.println("DTO Recebido no Controlador: " + dto);
        // Verifique os contatos
        for (ContatosDto contato : dto.getContatos()) {
            System.out.println("Contato: telefone=" + contato.getTelefone() + ", email=" + contato.getEmail());
        }

        Clientes clienteSalvo = clientesService.salvarCliente(dto);

        List<ContatoResponseDto> contatoResponseDtos = clienteSalvo.getContatos().stream()
                .map(contato -> new ContatoResponseDto(contato.getId(), contato.getTelefone(), contato.getEmail(), clienteSalvo.getId()))
                .collect(Collectors.toList());

        ClientesResponseDto responseDto = new ClientesResponseDto(clienteSalvo.getId(), clienteSalvo.getName(), clienteSalvo.getCpf(), contatoResponseDtos);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDto>> listarClientes() {
        List<ClientesResponseDto> clientes = clientesService.listarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(clientesService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesResponseDto> buscarClientePorId(@PathVariable Long id) {
        ClientesResponseDto clientes = (ClientesResponseDto) clientesService.buscarClientePorID(id);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDto>> listarContatosPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.listarContatosPorCliente(id));
    }

}
