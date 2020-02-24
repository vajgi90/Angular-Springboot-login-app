import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RegisterComponent } from '../register/register.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoginMode = true;
  showSpinner = false;
  errorMessage: string;

  authObs: Observable<any>;

  constructor(private auth: AuthService, private router: Router, private dialog: MatDialog) { }

  ngOnInit() {
  }

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  handleError(error: any) {
    return (error.status === 400) ? this.errorMessage = 'Invalid email or password!' : '';
  }

  onSubmit(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;
    this.displayLoadingIndicator();
    this.auth.login(email, password).
      subscribe(
        resData => {
          console.log(resData);
          console.log(form);
          this.router.navigate(['./home']);
        },
        error => {
          this.handleError(error);
        },
      );
    form.reset();
  }


displayLoadingIndicator() {
  this.showSpinner = true;
  setTimeout(() => {
      this.showSpinner = false;
  }, 2000);
}

openDialog() {
  this.dialog.open(RegisterComponent, {
    height: '500px',
    width: '320px',
  });
}

}
