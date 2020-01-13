import { Component, OnInit } from '@angular/core';

import { AuthService } from '../seguranca/auth.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent {

  constructor(private auth: AuthService) { }

  login(usuario: string, senha: string) {
    this.auth.islogin(usuario, senha);
  }

}