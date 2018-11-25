import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-small-housework-card',
  templateUrl: './small-housework-card.component.html',
  styleUrls: ['./small-housework-card.component.sass']
})
export class SmallHouseworkCardComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() value: number | string;

  ngOnInit() {
  }

}
