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

    let startMarker = new google.maps.Marker({
        position: { lat: startMarkerLatitude, lng: startMarkerLongitude },
        map: map,
        icon: startIcon,
        title: "Cafe",
    });

    const endMarker = new google.maps.Marker({
        position: { lat: endMarkerLatitude, lng: endMarkerLongitude },
        map: map,
        icon: "https://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=cafe|FFFF00",
        title: "Bank",
        visible: true
    });


    panorama = map.getStreetView(); // TODO fix type
    panorama.setPosition(centerPoint);
    panorama.setPov(
        /** @type {google.maps.StreetViewPov} */ {
            heading: 265,
            pitch: 0,
        }
    );
    panorama.addListener('position_changed', () => {

        startMarker.setPosition(updateMarker(panorama.getPosition().lat, panorama.getPosition().lng, startAzimuth));

    })
}

function updateMarker(lat, lng, azimuth) {
    var command = {
        centerPointLatitude: lat,
        centerPointLongitude: lng,
        azimuth: azimuth
    }
    var latLng;

    $.ajax({
        type: "GET",
        url:  "/update",
        data: command,
        async: false,
        success: function(result) {
            latLng = new google.maps.LatLng(result.latitude, result.longitude)
        }
    })
    return latLng
}

function toggleStreetView() {
    const toggle = panorama.getVisible();

    if (toggle === false) {
        panorama.setVisible(true);
    } else {
        panorama.setVisible(false);
    }
}