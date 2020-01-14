import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa } from './empresa';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders }    from '@angular/common/http';


@Injectable({
providedIn: 'root'
})
export class EmpresaService {

private baseUrl = 'http://localhost:8080/empresa';

constructor(private http: ControleHttp) { }

  getEmpresa(id: number): Observable<any> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl+'/findById'}/${id}`, {headers});
  }

  createEmpresa(empresa: Object): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl+'/save'}`, empresa, {headers});
  }

  updateEmpresa(id: number, value: any): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.put(`${this.baseUrl+'/update'}/${id}`, value, {headers});
  }

  deleteEmpresa(id: number): Observable<any> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' }); // setar headers
  }

  getEmpresaList(): Observable<Empresa[]> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get<Empresa[]>(`${this.baseUrl+'/findAll'}`, {headers});
  }

}
