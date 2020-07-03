import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isAuthenticated = false;
  private userSub: Subscription;
  now: number;

  constructor(private authService: AuthService, private router: Router) { 
    setInterval(() => {
      this.now = Date.now();
    }, 1);
  }

  ngOnInit() {
    this.userSub = this.authService.token.subscribe(token => {
      this.isAuthenticated = !!token;
    }
      );
  }

  // tslint:disable-next-line: use-lifecycle-interface
  ngOnDestroy() {
    this.userSub.unsubscribe();
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
