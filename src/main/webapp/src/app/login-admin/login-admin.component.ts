import { Component, OnInit } from '@angular/core';
import { AuthenticationService} from './auth.service';
import { Router, ActivatedRoute  } from '@angular/router';

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
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) {   }

  ngOnInit() {

  }

  handleLogin() {
    this.authenticationService.authenticationService(this.login, this.senha).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/dashboard']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });      
  }
   
}


