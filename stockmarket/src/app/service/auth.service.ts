import { Injectable } from "@angular/core";
import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpErrorResponse
} from "@angular/common/http";
import { catchError, tap } from "rxjs/operators";
import { BehaviorSubject, throwError } from "rxjs";
import { AuthToken } from "../model/authtoken";
import { Router } from "@angular/router";

export interface AuthRegisterData {
  username: string;
  role: string;
}

@Injectable({ providedIn: "root" })
export class AuthService {
  token = new BehaviorSubject<AuthToken>(null);
  private tokenExpirationTime: any;
  username: string;

  constructor(private http: HttpClient, private router: Router) {}

  user(): string {
    return this.username;
  }

  login(username: string, password: string) {
    const loginUrl = 'http://localhost:8080/oauth/token';
    this.username = username;
    const headers = new HttpHeaders({
      "Content-Type": "application/x-www-form-urlencoded",
      Authorization: "Basic " + btoa("fooClientIdPassword:secret")
    });

    const body = new HttpParams()
      .set("grant_type", "password")
      .set("username", username)
      .set("password", password);

    return this.http
      .post<AuthToken>(loginUrl, body, { headers })
      .pipe(
        catchError(this.handleError),
        tap(resData => this.getToken(resData))
      );
  }

  public signUp(username: string, password: string) {
    const registerUrl = 'http://localhost:8080/api/users';
    const headers = new HttpHeaders({
      "Content-Type": "application/json"
    });
    const body = {
      username,
      password,
      role: "USER"
    };
    return this.http
      .post<AuthRegisterData>(registerUrl, body, {
        headers
      })
      .pipe(catchError(this.handleError));
  }

  autoLogin() {
    const tokenData: AuthToken = JSON.parse(localStorage.getItem("tokenData"));
    if (!tokenData) {
      return;
    }

    if (tokenData.token) {
      const expritationDuration =
        new Date().getTime() + tokenData.expires_in - new Date().getTime();
      this.token.next(tokenData);
      this.autoLogout(expritationDuration);
    }
  }

  logout() {
    this.token.next(null);
    this.router.navigate(["/login"]);
    localStorage.removeItem("tokenData");
    if (this.tokenExpirationTime) {
      clearTimeout(this.tokenExpirationTime);
    }
    this.tokenExpirationTime = null;
  }

  autoLogout(expirationDuration: number) {
    console.log(expirationDuration);
    this.tokenExpirationTime = setTimeout(() => {
      this.logout();
    }, expirationDuration);
  }

  //convert/extract token
  private getToken(token: AuthToken) {
    this.token.next(token);
    this.autoLogout(token.expires_in * 1000);
    localStorage.setItem("tokenData", JSON.stringify(token));
  }

  private handleError(errorResponse: HttpErrorResponse) {
    const errorMessage = "An error occured";
    console.log(errorResponse);
    return throwError(errorMessage);
  }
}
