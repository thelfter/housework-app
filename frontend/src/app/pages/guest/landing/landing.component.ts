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

  ngOnInit() {
    const user: User = this.authService.getUser;

    if(user) {
      if(user.role['owner']) this.router.navigate(['/housework-manager']);
      else if(user.role['user']) this.router.navigate(['/housework-browser']);
    }
  }

}
