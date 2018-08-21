
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


// ANNONCES DE LA COMMUNAUTE
var annoncesAvecReponses = new Vue({
	
	  el: '#annoncesAvecReponses',
	  data: {
	    annonce: [],
	    reponse: [],
	    reponseRdv: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    src: "./resources/img/annoncescom/",
	    imgtype: ".png",
	    nbAnnonces: 0,
	    annonceSelectionnee : [],
	    srcuser: "./resources/img/utilisateur/",
		imgtypeuser: ".jpeg",
		btnRDVaConfirmer : true
	    
	    
	  },
	  methods: {
		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(idproduit) {
			return this.src + idproduit + this.imgtype;
		},
		getSrcUser: function(pseudo) {
			return this.srcuser + pseudo + this.imgtypeuser;
		},						
		moment: function (date) {
			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY hh:mm');
		},
		// Afficher les réponses d'une annonce
		afficherReponse: function(annonce) {
			var vm = this			
			vm.annonceSelectionnee = annonce;
			// Récupérer les réponses de l'annonce
			
		    axios.get('http://localhost:8080/myappWeb/services/rest/reponses/reponsesRecues/' + annonce.idAnnonce)
		      .then(function (response) {
		        vm.reponse = response.data;		        

		      })
			
		},
		
		// pour bouton accepter une réponse
		accepterRep: function(annonceTerminee, reponse) {
			console.log("accepter réponse");
			console.log(annonceTerminee);
			console.log(reponse);
			var vm = this;			
			// maj réponse et annonce dans la base
			for (var i = 0 ; i < vm.reponse.length ; i++) {				
				
				if (vm.reponse[i].idReponse == reponse.idReponse) { // Accepter la reponse
					
					
					// maj reponse
					vm.reponse[i].dateAcceptationReponse = Date.now();
					
					//ajout de la réponse dans repRdv
					vm.reponseRdv.push(vm.reponse[i]);
					
			    	// POST Reponse acceptee
			    	axios.post('http://localhost:8080/myappWeb/services/rest/reponses/maj',
			    			vm.reponse[i]).then((response) => {
			    				  
			    				// Maj annonce				
			    			annonceTerminee['dateFinAnnonce'] = Date.now();		    				    	  
		    						
    				    	// POST Reponse acceptee
    					    axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees',
    					    			annonceTerminee).then((response) => {
    					    			  });			    						
    						      })			    				  			    				  
			    			 					
				} else { // refuser les autres réponses
					vm.refuserRep(vm.reponse[i]);
				}
			}
			
			// maj liste annonce
			for (var i=0 ; i < vm.annonce.length ; i++) {
				if (vm.annonce[i][0].idAnnonce == annonceTerminee.idAnnonce) {
					vm.annonce.splice(i,1);
				}
			}
			
			// mettre la liste des réponse à null
			vm.reponse = [];	    			
		},
		
		// pour bouton refuser une réponse
		refuserRep: function(reponseRefusee) {
			console.log("refuser rep")
			var vm = this;
						
			
			// décrémente le nombre de réponse de vm.annonce :
			for (var i=0 ; i < vm.annonce.length ; i++) {				
				
				if ( vm.annonce[i][0].idAnnonce == reponseRefusee.annonce.idAnnonce) {
					vm.annonce[i][1] = vm.annonce[i][1] - 1;
					// supprime l'annonce lorsqu'il n'y a plus de réponses
					if (vm.annonce[i][1] == 0) {
						vm.annonce.splice(i,1);
					}
				}
			}
			
			// maj vm.reponse :
			var i;
			for (i = 0 ; i < vm.reponse.length ; i++) {								
				if (vm.reponse[i].idReponse == reponseRefusee.idReponse) {
					vm.reponse.splice(i,1);
				}
			}
			reponseRefusee.dateRefus = Date.now();
    	  
    	  // post			    	  
    	  axios.post('http://localhost:8080/myappWeb/services/rest/reponses/maj',
    			  reponseRefusee).then((response) => {
    			  });
		     
		},
				
		// Méthode : afficher le tableau des RDV à venir
		voirRdvAconfirmer: function() {
			this.btnRDVaConfirmer = true;
		},
		
		// Méthode : afficher le tableau des réponses à valider
		voirRdvAVenir: function() {
			this.btnRDVaConfirmer = false;
		}
		
	  },
	  
	  
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces ayant des réponses
	  created: function() {
		var vm = this;
		
		// annonce
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/annoncesAvecRep/' + vm.iduser)
	      .then(function (response) {
	    	  vm.annonce = response.data;
	    	  
	    	  // repRdv
	    	  axios.get('http://localhost:8080/myappWeb/services/rest/reponses/mesAnnoncesRdvAVenir/' + vm.iduser)
		      .then(function (response) {
		    	  vm.reponseRdv = response.data;
		      })

	      })
	  }
	
})