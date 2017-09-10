
function getTweets() {
	var screenName = document.getElementById('screenName').value;
	if (screenName.length == 0) {
		alert("Screen name cannot be empty.")
	} else {
		window.location.href = "api/tweet/" + screenName;
	}
}