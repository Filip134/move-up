function dateFormat()
{
    var dates = document.getElementsByClassName("dateFormat");
    for (var i = 0; i < dates.length; i++)
    {
        console.log(i);
        var dateFromat = new Date(dates[i].innerHTML);
        var year = dateFromat.getFullYear();
        var month = dateFromat.getMonth()+1;
        if(month < 10)
            month = 0 + "" + month;
        var day = dateFromat.getDate();
        if(day < 10)
            day = 0 + "" + day;
        var hour = dateFromat.getHours();
        if(hour < 10)
            hour = 0 + "" + hour;
        var minute = dateFromat.getMinutes();
        if(minute < 10)
            minute = 0 + "" + minute;

        dates[i].innerHTML = year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }
}