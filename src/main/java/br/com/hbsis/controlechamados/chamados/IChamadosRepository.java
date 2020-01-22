package br.com.hbsis.controlechamados.chamados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IChamadosRepository extends JpaRepository<Chamados, Long> {

}
