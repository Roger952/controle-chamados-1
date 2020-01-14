import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { LoginAdminComponent } from '../login-admin/login-admin.component';

const routes: Routes = [
  { path: 'login', component: LoginAdminComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SegurancaRoutingModule { }