
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


// ANNONCES DE LA COMMUNAUTE
var annoncesAvecReponses = new Vue({
	
	  el: '#annoncesAvecReponses',
	  data: {
	    annonce: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    src: "./resources/img/annoncescom/",
	    imgtype: ".png",
	    
	    
	  },
	  methods: {
		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(idproduit) {
			return this.src + idproduit + this.imgtype;
		},
		moment: function (date) {
			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		}
		
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces ayant des réponses
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/annoncesAvecRep/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data;
	      })
	  }
	
})

// LISTE DES REPONSES
var LesReponsesRecues = new Vue({
	  el: '#LesReponsesRecues',
	  data: {
	    reponse: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    src: "./resources/img/utilisateur/",
	    imgtype: ".jpeg",
	    nbReponses: ""
	    	    
	  },
	  
	  watch: {
		  nbReponses: function() {
			var vm = this
		    axios.get('http://localhost:8080/myappWeb/services/rest/reponses/reponsesRecues/' + 14)
		      .then(function (response) {
		        vm.reponse = response.data;
		      })		  
		  }
	  },
	  
	  methods: { 
		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
		moment: function (date) {
			return moment(date);
		},
		
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		},
		
		accepterRep: function(idReponse, idAnnonce) {
			
			var vm = this;
			
			var i;
			for (i = 0 ; i < vm.reponse.length ; i++) {				
				
				if (vm.reponse[i].idReponse == idReponse) { // Accepter la reponse
					// maj reponse
					vm.reponse[i].dateAcceptationReponse = Date.now();
					
			    	// POST Reponse acceptee
			    	axios.post('http://localhost:8080/myappWeb/services/rest/reponses/maj',
			    			vm.reponse[i]).then((response) => {
			    				  
			    				// Maj annonce				
		    					// récupérer annonce
		    					axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/' + idAnnonce)
		    				      .then(function (response) {
		    				    	  
		    				    	  var annonceTerminee = response.data;
		    				    	  annonceTerminee['dateFinAnnonce'] = Date.now();
		    						
		    				    	// POST Reponse acceptee
		    					    axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees',
		    					    			annonceTerminee).then((response) => {
		    					    			  });			    						
		    						      })			    				  			    				  
			    			  });
					
				} else { // refuser les autres réponses
					vm.refuserRep(vm.reponse[i]);
				}
			}
							      
			vm.nbReponses = 0;
	    			
		},
		
		refuserRep: function(reponseRefusee) {
			var vm = this;
			reponseRefusee.dateRefus = Date.now();
    	  
    	  // post			    	  
    	  axios.post('http://localhost:8080/myappWeb/services/rest/reponses/maj',
    			  reponseRefusee).then((response) => {
    				  vm.nbReponses = vm.nbReponses - 1;
    			  });
		     
		}
		
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération les réponses d'une annonce
	  created: function(idAnnonce) {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/reponses/reponsesRecues/' + 14)
	      .then(function (response) {
	        vm.reponse = response.data;
	        vm.nbReponses = vm.reponse.length;
	      })
	  }
	
	
})	
