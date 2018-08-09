function getDistance(fLong, fLat, tLong, tLat) {
    var fromLoc =fLat + ',' + fLong;
    /*Lähtöpiste*/

    var toLoc =tLat + ',' + tLong;
    /*määränpää */

    var location = fromLoc + '|' + toLoc;
    var distance = JSON.parse($.ajax({
        type: "GET", //rest Type-->
        dataType: 'json',
        url: "https://api.openrouteservice.org/directions?api_key=5b3ce3597851110001cf62488ff5afa00179428b810a585eb6cae348&coordinates=" + location + "&profile=driving-car&preference=fastest&format=json&units=mi",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        }
    }).responseText);
    var dist = distance.routes[0]["summary"]["distance"];
    return dist;
}