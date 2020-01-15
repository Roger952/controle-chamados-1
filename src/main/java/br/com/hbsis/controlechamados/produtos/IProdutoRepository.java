package br.com.hbsis.controlechamados.produtos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNome(String nome);
    Produto findByNome(String nome);
}
