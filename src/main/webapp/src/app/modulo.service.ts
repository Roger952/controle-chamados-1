import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';

@Injectable({
    providedIn: 'root'
})
export class ModuloService {
    private baseUrl = 'http://localhost:8080/modulos';

    constructor(private http: HttpClient){
    }

    createModulo(file: FormData): Observable<any>{

        return this.http.post(`${this.baseUrl+'/import'}`, file);
    }
  }
