import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-housework-list-item',
  templateUrl: './housework-list-item.component.html',
  styleUrls: ['./housework-list-item.component.sass']
})
export class HouseworkListItemComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() points: number;
  @Input() path: string;

  ngOnInit() {
  }

}
