import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseworkBrowserComponent } from './housework-browser.component';

describe('HouseworkBrowserComponent', () => {
  let component: HouseworkBrowserComponent;
  let fixture: ComponentFixture<HouseworkBrowserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HouseworkBrowserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HouseworkBrowserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
