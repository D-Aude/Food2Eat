
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
	    tileLayer: null,
	    layers: [],
	    seen: true,
	    markerList: []

	   
	  },
	  
	  watch: {
		  map: function() {
			  console.log("watcher map")
			  this.initLayers();
		  }
	  },
	  
	  mounted() {
		  this.initMap();		  
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
	  created: function() {
		var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data;
	        console.log("fin fonction created")
	      });
	    
	      
	  },
	  
	  computed: {
			filteredList() {

				return this.annonce.filter(post => {
			        return post.stock.produit.nomProduit.toLowerCase().includes(this.search.toLowerCase())
			      })
			      
			}
	  },	  
	  
	  methods: {
		
		// Code pour la carte
		  initMap() {
			  
			  this.map = L.map('map').setView([48.8162038, 2.327159599999959], 16);

			  this.tileLayer = L.tileLayer(
			    'https://cartodb-basemaps-{s}.global.ssl.fastly.net/rastertiles/voyager/{z}/{x}/{y}.png',
			    {
			      maxZoom: 18,
			      id: 'mapbox.streets',
			      accessToken: 'pk.eyJ1Ijoid2luZ3kiLCJhIjoiY2preThmNnB3MGZlYTNrcWk3cWQzeDFtdCJ9.fRvBd-XU2TlX9QRMye3zLA'
			    }
			  );
			  
			  this.tileLayer.addTo(this.map);
			  
			  console.log(this.annonce);
			  
		  },
		  initLayers() {
			  
			  for (i=0; i < this.annonce.length ; i++) {
					var marker = L.marker([this.annonce[i].adresse.x, this.annonce[i].adresse.y]).addTo(this.map);
					var nomProduit = this.annonce[i].stock.produit.nomProduit
					marker.bindPopup(nomProduit);
					marker.annonce = this.annonce[i];
					// Evenement
					marker.on('mouseover', function (e) {
			            this.openPopup();
			        });
					
					marker.on('mouseout', function (e) {
			            this.closePopup();
			        });
					
					marker.on('click', function(e, info) {
						alert(this.annonce.stock.utilisateur.pseudo + " donne " + this.annonce.stock.produit.nomProduit);
						});				
					
					
					this.markerList.push(marker);
			  }
			  
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
		
		afficherAnnonces: function() {
			
			var i;

			for (i=0; i < this.annonce.length ; i++) {
				var marker = L.marker([this.annonce[i].adresse.x, this.annonce[i].adresse.y]).addTo(this.map);
				var nomProduit = this.annonce[i].stock.produit.nomProduit
				marker.bindPopup(nomProduit);
				marker.annonce = this.annonce[i];
				// Evenement
				marker.on('mouseover', function (e) {
		            this.openPopup();
		        });
				
				marker.on('mouseout', function (e) {
		            this.closePopup();
		        });
				
				marker.on('click', function(e, info) {
					alert(this.annonce.stock.utilisateur.pseudo + " donne " + this.annonce.stock.produit.nomProduit);
					});				
				
				
				this.markerList.push(marker);
			}
			

			
		},
		
		// afficher sous forme de carte
		afficherCarte: function() {
			if (this.seen == false) {
				this.seen = true;
				document.getElementById('btnMap').textContent = "Afficher sur la carte";
				document.getElementById('map').style.visibility = "hidden";
				// initialisation de la carte				
				
			} else {
				this.seen = false;
				document.getElementById('btnMap').textContent = "Afficher la liste";
				document.getElementById('map').style.visibility = "visible";
				this.initLayers();
			}
			
			
		}
	  }
	
})


// Carte



