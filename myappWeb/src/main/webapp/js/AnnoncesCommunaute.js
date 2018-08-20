
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
	    afficherModal : false,
	    annonceDetail : [],
	    date1: "",
	    date2: "",
	    date3: "",
	    dateChoisie: null,
	    search: '',
	    voirCarte: false,
	    map: null,
	    tileLayer:null,
	    layers: []

	   
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data
	      })
	  },
	  
	  computed: {
			filteredList() {

				return this.annonce.filter(post => {
			        return post.stock.produit.nomProduit.toLowerCase().includes(this.search.toLowerCase())
			      })
			      
			}
	  },
	  
	  mounted() {
		this.initMap();
		this.initLayers();
	  },
	  
	  methods: {
		  
		initMap() {
			this.map = L.map(this.carte).setView([51.505, -0.09], 13);

			this.tileLayer = L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
			    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
			    maxZoom: 18,
			    id: 'mapbox.streets',
			    accessToken: 'pk.eyJ1Ijoid2luZ3kiLCJhIjoiY2preThmNnB3MGZlYTNrcWk3cWQzeDFtdCJ9.fRvBd-XU2TlX9QRMye3zLA'
			}).addTo(this.map);
		},
		
		initLayers() {
			
		},
		  
		// METHODE : générer le lien URL à partir d'un pseudo  
		getSrc: function(idproduit) {
			return this.src + idproduit + this.imgtype;
		},
		moment: function (date) {
			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('Do MMMM YYYY, h:mm:ss a');
		},
		voirAnnonceDetail: function (annonceSelection) {
			vm = this;
			vm.annonceDetail = annonceSelection;
			
			this.date1 = annonceSelection.dateRdv1;
			this.date2 = annonceSelection.dateRdv2;
			this.date3 = annonceSelection.dateRdv3;

			vm.afficherModal = true;

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
	    			  "dateRdv" : vm.dateChoisie,
	    			  "dateRefus" : null,
	    			  "dateReponse" : Date.now(),
	    			  "annonce" : annonceChoisie,
	    			  "utilisateur" : utilisateurEnCours
	    	  }
			console.log(repannonce);
			// POST
			console.log("début du post")
			
	    	axios.post('http://localhost:8080/myappWeb/services/rest/reponses/nouvelleReponse',
	    			repannonce).then((response) => {
	    				  console.log(response);
	    				  console.log("terminé");
	    			  });
		},
		
		// afficher sous forme de carte
		afficherCarte: function() {
			if (this.voirCarte == false) {
				this.voirCarte = true;
				document.getElementById('btnMap').textContent = "Afficher liste";
				console.log("fonction afficher carte");
				
				// initialisation de la carte
				

				
				
				
				
			} else {
				this.voirCarte = false;
				document.getElementById('btnMap').textContent = "Afficher la carte";
			}
			
			
		}

		
	  },
	  // CREATED NUMERO 2
	  // METHODEBIS : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data;
	        vm.date1 = vm.annonce.dateRdv1;
	        vm.date2 = vm.annonce.dateRdv2;
	        vm.date3 = vm.annonce.dateRdv3;
	        vm.selected = "";
	        
	      })

	  }
	
})


// Carte




