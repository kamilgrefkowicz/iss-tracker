import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Flyover } from '../flyover.model';
import { LocatorService } from '../locator.service';

@Component({
  selector: 'app-flyover-list',
  templateUrl: './flyover-list.component.html',
  styleUrls: ['./flyover-list.component.css'],
})
export class FlyoverListComponent implements OnInit, OnDestroy {
  flyovers: Flyover[] = [];
  flyoverSub: Subscription;

  constructor(private locatorService: LocatorService) {}

  ngOnInit(): void {
    this.flyoverSub = this.locatorService.flyovers.subscribe((flyovers) => {
      this.flyovers = flyovers;
    });
  }

  ngOnDestroy(): void {
    this.flyoverSub.unsubscribe();
  }
}
