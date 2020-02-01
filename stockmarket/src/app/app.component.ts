import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'stockmarket';

  isAuthenticated = false;
  private userSub: Subscription;

  constructor(private authService: AuthService) {
    this.userSub = this.authService.token.subscribe(token => {
      this.isAuthenticated = !!token;
    }
      );
  }
  ngOnInit() {
    this.authService.autoLogin();
  }
}
