import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorporateSpecificComponent } from './corporate-specific.component';

describe('CorporateSpecificComponent', () => {
  let component: CorporateSpecificComponent;
  let fixture: ComponentFixture<CorporateSpecificComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorporateSpecificComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorporateSpecificComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
