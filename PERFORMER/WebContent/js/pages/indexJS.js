

$(document).ready(function() {
	console.log("load indexJS.js");
	$("#performerTitle").append("<h2>Performer Of The Release june19th<h2>");
});
function launchHelp() {
	$('#myHelp').modal({
		show : true
	});
}

function Login() {

	var username = $("#username").val();
	var password = $("#password").val();
	console.log(" username :"+username);
	console.log(" password :"+password);
	filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z\-])+\.)+([a-zA-Z]{2,4})+$/;
	if (!filter.test(username) || username == null || username == ''
		|| password == null || password == '') {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>username and password invalid </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	} else {
		var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4) {

				var res = xhttp.responseText;
				if ("Success" == res) {
					localStorage.setItem("prlogin","true");

					window.location.href = "HomePage.jsp";
				} else {
					var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
					alertBuilder += '<div class="modal-dialog">';
					alertBuilder += '<div class="modal-content">';
					alertBuilder += '<div class="modal-header">';
					alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
					alertBuilder += '<h4 class="modal-title">Error</h4>';
					alertBuilder += '</div>';
					alertBuilder += '<div class="modal-body">';
					alertBuilder += '<p>username and password invalid </p>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					document.getElementById("alertContainer").innerHTML = alertBuilder;
					$('#alertModal').modal({
						show : 'true'
					});

				}
			}
		};

		xhttp.open("POST", "./LoginValidate?username=" + username
				+ "&password=" + password, true);
		xhttp.send();
	}
}

function callservlet() {
	var username = $("#username1").val();
	var password = $("#password").val();
	var cpassword = $("#cpassword").val();
	console.log(" username :"+username);
	console.log(" password :"+password);
	console.log("confirm password :"+cpassword);
	if (username == null || username == ''
		|| password == null || password == '') {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>username and password invalid </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	}else{
		var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4) {

				var res = xhttp.responseText;
				if ("Success" == res) {
					localStorage.setItem("prlogin","true");

					window.location.href = "HomePage.jsp";
				} else {
					var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
					alertBuilder += '<div class="modal-dialog">';
					alertBuilder += '<div class="modal-content">';
					alertBuilder += '<div class="modal-header">';
					alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
					alertBuilder += '<h4 class="modal-title">Error</h4>';
					alertBuilder += '</div>';
					alertBuilder += '<div class="modal-body">';
					alertBuilder += '<p>username and password invalid </p>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					document.getElementById("alertContainer").innerHTML = alertBuilder;
					$('#alertModal').modal({
						show : 'true'
					});

				}
			}
		};
		xhttp.open("POST", "./ForgetPassword?username=" + username
				+ "&password=" + password + "&cpassword=" + cpassword , true);
		xhttp.send();
	}
}

function submitBestPerformer(){
	var releaseDate;
	var bestPerformer = $("#bestPerformer").val();
	var nextRelease = $("#nextRelease").val();



	if ( bestPerformer == null || bestPerformer == '') {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>please enter Best Perfomrer name </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	} else if ( nextRelease == null || nextRelease == '') {
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>please enter Next Release Date </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	}else {
		var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4) {

				var res = xhttp.responseText;
				if ("Success" == res) {

					if (window.XMLHttpRequest) {
						xhttp = new XMLHttpRequest();
					} else {
						// code for IE6, IE5
						xhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}

					xhttp.onreadystatechange = function() {

						if (xhttp.readyState == 4) {
							document.getElementById("maindiv").innerHTML = xhttp.responseText;
							document.getElementById("maindiv").style.display = 'block';
						}
					};
					xhttp.open("POST", "congrats.jsp", true);
					xhttp.send();
				} else {
					var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
					alertBuilder += '<div class="modal-dialog">';
					alertBuilder += '<div class="modal-content">';
					alertBuilder += '<div class="modal-header">';
					alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
					alertBuilder += '<h4 class="modal-title">Error</h4>';
					alertBuilder += '</div>';
					alertBuilder += '<div class="modal-body">';
					alertBuilder += '<p>Sorry, something went wrong</p>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					document.getElementById("alertContainer").innerHTML = alertBuilder;
					$('#alertModal').modal({
						show : 'true'
					});

				}
			}
		};

		xhttp.open("POST", "./BestPerformer?bestPerformer=" + bestPerformer+ "&nextRelease=" + nextRelease, true);
		xhttp.send();
	}

}



