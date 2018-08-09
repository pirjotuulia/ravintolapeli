function getDistance(fLong, fLat, tLong, tLat) {

    /*Näillä testataan, poistan kun pelittää parametreillä*/
    var test = '-73.8642349,40.75356';
    var jep = '-73.871194,40.6730975';

    var fromLoc = fLong + ',' + fLat; /*lähtöpaikka*/
    var toLoc = tLong + ',' + tLat; /*seuraava ravintola*/

    var location = test + '|' + jep;
    console.log(location);
    $.ajax({
        type: "GET", //rest Type-->
        dataType: 'json',
        url: "https://api.openrouteservice.org/directions?api_key=5b3ce3597851110001cf62488ff5afa00179428b810a585eb6cae348&coordinates=" + location + "&profile=driving-car&preference=fastest&format=json&units=mi",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            return data;
        }

    });
}