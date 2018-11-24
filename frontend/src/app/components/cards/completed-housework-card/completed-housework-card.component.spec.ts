import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedHouseworkCardComponent } from './completed-housework-card.component';

describe('CompletedHouseworkCardComponent', () => {
  let component: CompletedHouseworkCardComponent;
  let fixture: ComponentFixture<CompletedHouseworkCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletedHouseworkCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletedHouseworkCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
