let panorama;

function initMap() {

    const centerPoint = { lat: centerPointLat, lng: centerPointLng };
    // Set up the map
    const map = new google.maps.Map(document.getElementById("map"), {
        scaleControl: true,
        center: centerPoint,
        zoom: 18,
        streetViewControl: false,
    });
    document.getElementById("toggle").addEventListener("click", toggleStreetView);

    var startIcon = {
        url: "./icons/start_icon.svg",
        size: new google.maps.Size(100, 100),
        scaledSize: new google.maps.Size(100, 100),
    };

    const startMarker = new google.maps.Marker({
        position: { lat: startMarkerLatitude, lng: startMarkerLongitude },
        map: map,
        icon: startIcon,
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
    panorama.addListener('position_changed', () => {
        console.log(panorama.getPosition().lat())

        let startMarker = updateMarker(panorama.getPosition().lat, panorama.getPosition().lng, startAzimuth);
    })
}

function updateMarker(lat, lng, azimuth) {
    var command = {
        centerPointLatitude: lat,
        centerPointLongitude: lng,
        azimuth: azimuth
    }

    $.ajax({
        type: "GET",
        url:  "/update",
        data: command,
        success: function(result) {
            return new google.maps.LatLng(result.latitude, result.longitude)
        }
    })
}

function toggleStreetView() {
    const toggle = panorama.getVisible();

    if (toggle === false) {
        panorama.setVisible(true);
    } else {
        panorama.setVisible(false);
    }
}