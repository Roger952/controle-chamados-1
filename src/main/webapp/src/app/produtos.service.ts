
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';
import { AuthService } from '../app/seguranca/auth.service';

@Injectable({
    providedIn: 'root'
})
export class ProdutosService {
    private baseUrl = 'http://localhost:8080/produtos';

    constructor(private http: HttpClient, public auth: AuthService){
    }
   
    getProduto(id: number): Observable<any>{
        return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
    }

    createProduto(produtos: Object): Observable<Object>{

        const headers = new HttpHeaders().append('Authorization', `Bearer ${ this.auth.getToken() }`);
    
        return this.http.post<string>(`${this.baseUrl+'/save'}`, produtos, { headers, withCredentials: true });
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