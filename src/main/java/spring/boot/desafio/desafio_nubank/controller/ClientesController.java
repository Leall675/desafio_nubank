package spring.boot.desafio.desafio_nubank.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.desafio.desafio_nubank.dto.ClientesDto;
import spring.boot.desafio.desafio_nubank.dto.ClientesResponseDto;
import spring.boot.desafio.desafio_nubank.dto.ContatoResponseDto;
import spring.boot.desafio.desafio_nubank.model.Clientes;
import spring.boot.desafio.desafio_nubank.service.ClientesService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClientesResponseDto> salvarClientes(@RequestBody ClientesDto dto) {
        Clientes clienteSalvo = clientesService.salvarCliente(dto);
        List<ContatoResponseDto> contatoResponseDtos = clienteSalvo.getContatos().stream()
                .map(contato -> new ContatoResponseDto(contato.getId(), contato.getTelefone(), contato.getEmail(), clienteSalvo.getId()))
                .collect(Collectors.toList());
        ClientesResponseDto responseDto = new ClientesResponseDto(clienteSalvo.getId(), clienteSalvo.getName(), clienteSalvo.getCpf(), contatoResponseDtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDto>> listarClientes(@RequestParam(required = false) String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            ClientesResponseDto cliente = clientesService.buscarClientePorCpf(cpf);
            return ResponseEntity.ok(Collections.singletonList(cliente));
        }
        List<ClientesResponseDto> clientes = clientesService.listarTodos();
        if (clientes.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesResponseDto> buscarClientePorId(@PathVariable Long id) {
        ClientesResponseDto clientes = clientesService.buscarClientePorID(id);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDto>> listarContatosPorCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.listarContatosPorCliente(id));
    }

}
