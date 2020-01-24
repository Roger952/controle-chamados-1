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
import { AuthService } from './seguranca/auth.service';
import { ColaboradorComponent } from './colaborador/colaborador.component';
import { AuthGuard2 } from './seguranca/auth.guard2';
import { ChamadoComponent } from './chamado/chamado.component';
import { NaoAutorizadoComponent } from './page-not-found/nao-autorizado-component';

const routes: Routes = [
  { path: 'login-admin', component: LoginAdminComponent, canActivate: [AuthGuard2]},
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  { path: 'produtos', component: ProdutosComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO']} },
  { path: 'atendente', component: AtendenteComponent, canActivate:[AuthGuard], data: { roles: ['ROLE_CADASTRAR_ATENDENTE']} },
  { path: 'empresa', component: EmpresaComponent, canActivate:[AuthGuard], data: { roles: ['ROLE_CADASTRAR_EMPRESA']} },
  { path: 'colaborador', component: ColaboradorComponent, canActivate:[AuthGuard], data: { roles: ['ROLE_CADASTRAR_COLABORADOR']} },
  { path: 'modulo', component: ModuloComponent, canActivate:[AuthGuard], data: { roles: ['ROLE_CADASTRAR_MODULO']} },
  { path: 'inicio', component: InicioComponent, canActivate:[AuthGuard] },
  { path: 'chamado', component: ChamadoComponent, canActivate:[AuthGuard], data: { roles: ['ROLE_LISTAR_CHAMADOS']}  },

  { path: '', redirectTo: '/login-admin', pathMatch: 'full'},
  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
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
