import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-todo-housework-card',
  templateUrl: './todo-housework-card.component.html',
  styleUrls: ['./todo-housework-card.component.sass']
})
export class TodoHouseworkCardComponent implements OnInit {

  constructor() { }

  @Input() title: string;
  @Input() value: number;

  ngOnInit() {
  }

}
