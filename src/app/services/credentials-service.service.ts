import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CredentialsServiceService {

  constructor() { }

  username: string = '';
  password: string = '';

  setCredentials(username: string, password: string) {
    this.username = username;
    this.password = password;
  }

  getCredentials() {
    return { username: this.username, password: this.password };
  }

}
