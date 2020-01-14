import { Component, OnInit, NgModule } from '@angular/core';
import { LogoutService } from '../seguranca/logout.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  constructor(
    private logoutService : LogoutService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  logout() {
    this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login-admin']);
      })
      .catch(erro => console.error(erro));
  }
}
