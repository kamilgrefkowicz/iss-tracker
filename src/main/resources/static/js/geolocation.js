function getPosition() {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            let altitude = geolocationCoordinatesInstance.altitude
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;
            document.getElementById("altitude").value = altitude;
        });
    } else {
        alert("Sorry, your browser does not support HTML5 geolocation.");
    }
}