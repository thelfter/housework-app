import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworkDetailsComponent } from './housework-details.component';

describe('HouseworkDetailsComponent', () => {
  let component: HouseworkDetailsComponent;
  let fixture: ComponentFixture<HouseworkDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworkDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworkDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
