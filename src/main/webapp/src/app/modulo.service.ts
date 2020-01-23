import {Injectable} from '@angular/core';
import {HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ControleHttp} from "./seguranca/Controle-http";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {
  
  private baseUrl = environment.apiUrl;

  constructor(private http: ControleHttp) {
  }

  createModulo(file: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl + '/modulos/import'}`, file);
  }

  downloadFile(): Observable<ArrayBuffer> {

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

    let url = this.baseUrl + '/modulos/export';

    return this.http.get(url, options).pipe(map((file: ArrayBuffer) => {
      return file;
    }));
  }

  downloadModule(): Observable<ArrayBuffer> {

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

    let url = this.baseUrl + '/modulos/export-formatted';


    return this.http.get(url, options).pipe(map((file: ArrayBuffer) => {
      return file;
    }));
  }

}
