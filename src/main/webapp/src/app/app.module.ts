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
//import { LogoutComponent } from './login-admin/logout.component';
import { AuthService } from './seguranca/auth.service';


import { HttpInterceptorService } from './seguranca/httpInterceptor.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { EmpresaListComponent } from './empresa-list/empresa-list.component';
import { InicioComponent } from './inicio/inicio.component';

import { AuthGuard } from './seguranca/auth.guard';


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
    InicioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgxMaskModule.forRoot(),
    BrowserAnimationsModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [
    AuthGuard,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }