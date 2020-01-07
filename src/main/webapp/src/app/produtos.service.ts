import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ProdutosService {
    private baseUrl = 'http://localhost:8080/produtos';

    constructor(private http: HttpClient){
    }

    getProduto(id: number): Observable<any>{
        return this.http.get(`${this.baseUrl+'/findById'}/${id}`);
    }

    createProduto(produtos: Object): Observable<Object>{
        return this.http.post<string>(`${this.baseUrl+'/save'}`, produtos);
    }

    updateProduto(id: number, value: any): Observable<Object>{
        return this.http.put(`${this.baseUrl+'/update'}/${id}`, value);
    }

    deleteProduto(id: number): Observable<any>{
        return this.http.delete(`${this.baseUrl+'/delete'}/${id}`, { responseType: 'text' });
    }

    getProdutosList(): Observable<any>{
        return this.http.get(`${this.baseUrl+'/lista'}`);
    }
}
