import { EmpresaService } from '../empresa.service';
import { Empresa } from '../empresa';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  empresa: Empresa = new Empresa();
  submitted = false;

  /* RETORNO DE ERROS AO USER */
  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

  /* LIMPAR OS CAMPOS DE TEXTO */
  razaoSocial: string;
  nomeFantasia: string;
  cnpj: string;
  ie: string;
  email: string;

  constructor(private empresaService: EmpresaService) { }

  ngOnInit() {
  }

  newEmpresa(): void {
    this.submitted = false;
    this.empresa = new Empresa();
  }
  
  save() {
    this.empresaService.createEmpresa(this.empresa).subscribe(
      (data) => {
        this.msgSucesso = 'Cadastro realizado com sucesso!';
        this.erro = false;
        this.sucesso = true;
        console.log(this.msgSucesso);
        this.limpar();
    },
      (error) => {
        this.msgErro = error.error[0].mensagemDesenvolvedor;
        this.erro = true;
        this.sucesso = false;
        console.log(this.msgErro);
    });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  /* LIMPAR OS CAMPOS APÓS CADASTRO */
  limpar() {

    this.razaoSocial = (<HTMLInputElement>document.getElementById("razaoSocial")).value;
    this.razaoSocial = "";

    this.nomeFantasia = (<HTMLInputElement>document.getElementById("nomeFantasia")).value;
    this.nomeFantasia = "";

    this.cnpj = (<HTMLInputElement>document.getElementById("cnpj")).value;
    this.cnpj = "";

    this.ie = (<HTMLInputElement>document.getElementById("ie")).value;
    this.ie = "";

    this.email = (<HTMLInputElement>document.getElementById("email")).value;
    this.email = "";
  }
}
