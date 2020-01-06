package br.com.hbsis.controlechamados.produtos;

import org.springframework.data.jpa.repository.JpaRepository;

interface IProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNomeProduto(String nomeProduto);
}
