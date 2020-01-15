package br.com.hbsis.controlechamados.modulo;

import br.com.hbsis.controlechamados.produtos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IModuloRepositoty extends JpaRepository<Modulo, Long> {
    Boolean existsByNomeModuloAndProduto(String nomeProduto, Produto produto);
}
