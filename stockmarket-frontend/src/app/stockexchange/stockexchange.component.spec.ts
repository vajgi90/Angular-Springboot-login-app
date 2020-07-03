import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockexchangeComponent } from './stockexchange.component';

describe('StockexchangeComponent', () => {
  let component: StockexchangeComponent;
  let fixture: ComponentFixture<StockexchangeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockexchangeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockexchangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
