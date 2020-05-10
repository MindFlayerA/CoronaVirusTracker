$(document).ready(function () {
	$('#dtBasicExample').DataTable({
		"scrollY": "50vh",
		"scrollCollapse": true,
		});
	$('.dataTables_length').addClass('bs-select');
	});

$(document).ready(function () {
	$('#dtdeath').DataTable({
		"scrollY": "50vh",
		"scrollCollapse": true,
		});
	$('.dataTables_length').addClass('bs-select');
	});

$(document).ready(function () {
	$('#dtrecovered').DataTable({
		"scrollY": "50vh",
		"scrollCollapse": true,
		});
	$('.dataTables_length').addClass('bs-select');
	});

makeHttpRequest("http://localhost:8080/getJson/unfiltered/all", true,'GET');