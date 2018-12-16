import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-housework-to-approve-card',
  templateUrl: './housework-to-approve-card.component.html',
  styleUrls: ['./housework-to-approve-card.component.sass']
})
export class HouseworkToApproveCardComponent implements OnInit {

  constructor() { }

  @Input() name: string;
  @Input() title: string;

  @Output() accept: EventEmitter<any> = new EventEmitter();
  @Output() reject: EventEmitter<any> = new EventEmitter();

  private approve() {
    this.accept.emit();
  }

  private decline() {
    this.reject.emit();
  }

  ngOnInit() {
  }

}
