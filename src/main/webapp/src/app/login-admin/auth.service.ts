import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  public login: String;
  public senha: String;
  private usuarioAutenticado: boolean = false;

  constructor(private http: HttpClient) {

  }

  authenticationService(login: String, senha: String) {
    return this.http.get(`http://localhost:8080/api/login-admin/basicauth`,
      { headers: { authorization: this.createBasicAuthToken(login, senha) } }).pipe(map((res) => {
        this.login = login;
        this.senha = senha;
        this.registerSuccessfulLogin(login, senha);
        this.usuarioAutenticado = true;
      }));
  }

  createBasicAuthToken(login: String, senha: String) {
    return 'Basic ' + window.btoa(login + ":" + senha)
  }

  registerSuccessfulLogin(login, senha) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, login)
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.login = null;
    this.senha = null;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }

  LoggedUser(){
    return this.usuarioAutenticado;
  }
}