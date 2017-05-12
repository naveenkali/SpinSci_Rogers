$(document).ready(
		function() {var alertBuilder = '<div class="modal fade" id="alertModal" role="dialog">';
		alertBuilder += '<div class="modal-dialog">';
		alertBuilder += '<div class="modal-content">';
		alertBuilder += '<div class="modal-header">';
		alertBuilder += '<button type="button" class="close" data-dismiss="modal">&times;</button>';
		alertBuilder += '<h4 class="modal-title">Exception</h4>';
		alertBuilder += '</div>';
		alertBuilder += '<div class="modal-body">';
		alertBuilder += '<p>There is a Exception.</p>';
		alertBuilder += '</div>';

		alertBuilder += '</div>';

		alertBuilder += '</div>';
		alertBuilder += '</div>';
		document.getElementById("alertException").innerHTML = alertBuilder;
		$('#alertModal').modal({
			show : 'true'
		});
		result = false;
		});