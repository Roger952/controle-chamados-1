import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  oauthTokenUrl = 'http://localhost:8080/oauth/token';
  public login: String;
  public senha: String;
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  constructor() {

  }


  islogin(login: string, senha: string){
    const headers = new HttpHeaders({
    'Content-Type': 'application/x-www-form-urlencoded',
    'Authorization': 'Basic YW5ndWxhcjpAbmd1bEByMA=='});

    const body = `username=${login}&password=${senha}&grant_type=password`;
        
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
    return this.islogin;
  }
}
