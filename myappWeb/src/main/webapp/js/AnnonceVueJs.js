
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vm = new Vue({
	el: '#listeAnnonceEnCours',
	data: {
		annonces: [],
		annulations :[]

	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/enCours/3')
		.then(function (response) {
			vm.annonces = response.data
		})
	},
	methods: {
		AnnulerMonAnnonce: function(idannonce){

			var vm = this;
			alert(idannonce+ " Votre annonce est annulée" + Date.now())
			//récupération de l'annonce qu'on souhaite modifier
			axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/'+ idannonce)
			.then(function (response) {
				var annonceAmodif = response.data;


				annonceAmodif['dateAnnulation'] = Date.now();

				//post pour la modification
				axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees',
						annonceAmodif).then((response)=> {
							consol.log(response);
						});
				// Afficher la nouvelle liste
				location.reload();
			})
		},

		moment: function (date) {
			return moment(date);
		},
		date: function (date) {
			return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		}
	},
	filters: {
		moment: function (date) {
			return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		}
	}

})

var vb = new Vue({
	el: '#listeMesAnnonceTermines',
	data: {
		annonces: [],
		typeAnnoncesCloturees:''
	},
	watch : {
		typeAnnoncesCloturees: function (typeAnnonces, ancienTypeAnonces){
			this.chargerTypeAnnoncesCloturees(typeAnnonces)
		}
	},
	created : function () {
		console.log('init');
		this.chargerAnnonces()

	},

	methods: {
		chargerAnnonces()
		{
			var vb = this
			console.log('plopplop charger annonce du dbéut')
			axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/8')
			.then(function (response) {
				vb.annonces = response.data
			})
		},

		chargerTypeAnnoncesCloturees(typeAnnonces)
		{ 
			console.log(typeAnnonces+' = typeAnnonces')
			var vb=this
			if(typeAnnonces =="plop")
			{
				console.log(typeAnnonces + 'choix1')
				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/8')
				.then(function (response) {
					vb.annonces = response.data
				})
			}
			if  (typeAnnonces =="mesAnnoncesAnnulees")
			{ 
				console.log(typeAnnonces + 'choix2')
				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesAnnoncesTerminesCarAnnulees/8')
				.then(function(response)
						{ 
					vb.annonces = response.data
						})
			}
			else 
			{

				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesAnnoncesTerminesNonAnnulees/8')
				.then(function(response)
						{
					vb.annonces = response.data
						})
			}
		},
		// Afficher les date correctement et en français
		moment: function (date) {


			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');

		}

	},


	filters: {
		moment: function (date) {
			return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		}
	}

})

var vl = new Vue({
	el: '#listeMesEnviesEnCours',
	data: {
		annonces: []

	},
	created : function () {
		var vl = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnvies/8')
		.then(function (response) {
			vl.annonces = response.data
		})
	},
	methods: {
		moment: function (date) {

			return moment(date);
		},
		date: function (date) {


			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		}
	},
	filters: {
		moment: function (date) {
			return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		}
	}

})

// MES POTENTIELLES ENVIES = TOUTES LES ANNONCES DE LA COMMUNAUTE ______________________________________________
var vm = new Vue({

	el: '#listeMesPotentiellesEnvies',
	data: {
		annonces: [],
		selected: "",
		choixRdv: "",
		iduser: id // Récupération de l'idUtilisateur de la session
	},
	created : function () {
		var vm = this

		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.iduser)
		.then(function (response) {
			vm.annonces = response.data;
		})
		
	},
	methods: {
		moment: function (date) {
			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		},
		majvalueRdv : function(dateRdv) {
			var vm = this;
			this.choixRdv = dateRdv;
			console.log(this.choixRdv)
		},
		envoyerDemandes: function () {
			var vm = this;
			var r = confirm("Vous avez choisi un RDV pour le " + moment(this.choixRdv).locale('fr').format('Do MMMM YYYY, à h:mm a') + ".\n\nCliquez sur OK pour confirmer." );
			
			if (r==true) {
				alert("Votre demande a bien été envoyée.");
				
				// Récupération de l'utilisateur de la session
				axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur/' + vm.iduser)
			      .then(function (response) {
			    	  var user = response.data;

			    	  
			    	  // Récupération de l'annonce
						axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/' + 1)
					      .then(function (response2) {
					    	  var annonce = response2.data;

					    	  // Instanciation d'une réponse
					    	  var reponse = {
					    			  "idReponse" : null,
					    			  "dateAcceptationReponse" : null,
					    			  "dateAnnulationReponse" : null,
					    			  "dateRdv" : vm.choixRdv,
					    			  "dateRefus" : null,
					    			  "dateReponse" : Date.now(),
					    			  "utilisateur" : user,
					    			  "annonce" : annonce
					    	  }
					    	   console.log(reponse)
					    	  console.log("debut fonction POST")
					    	  // POST
					    	  axios.post('http://localhost:8080/myappWeb/services/rest/reponses/envoieDemande',
					    			  foodfriendJson).then((response) => {
					    				  console.log("dans la fonction POST")
					    				  console.log(response);
					    				  console.log("fin post")
					    			  });
					    	  
					      })
				    	  
				      })		      
				}
			
		}
	},
	filters: {
		moment: function (date) {
			return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		}
	}
})


var vl = new Vue({
	el: '#listeMesEnviesEnAttente',
	data: {
		annonces: []
	
	},
	created : function () {
		var vl = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnvies/8')
		.then(function (response) {
			vl.annonces = response.data
		})
	},
	methods: {
		moment: function (date) {
			
			return moment(date);
		},
		date: function (date) {
			
			
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		}
	},
	filters: {
		    moment: function (date) {
		      return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		    }
		  }

})


//Pour RadioButton mais marche pas 
//new Vue({
//el:"#DatesRdv",
//data : {
//picked : ''
//}   
//})

/*var titre = new Vue({
	el : '#pseudoTitre',
	data : {
		annonce 
	},
})*/