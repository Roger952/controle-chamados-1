import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Empresa } from './empresa';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private baseUrl = environment.apiUrl;

  constructor(private http: ControleHttp) { }

  getEmpresa(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl+'/empresa/findById'}/${id}`);
  }

  createEmpresa(empresa: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl+'/empresa/save'}`, empresa);
  }

  updateEmpresa(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl+'/empresa/update'}/${id}`, value);
  }

  deleteEmpresa(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+'/empresa/delete'}/${id}`, { responseType: 'text' });
  }

  getEmpresaList(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(`${this.baseUrl+'/empresa/findAll'}`);
  }

}
