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

  // getAtendente(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
  // }

  createAtendente(atendente: Object): Observable<Object> {

    console.log("Atendente: "+ atendente);

    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl+'/save'}`, atendente, headers);
  }

  // updateAtendente(id: number, value: any): Observable<Object> {
  //   return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
  // }

  // deleteAtendente(id: number): Observable<any> {
  //   return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
  // }

  getAtendenteList(): Observable<any> {
    return this.http.get(`${this.baseUrl+'/findAll'}`);
  }

  /* FILE-UPLOAD */
  uploadImg(file: File): Observable<any>{

    console.log("Arquivo (file) dentro do método uploadimg: "+file.name);

    let url = this.baseUrl + '/saveImagem';
    let formData: FormData = new FormData();
    formData.append('file', file);

    return this.http.post(url, formData);
  }

  getAtendenteList(): Observable<any> {
    return this.http.get(`${this.baseUrl+'/findAll'}`);
  }
}
