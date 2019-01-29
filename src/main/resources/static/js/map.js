var mymap = L.map('mapid').setView([51.1052862455, 17.055921443], 15);

L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);

var markers = [];
function onMapClick(e)
{
    v1 = e.latlng.lat;
    v2 = e.latlng.lng;
    document.getElementById("latitude").value = v1;
    document.getElementById("longitude").value = v2;
    markers.push(L.marker([v1, v2]).addTo(mymap));
    if((markers.length > 1) ) {
        mymap.removeLayer(markers[markers.length - 2])
    }

    setTimeout(function()
    {
        document.getElementById("mapContainer").style.visibility = "hidden";
        // document.getElementById("mapid").style.display = "none";
    }, 500);

}
mymap.on('click', onMapClick);

function openMap()
{
    document.getElementById("mapContainer").style.visibility = "visible";
}

