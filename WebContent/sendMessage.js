$(document).ready(function() {
	$('#send').click(function(e) {
		$.ajax({
			type: 'POST',
			url: 'WritingMail',
			dataType: 'json',
			data: { mailto: $('#mailto').val(), mailsubject: $('#mailsubject').val(), mailbody: $('#mailbody').val() }
		}).done(function(msg) {
			if (msg.success === true) {
				alert("Mejl uspesno poslat!");
				$('#mailto').val('');
				$('#mailsubject').val('');
				$('#mailbody').val('');
			} else {
				alert(msg.error);
			}
		}).fail(function(req, status, error) {
			alert("Server did not respond!");
		});
	});
});
