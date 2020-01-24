import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';
import { AuthService } from '../app/seguranca/auth.service';
import { ControleHttp } from '../app/seguranca/Controle-http';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class ProdutosService {

    private baseUrl = environment.apiUrl;

    constructor(public http:ControleHttp, public auth: AuthService){
    }
   
    getProduto(id: number): Observable<any>{
        return this.http.get(`${this.baseUrl+'/produtos/findById'}/${id}`);
    }

    createProduto(produtos: Object): Observable<Produtos>{
        return this.http.post<Produtos>(`${this.baseUrl+'/produtos/save'}`, produtos);
    }

    updateProduto(id: number, value: any): Observable<Object>{
        return this.http.put(`${this.baseUrl+'/produtos/update'}/${id}`, value);
    }

    deleteProduto(id: number): Observable<any>{
        return this.http.delete(`${this.baseUrl+'/produtos/delete'}/${id}`, { responseType: 'text' });
    }

    getProdutosList(): Observable<Produtos[]>{
        return this.http.get<Produtos[]>(`${this.baseUrl+'/produtos/lista'}`);
    }
}