package br.com.hbsis.controlechamados.empresa;

import org.springframework.data.jpa.repository.JpaRepository;

interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
}
