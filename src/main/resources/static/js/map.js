let panorama;

function initMap() {
    const pov = document.querySelector('#pov');



    const centerPoint = { lat: Number(pov.dataset.latitude), lng: Number(pov.dataset.longitude) };
    // Set up the map
    const map = new google.maps.Map(document.getElementById("map"), {
        center: centerPoint,
        zoom: 18,
        streetViewControl: false,
    });
    document.getElementById("toggle").addEventListener("click", toggleStreetView);
    // Set up the markers on the map
    const cafeMarker = new google.maps.Marker({
        position: { lat: 40.730031, lng: -73.991428 },
        map,
        icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=cafe|FFFF00",
        title: "Cafe",
    });
    const bankMarker = new google.maps.Marker({
        position: { lat: 40.729681, lng: -73.991138 },
        map,
        icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=dollar|FFFF00",
        title: "Bank",
    });
    const busMarker = new google.maps.Marker({
        position: { lat: 40.729559, lng: -73.990741 },
        map,
        icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=bus|FFFF00",
        title: "Bus Stop",
    });
    // We get the map's default panorama and set up some defaults.
    // Note that we don't yet set it visible.
    panorama = map.getStreetView(); // TODO fix type
    panorama.setPosition(centerPoint);
    panorama.setPov(
        /** @type {google.maps.StreetViewPov} */ {
            heading: 265,
            pitch: 0,
        }
    );
}

function toggleStreetView() {
    const toggle = panorama.getVisible();

    if (toggle == false) {
        panorama.setVisible(true);
    } else {
        panorama.setVisible(false);
    }
}