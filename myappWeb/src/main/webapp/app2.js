
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


// ANNONCES DE LA COMMUNAUTE
var listeannoncesCommnunaute = new Vue({
	
	  el: '#annoncesCommunaute',
	  data: {
	    annonce: [],
	    iduser: id, // Récupération de l'idUtilisateur de la session
	    useradresseprinc: "", // adresse principale de l'utilisateur
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
//	    voirCarte: false,
	    map: null,
	    tileLayer: null,
//	    layers: [],
	    seen: true
//	    annonceMapChoisie: [],
	    
	   
	  },
	  
	  watch: {
		  map: function() {
			  this.initLayers();
		  }
	  },
	  
	  mounted() {
		  this.initMap();		  
	  },
	  
	  // METHODE : qui se lance à la création de la page : récupération de la liste des annonces de la communaute
	  created: function() {
		var vm = this
		
		// récupération de l'adresse principale de l'utilisateur
		axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur/adresse?iduser=' + vm.iduser)
	      .then(function (response) {
	        vm.useradresseprinc = response.data;
	      });
		
		// récupération des annonces
	    axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.iduser)
	      .then(function (response) {
	        vm.annonce = response.data;
	        
	        // Calcul de la distance en km par rapport à l'utilisateur pour chaque annonce
	        var i;
		    for (i=0 ; i < vm.annonce.length ; i++) {	    	
		    	vm.annonce[i].distance = vm.calculDistance(vm.useradresseprinc.adresse.x, vm.useradresseprinc.adresse.y, vm.annonce[i].adresse.x, vm.annonce[i].adresse.y, "K");
		    }
		    
		    
	        
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
			  
			  this.map = L.map('map').setView([vm.useradresseprinc.adresse.x, vm.useradresseprinc.adresse.y], 16);

			  this.tileLayer = L.tileLayer(
			    'https://cartodb-basemaps-{s}.global.ssl.fastly.net/rastertiles/voyager/{z}/{x}/{y}.png',
			    {
			      maxZoom: 18,
			      id: 'mapbox.streets',
			      accessToken: 'pk.eyJ1Ijoid2luZ3kiLCJhIjoiY2preThmNnB3MGZlYTNrcWk3cWQzeDFtdCJ9.fRvBd-XU2TlX9QRMye3zLA'
			    }
			  );
			  
			  this.tileLayer.addTo(this.map);
			  
			  var marker = L.marker([48.8162038, 2.327159599999959]).addTo(this.map);
			  marker.valueOf()._icon.src = "http://localhost:8080/myappWeb/resources/leaflet/images/marker-icon-green.png";
			  marker.bindPopup("Ma position !");
			  marker.on('mouseover', function (e) {
		            this.openPopup();
		        });
				
				marker.on('mouseout', function (e) {
		            this.closePopup();
		        });
			  
			  
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
						this.annonceDetail = this.annonce;
						vm.afficherModal = true;
						});				
										
			  }
			  
		},
		
		calculDistance: function(lat1, lon1, lat2, lon2, unit) {
			var radlat1 = Math.PI * lat1/180;
			var radlat2 = Math.PI * lat2/180;
			var theta = lon1-lon2;
			var radtheta = Math.PI * theta/180;
			var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
			if (dist > 1) {
				dist = 1;
			}
			dist = Math.acos(dist);
			dist = dist * 180/Math.PI;
			dist = dist * 60 * 1.1515;
			if (unit=="K") { dist = dist * 1.609344 };
			if (unit=="N") { dist = dist * 0.8684 };
			return dist.toFixed(2);
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

			// POST
			
	    	axios.post('http://localhost:8080/myappWeb/services/rest/reponses/nouvelleReponse',
	    			repannonce).then((response) => {
	    			  });		
			
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






