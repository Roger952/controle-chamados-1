package br.com.hbsis.controlechamados.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByLogin(String login);
    boolean existsByLogin(String email);
}
