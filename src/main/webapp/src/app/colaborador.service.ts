import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { Colaborador } from './colaborador';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {

  private baseUrl = environment.apiUrl;

  constructor(private http: ControleHttp) { }

  // getAtendente(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl+'/colaborador/findById'}/${id}`);
  // }

  createColaborador(colaborador: Colaborador): Observable<Object> {

    console.log("Colaborador: "+ colaborador.email);
    console.log("Colaborador: "+ colaborador.nome);
    console.log("Colaborador: "+ colaborador.senha);
    console.log("Colaborador: "+ colaborador.empresaId);
    console.log("Colaborador: "+ colaborador.produtoList);

    return this.http.post(`${this.baseUrl+'/colaborador/save'}`, colaborador);
  }

   updateColaborador(id: number, value: any): Observable<Object> {
     return this.http.put(`${this.baseUrl+'/colaborador/update'}/${id}`, value);
   }

   deleteColaborador(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl+'/colaborador/delete'}/${id}`, { responseType: 'text' });
   }

  /* FILE-UPLOAD */
//   uploadImg(file: File): Observable<any>{

//     console.log("Arquivo (file) dentro do método uploadimg: "+file.name);

//     let url = this.baseUrl + '/colaborador/saveImagem';
//     let formData: FormData = new FormData();
//     formData.append('file', file);
//     const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
//     return this.http.post(url, formData, { headers });
//   }

  getColaboradorList(): Observable<Colaborador[]> {
    return this.http.get(`${this.baseUrl+'/colaborador/findAll'}`);
  }
}
