import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoHouseworkCardComponent } from './todo-housework-card.component';

describe('TodoHouseworkCardComponent', () => {
  let component: TodoHouseworkCardComponent;
  let fixture: ComponentFixture<TodoHouseworkCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodoHouseworkCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoHouseworkCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
