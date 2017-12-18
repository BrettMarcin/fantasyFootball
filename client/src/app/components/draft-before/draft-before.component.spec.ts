import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DraftBeforeComponent } from './draft-before.component';

describe('DraftBeforeComponent', () => {
  let component: DraftBeforeComponent;
  let fixture: ComponentFixture<DraftBeforeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DraftBeforeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DraftBeforeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
