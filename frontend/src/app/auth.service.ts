import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { User } from './models/user.model';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {
  private loggedInStatus = false;
  private user: User = new User();

  constructor(
    private httpService: HttpService,
    private router: Router
  ) {
    this.user.id = 1;
    this.user.name = 'My Name';
    this.user.username = 'myusername';
    this.user.scoreActual = 12;
    this.user.scoreSum = 98;
  }

  get isLoggedIn() {
    return this.loggedInStatus;
  }

  public async login(username: string, password: string): Promise<User> {
    // return true;
    try {
      const token = btoa(username + ':' + password);
      window.localStorage.setItem('token', token);
      // this.user = await this.httpService.post('users/login', username) as User;
      this.loggedInStatus = true;

      return Promise.resolve(this.user);
    } catch (e) {
      window.localStorage.setItem('token', '');
      return Promise.reject(console.log(e));
    }
  }

  public logout() {
    this.loggedInStatus = false;
    this.user = null;
    window.localStorage.setItem('token', '');
    this.router.navigate(['/login']);
  }

}
