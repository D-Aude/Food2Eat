function initComportementJs(){
	console.log("initComportementJs");
	var btnEnregistrer =  document.querySelector("#btnEnvoyer");
	btnEnregistrer.addEventListener("click",enregistrer,false);
	
	var btnListeInscriptions =  document.querySelector("#btnListeInscriptions");
	btnListeInscriptions.addEventListener("click",recupererListeInscriptions,false);
}

function afficherDansTable(listeClientAsJsonString){
	var tabClientJs = JSON.parse(listeClientAsJsonString);
	var eltTable = document.querySelector("#idTable");
	var eltTbody = eltTable.getElementsByTagName("tbody").item(0);
	var nbLignes = eltTbody.getElementsByTagName("tr").length;
	for(i=0;i<nbLignes;i++){
	  eltTable.deleteRow(-1/* -1 ou numLigne */); //supprime la dernière ligne du tableau si -1
	}
	
	//initialisation "Handlebars"
	var sourceTemplateDansTr   = document.getElementById("template-dans-tr").innerHTML;
	var templateHandlebarsDansTr = Handlebars.compile(sourceTemplateDansTr);
	
	for(i=0;i<tabClientJs.length;i++){
		var clientJs = tabClientJs[i];
		var eltTr = document.createElement("tr");
		/*
		eltTr.innerHTML="<td>" + clientJs.prenom + "</td>"
		                   + "<td>"+clientJs.nom + "</td>"
		                   + "<td>...</td>";*/
		var htmlDansTr    = templateHandlebarsDansTr(clientJs);
		eltTr.innerHTML = htmlDansTr;
		eltTbody.appendChild(eltTr);
	}
}

function recupererListeInscriptions(){
	var httpRequest = new XMLHttpRequest(); //objet prédéfini du navigateur
    //pour declencher requête Ajax (XHR : XML Http Request)

	//on enregistre sur httpRequest une fonction anonyme "callback"
	//pour traiter la réponse qui va arriver en différé
	httpRequest.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
		//si status HTTP en retour == 200 : OK 
		//document.querySelector("#spanTableau").innerHTML = this.responseText; 
		afficherDansTable(this.responseText);
		}
	};
	//declenchement de la requête:
	httpRequest.open("GET", "./services/rest/client");
	httpRequest.send(null);
}

function enregistrer(){
		  var nom =  document.querySelector("#nom").value; 
		             //document.getElementById("nom").value; 
		  var prenom =   document.querySelector("#prenom").value;  
		  
		  var client = {
			numClient : null,
			nom : nom ,
			prenom : prenom 
		  }; //expression "objet lit(t)eral javascript" (très proche du format JSON) .
		  
		  //on peut dynamiquement ajouter des propriétés supplémentaires sur l'objet client :
		  client.telephone = document.querySelector("#telephone").value;
		  client.adresse = document.querySelector("#adresse").value;
		  client.email = document.querySelector("#email").value;
	      var clientAsJsonString = JSON.stringify(client);
	      
	      var httpRequest = new XMLHttpRequest(); //objet prédéfini du navigateur
	                  //pour declencher requête Ajax (XHR : XML Http Request)

	      //on enregistre sur httpRequest une fonction anonyme "callback"
	      //pour traiter la réponse qui va arriver en différé
	      httpRequest.onreadystatechange = function() {
	    	  if (this.readyState == 4 && this.status == 200) {
	    		 //si status HTTP en retour == 200 : OK 
	    		  document.querySelector("#spanRes").innerHTML = this.responseText; 
	    	  }
	      };
	      //declenchement de la requête:
	      httpRequest.open("POST", "./services/rest/client");
	      httpRequest.setRequestHeader("Content-Type" , "application/json");
	      httpRequest.send(clientAsJsonString);
	      console.log ("donnees de la requete envoyee : " + clientAsJsonString)	; 	  
}