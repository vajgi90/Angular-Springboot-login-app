import { Injectable } from "@angular/core";
import {
  HttpClient,
  HttpHeaders,
  HttpParams,
  HttpErrorResponse
} from "@angular/common/http";
import { catchError, tap } from "rxjs/operators";
import { BehaviorSubject, throwError } from "rxjs";
import { AuthToken } from '../model/authtoken';
import { Router } from '@angular/router';

export interface AuthResponseData {
  access_token: string;
  token_type: string;
  refresh_token: string;
  expires_in: number;
  scope: string;
  jti: string;
}

export interface AuthRegisterData {
  username: string;
  role: string;
}

@Injectable({ providedIn: "root" })
export class AuthService {
  token = new BehaviorSubject<AuthToken>(null);

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string) {
    const headers = new HttpHeaders({
      "Content-Type": "application/x-www-form-urlencoded",
      Authorization: "Basic " + btoa("fooClientIdPassword:secret")
    });

    const body = new HttpParams()
      .set("grant_type", "password")
      .set("username", username)
      .set("password", password);

    return this.http.post<AuthResponseData>(
      "http://localhost:8080/oauth/token",
      body,
      { headers: headers }
    )
    .pipe(
      catchError(this.handleError),
      tap(resData => this.getToken(resData))
    );
  }

  public signUp(username: string, password: string) {
    const headers = new HttpHeaders({
      "Content-Type": "application/json"
    });
    const body = {
      username: username,
      password: password,
      role: "USER"
    };
    return this.http
      .post<AuthRegisterData>("http://localhost:8080/api/users", body, {
        headers: headers
      })
      .pipe(catchError(this.handleError));
  }

  logout() {
    this.token.next(null);
    this.router.navigate(['/login']);

  }

  private getToken(authResponse: AuthResponseData) {
    const token = new AuthToken(
      authResponse.access_token, authResponse.token_type,
      authResponse.refresh_token, authResponse.expires_in,
      authResponse.scope, authResponse.jti);
    this.token.next(token);
  }

  private handleError(errorResponse: HttpErrorResponse) {
    let errorMessage = 'An error occured';
    console.log(errorResponse);
    return throwError(errorMessage);
  }
}
