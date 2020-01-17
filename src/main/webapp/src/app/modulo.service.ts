import { Injectable } from '@angular/core';
import { HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ControleHttp } from '../app/seguranca/Controle-http';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {
  private baseUrl = 'http://localhost:8080/modulos';

  constructor(private http: ControleHttp) {
  }

  createModulo(file: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl + '/import'}`, file);
  }

  downloadFile(): Observable<ArrayBuffer> {
    let headers = new HttpHeaders();

    const options: {
      headers?: HttpHeaders;
      observe?: 'body';
      params?: HttpParams;
      reportProgress?: boolean;
      responseType: 'arraybuffer';
      withCredentials?: boolean;
    } = {
      responseType: 'arraybuffer'
    };

    let url = this.baseUrl + '/export';

    

    return this.http.get(url, options).pipe(map((file: ArrayBuffer) => {
      return file;
    }));
  }

}
