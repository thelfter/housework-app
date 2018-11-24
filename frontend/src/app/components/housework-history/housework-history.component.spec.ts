import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworkHistoryComponent } from './housework-history.component';

describe('HouseworkHistoryComponent', () => {
  let component: HouseworkHistoryComponent;
  let fixture: ComponentFixture<HouseworkHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworkHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworkHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
