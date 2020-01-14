import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders }    from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AtendenteService {

  private baseUrl = 'http://localhost:8080/atendente';

  constructor(private http: ControleHttp) { }

  getAtendente(id: number): Observable<any> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl+'/findById'}/${id}`, {headers});
  }

  createAtendente(atendente: Object): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl+'/save'}`, atendente, {headers});
  }

  updateAtendente(id: number, value: any): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.put(`${this.baseUrl+'/update'}/${id}`, value, {headers});
  }

  deleteAtendente(id: number): Observable<any> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });// setar header
  }

  getAtendenteList(): Observable<any> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl+'/findAll'}`, {headers});
  }
}
