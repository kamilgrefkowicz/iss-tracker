import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { LocatorService } from './locator.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class AppComponent implements OnInit {
  isLocationChosen: boolean = false;
  isFlyoverChosen: boolean = false;
  selectedIndex: number = 2;

  constructor(private locatorService: LocatorService) {}

  ngOnInit() {
    this.locatorService.chosenLocation.subscribe((location) => {
      if (location) {
        this.isLocationChosen = true;
        this.selectedIndex = 1;
      }
    });
  }

  changeIndex(event: any) {
    this.selectedIndex = event;
  }
}
