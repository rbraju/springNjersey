
function getProfile() {
	var userid = document.getElementById('userid').value;
	if (userid.length == 0) {
		alert("User id cannot be empty.")
	} else {
		window.location.href = "api/user/profile?id=" + userid;
	}
}

function getAddress() {
	var userid = document.getElementById('userid').value;
	if (userid.length == 0) {
		alert("User id cannot be empty.")
	} else {
		window.location.href = "api/user/address?id=" + userid;
	}
}

function getTweets() {
	var screenName = document.getElementById('screenName').value;
	if (screenName.length == 0) {
		alert("Screen name cannot be empty.")
	} else {
		window.location.href = "api/tweet/" + screenName;
	}
}

