import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private baseUrl = 'http://localhost:8080/empresa';

  constructor(private http: HttpClient) { }

  getEmpresa(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
  }

  createEmpresa(empresa: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl+'/save'}`, empresa);
  }

  updateEmpresa(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
  }

  deleteEmpresa(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
  }

  getEmpresaList(): Observable<any> {
    return this.http.get(`${this.baseUrl+'/findAll'}`);
  }

}
