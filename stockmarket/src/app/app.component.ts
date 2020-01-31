import { Component, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { SidenavService } from './service/sidenav.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'stockmarket';
  public onSideNavChange: boolean;

  constructor(private authService: AuthService, private sideNavService: SidenavService) {
    this.sideNavService.sideNavState$.subscribe( res => {
      console.log(res);
      this.onSideNavChange = res;
    });
  }
  ngOnInit() {
    this.authService.autoLogin();
  }
}
