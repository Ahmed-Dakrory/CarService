


$("#profile-side-menu .responsive_header").click(function(){
	$("#profile-side-menu").find('.responsive_box').each(function() {
	    
		$(this).fadeToggle();
	});

	$("#profile-side-menu").find('.group-title').each(function() {
		
		$(this).toggleClass('active');
	});
	});



$(".sublevel").click(function(){
	$(this).next().fadeToggle();

		
		$(this).toggleClass('expand');
	
	});