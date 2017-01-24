$(document).ready(function() {
	var id = getUrlParameter('id');
	
	$.ajax({
		type: 'POST',
		url: 'ReadingMail',
		dataType: 'json',
		data: { mailid: id }
	}).done(function(msg) {
		if (msg.success) {
			$('#mailid').html(id);
			$('#mailfrom').html(msg.from);
			$('#mailsubject').html(msg.subject);
			$('#mailbody').html(msg.body);
		} else {
			alert(msg.error);
		}
	}).fail(function(req, status, error) {
		alert("Server did not respond!");
	});
});


var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
