import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders }    from '@angular/common/http';
import { Atendente } from './atendente';
import { Colaborador } from './colaborador';


@Injectable({
  providedIn: 'root'
})
export class ColaboradorService {

  private baseUrl = 'http://localhost:8080/colaborador';

  constructor(private http: ControleHttp) { }

  // getAtendente(id: number): Observable<any> {
  //   return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
  // }

  createColaborador(colaborador: Colaborador): Observable<Object> {

    console.log("Colaborador: "+ colaborador.email);
    console.log("Colaborador: "+ colaborador.nome);
    console.log("Colaborador: "+ colaborador.senha);
    console.log("Colaborador: "+ colaborador.empresaId);
    console.log("Colaborador: "+ colaborador.produtoList);

    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl+'/save'}`, colaborador, { headers });
  }

   updateColaborador(id: number, value: any): Observable<Object> {
     return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
   }

   deleteColaborador(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
   }
   getColaboradorFindBy(nome: string): Observable<Colaborador[]> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl + '/findByNome'}/${nome}`, {headers});
  }


  /* FILE-UPLOAD */
//   uploadImg(file: File): Observable<any>{

//     console.log("Arquivo (file) dentro do método uploadimg: "+file.name);

//     let url = this.baseUrl + '/saveImagem';
//     let formData: FormData = new FormData();
//     formData.append('file', file);
//     const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
//     return this.http.post(url, formData, { headers });
//   }

  getColaboradorList(): Observable<Colaborador[]> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.get(`${this.baseUrl+'/findAll'}`, {headers} );
  }
}
