import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrentDraftComponent } from './current-draft.component';

describe('CurrentDraftComponent', () => {
  let component: CurrentDraftComponent;
  let fixture: ComponentFixture<CurrentDraftComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CurrentDraftComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrentDraftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
