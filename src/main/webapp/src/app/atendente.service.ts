import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { Atendente } from './atendente';

@Injectable({
  providedIn: 'root'
})
export class AtendenteService {

  private baseUrl = 'http://localhost:8080/atendente';

  constructor(private http: ControleHttp) { }

   getAtendente(id: number): Observable<any> {
     return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
   }

  createAtendente(atendente: Object): Observable<Object> {

    console.log("Atendente: "+ atendente);
    return this.http.post(`${this.baseUrl+'/save'}`, atendente);
  }

   updateAtendente(id: number, value: any): Observable<Object> {
     return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
   }

   deleteAtendente(id: number): Observable<any> {
     return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
   }

    getAtendenteList(): Observable<any> {
      return this.http.get(`${this.baseUrl+'/findAll'}`);
    }

  uploadImg(file: File): Observable<any>{

    let url = this.baseUrl + '/saveImagem';
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(url, formData);
  }

  getAtendenteList(): Observable<Atendente[]> {
    return this.http.get(`${this.baseUrl+'/findAll'}`);
  }
}
