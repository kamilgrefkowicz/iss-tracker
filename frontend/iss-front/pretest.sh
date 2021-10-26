echo "Running 'pretest.sh' script do download Google Maps API for testing..."
# rm google-maps-api.js
date +"// Download time: %Y-%m-%d %H:%M" >> google-maps-api.js
curl "https://maps.googleapis.com/maps/api/js?key=AIzaSyAoJnPw89bM2cW5X4UNR4H1rJGv8AlSyrU&libraries=&v=weekly&language=en" >> google-maps-api.js
echo "'pretest.sh' finished"

 