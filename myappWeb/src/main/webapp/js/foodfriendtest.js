function initComportementJs(){
	console.log("initComportementJs");
	$('#btnEnregistrer').on('click',enregistrer);
	console.log("fin");
}



function recupFoodfriend(idfoodfriend) {
	$.ajax({
		type: "GET",
		url: "./services/rest/foodfriend/" + idfoodfriend,
		success: function (data, status, xhr) {
			console.log(JSON.stringify(data));
			$("#spanRes").html(JSON.stringify(data));
			return JSON.stringify(data);
		}
	});
	

}

function enregistrer(){ 
	console.log("Début fonction enregistrer");
	
	var idfoodfriend = $('#idfoodfriend').val();
	var foodfriendAsJsonString = recupFoodfriend(idfoodfriend);
    
	console.log(foodfriendAsJsonString);
	foodfriendAsJsonString["dateReponse"] = '2018-08-30';
	
    $.ajax({
         type: "POST",
         url: "./services/rest/foodfriend",
         contentType : "application/json" ,
         dataType: "json",
		 data: foodfriendAsJsonString,
         success: function (data) {
      	   $("#spanRes").html(JSON.stringify(data)); 
         },
         error: function(xhr, status, error){
      	   $("#spanRes").html( "status:" + status + " error : " + error ) ;
         }
		  });
		
}