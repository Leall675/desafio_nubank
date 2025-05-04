package spring.boot.desafio.desafio_nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.desafio.desafio_nubank.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
