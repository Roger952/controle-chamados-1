import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { AtendenteComponent } from './atendente/atendente.component';
import { EmpresaComponent } from './empresa/empresa.component';
import { AuthGuard } from './seguranca/auth.guard';


const routes: Routes = [
  { path: 'login-admin', component: LoginAdminComponent},
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  { path: 'produtos', component: ProdutosComponent, canActivate: [AuthGuard] },
  { path: 'atendente', component: AtendenteComponent, canActivate:[AuthGuard] },
  { path: 'empresa', component: EmpresaComponent, canActivate:[AuthGuard] },
  { path: '', redirectTo: '/login-admin', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
  ]
})
export class AppRoutingModule { }
