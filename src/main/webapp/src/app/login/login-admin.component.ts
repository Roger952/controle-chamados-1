import { Component, OnInit } from '@angular/core';
import { AuthenticationService} from './auth.service';
import { Router  } from '@angular/router';

@Component({
selector: 'app-login-admin',
templateUrl: './login-admin.component.html',
styleUrls: ['./login-admin.component.css']
})

export class LoginAdminComponent implements OnInit {

login: string;
senha: string;
errorMessage = 'Usuário e/ou Senha inválidos!';
successMessage: string;
invalidLogin = false;
loginSuccess = false;

constructor(
    private router: Router,
    private authenticationService: AuthenticationService) {   }

  ngOnInit() {

  }

  handleLogin() {
    this.authenticationService.islogin(this.login, this.senha); {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/inicio']);
    }
  }
}
