import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementBoard } from './management-board';

describe('ManagementBoard', () => {
  let component: ManagementBoard;
  let fixture: ComponentFixture<ManagementBoard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagementBoard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManagementBoard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
