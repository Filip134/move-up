var login = document.getElementById("login");
var password = document.getElementById("password");


login.onkeyup = function()
{
    var wrongCredentials = document.getElementById("wrongCredentials");
    if(wrongCredentials != null)
        wrongCredentials.style.display = "none";
}


password.onkeyup = function()
{
    var wrongCredentials = document.getElementById("wrongCredentials");
    if(wrongCredentials != null)
        wrongCredentials.style.display = "none";
}