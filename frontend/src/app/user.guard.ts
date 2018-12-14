import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth.service';

@Injectable()
export class UserGuard implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      
    if (!this.authService.isLoggedIn || ( !this.authService.getUser.role['user'] && !this.authService.getUser.role['owner'])) {
      this.router.navigateByUrl('/');
    }

    return this.authService.isLoggedIn;
  }
}
