
function bodyMargin(){
	document.getElementsByTagName("body").setAttribute("style","margin-top:"+document.getElementById('#fixed_header').offsetHeight+";");	
}

function checkCookie(name) {
/*	  var nameEQ = name + "=";*/
//			To print all cookies of current site
//			var x= document.cookie;
//			window.alert(x);
//			To print all cookies of current site

	var ca = document.cookie.split('; ');			//	split(';'); --> will not work because after 1st cookie ; comes and then comes space so, 2nd cookie starts actually after space so split from there.
	  for(var i=0;i < ca.length;i++) {
	    var c = ca[i];
	    var cnames = c.split('=');
	  		if(cnames[0] == name){
	  			return cnames[1];
	  		}
	   }
	  return null;
}

function validatePass(x){
	var pass = document.getElementById(x).value;
	if(pass.includes('"')){
		alert('Cant Use "(Double Inverted Commas) ');
		document.getElementById(x).value = "";
	}
}

function chk() {
	var mail = document.getElementById("txtMailId").value;
	if(validateMail(mail))
	{
		var cValue = checkCookie(mail);
		if(cValue != null)
		{
			var finalCValue = cValue.split('"');
			var a = finalCValue.length;
			if(a == 1){
				// Do Nothing
			}
			else{
				cValue = finalCValue[1];
			}
			if(cValue != null){
				document.getElementById("txtPassword").setAttribute("value",cValue);
			}
		}
		else if(cValue == null){
			document.getElementById("txtPassword").setAttribute("value","");
		}
	}
}

function confirmPass(){
	var pass= document.getElementById("pass").value;
	var pass1= document.getElementById("pass1").value;
	if(pass != pass1){
		alert("Password not same.");
		window.location.href="";
	}
}

function attachHref(appendingValueElementId,hrefTargetId,targetPage,targetAttributeToAppend){
	var mail = document.getElementById(appendingValueElementId).value;
	var tagetHref=targetPage+"?"+targetAttributeToAppend+"="+mail;
	document.getElementById(hrefTargetId).setAttribute("href",targetPage+"?"+targetAttributeToAppend+"="+mail);
}

function temp1(){
	var mail = document.getElementById("txtMailId").value;
	if(validateMail(mail))
		{
		document.getElementById("fp").setAttribute("href","MailServlet?txtEmailId="+mail);
		}
}

function removeStyle(x){
	document.getElementById(x).removeAttribute("style");
}

function borderBlue(){
	document.getElementById("remember").setAttribute("style","padding: 5px;");
}

function Shadow(){
	document.getElementById("submit").setAttribute("style","margin: 5px;");
}

function forgetPass(){
	document.getElementById("fp2").setAttribute("style","font-size: 20px; text-decoration:overline;");
}

function otpResend(){
	document.getElementById("otp").setAttribute("style","font-size: 20px; text-decoration:overline;");
}

function validateMobile(){
	var mobile = document.getElementById("txtMobile").value;
    var pmobile=/^[0-9]{1,10}$/;
	if(mobile.length!=10)
	{
        alert("Only 10 Digits");
	}
	if (!pmobile.test(mobile)) {
        alert("Phone nubmer is in 0123456789 format(Only Numbers)");
        return false;
    }
}

function validatePin(){
	var pin = document.getElementById("txtPin").value;
    var ppin=/^[0-9]{1,6}$/;
    if ( !ppin.test(pin) )  {
        alert("Pin code should be 6 digits ");
    	return false;
    }	
}

function validateMail(){
    var mail = document.getElementById("txtMailId").value;
    var pmail=/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    if(mail.length == 0)
    	{
    	alert("Email is Required");
    	return false;
    	}
    else if (!pmail.test(mail)) {
        alert("Email must be thakkarbhumit.1@gmail.com format");
        return false;
    }
    else if(pmail.test(mail)){
    	   return true;
     }
}

function checkAndSign(){										/* from add_mall.jsp when mall name consists of & sign  */
	var mallName = document.getElementById("txtMallName").value;
	var patt = /[&]/g;
	if(mallName.match(patt)){
		alert("No '&' Accepted, Instead use 'and'");
	}
}

function setValueTo(value,toElementId){
	document.getElementById(toElementId).value = value;
}

function setSpecialCaseValueFromTo(fromElementId,toElementId,fromElementId2,toElementId2){
	document.getElementById(toElementId).value = document.getElementById(fromElementId).innerHTML;
	document.getElementById(toElementId2).value = document.getElementById(fromElementId2).innerHTML;
}

function displayOn(toOnElementId){
	document.getElementById(toOnElementId).setAttribute("style","display:true;");
}

function displayOff(toOffElementId){
	document.getElementById(toOffElementId).setAttribute("style","display:none;");
	document.getElementById(toOffElementId).value=null;
}

function dropdown(){
	
}