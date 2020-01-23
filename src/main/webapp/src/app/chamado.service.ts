import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders } from '@angular/common/http'
import { Chamado } from './chamado';


@Injectable({
  providedIn: 'root'
})
export class ChamadoService {

  private baseUrl = 'http://localhost:8080/';

  constructor(private http: ControleHttp) { }


  createChamado(chamado: Chamado): Observable<Object> {


    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl + 'chamado/save'}`, chamado);
  }

  getChamadoList(): Observable<Chamado[]> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl + '/listar'}`, { headers });
  }

  /* FILE-UPLOAD */
  uploadFile(formData: FormData): Observable<any> {

    let url = this.baseUrl + 'arquivos/save';
    return this.http.post(url, formData);
  }

}