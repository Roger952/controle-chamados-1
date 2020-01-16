import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { AtendenteComponent } from './atendente/atendente.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { NgxMaskModule } from 'ngx-mask';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AuthService } from './seguranca/auth.service';
import { ControleHttp } from '../app/seguranca/Controle-http';
import {ProdutosService} from './produtos.service';

import { HttpClientModule } from '@angular/common/http';

import { EmpresaListComponent } from './empresa-list/empresa-list.component';
import { InicioComponent } from './inicio/inicio.component';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { LogoutService } from '../app/seguranca/logout.service'
import { AuthGuard } from './seguranca/auth.guard';
import { ModuloComponent } from './modulo/modulo.component';


/*export function tokenGetter() {
  return localStorage.getItem('token');
}*/

@NgModule({
  declarations: [
    AppComponent,
    LoginAdminComponent,
    DashboardComponent,
    PageNotFoundComponent,
    ProdutosComponent,
    EmpresaComponent,
    AtendenteComponent,

    EmpresaListComponent,
    InicioComponent,
    ModuloComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxMaskModule.forRoot(),
    BrowserAnimationsModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return '';
        },
}
    })
  ]
  ,
  providers: [
    AuthService,
    JwtHelperService,
    ProdutosService,
    ControleHttp,
    LogoutService,
    AuthGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
