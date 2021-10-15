import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'app-flyover-single',
  templateUrl: './flyover-single.component.html',
  styleUrls: ['./flyover-single.component.css'],
})
export class FlyoverSingleComponent implements OnInit, AfterViewInit {
  @ViewChild('map') mapElement: ElementRef;

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    this.initMap();
  }

  initMap() {
    let map;
    let panorama;

    const loc = new google.maps.LatLng(54.516, 18.54);

    map = new google.maps.Map(this.mapElement.nativeElement, {
      center: loc,
      zoom: 15,
    });

    panorama = map.getStreetView();
    panorama.setPosition(loc);
    panorama.setVisible(true);
  }
}
