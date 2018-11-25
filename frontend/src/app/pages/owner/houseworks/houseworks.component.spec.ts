import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworksComponent } from './houseworks.component';

describe('HouseworksComponent', () => {
  let component: HouseworksComponent;
  let fixture: ComponentFixture<HouseworksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
