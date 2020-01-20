import { Injectable } from '@angular/core';

import { AuthService } from './auth.service';
import { ControleHttp } from './Controle-http';

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
    return this.http.delete(this.tokensRevokeUrl, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.auth.limparAccessToken();
      });
  }

}