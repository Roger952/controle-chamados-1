import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ProdutosComponent } from './produtos/produtos.component';
import { EmpresaComponent } from './empresa/empresa.component';


const routes: Routes = [
  { path: 'login-admin', component: LoginAdminComponent, },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'produtos', component: ProdutosComponent },
  { path: 'empresa', component: EmpresaComponent },
  { path: '', redirectTo: '/login-admin', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
