function choose(event) {
    console.log("Täällä ollaan!");
    var td = $(event.target).parent().parent();
    var parent = td.parent();
    var row = parent.index(td);
    console.log(row);
    // console.log(row);
    // var restaurant = JSON.parse(sessionStorage.restaurants)[row];
    // $('.play').empty();
    // $('<p></p>')
    //     .text("You chose " + restaurant.name/* + " (" + restaurant.address.building + ", " + restaurant.address.street + ", " + restaurant.borough */ + ")")
    //     .appendTo('.play');
    // $('<button>Confirm</button>')
    //     .appendTo('.play');
    // $('button').on('click', function (event) {
    //     event.preventDefault();
    //     console.log(restaurant.name);
    // });

    return;
}

