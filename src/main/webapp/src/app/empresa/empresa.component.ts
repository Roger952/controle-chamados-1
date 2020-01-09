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

  constructor(private empresaService: EmpresaService) { }

  ngOnInit() {
  }

  newEmpresa(): void {
    this.submitted = false;
    this.empresa = new Empresa();
  }
  save() {
    this.empresaService.createEmpresa(this.empresa).subscribe(data => alert("Aplicação deu certo."),
      error => alert("Erro ao cadastrar."));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}
