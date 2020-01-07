import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-atendente',
  templateUrl: './atendente.component.html',
  styleUrls: ['./atendente.component.css']
})
export class AtendenteComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  toppings = new FormControl();
  toppingList: string[] = ['Gustavo', 'Guga', 'Guganaro', 'Guguero', 'Bacon', 'Tomate'];
}
