
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


// ANNONCES DE LA COMMUNAUTE
var listeannoncesCommnunaute = new Vue({
	
	  el: '#annoncesCommunaute',
	  data: {
	    annonce: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    idAnnonceEncours : "",
	    src: "./resources/img/annoncescom/",
	    imgtype: ".png",
	    annonceDetail : [],
	    produitDetail : [],
	    utilisateurDonneurDetail : [],
	    date1: "",
	    date2: "",
	    date3: "",
	    dateChoisie: ""
	   
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
		},
		voirAnnonceDetail: function (annonceSelection) {
			
			this.annonceDetail = annonceSelection;
			this.produitDetail = annonceSelection.stock.produit;
			this.utilisateurDonneurDetail = annonceSelection.stock.utilisateur;
			
			this.date1 = annonceSelection.dateRdv1;
			this.date2 = annonceSelection.dateRdv2;
			this.date3 = annonceSelection.dateRdv3;
			
			console.log(this.utilisateurDonneurDetail)

		}
		
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data
	      })
	  }
	
})

// ANNONCES DETAIL : REPONDRE A L ANNONCE
var annoncesCommunauteDetail = new Vue({
	  el: '#annoncesCommunauteDetail',
	  data: {
		annonceDetail: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    src: "./resources/img/annoncescom/",
	    imgtype: ".png",
	    selected: "",
	    date1 : "",
	    date2 : "",
	    date3 : ""


	    	    
//	  },
//	  
//	  watch: {
//		  idAnnonce: function() {
//			  var vm = this
//			  axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/' + vm.idAnnonce)
//			      .then(function (response) {
//			        vm.annonceDetail = response.data;
//			        vm.date1 = vm.annonceDetail.dateRdv1;
//			        vm.date2 = vm.annonceDetail.dateRdv2;
//			        vm.date3 = vm.annonceDetail.dateRdv3;
//			        vm.selected = "";
//			        console.log("annoncedetail");
//			        console.log(vm.annonceDetail.stock.produit.nomProduit);
//			      })
//		  }
		  
		  
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
		},
		
		envoyerDemandeProduit: function(idAnnonce) {
			console.log("Début de la fonction");
			var vm = this;
			
			// Instanciation d'une annonce avec l'idAnnonce
			var annonceChoisie = {
					"idAnnonce" : idAnnonce
			}
			
			// Instanciation de l'utilisateur de la session
			var utilisateurEnCours = {
					"idUtilisateur" : vm.iduser
			}
			
			
			// Instanciation d'une Repannonce
	    	var repannonce = {
	    			  "idReponse" : null,
	    			  "dateAcceptationReponse" : null,
	    			  "dateAnnulationReponse" : null,
	    			  "dateRdv" : vm.selected,
	    			  "dateRefus" : null,
	    			  "dateReponse" : Date.now(),
	    			  "annonce" : annonceChoisie,
	    			  "utilisateur" : utilisateurEnCours
	    	  }
			
			// POST
			console.log("début du post")
			
	    	axios.post('http://localhost:8080/myappWeb/services/rest/reponses/nouvelleReponse',
	    			repannonce).then((response) => {
	    				  console.log(response);
	    				  console.log("terminé");
	    			  });
		}
		
//	  },
//	  
//	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
//	  created: function() {
//		var vm = this
//	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/' + vm.idAnnonce)
//	      .then(function (response) {
//	        vm.annonce = response.data;
//	        vm.date1 = vm.annonce.dateRdv1;
//	        vm.date2 = vm.annonce.dateRdv2;
//	        vm.date3 = vm.annonce.dateRdv3;
//	        vm.selected = "";
//	        
//	      })
	  }
	
	
})	
