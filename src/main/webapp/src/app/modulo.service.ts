import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produtos } from './produtos';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {
  private baseUrl = 'http://localhost:8080/modulos';

  constructor(private http: HttpClient) {
  }

  createModulo(file: FormData): Observable<any> {

    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.post(`${this.baseUrl + '/import'}`, file, {headers});
  }

  downloadFile(): Observable<ArrayBuffer> {
    let headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));

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
