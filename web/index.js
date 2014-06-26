function checkInput(frm)
{
    var username=frm.username.value;
    var password=frm.password.value;
    var authentic=true;
    var firstWrongComponent=null;
    if(username.length==0)
        {
            document.getElementById("usernameErrorSection").innerHTML="Required";
            authentic=false;
            firstWrongComponent=frm.username;
        }
    else
        {
            document.getElementById("usernameErrorSection").innerHTML="";
        }
        if(password.length==0)
        {
            document.getElementById("passwordErrorSection").innerHTML="Required";
            authentic=false;
            if(firstWrongComponent==null)firstWrongComponent=frm.password;
        }
        else
            {
                document.getElementById("passwordErrorSection").innerHTML="";
            }
        if(!authentic)firstWrongComponent.focus();
        return authentic;
}