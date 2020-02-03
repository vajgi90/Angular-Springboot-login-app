import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoginMode = true;
  showSpinner = false;

  authObs: Observable<any>;

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  onSubmit(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;

    if (this.isLoginMode) {
      this.displayLoadingIndicator();
      this.auth.login(email, password).
      subscribe(
        resData => {
          console.log(resData);
          console.log(form);
          this.router.navigate(['./home']);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      //if ág elé
      this.displayLoadingIndicator();
      this.auth.signUp(email, password).
      subscribe(
        resData => {
          console.log(resData);
        },
        error => {
          console.log(error);
        }
      );
    }

    this.authObs.subscribe(
      resData => {
        console.log(resData);
        this.router.navigate(['./home']);
      },
      error => {
        console.log(error);
      }
    );
    form.reset();
  }


displayLoadingIndicator() {
  this.showSpinner = true;
  setTimeout(() => {
      this.showSpinner = false;
  }, 5000);
}

}
