import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-forgot-pass',
  standalone: true,
  imports: [NgIf],
  templateUrl: './forgot-pass.component.html',
  styleUrl: './forgot-pass.component.css'
})
export class ForgotPassComponent {

  flag : boolean = false;
  
  constructor(private http: HttpClient, private router: Router) { }

  getPassword(email: string ) {
    this.http.get(`http://localhost:8080/mail/password?email=${email}`, { responseType: 'text' }).subscribe(
      (response) => {
        console.log(response);
        this.flag = true;
        this.router.navigate(['/login']);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
