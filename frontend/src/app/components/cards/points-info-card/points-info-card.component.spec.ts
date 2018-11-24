import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PointsInfoCardComponent } from './points-info-card.component';

describe('PointsInfoCardComponent', () => {
  let component: PointsInfoCardComponent;
  let fixture: ComponentFixture<PointsInfoCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PointsInfoCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PointsInfoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
