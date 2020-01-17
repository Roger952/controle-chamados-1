import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';
import { AuthService } from '../app/seguranca/auth.service';
import { ControleHttp } from '../app/seguranca/Controle-http';


@Injectable({
    providedIn: 'root'
})
export class ProdutosService {
    private baseUrl = 'http://localhost:8080/produtos';

    constructor(public http:ControleHttp, public auth: AuthService){
    }
   
    getProduto(id: number): Observable<any>{
        return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
    }

    createProduto(produtos: Object): Observable<Produtos>{
        return this.http.post<Produtos>(`${this.baseUrl+'/save'}`, produtos);
    }

    updateProduto(id: number, value: any): Observable<Object>{
        return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
    }

    deleteProduto(id: number): Observable<any>{
        return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
    }

    getProdutosList(): Observable<Produtos[]>{
        return this.http.get<Produtos[]>(`${this.baseUrl+'/lista'}`);
    }
}
