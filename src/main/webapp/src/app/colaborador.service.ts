import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders } from '@angular/common/http';
import { Colaborador } from './colaborador';


@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {

  private baseUrl = 'http://localhost:8080/colaborador';

  constructor(private http: ControleHttp) { }

  createColaborador(colaborador: Colaborador): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl + '/save'}`, colaborador, { headers });
  }

  updateColaborador(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl + '/update'}/${id}`, value);
  }

  deleteColaborador(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl + '/delete'}/${id}`, { responseType: 'text' });
  }

  getColaboradorList(): Observable<Colaborador[]> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl + '/findAll'}`, { headers });
  }
}
