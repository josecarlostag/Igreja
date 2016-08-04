$(document).ready(function() {
	
	$('.js-toggle').bind('click', function() {
		$('.js-sidebar').toggleClass('is-toggled');
		$('.js-content').toggleClass('is-toggled');
		$('.js-rodape').toggleClass('is-toggled');
	});
	
	$('.js-menu').bind('click', function() {
		$('.js-pessoa').toggleClass('is-selected');
		
	});
	
});