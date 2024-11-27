import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { error } from 'console';


@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  userBaseUrl: string = 'http://localhost:8080/user/signup'

  constructor(private router: Router, private http: HttpClient) { }

  onClick(fname: string, lname: string, mob: string, username: string, password: string) {
    const data = { 'firstName': fname, 'lastName': lname, 'mobileNumber': mob, 'email': username, 'password': password };

    this.http.put(this.userBaseUrl, data , {responseType : 'text'}).subscribe(
      (response) => {
        console.log("sucess");
        this.router.navigate(['/home']);
      },
      (error) => {
        console.log(error);
      }
    );

  }

}
