import { Component, OnInit, NgModule } from '@angular/core';
import { LogoutService } from '../seguranca/logout.service';
import { Router } from '@angular/router';
import { AuthService } from '../seguranca/auth.service';
import { ControleHttp } from '../seguranca/Controle-http';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  usuarioAutenticado = '';

  constructor(
    private logoutService: LogoutService,
    private router: Router,
    private auth: AuthService,
    private http: ControleHttp,
  ) {
  }

  ngOnInit() {
    this.usuarioAutenticado = sessionStorage.getItem('loggedUser');
  }

  tokenInicio() {
    if (this.auth.isAccessTokenInvalido()) {
      console.log('Access token inválido. Obtendo novo token...');

      const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
        .then(() => {
          this.router.navigate(['/inicio']);
        })
        .catch(erro => console.error(erro));
    }
    this.router.navigate(['/inicio']);
  }

  tokenEmpresa() {
    if (this.auth.isAccessTokenInvalido()) {
      console.log('Access token inválido. Obtendo novo token...');

      const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
        .then(() => {
          this.router.navigate(['/empresa']);
        })
        .catch(erro => console.error(erro));
    }
    this.router.navigate(['/empresa']);
  }

  tokenAtendente() {
    if (this.auth.isAccessTokenInvalido()) {
      console.log('Access token inválido. Obtendo novo token...');

      const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
        .then(() => {
          this.router.navigate(['/atendente']);
        })
        .catch(erro => console.error(erro));
    }
    this.router.navigate(['/atendente']);
  }

  tokenProduto() {
    if (this.auth.isAccessTokenInvalido()) {
      console.log('Access token inválido. Obtendo novo token...');

      const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
        .then(() => {
          this.router.navigate(['/produtos']);
        })
        .catch(erro => console.error(erro));
    }
    this.router.navigate(['/produtos']);
  }

   tokenColaborador() {
      if (this.auth.isAccessTokenInvalido()) {
        console.log('Access token inválido. Obtendo novo token...');

        const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
          .then(() => {
            this.router.navigate(['/colaborador']);
          })
          .catch(erro => console.error(erro));
      }
      this.router.navigate(['/colaborador']);
    }

  tokenModulo() {
    if (this.auth.isAccessTokenInvalido()) {
      console.log('Access token inválido. Obtendo novo token...');

      const chamadaNovoAccessToken = this.auth.obterNovoAccessToken()
        .then(() => {
          this.router.navigate(['/modulo']);
        })
        .catch(erro => console.error(erro));
    }
    this.router.navigate(['/modulo']);
  }

  logout() {
    this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login-admin']);
      })
      .catch(erro => console.error(erro));
  }
}
