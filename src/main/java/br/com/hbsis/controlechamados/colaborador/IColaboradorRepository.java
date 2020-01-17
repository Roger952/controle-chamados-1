package br.com.hbsis.controlechamados.colaborador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface IColaboradorRepository extends JpaRepository<Colaborador, Long> {
    boolean existsByEmail(String email);
    List<Colaborador> findByNomeContaining(String nome);
}
