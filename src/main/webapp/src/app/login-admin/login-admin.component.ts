import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthService } from '../seguranca/auth.service';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {

  login: string;
  senha: string;
  errorMessage = 'Usuário e/ou Senha inválidos/nulos!';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  islogin() {
    this.auth.islogin(this.login, this.senha)
      .then(() => {
        this.invalidLogin = false;
        this.loginSuccess = true;
        this.successMessage = 'Login Successful.';
        this.router.navigate(['/inicio']);
      })
      .catch(erro => {
        this.invalidLogin = true;
        this.loginSuccess = false;
        console.log(erro);
      });
  }

  ngOnInit() {
  }
}
