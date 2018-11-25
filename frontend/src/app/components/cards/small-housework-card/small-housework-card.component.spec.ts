import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SmallHouseworkCardComponent } from './small-housework-card.component';

describe('SmallHouseworkCardComponent', () => {
  let component: SmallHouseworkCardComponent;
  let fixture: ComponentFixture<SmallHouseworkCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SmallHouseworkCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SmallHouseworkCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
