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
    return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
  }

  createEmpresa(empresa: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl+'/save'}`, empresa);
  }

  updateEmpresa(id: number, value: any): Observable<Object> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.put(`${this.baseUrl+'/update'}/${id}`, value, {headers});
  }

  deleteEmpresa(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
  }

  getEmpresaList(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(`${this.baseUrl+'/findAll'}`);
  }

}
