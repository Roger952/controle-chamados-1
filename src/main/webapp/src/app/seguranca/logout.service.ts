import { Injectable } from '@angular/core';

import { AuthService } from './auth.service';
import { ControleHttp } from './Controle-http';
import { HttpHeaders }    from '@angular/common/http';

@Injectable()
export class LogoutService {

  tokensRevokeUrl: string;

  constructor(
    private http: ControleHttp ,
    private auth: AuthService
  ) {
    this.tokensRevokeUrl = `http://localhost:8080/tokens/revoke`;
  }

  logout() {
    const headers = new HttpHeaders().append('Authorization', 'Bearer' + localStorage.getItem('token'));
    return this.http.delete(this.tokensRevokeUrl, {headers})
      .toPromise()
      .then(() => {
        this.auth.limparAccessToken();
      });
  }

}