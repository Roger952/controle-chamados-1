import { Component, OnInit } from '@angular/core';
import { Empresa } from '../empresa';
import { EmpresaService } from '../empresa.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html',
  styleUrls: ['./empresa-list.component.css']
})
export class EmpresaListComponent implements OnInit {

  empresas: Empresa[];
  constructor(private empresaService: EmpresaService, private router: Router) { }

  ngOnInit() {
    this.empresaService.getEmpresaList().subscribe(data => { this.empresas = data; }, error => { console.log(error); });
  }



}
