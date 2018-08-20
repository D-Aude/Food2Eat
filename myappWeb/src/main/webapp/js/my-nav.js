$( function() {
	// ici, le DOM de la page est entièrement défini et peut être manipulé via jQuery
	console.log("initialisation comportement page via jquery");
	$('#btnPartieA').on('click', function(){
		$("#divContent").load("./sous_page_a.html");
	  }
	);
	
	$('#btnPartieB').on('click',function(){
		$("#divContent").load("./sous_page_b.html");
	  }
	);//fin de on-click
	
});