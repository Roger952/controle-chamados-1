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

  msgErro: string;
  msgSucesso: string;
  erro = false;
  sucesso = false;

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

  limpar() {
    this.empresa.razaoSocial = '';
    this.empresa.nomeFantasia = '';
    this.empresa.cnpj = '';
    this.empresa.ie = '';
    this.empresa.email = '';
  }
}
