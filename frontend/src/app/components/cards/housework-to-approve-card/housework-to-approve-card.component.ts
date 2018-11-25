import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-housework-to-approve-card',
  templateUrl: './housework-to-approve-card.component.html',
  styleUrls: ['./housework-to-approve-card.component.sass']
})
export class HouseworkToApproveCardComponent implements OnInit {

  constructor() { }

  @Input() name: string;
  @Input() title: string;

  ngOnInit() {
  }

}