function Logout() {
	localStorage.removeItem("prlogin");
	window.location.href = "Login.jsp";
	return false;
}
function display_Performer() {
	var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4) {
			var res=xhttp.responseText;

			if("ValidUser"==res){
				if (window.XMLHttpRequest) {
					xhttp = new XMLHttpRequest();
				} else {
					// code for IE6, IE5
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}

				xhttp.onreadystatechange = function() {

					if (xhttp.readyState == 4) {
						document.getElementById("maindiv").innerHTML = xhttp.responseText;
						document.getElementById("maindiv").style.display = 'block';
					}
				};
				xhttp.open("POST", "results.jsp", true);
				xhttp.send();
			}
			else{


				if (window.XMLHttpRequest) {
					xhttp = new XMLHttpRequest();
				} else {
					// code for IE6, IE5
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}

				xhttp.onreadystatechange = function() {

					if (xhttp.readyState == 4) {

						document.getElementById("maindiv").innerHTML = xhttp.responseText;
						$('.selectpicker').selectpicker('refresh');
						$.getJSON("resources/names.json", function(obj2) {
							console.log("in res");
							$.each(obj2.names, function(key, value) {
								$("#perfomer1").append(
										"<option value='" + value.value + "'>" + value.name
										+ "</option>").selectpicker('refresh');
								$("#perfomer2").append(
										"<option value='" + value.value + "'>" + value.name
										+ "</option>").selectpicker('refresh');
								$("#perfomer3").append(
										"<option value='" + value.value + "'>" + value.name
										+ "</option>").selectpicker('refresh');
							});
						});
						document.getElementById("maindiv").style.display = 'block';
					}
				};
				xhttp.open("POST", "performer.jsp", true);
				xhttp.send();

			}
		}
	};

	xhttp.open("POST", "./ValidUser", true);
	xhttp.send();


}
function display_History() {
	window.history.forward();
	$.getJSON("resources/ReleaseHistory.json",
			function(obj2) {
		var tabdisp = "<table id='example' class='table table-striped table-bordered'";
		tabdisp = tabdisp + "cellspacing='0' width='100%'>";
		tabdisp = tabdisp
		+ "<thead class='thbackground'><tr><th>Photo</th>";
		tabdisp = tabdisp
		+ "<th>Release ID</th><th>Performer</th></tr></thead>";
		console.log("in res");
		$
		.each(
				obj2.names,
				function(key, value) {
					//alert(value.justification)

					tabdisp = tabdisp
					+ '<tr onclick="getjustification(\'' +value.justification+ '\')"><td>'
					+ '<img src=./Photo/'+value.username
					+ '.jpg alt="Smiley face" height="50" width="50"></td>';
					tabdisp = tabdisp + "<td>"
					+ value.releaseName
					+ '</td><td>'
					+ value.username
					+ '</td></tr>';

				});
		tabdisp = tabdisp + "</table>";

		document.getElementById("maindiv").innerHTML = tabdisp;
		document.getElementById("maindiv").style.display = 'block';
	});
}
function ValidatePerformer() {
	var result = false;
	var perfomer1 = $("#perfomer1").val();
	var perfomer2 = $("#perfomer2").val();
	var perfomer3 = $("#perfomer3").val();
	var justification1 = $("#justification1").val().replace(/^\s+|\s+$/g, '');
	var justification2 = $("#justification2").val().replace(/^\s+|\s+$/g, '');
	var justification3 = $("#justification3").val().replace(/^\s+|\s+$/g, '');
	console.log(" perfomer1 :"+perfomer1);
	console.log(" justification1 :"+justification1);
	console.log(" perfomer2 :"+perfomer2);
	console.log(" justification2 :"+justification2);
	console.log(" perfomer3 :"+perfomer3);
	console.log(" justification3 :"+justification3);

	var temp = '';
	console.log(" perfomer1 :" + perfomer1);
	if ('ChoosePerformer-1' == (perfomer1)) {
		result = false;
		console.log("temp  .." + temp);
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please select ChoosePerformer-1</p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	} else if ('' == justification1 || justification1.length==0) {
		console.log("justification1  .." + temp);
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please write justification1</p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	} else if (('ChoosePerformer-2' == (perfomer2))
			&& ('' != justification2 || justification2.length!=0)) {
		result = false;
		console.log("temp  .." + temp);
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please select ChoosePerformer-2 </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	}else if (('ChoosePerformer-2' != (perfomer2))
			&& ('' == justification2 || null == justification2 || justification2.length==0)) {

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please write justification2</p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	}  else if (('ChoosePerformer-3' == (perfomer3))
			&& ('' != justification3 || justification3.length!=0)) {
		var temp='<p>Please select ChoosePerformer-3 </p>';
		result = false;
		console.log("temp  .." + temp);
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += temp ;
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	}else if (('ChoosePerformer-3' != (perfomer3))
			&& ('' == justification3 || null == justification3 || justification3.length==0) ) {
		var temp='<p>Please write justification3</p>';
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += temp;
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	} else if (('ChoosePerformer-3' != (perfomer3))
			&& ('' != justification3 || null != justification3 || justification3.length!=0) &&
			('ChoosePerformer-2' == (perfomer2)||('' == justification2 || justification2.length==0))) {
		var temp='<p>Please select ChoosePerformer-2 and write justification2</p>';
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Error</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += temp;
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;

	}  else{
		console.log("before calling sendRequest  :");
		sendrequest(perfomer1, perfomer2, perfomer3, justification1,
				justification2, justification3);
		result=true;
	}

	return result;

}
function getjustification(justfication){


	var justfication1 = justfication.split(",");
	var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
	alertBuilder += '<div class="modal-dialog">';
	alertBuilder += '<div class="modal-content">';
	alertBuilder += '<div class="modal-header">';
	alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
	alertBuilder += '<h4 class="modal-title">Justification</h4>';
	alertBuilder += '</div>';
	alertBuilder += '<div class="modal-body">';
	alertBuilder += '<ul>';
	for (i = 0; i <= justfication1.length-1; i++) { 
		alertBuilder += '<li><span class="myjustification">'+justfication1[i]+'<span></li>';
	}
	alertBuilder += '</ul>';
	alertBuilder += '</div>';
	alertBuilder += '</div>';
	alertBuilder += '</div>';
	alertBuilder += '</div>';

	document.getElementById("alertContainer").innerHTML = alertBuilder;
	$('#alertModal').modal({
		show : 'true'
	});

}
function sendrequest(perfomer1, perfomer2, perfomer3, justification1,
		justification2, justification3) {
	console.log("sendRequest  :");
	var xhttp;
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4) {

			var res = xhttp.responseText;
			if ("Success" == res)
			{

				if (window.XMLHttpRequest) {
					xhttp = new XMLHttpRequest();
				} else {
					// code for IE6, IE5
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}

				xhttp.onreadystatechange = function() {

					if (xhttp.readyState == 4) {
						document.getElementById("maindiv").innerHTML = xhttp.responseText;
						document.getElementById("maindiv").style.display = 'block';
					}
				};
				xhttp.open("POST", "results.jsp", true);
				xhttp.send();
			} else {
				var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
				alertBuilder += '<div class="modal-dialog">';
				alertBuilder += '<div class="modal-content">';
				alertBuilder += '<div class="modal-header">';
				alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
				alertBuilder += '<h4 class="modal-title">Error</h4>';
				alertBuilder += '</div>';
				alertBuilder += '<div class="modal-body">';
				alertBuilder += '<p>Sorry Please try again</p>';
				alertBuilder += '</div>';
				alertBuilder += '</div>';
				alertBuilder += '</div>';
				alertBuilder += '</div>';
				document.getElementById("alertContainer").innerHTML = alertBuilder;
				$('#alertModal').modal({
					show : 'true'
				});
				display_Performer();
			}

		}
	};
	console.log("sendRequest Goin to java class :");
	var url = "./Performer?perfomer1=" + perfomer1
	+ "&perfomer2=" + perfomer2 + "&perfomer3=" + perfomer3
	+ "&justification1=" + justification1 + "&justification2="
	+ justification2 + "&justification3=" + justification3;

	xhttp.open("POST", url, true);
	xhttp.send();
}
function removeFromSelect1() {
	console.log("onchange of select1");
	var perfomer1 = $("#perfomer1").val();
	var perfomer2val = $("#perfomer2").val();
	console.log("onchange of select1 perfomer1"+perfomer1);
	var perfomer2 = document.getElementById("perfomer2");
	console.log("onchange of select1 perfomer2 length :"+perfomer2);
	var perfomer3 = document.getElementById("perfomer3");
	for (var i=0; i<perfomer2.length; i++){
		console.log("onchange performer2 index:"+i+"  value :"+perfomer2.options[i].value);
		if (perfomer2.options[i].value == perfomer1 ){
			console.log("onchange performer2 found");
			//perfomer2.remove(i);
			perfomer2.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
		}
	}
	for (var i=0; i<perfomer3.length; i++){
		if (perfomer3.options[i].value == perfomer1 || perfomer3.options[i].value == perfomer2val){
			console.log("onchange performer3 found");
			perfomer3.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
			//perfomer3.remove(i);
		}
		else
			perfomer3.options[i].style.visibility = 'visible';

	}
}
function removeFromSelect2() {
	console.log("onchange of select2");
	var perfomer2 = $("#perfomer2").val();
	var perfomer1val = $("#perfomer1").val();
	console.log("onchange of select1 perfomer2"+perfomer1);
	var perfomer1 = document.getElementById("perfomer1");
	console.log("onchange of select1 perfomer1 length :"+perfomer2);
	var perfomer3 = document.getElementById("perfomer3");
	for (var i=0; i<perfomer2.length; i++){
		console.log("onchange performer1 index:"+i+"  value :"+perfomer1.options[i].value);
		if (perfomer1.options[i].value == perfomer2 ){
			console.log("onchange performer1 found");
			//perfomer2.remove(i);
			perfomer1.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
		}
		else
			perfomer1.options[i].style.visibility = 'visible';
	}
	for (var i=0; i<perfomer3.length; i++){
		if (perfomer3.options[i].value == perfomer2 || perfomer3.options[i].value == perfomer1val ){
			console.log("onchange performer3 found");
			perfomer3.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
			//perfomer3.remove(i);
		}
		else
			perfomer3.options[i].style.visibility = 'visible';

	}
}
function removeFromSelect3() {
	console.log("onchange of select1");
	var perfomer3 = $("#perfomer3").val();
	var perfomer1val = $("#perfomer1").val();
	var perfomer2val = $("#perfomer2").val();
	console.log("onchange of select3 perfomer3"+perfomer3);
	var perfomer2 = document.getElementById("perfomer2");
	console.log("onchange of select1 perfomer2 length :"+perfomer2);
	var perfomer1 = document.getElementById("perfomer1");
	for (var i=0; i<perfomer2.length; i++){
		console.log("onchange performer2 index:"+i+"  value :"+perfomer2.options[i].value);
		if (perfomer2.options[i].value == perfomer3 || perfomer2.options[i].value == perfomer1val ){
			console.log("onchange performer2 found");
			//perfomer2.remove(i);
			perfomer2.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
		}
		else
			perfomer2.options[i].style.visibility = 'visible';
	}
	for (var i=0; i<perfomer1.length; i++){
		if (perfomer1.options[i].value == perfomer3 || perfomer1.options[i].value == perfomer2val){
			console.log("onchange performer1 found");
			perfomer1.options[i].style.visibility = 'hidden';
			$('.selectpicker').selectpicker('refresh');
			//perfomer3.remove(i);
		}
		else
			perfomer1.options[i].style.visibility = 'visible';
	}
}
function sendMail(){

	var username = $("#username").val();
	console.log(" forgot username :"+username);
	filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z\-])+\.)+([a-zA-Z]{2,4})+$/;
	if (!filter.test(username) || username == null || username == '') {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">username  invalid </h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>username  invalid </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	} else {
		var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4) {

				var res = xhttp.responseText;
				if ("Success" == res) {
					localStorage.setItem("prlogin","true");

					window.location.href = "ForgetPass.jsp";
				} else {
					var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
					alertBuilder += '<div class="modal-dialog">';
					alertBuilder += '<div class="modal-content">';
					alertBuilder += '<div class="modal-header">';
					alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
					alertBuilder += '<h4 class="modal-title">Please check username ee'+res+' </h4>';
					alertBuilder += '</div>';
					alertBuilder += '<div class="modal-body">';
					alertBuilder += '<p>Please check username eee </p>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					document.getElementById("alertContainer").innerHTML = alertBuilder;
					$('#alertModal').modal({
						show : 'true'
					});

				}
			}
		};

		xhttp.open("POST", "./SendMail?username=" + username, true);
		xhttp.send();
	}
}

