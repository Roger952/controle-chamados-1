package br.com.hbsis.controlechamados.chamados.arquivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IArquivoRepository extends JpaRepository<Arquivo, Long> {

}
