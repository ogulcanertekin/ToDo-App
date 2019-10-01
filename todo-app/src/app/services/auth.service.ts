import { Injectable } from '@angular/core';
import { LoginUser } from "../models/loginUser";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { AlertifyService } from "./alertify.service";
import { RegisterUser } from "../models/registerUser";
import { JwtHelper, tokenNotExpired } from 'angular2-jwt'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private alertifyService: AlertifyService
  ) { }

  path = "http://localhost:8080/users";
  userToken: any;
  userId: any;
  decodedToken: any;
  jwtHelper: JwtHelper = new JwtHelper();
  TOKEN_KEY = "token"

  login(loginUser: LoginUser) {
    console.log(loginUser)
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json");
    this.httpClient
      .post(this.path + "/login", loginUser, { headers: headers, responseType: 'text', observe: 'response' })
      .subscribe(data => {
        const token = data.headers.get('authorization');
        const userId = data.headers.get('userid');
        console.log(token);
        this.saveToken(token, userId);
        this.userToken = token;
        this.userId = userId;
        this.decodedToken = this.jwtHelper.decodeToken(token.toString());
        this.alertifyService.success("Log-in Succesfull");
        this.router.navigateByUrl("/");
      });
  }

  register(registerUser: RegisterUser) {
    let headers = new HttpHeaders();
    console.log(registerUser);
    headers = headers.append("Content-Type", "application/json");
    this.httpClient
      .post(this.path, registerUser, { headers: headers })
      .subscribe(data => {
        this.alertifyService.success("Register Succesfull");
        this.router.navigateByUrl("/");
      });
  }

  saveToken(token, userId) {
    console.log(token);
    localStorage.setItem(this.TOKEN_KEY, token);
    localStorage.setItem("userId", userId);
  }

  logOut() {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem("userId");
    this.alertifyService.error("Logged out !");
  }

  loggedIn() {
    return tokenNotExpired(this.TOKEN_KEY)
  }

  get token() {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getCurrentUserId() {
    return localStorage.getItem("userId");
  }
}

