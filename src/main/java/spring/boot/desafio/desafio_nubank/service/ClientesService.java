package spring.boot.desafio.desafio_nubank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.desafio.desafio_nubank.dto.ClientesDto;
import spring.boot.desafio.desafio_nubank.dto.ClientesResponseDto;
import spring.boot.desafio.desafio_nubank.dto.ContatoResponseDto;
import spring.boot.desafio.desafio_nubank.exceptions.ClientNotFoundException;
import spring.boot.desafio.desafio_nubank.model.Clientes;
import spring.boot.desafio.desafio_nubank.model.Contatos;
import spring.boot.desafio.desafio_nubank.repository.ClientesRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarCliente(ClientesDto dto) {
        Clientes clientes = new Clientes();
        clientes.setName(dto.getName());
        clientes.setCpf(dto.getCpf());

        if (dto.getContatos() != null && !dto.getContatos().isEmpty()) {
            List<Contatos> contatos = dto.getContatos().stream().map(c -> {
                Contatos contato = new Contatos();
                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDto> listarTodos() {
        List<Clientes> clientes = clientesRepository.findAll();
        return clientes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ClientesResponseDto buscarClientePorID(Long id) {
        Clientes clientes = clientesRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Nenhum cliente encontrado para o ID informado"));
        return toDto(clientes);
    }

    public ClientesResponseDto buscarClientePorCpf(String cpf) {
        Clientes cliente = clientesRepository.findByCpf(cpf);
        if (cliente == null) {
            throw new ClientNotFoundException("Nenhum cliente encontrado para o CPF informado");
        }
        return toDto(cliente);
    }

    public List<ContatoResponseDto> listarContatosPorCliente(Long id) {
        Clientes cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Nenhum cliente encontrado para o ID informado"));

        return cliente.getContatos().stream()
                .map(c -> {
                    ContatoResponseDto contatoDTO = new ContatoResponseDto();
                    contatoDTO.setId(c.getId());
                    contatoDTO.setTelefone(c.getTelefone());
                    contatoDTO.setEmail(c.getEmail());
                    contatoDTO.setClientesId(c.getId());
                    return contatoDTO;
                })
                .collect(Collectors.toList());
    }

    public ClientesResponseDto toDto(Clientes clientes) {
        ClientesResponseDto dto = new ClientesResponseDto();
        dto.setId(clientes.getId());
        dto.setName(clientes.getName());
        dto.setCpf(clientes.getCpf());

        List<ContatoResponseDto> contatosDto = clientes.getContatos().stream().map(c -> {
            ContatoResponseDto contatoDTO = new ContatoResponseDto();
            contatoDTO.setId(c.getId());
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());
            contatoDTO.setClientesId(clientes.getId()); // Se necessário
            return contatoDTO;
        }).collect(Collectors.toList());

        dto.setContatos(contatosDto);
        return dto;
    }


}
