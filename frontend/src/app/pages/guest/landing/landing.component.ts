import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../../models/user.model';
import { AuthService } from '../../../auth.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.sass']
})
export class LandingComponent implements OnInit {

  constructor(private authService: AuthService,
              private router: Router) { }

  async ngOnInit() {
    const user: User = await this.authService.getUser(localStorage.getItem('user'));

    if(user) {
      if(user.role == 'ROLE_ADMIN') this.router.navigate(['/housework-manager']);
      else if(user.role == 'ROLE_USER') this.router.navigate(['/housework-browser']);
    }
  }

}
