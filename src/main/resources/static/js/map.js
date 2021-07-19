let panorama;

function initMap() {
    const pov = document.querySelector('#pov');

    const centerPoint = { lat: centerPointLatitude, lng: centerPointLongitude };
    // Set up the map
    const map = new google.maps.Map(document.getElementById("map"), {
        center: centerPoint,
        zoom: 18,
        streetViewControl: false,
    });
    document.getElementById("toggle").addEventListener("click", toggleStreetView);
    // Set up the markers on the map
    const startMarker = new google.maps.Marker({
        position: { lat: startMarkerLatitude, lng: startMarkerLongitude },
        map: map,
        icon: "./icons/start_icon.svg",
        title: "Cafe",
    });
    const endMarker = new google.maps.Marker({
        position: { lat: endMarkerLatitude, lng: endMarkerLongitude },
        map: map,
        // icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=dollar|FFFF00",
        icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=cafe|FFFF00",
        title: "Bank",
        visible: true
            // <div>Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="</a></div>
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