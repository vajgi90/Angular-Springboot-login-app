import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';
import { UserUpdate } from '../model/userupdate';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {
  constructor(private authService: AuthService) {}
  user: UserUpdate = {} as any;

  email: string;
  firstName: string;
  password: string;
  lastName: string;
  birthdate: Date;
  budget: number;
  errorMessage: any;

  private firstObsSubscription: Subscription;
  private secondObsSubsription: Subscription;

  ngOnInit() {
    this.firstObsSubscription = this.authService.getUser().subscribe(data => {
      this.email = data.username;
      this.firstName = data.firstName;
      this.lastName = data.lastName;
      this.birthdate = data.birthdate;
      this.budget = data.budget;
    });
    console.log(this.firstObsSubscription);
  }

  onSubmit(form: NgForm) {
    this.user.username = form.value.email;
    this.user.password = form.value.password;
    this.user.firstName = form.value.firstName;
    this.user.lastName = form.value.lastName;
    this.user.birthdate = form.value.birthdate;
    console.log(this.user);
    this.secondObsSubsription = this.authService
      .updateUser(this.user)
      .subscribe(
        resData => {
          console.log(resData);
        },
        error => {
          console.log(this.errorMessage);
        }
      );
    //this.secondObsSubsription.unsubscribe();
  }

  ngOnDestroy() {
    this.firstObsSubscription.unsubscribe();
    //this.secondObsSubsription.unsubscribe();
  }
}
