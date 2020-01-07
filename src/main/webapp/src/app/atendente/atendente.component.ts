import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Atendente } from '../atendente';
import { AtendenteService } from '../atendente.service';

@Component({
  selector: 'app-atendente',
  templateUrl: './atendente.component.html',
  styleUrls: ['./atendente.component.css']
})
export class AtendenteComponent implements OnInit {

  atendente: Atendente = new Atendente();
  submitted = false;

  constructor(private atendenteService: AtendenteService) { }

  toppings = new FormControl();
  toppingList: string[] = ['Gustavo', 'Guga', 'Guganaro', 'Guguero', 'Bacon', 'Tomate'];

  ngOnInit() {

  }

  newAtendente(): void{
    this.submitted = false;
    this.atendente = new Atendente();
  }
  save(){
    this.atendenteService.createAtendente(this.atendente).subscribe(data => alert("Aplicação deu certo."), error => alert("Erro ao cadastrar."));
    this.atendente = new Atendente();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }
}
