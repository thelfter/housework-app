import { Component, OnInit } from '@angular/core';
import { NavigationEnd, RouterEvent, Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.sass']
})
export class FooterComponent implements OnInit {

  constructor(private router: Router) {}

  public currentUrl: string;

  ngOnInit() {
    this.router.events.subscribe((event: RouterEvent) => {
      if (event instanceof NavigationEnd ) {
        this.currentUrl = event.url;
      }
    });
  }

}
