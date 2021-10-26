import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Flyover } from '../flyover.model';
import { LocationData } from '../location.model';
import { LocatorService } from '../locator.service';

@Component({
  selector: 'app-flyover-list',
  templateUrl: './flyover-list.component.html',
  styleUrls: ['./flyover-list.component.css'],
})
export class FlyoverListComponent implements OnInit, OnDestroy {
  flyovers: Flyover[] = [];
  location: LocationData;

  flyoverSub: Subscription;
  chosenLocation: Subscription;

  constructor(private locatorService: LocatorService) {}

  ngOnInit(): void {
    this.flyoverSub = this.locatorService.flyovers.subscribe((flyovers) => {
      this.flyovers = flyovers;
    });
    this.chosenLocation = this.locatorService.chosenLocation.subscribe(
      (location) => {
        this.location = location;
      }
    );
  }

  ngOnDestroy(): void {
    this.flyoverSub.unsubscribe();
  }

  onFlyoverChosen(flyover: Flyover) {
    this.locatorService.getMarkers(
      this.location,
      flyover.startAzimuth,
      flyover.endAzimuth
    );
  }
}
