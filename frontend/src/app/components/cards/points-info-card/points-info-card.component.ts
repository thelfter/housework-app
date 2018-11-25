import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-points-info-card',
  templateUrl: './points-info-card.component.html',
  styleUrls: ['./points-info-card.component.sass']
})
export class PointsInfoCardComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() value: string | number;

  ngOnInit() {
  }

}
