import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './auth.service';

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

    constructor(private authService: AuthenticationService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.authService.isUserLoggedIn() && req.url.indexOf('/oauth/token') === -1) {
            const authReq = req.clone({
                headers: new HttpHeaders({
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'Authorization': 'Basic YW5ndWxhcjpAbmd1bEByMA=='
                    
                })
            });
            return next.handle(authReq);
        } else {
            return next.handle(req);
        }
    }
}