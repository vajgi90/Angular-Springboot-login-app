import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { AuthService } from "../service/auth.service";
import { Router } from "@angular/router";
import { UserRegister } from "../model/userregister";
import { MatDialog, MatDialogConfig } from '@angular/material';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {

  startDate = new Date(1990, 0, 1);

  showSpinner = false;

  errorMessage: any = '';

  constructor(private authService: AuthService, private router: Router, private dialog: MatDialog) {}

  ngOnInit() {}

  onSubmit(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;
    const firstName = form.value.firstName;
    const lastName = form.value.lastName;
    const birthdate = form.value.birthdate;
    const user = new UserRegister(
      email,
      password,
      firstName,
      lastName,
      birthdate
    );
    console.log(user);
    this.authService.signUp(user).subscribe(
      resData => {
        console.log(resData);
        this.dialog.closeAll();
        this.router.navigate(['./']);
      },
      error => {
        console.log(this.errorMessage);
        this.handleError(error);
      }
    );
    form.reset();
  }

  handleError(error: any) {
    return (error.status === 400) ? this.errorMessage = 'This username has been registered yet!' : '';
  }

  displayLoadingIndicator() {
    this.showSpinner = true;
    setTimeout(() => {
      this.showSpinner = false;
    }, 5000);
  }
}
