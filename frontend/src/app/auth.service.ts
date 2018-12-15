import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { User } from './models/user.model';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {
  private user: User;

  constructor(
    private httpService: HttpService,
    private router: Router
  ) {
    /*this.user.id = 1;
    this.user.name = 'My Name';
    this.user.username = 'myusername';
    this.user.scoreActual = 12;
    this.user.scoreSum = 98;
    this.user.role = {
      owner: true
    };*/
  }

  public async getUser(username: string) {
    const user: User = await this.httpService.post('login', username) as User;
    this.user = user;
    return user;
  }

  get isLoggedIn() {
    const token = window.localStorage.getItem('token');
    if (token) {
      return true;
    }
    return false;
  }

  public async login(username: string, password: string): Promise<User> {
    // return true;

    try {
      const token = btoa(username + ':' + password);
      window.localStorage.setItem('token', token);
      
      this.user = await this.httpService.post('login', username) as User;
      window.localStorage.setItem('user', this.user.username);

      return Promise.resolve(this.user);
    } catch (e) {
      window.localStorage.removeItem('token');
      window.localStorage.removeItem('user');
      console.log(e);
      //return Promise.reject(console.error(e));
    }
  }

  public async setUser(){
    const username: string = window.localStorage.getItem('user');
    if(username) {
      this.user = await this.httpService.post('login', username) as User;
    }
  }

  public logout() {
    this.user = null;
    window.localStorage.removeItem('token');
    window.localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }

  public loginWithToken() {
    const token = window.localStorage.getItem('token');
    const [username, password] = atob(token).split(':');
    this.login(username, password);
  }

  private checkRole(role: string): boolean {
    if(!this.user) return false;

    if(this.user.role == role) return true;

    return false;
  }

}
