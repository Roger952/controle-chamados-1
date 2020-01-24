import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { Colaborador } from './colaborador';
import { environment } from 'src/environments/environment';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {

  private baseUrl = environment.apiUrl;

  constructor(private http: ControleHttp) { }

  createColaborador(colaborador: Colaborador): Observable<Object> {
    return this.http.post(`${this.baseUrl + '/colaborador/save'}`, colaborador);
  }

  updateColaborador(colaborador: Colaborador): Observable<Object> {
    return this.http.put(`${this.baseUrl + '/colaborador/update'}`, colaborador);
  }

  deleteColaborador(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl + '/colaborador/delete'}/${id}`, { responseType: 'text' });
  }

  getColaboradorFindBy(nome: string): Observable<Colaborador[]> {
    return this.http.get(`${this.baseUrl + '/colaborador/findByNome'}/${nome}`);
  }

  getColaboradorList(): Observable<Colaborador[]> {
    return this.http.get(`${this.baseUrl + '/colaborador/findAll'}`);
  }

  getColaborador(id: number): Observable<Colaborador> {
    return this.http.get(`${this.baseUrl + '/colaborador/findById'}/${id}`);
  }
}
