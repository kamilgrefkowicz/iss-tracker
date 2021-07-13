function getPosition() {
    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude.toFixed(3);
            var longitude = position.coords.longitude.toFixed(3);
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;
        });
    } else {
        alert("Sorry, your browser does not support HTML5 geolocation.");
    }
}