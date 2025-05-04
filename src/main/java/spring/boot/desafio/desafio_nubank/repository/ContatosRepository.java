package spring.boot.desafio.desafio_nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.desafio.desafio_nubank.model.Contatos;

@Repository
public interface ContatosRepository extends JpaRepository<Contatos, Long> {
}
