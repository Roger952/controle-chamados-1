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

  createAtendente(atendente: Object): Observable<Object> {

    console.log("Atendente: "+ atendente);
    return this.http.post(`${this.baseUrl+'/save'}`, atendente);
  }

  /* FILE-UPLOAD */
  uploadImg(file: File): Observable<any>{

    console.log("Arquivo (file) dentro do método uploadimg: "+ file.name);

    let url = this.baseUrl + '/saveImagem';
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(url, formData);
  }

  getAtendenteList(): Observable<Atendente[]> {
    return this.http.get(`${this.baseUrl+'/findAll'}`);
  }
}
