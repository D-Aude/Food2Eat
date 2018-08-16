
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
//		nbFoodfriend: ""    
	},
  
//	watch: {
	  // Méthode lancee à chaque fois que nbFoodfriend est modifié
//		nbFoodfriend: function() {
//		var vm = this;
//	  	axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=' + vm.iduser)
//	      .then(function (response) {
//	        vm.foodfriend = response.data;
//	      })
//		}
//	},
  
  	methods: {
  		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
	
		// METHODE : retirer un foodfriend
	
		RetirerFF: function(foodfriend) {
			
			var vm = this;
			var i;

			// Maj vm.foodfriend (liste des foodfriend)
			for (i=0 ; i < vm.foodfriend.length ; i++) {
				if (foodfriend.idFoodfriend == vm.foodfriend[i].idFoodfriend) {
					vm.foodfriend.splice(i,1);
				}
			}

			// maj attrinute dateFinRelation du foodfriend selectionne
			foodfriend.dateFinRelation = Date.now();
			
		  	  // post			    	  
		  	axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
		  			foodfriend).then((response) => {
		  			vm.nbFoodfriend = vm.nbFoodfriend - 1;
		  				
	  			  });	  		  	
		},	
  	},
  	
  	// METHODE : qui se lance à la création de la page : récupération de la liste des foodfriend 
  	created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=' + vm.iduser)
	      .then(function (response) {
	        vm.foodfriend = response.data;
//	        vm.nbFoodfriend = vm.foodfriend.length;
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
	    imgtype: ".jpeg",
//	    nbInvitations: ""
	    	
	},
	 
//	watch: {
//  // Méthode lancee à chaque fois que nbFoodfriend est modifié
//		nbInvitations: function() {
//		    var vm = this;
//			axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=' + vm.iduser)
//		      .then(function (response) {
//		        vm.foodfriend = response.data;
//		      })
//		}
//	},
	  	  
	methods: {
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
		
		RefuserDemande: function(foodfriend) {

			alert("Votre réponse a bien été prise en compte.")			
			// Instanciation
			var vm = this;
			

			// Maj vm.foodfriend (liste des invitations)
			for (i=0 ; i < vm.foodfriend.length ; i++) {
				if (foodfriend.idFoodfriend == vm.foodfriend[i].idFoodfriend) {
					vm.foodfriend.splice(i,1);
				}
			}
			
			
			// Maj foodfriend
	    	  foodfriend.dateReponse = Date.now();
	    	  foodfriend.dateFinRelation = Date.now();
	    	  
	    	  // post			    	  
	    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
	    			  foodfriend).then((response) => {
//	    			  vm.nbInvitations = vm.nbInvitations - 1;	     				  
	    			  });
	    	   
		},
		

		AccepterDemande: function(foodfriend) {
			console.log(foodfriend);
			alert("Félicitation ! Vous avez un nouveau foodfriend !")			
			// Instanciation
			var vm = this;
			
			// Maj vm.foodfriend (liste des invitations)
			for (i=0 ; i < vm.foodfriend.length ; i++) {
				if (foodfriend.idFoodfriend == vm.foodfriend[i].idFoodfriend) {
					vm.foodfriend.splice(i,1);
				}
			}

			// Maj foodfriend

			foodfriend.dateReponse = Date.now();
		    console.log(foodfriend);	  
	    	  // post			    	  
	    	axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
	    			  foodfriend).then((response) => {
	    				  console.log(response);
//	    				  vm.nbInvitations = vm.nbInvitations - 1;
	    				  
	    			  });
	    	
		}
	  
	  },
	   
	  
	  created: function() {
	    
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=' + vm.iduser)
	      .then(function (response) {
	        vm.foodfriend = response.data;
//	        vm.nbInvitation = vm.foodfriend.length;
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
		
		// METHODE : envoie d'une invitation foodfriend
		EnvoyerInvitation: function(idFoodfriend) {
			alert("Votre invitation a bien été envoyée.")
			console.log("Début fonction EnvoyerInvitaion" + idFoodfriend)
			var vm = this;
			
			// Récupérer l'utilisateur principale
			axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur/' + vm.iduser)
		      .then(function (response) {
		    	  var user1 = response.data;

		    	  // Récupérer l'utilisateur qui recevra l'invitation
		    	  axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur/' + idFoodfriend)
			      .then(function (response2) {
			    	  var user2 = response2.data;
			    	  
			    	  // Instanciation d'un foodfriend
			    	  var foodfriendJson = {
			    			  "idFoodfriend" : null,
			    			  "dateDemande" : Date.now(),
			    			  "dateFinRelation" : null,
			    			  "dateReponse" : null,
			    			  "utilisateur1" : user1,
			    			  "utilisateur2" : user2			    			  
			    	  }

			    	  // POST
			    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend/envoieInvitation',
			    			  foodfriendJson).then((response) => {
			    				  console.log(response);
			    			  });
			    	  
			      })
		    	  
		      })		      
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