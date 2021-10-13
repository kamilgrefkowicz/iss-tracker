import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlyoverListComponent } from './flyover-list.component';

describe('FlyoverListComponent', () => {
  let component: FlyoverListComponent;
  let fixture: ComponentFixture<FlyoverListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlyoverListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlyoverListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