function validatePassword() {

	//var passcode = $("#passcode").val();
	var password = $("#password").val();
	var cpassword = $("#cpassword").val();
	//console.log(" passcode :"+passcode);
	console.log(" password :"+password);
	console.log(" cpassword :"+cpassword);
	/*if (passcode == null || passcode == ''){
		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Please enter passcode </h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please enter passcode </p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
	}
	else*/ if( (password == null || password == ''|| cpassword == null || cpassword == '')
	) {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">password and confirm password are not matched</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>password and confirm password are not matched</p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	} else if(password == cpassword ) {
		var xhttp;
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			// code for IE6, IE5
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState == 4) {

				var res = xhttp.responseText;
				if ("Success" == res) {
					localStorage.setItem("prlogin","true");
					window.location.href = "Login.jsp";
				} else {
					var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
					alertBuilder += '<div class="modal-dialog">';
					alertBuilder += '<div class="modal-content">';
					alertBuilder += '<div class="modal-header">';
					alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
					alertBuilder += '<h4 class="modal-title">Password was not updated</h4>';
					alertBuilder += '</div>';
					alertBuilder += '<div class="modal-body">';
					alertBuilder += '<p>Password was not updated </p>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					alertBuilder += '</div>';
					document.getElementById("alertContainer").innerHTML = alertBuilder;
					$('#alertModal').modal({
						show : 'true'
					});

				}
			}
		};

		xhttp.open("POST", "./ForgetPassword?password=" + password
				 +"&cpassword="+ cpassword, true);
		xhttp.send();
	}
	else  {
		// invalid

		var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">password and confirm password are not matched</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>Please check password and confirm password are not matched</p>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertContainer").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});

	}
}