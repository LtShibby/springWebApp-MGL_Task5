import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultReviewComponent } from './result-review.component';

describe('ResultReviewComponent', () => {
  let component: ResultReviewComponent;
  let fixture: ComponentFixture<ResultReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ResultReviewComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
