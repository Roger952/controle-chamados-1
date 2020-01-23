import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


@Injectable({
    providedIn: 'root'
})
export class AuthGuard2 implements CanActivate {

    constructor(
        private authService: AuthService,
        private router: Router

    ) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

        if (!this.authService.isAccessTokenInvalido()) {

            this.router.navigate(['/inicio']);
            return false;
        }

        return true;
    }

}