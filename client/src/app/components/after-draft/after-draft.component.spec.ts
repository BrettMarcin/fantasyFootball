import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AfterDraftComponent } from './after-draft.component';

describe('AfterDraftComponent', () => {
  let component: AfterDraftComponent;
  let fixture: ComponentFixture<AfterDraftComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AfterDraftComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AfterDraftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
