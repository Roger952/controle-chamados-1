package br.com.hbsis.controlechamados.usuario.atendente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface IAtendenteRepository extends JpaRepository<Atendente, Long> {
    boolean existsByEmail(String email);
    Optional<Atendente> findByEmail(String email);
}
