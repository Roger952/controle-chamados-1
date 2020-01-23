import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { Atendente } from './atendente';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AtendenteService {

  private baseUrl = environment.apiUrl;

  constructor(private http: ControleHttp) { }

   getAtendente(id: number): Observable<any> {
     return this.http.get(`${this.baseUrl+'/atendente/findById'}/${id}`);
   }

  createAtendente(atendente: Object): Observable<Object> {

    console.log("Atendente: "+ atendente);
    return this.http.post(`${this.baseUrl+'/atendente/save'}`, atendente);
  }

   updateAtendente(id: number, value: any): Observable<Object> {
     return this.http.put(`${this.baseUrl+'/atendente/update'}/${id}`, value);
   }

   deleteAtendente(id: number): Observable<any> {
     return this.http.delete(`${this.baseUrl+'/atendente/delete'}/${id}`, { responseType: 'text' });
   }

  uploadImg(file: File): Observable<any>{

    let url = this.baseUrl + '/atendente/saveImagem';
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(url, formData);
  }

  getAtendenteList(): Observable<Atendente[]> {
    return this.http.get(`${this.baseUrl+'/atendente/findAll'}`);
  }
}
