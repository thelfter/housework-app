import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewHouseworkComponent } from './new-housework.component';

describe('NewHouseworkComponent', () => {
  let component: NewHouseworkComponent;
  let fixture: ComponentFixture<NewHouseworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewHouseworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewHouseworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
