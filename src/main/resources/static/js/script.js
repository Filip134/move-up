function adjustTextarea(h)
{
    h.style.height = "20px";
    h.style.height = (h.scrollHeight)+"px";
}

function openActivityPopup()
{
    document.getElementById("activityPopup").style.display = "block";
}

function closeActivityPopup()
{
    document.getElementById("activityPopup").style.display = "none";
    var select = document.getElementById("selectActivity");
    var option = document.createElement("option");
    option.text = document.getElementById("activityName").value;
    select.add(option);
    select.selectedIndex = select.length -1;
}

function resetActivityPopup()
{
    document.getElementById("activityPopup").style.display = "none";
    var activityName = document.getElementById("activityName");
    activityName.value = activityName.defaultValue;
}

function addEventValidation()
{
    var currentDateTime = new Date();
    var userDate = document.getElementById("date").value;
    var userTime = document.getElementById("time").value;
    var userDateTime = new Date(userDate + ' ' + userTime);

    if(currentDateTime > userDateTime)
    {
        document.getElementById("wrongDate").style.display = "block";
        return false;
    }
    else
    {
        return true;
    }
}
