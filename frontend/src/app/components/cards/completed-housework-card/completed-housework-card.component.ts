import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-completed-housework-card',
  templateUrl: './completed-housework-card.component.html',
  styleUrls: ['./completed-housework-card.component.sass']
})
export class CompletedHouseworkCardComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() value: number;

  ngOnInit() {
  }

}
