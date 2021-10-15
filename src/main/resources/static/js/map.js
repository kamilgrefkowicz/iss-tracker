let panorama;

function initMap() {

    const centerPoint = { lat: centerPointLat, lng: centerPointLng };
    const map = new google.maps.Map(document.getElementById("map"), {
        center: centerPoint,
        zoom: 18,
        scaleControl: true,
        streetViewControl: false,
    });
    document.getElementById("toggle").addEventListener("click", toggleStreetView);

    const startIcon = {
        url: "./icons/start_icon.svg",
        size: new google.maps.Size(200, 200),
        scaledSize: new google.maps.Size(200, 200),
    };
    const endIcon = {
        url: "/icons/end_icon.svg",
        size: new google.maps.Size(200, 200),
        scaledSize: new google.maps.Size(200, 200),
    }

    let startMarker = new google.maps.Marker({
        position: { lat: startMarkerLatitude, lng: startMarkerLongitude },
        map: map,
        icon: startIcon,
        title: "Start",
    });

    const endMarker = new google.maps.Marker({
        position: { lat: endMarkerLatitude, lng: endMarkerLongitude },
        map: map,
        icon: endIcon,
        title: "End",
    });

    panorama = map.getStreetView();
    panorama.setPosition(centerPoint);
    panorama.setPov(
        /** @type {google.maps.StreetViewPov} */ {
            heading: 265,
            pitch: 0,
        }
    );
    panorama.addListener('position_changed', () => {

        startMarker.setPosition(updateMarker(panorama.getPosition().lat, panorama.getPosition().lng, startAzimuth));
        endMarker.setPosition(updateMarker(panorama.getPosition().lat, panorama.getPosition().lng, endAzimuth))

    })
}

function updateMarker(lat, lng, azimuth) {
    const command = {
        centerPointLatitude: lat,
        centerPointLongitude: lng,
        azimuth: azimuth
    }
    let latLng;

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