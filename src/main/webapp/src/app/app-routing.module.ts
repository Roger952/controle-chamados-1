import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { AtendenteComponent } from './atendente/atendente.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { InicioComponent } from './inicio/inicio.component';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthGuard } from './seguranca/auth.guard';
import { ModuloComponent } from './modulo/modulo.component';
import { ColaboradorComponent } from './colaborador/colaborador.component';
import { AuthGuard2 } from './seguranca/auth.guard2';
import { ChamadoComponent } from './chamado/chamado.component';
const routes: Routes = [
  { path: 'login-admin', component: LoginAdminComponent, canActivate: [AuthGuard2]},
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  { path: 'produtos', component: ProdutosComponent, canActivate: [AuthGuard] },
  { path: 'atendente', component: AtendenteComponent, canActivate:[AuthGuard] },
  { path: 'empresa', component: EmpresaComponent, canActivate:[AuthGuard] },
  { path: 'colaborador', component: ColaboradorComponent, canActivate:[AuthGuard] },
  { path: 'modulo', component: ModuloComponent, canActivate:[AuthGuard] },
  { path: 'inicio', component: InicioComponent, canActivate:[AuthGuard] },
  { path: 'chamado', component: ChamadoComponent, canActivate:[AuthGuard] },

  { path: '', redirectTo: '/login-admin', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    JwtHelperService
  ]
})
export class AppRoutingModule { }