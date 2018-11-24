import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworkListItemComponent } from './housework-list-item.component';

describe('HouseworkListItemComponent', () => {
  let component: HouseworkListItemComponent;
  let fixture: ComponentFixture<HouseworkListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworkListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworkListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
