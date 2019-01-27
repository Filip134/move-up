var login = document.getElementById("login");
var password = document.getElementById("password");
var confirmPassword = document.getElementById("confirmPassword");

login.onkeyup = function()
{
    document.getElementById("takenLogin").style.display = "none";
    document.getElementById("wrongCredentials").style.display = "none";
}

password.onkeyup = function()
{
    document.getElementById("passwordRegex").style.display = "block";

    if(password.value.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/))
        document.getElementById("passwordRegex").style.color = "green";
    else
        document.getElementById("passwordRegex").style.color = "red";
}

password.onblur = function()
{
    document.getElementById("passwordRegex").style.display = "none";
}


confirmPassword.onblur = function()
{
    document.getElementById("matchingPassword").style.display = "none";
}

confirmPassword.onkeyup = function()
{
    if(password.value.localeCompare(confirmPassword.value) == 0)
        document.getElementById("matchingPassword").style.display = "none";
}

function validatePassword()
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
        return false;
}
