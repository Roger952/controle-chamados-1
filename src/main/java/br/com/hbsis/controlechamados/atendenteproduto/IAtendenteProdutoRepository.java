package br.com.hbsis.controlechamados.atendenteproduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAtendenteProdutoRepository extends JpaRepository<AtendenteProduto, Long> {
}
