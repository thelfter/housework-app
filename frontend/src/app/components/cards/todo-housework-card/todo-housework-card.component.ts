import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-todo-housework-card',
  templateUrl: './todo-housework-card.component.html',
  styleUrls: ['./todo-housework-card.component.sass']
})
export class TodoHouseworkCardComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() value: number;

  @Output() complete: EventEmitter<any> = new EventEmitter();
  @Output() refuse: EventEmitter<any> = new EventEmitter();

  private completeTask() {
    this.complete.emit();
  }

  private refuseTask() {
    this.refuse.emit();
  }

  ngOnInit() {
  }

}
