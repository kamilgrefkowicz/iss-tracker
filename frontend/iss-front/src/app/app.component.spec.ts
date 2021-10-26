import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DebugElement } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { AppComponent } from './app.component';
import { Flyover } from './flyover.model';
import { LocationData } from './location.model';
import { LocatorService } from './locator.service';

class MockLocatorService {
  flyovers: BehaviorSubject<Flyover[]> = new BehaviorSubject<Flyover[]>([]);
  chosenLocation: BehaviorSubject<any> = new BehaviorSubject<any>(null);
}

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let debugElement: DebugElement;
  let locatorService: LocatorService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AppComponent],
      providers: [{ provide: LocatorService, useClass: MockLocatorService }],
    }).compileComponents();
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    debugElement = fixture.debugElement;
    locatorService = TestBed.inject(LocatorService);

    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should start on wizard index 0', () => {
    expect(component.selectedIndex).toBe(0);
  });

  it('should start with wizard index 1 and 2 disabled', () => {
    const tabIndex1 = debugElement.query(By.css('#index1'));
    const tabIndex2 = debugElement.query(By.css('#index2'));

    expect(tabIndex1.properties.disabled).toBeTruthy();
    expect(tabIndex2.properties.disabled).toBeTruthy();
  });

  it('should move to wizard index 1 once a location is chosen', () => {
    const tabIndex1 = debugElement.query(By.css('#index1'));
    component.ngOnInit();

    locatorService.chosenLocation.next(new LocationData());

    expect(tabIndex1.properties.disabled).toBeFalsy();
  });
});
