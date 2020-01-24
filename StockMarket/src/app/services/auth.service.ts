import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _registerUrl = "http://localhost:8080/register";
  private _loginUrl = "http://localhost:8080/oauth/token";


  item: any;

  constructor(private http: HttpClient) { }


  public getUser(user: { username: string, password: string }): Observable<Object> {
    let req = new HttpParams();
    req = req.set('username', user.username);
    req = req.set('password', user.password);
    req = req.set('grant_type', 'password');
    return this.http.post<any>(this._loginUrl, req, {
      headers: new HttpHeaders({
        'Authorization' : `Basic ${btoa('fooClientIdPassword:secret')}`,
        'Content-Type': 'application/x-www-form-urlencoded'
      })
    })
  }

  registerUser(user: Object): Observable<Object> {
    return this.http.post<any>(this._registerUrl, user);
  }

  public getUserByEmail(email: string) {
    return this.http.get("http://localhost:8080/user/" + email);
  }

  public updateUserPassword(userUpdate: any): Observable<any> {
    return this.http.put("http://localhost:8080/user", userUpdate)

  }

  public deleteUserByEmail(email: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8080/user/" + email);
  }

}


