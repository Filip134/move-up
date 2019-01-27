var login = document.getElementById("login");
var password = document.getElementById("password");
var confirmPassword = document.getElementById("confirmPassword");
var oldPassword = document.getElementById("oldPassword");

login.onkeyup = function()
{
    document.getElementById("wrongCredentials").style.display = "none";
}


oldPassword.onkeyup = function()
{
    document.getElementById("wrongCredentials").style.display = "none";
}

password.onkeyup = function ()
{
    document.getElementById("changePasswordRegex").style.display = "none";
}


confirmPassword.onkeyup = function ()
{
    document.getElementById("changePasswordRegex").style.display = "none";
}

function validateChangedPassword()
{
    if(password.value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/))
    {
        if(password.value.localeCompare(confirmPassword.value) == 0)
            return true;
        else
        {
            document.getElementById("matchingPassword").style.display = "block";
            return false;
        }
    }
    else
    {
        document.getElementById("changePasswordRegex").style.display = "block";
        return false;
    }
}