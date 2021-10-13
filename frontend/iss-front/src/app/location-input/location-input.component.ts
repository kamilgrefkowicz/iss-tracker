import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LocationData } from '../location.model';
import { LocatorService } from '../locator.service';

@Component({
  selector: 'app-location-input',
  templateUrl: './location-input.component.html',
  styleUrls: ['./location-input.component.css'],
})
export class LocationInputComponent implements OnInit {
  lat: number = 0;
  lon: number = 0;

  latLonForm: FormGroup;
  LAT_VALIDATORS = [
    Validators.required,
    Validators.min(-90),
    Validators.max(90),
  ];
  LON_VALIDATORS = [
    Validators.required,
    Validators.min(-180),
    Validators.max(180),
  ];

  constructor(private locatorService: LocatorService) {}

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.latLonForm = new FormGroup({
      latitude: new FormControl(this.lat, this.LAT_VALIDATORS),
      longitude: new FormControl(this.lon, this.LON_VALIDATORS),
    });
  }

  onSubmit() {
    this.locatorService
      .findFlyovers(<LocationData>this.latLonForm.value)
      .subscribe(() => {});
  }

  onGetLocationClick() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = +position.coords.latitude.toFixed(3);
        this.lon = +position.coords.longitude.toFixed(3);
        this.initForm();
      });
    } else {
      alert('nope');
    }
  }
}
