import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders } from '@angular/common/http'
import { Chamado } from './chamado';


@Injectable({
  providedIn: 'root'
})
export class ChamadoService {

  private baseUrl = 'http://localhost:8080/chamado';

  constructor(private http: ControleHttp) { }

  // getAtendente(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
  // }

  createChamado(chamado: Chamado): Observable<Object> {

    console.log("Chamado: " + chamado.titulo);
    console.log("Chamado: " + chamado.arquivoDTOS);
    console.log("Chamado: " + chamado.descricao);
    console.log("Chamado: " + chamado.produtoList);

    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl + '/save'}`, chamado, { headers });
  }

  getChamadoList(): Observable<Chamado[]> {
   
    return this.http.get(`${this.baseUrl+'/findAll'}`); 
  }

  /* FILE-UPLOAD */
  uploadFile(file: File): Observable<any> {

    console.log("Arquivo (file) dentro do método uploadimg: " + file.name);

    let url = this.baseUrl + '/save';
    let formData: FormData = new FormData();
    formData.append('file', file);
    return this.http.post(url, formData);
  }

}
