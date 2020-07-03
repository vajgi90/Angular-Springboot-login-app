import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { UserRegister } from '../model/userregister';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  startDate = new Date(1990, 0, 1);

  showSpinner = false;

  errorMessage: any = '';

  subs: Subscription;

  // reactive approach
  signupForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private dialog: MatDialog) {}

  ngOnInit() {
    this.signupForm = new FormGroup({
      email: new FormControl(null, Validators.required && Validators.email),
      password: new FormControl(null, Validators.required),
      firstName: new FormControl(null, Validators.required),
      lastName: new FormControl(null, Validators.required),
      birthdate: new FormControl(null, Validators.required)
    });
  }

  onSubmit() {
    const user = new UserRegister(
      this.signupForm.value.email,
      this.signupForm.value.password,
      this.signupForm.value.firstName,
      this.signupForm.value.lastName,
      this.signupForm.value.birthdate
    );
    console.log(user);
    this.subs = this.authService.signUp(user).subscribe(
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
    this.signupForm.reset();
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
