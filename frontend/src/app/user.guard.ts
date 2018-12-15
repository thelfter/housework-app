import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth.service';
import { User } from './models/user.model';

@Injectable()
export class UserGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  async canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean> {

    const user: User = await this.authService.getUser(window.localStorage.getItem('user'));

    if (!this.authService.isLoggedIn || (user.role != 'ROLE_ADMIN' && user.role != 'ROLE_USER')) {
      this.router.navigateByUrl('/');
    }

    return this.authService.isLoggedIn;
  }
}
