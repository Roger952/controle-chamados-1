import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard /*implements CanActivate*/ {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  
  ) {}

  
canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | boolean {

    if(this.authenticationService.LoggedUser()){
    return true;
    }

    this.router.navigate(['/login-admin']);

    return false;
  }
  
}
