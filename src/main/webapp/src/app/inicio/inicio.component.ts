import { Component, OnInit } from '@angular/core';
import { Empresa } from '../empresa';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';
import { ProdutosService } from '../produtos.service';
import { Produtos } from '../produtos';
import { AtendenteService } from '../atendente.service';
import { Atendente } from '../atendente';
import { AuthService } from '../seguranca/auth.service';
import { Chamado } from '../chamado';
import { ChamadoService } from '../chamado.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  empresas: Empresa[];
  produtos: Produtos[];
  atendentes: Atendente[];
  chamado: Chamado[];

  constructor(private empresaService: EmpresaService,
    private produtoService: ProdutosService,
    private atendenteService: AtendenteService,
    private chamadoService: ChamadoService,
    private auth: AuthService
    ) { }

  ngOnInit() {
    this.empresaService.getEmpresaList().subscribe(data => { this.empresas = data; }, error => { console.log(error); });
    this.produtoService.getProdutosList().subscribe(data => { this.produtos = data; }, error => { console.log(error); });
    this.atendenteService.getAtendenteList().subscribe(data => { this.atendentes = data; }, error => { console.log(error); });
    this.chamadoService.getChamadoList().subscribe(data => { this.chamado = data; }, error => { console.log(error); });

  }

}
