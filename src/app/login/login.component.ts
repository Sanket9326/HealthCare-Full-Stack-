import { HttpClient } from '@angular/common/http';
import { HostBinding } from '@angular/core';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'console';
import { response } from 'express';
import { CredentialsServiceService } from '../services/credentials-service.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  userBaseUrl: string = 'http://localhost:8080/user/login'
  adminBaseUrl: string = 'http://localhost:8080/admin/login'


  constructor(private http: HttpClient, private router: Router, private credentialsService: CredentialsServiceService) { }

  userLogin(username: string, password: string) {
    this.userBaseUrl = this.userBaseUrl + `?username=${username}&password=${password}`;
    this.http.get(this.userBaseUrl, { responseType: 'text' }).subscribe(
      (response) => {
        console.log(response);
        this.credentialsService.setCredentials(username, password);
        this.router.navigate(['/user'])
      },
      (error) => {
        console.log(error);
      }
    );
  }
  adminLogin(username: string, password: string) {
    this.adminBaseUrl = this.adminBaseUrl + `?username=${username}&password=${password}`;
    this.http.get(this.adminBaseUrl, { responseType: 'text' }).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/admin'])
      },
      (error) => {
        console.log(error);
      }
    );
  }

  forgotPassword() {
    this.router.navigate(['/forgotPass']);
  }

}
