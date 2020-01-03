package br.com.hbsis.controlechamados.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
}
