
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

// MES FOODFRIEND ____________________________________________________________________________
var mesFoodfriend = new Vue({
  
  el: '#listeFoodfriend',
  data: {
    foodfriend: [],
    iduser: id, // Récupération de l'idUtilisateur de la session
    src: "./resources/img/utilisateur/",
    imgtype: ".jpeg",
    
    
  },
  methods: {
	// METHODE : générer le lien URL à partir d'un pseudo  
	getSrc: function(pseudo) {
		return this.src + pseudo + this.imgtype;
	},
	
	// METHODE : retirer un foodfriend
	RetirerFF: function(idfoodfriend, pseudo) {

		alert(pseudo + " n'est plus votre foodfriend.")			
		// Instanciation
		var vm = this;

		// Récupération du foodfriend qui nous interesse
		axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
	      .then(function (response) {
	    	  var ffjson = response.data;

	    	  ffjson['dateFinRelation'] = Date.now();
	    	  
	    	  // post			    	  
	    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
	    			  ffjson).then((response) => {
	    				  console.log(response);
	    			  });
	    	  
	    	  // redirection vers l'acceuil
	  	    location.reload();

	      })		      
	    
	}
	
	
  },
  // METHODE : qui se lance à la création de la page : récupération de la liste des foodfriend 
  created: function() {
	var vm = this
    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=' + vm.iduser)
      .then(function (response) {
        vm.foodfriend = response.data
      })
  }
  
})

// MES DEMANDES RECUES ________________________________________________________________________
var mesDemandesFFRecues = new Vue({
	  
	  el: '#listeDemandesFoodfriend',
	  data: {
	    foodfriend: [],
	    iduser: id,
	    src: "./resources/img/utilisateur/",
	    imgtype: ".jpeg"
	    	
	  },
	  
	  	  
	  methods: {
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
		
		RefuserDemande: function(idfoodfriend) {

			alert("Votre réponse a bien été prise en compte.")			
			// Instanciation
			var vm = this;

			// Récupération du foodfriend qui nous interesse
			axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
		      .then(function (response) {
		    	  var ffjson = response.data;

		    	  ffjson['dateReponse'] = Date.now();
		    	  ffjson['dateFinRelation'] = Date.now();
		    	  
		    	  // post			    	  
		    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
		    			  ffjson).then((response) => {
		    				  console.log(response);
		    			  });
		    	  
		    	  // redirection vers la page d'accueil
		  	    location.reload();

		      })		      
		    
		},
		

		AccepterDemande: function(idfoodfriend) {

			alert("Félicitation ! Vous avez un nouveau foodfriend !")			
			// Instanciation
			var vm = this;

			// Récupération du foodfriend qui nous interesse
			axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
		      .then(function (response) {
		    	  var ffjson = response.data;

		    	  ffjson['dateReponse'] = Date.now();
		    	  
		    	  // post			    	  
		    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
		    			  ffjson).then((response) => {
		    				  console.log(response);
		    			  });
		    	  
		    	  // récupérer les nouvelles données
		  	    location.reload();

		      })		      
		    
		}
	  
	  },
	   
	  created: function() {
	    
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=' + vm.iduser)
	      .then(function (response) {
	        vm.foodfriend = response.data
	      })
	      
	    
	  }
	  
})

// UTILISATEURS NOT FOODFRIEND
var utilisateursNotFoodfriend = new Vue({
	
	  el: '#listeUtilisateurs',
	  data: {
	    utilisateur: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    src: "./resources/img/utilisateur/",
	    imgtype: ".jpeg",
	    
	    
	  },
	  methods: {
		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
		
		EnvoyerInvitation: function() {
			console.log("envoie d'une demande de foodfriend")
		}
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des utilisateurs avec qui je ne suis pas foodfriend 
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur/search?iduser=' + vm.iduser)
	      .then(function (response) {
	        vm.utilisateur = response.data
	      })
	  }
	
})