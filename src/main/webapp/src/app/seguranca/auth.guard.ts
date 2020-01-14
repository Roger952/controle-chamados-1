import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
providedIn: 'root'
})
export class AuthGuard implements CanActivate {

constructor(
        private authService: AuthService,
        private router: Router

    ) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

        if (this.authService.isAccessTokenInvalido()) {
            console.log('Navegação com access token inválido. Obtendo novo token...');

            return this.authService.obterNovoAccessToken().then(() => {
                if (this.authService.isAccessTokenInvalido()) {
                    this.router.navigate(['/login-admin']);
                    return false;
                }else{
                return true;
            }
            });
        }
        return true;
    }
}
