import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworkToApproveCardComponent } from './housework-to-approve-card.component';

describe('HouseworkToApproveCardComponent', () => {
  let component: HouseworkToApproveCardComponent;
  let fixture: ComponentFixture<HouseworkToApproveCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworkToApproveCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworkToApproveCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
