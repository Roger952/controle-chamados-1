import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';
import { AuthService } from '../app/seguranca/auth.service';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { HttpHeaders }    from '@angular/common/http';


@Injectable({
    providedIn: 'root'
})
export class ProdutosService {
    private baseUrl = 'http://localhost:8080/produtos';

    constructor(public http:ControleHttp, public auth: AuthService){
    }
   
    getProduto(id: number): Observable<any>{
        const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
        return this.http.get(`${this.baseUrl+'/findById'}/${id}`, {headers});
    }

    createProduto(produtos: Object): Observable<Produtos>{
        const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
        return this.http.post<Produtos>(`${this.baseUrl+'/save'}`, produtos, {headers});
    }

    updateProduto(id: number, value: any): Observable<Object>{
        const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
        return this.http.put(`${this.baseUrl+'/update'}/${id}`, value, {headers});
    }

    deleteProduto(id: number): Observable<any>{
        const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
        return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' }); // colocar headers
    }

    getProdutosList(): Observable<Produtos[]>{
        const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
        return this.http.get<Produtos[]>(`${this.baseUrl+'/lista'}`, {headers});
    }
}
