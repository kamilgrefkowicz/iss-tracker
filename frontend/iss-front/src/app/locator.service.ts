import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LocationData } from './location.model';
import { tap } from 'rxjs/operators';
import { Flyover } from './flyover.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({ providedIn: 'root' })
export class LocatorService {
  URL_GET_MANY = 'http://localhost:8080/get-flyovers';
  URL_GET_MARKERS = 'http://localhost:8080/get-markers';

  flyovers: BehaviorSubject<Flyover[]> = new BehaviorSubject<Flyover[]>([]);
  chosenLocation: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private http: HttpClient) {}

  findFlyovers(location: LocationData) {
    let params = new HttpParams()
      .set('latitude', location.latitude)
      .set('longitude', location.longitude);

    return this.http.get<Flyover[]>(this.URL_GET_MANY, { params }).pipe(
      tap((result) => {
        this.flyovers.next(result);
        this.chosenLocation.next(location);

        // console.log(result);
      })
    );
  }

  getMarkers(
    currentPosition: LocationData,
    startAzimuth: number,
    endAzimuth: number
  ) {
    const params = new HttpParams()
      .set('latitude', currentPosition.latitude)
      .set('longitude', currentPosition.longitude)
      .set('startAzimuth', startAzimuth)
      .set('endAzimuth', endAzimuth);

    this.http
      .get<LocationData[]>(this.URL_GET_MARKERS, { params })
      .subscribe((result) => {
        // console.log(result);
      });
  }
}
