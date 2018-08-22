
// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


var vm = new Vue({
	el: '#listeAnnonceEnCours',
	data: {
	annonces: [],
	idUtilisateur : id,
	src: "./resources/img/Annonce/",
	 imgtype: ".png",

	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/enCours/' + vm.idUtilisateur)
		.then(function (response) {
			vm.annonces = response.data
		})
	
	},
	methods: {
		getSrc: function(idProduit) {
			return this.src + idProduit + this.imgtype;
		},
		
		AnnulerMonAnnonce: function(idannonce){

			var vm = this;
			console.log("debut fonction annuler" + idannonce)
			alert(" Votre annonce est annulée")
			//récupération de l'annonce qu'on souhaite modifier
			axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/'+ idannonce)
			.then(function (response) {
				var annonceAmodif = response.data;
console.log(annonceAmodif)

				annonceAmodif['dateAnnulation'] = Date.now();

				for (i=0 ; i < vm.annonces.length ; i++) {
					if (idannonce == vm.annonces[i].idAnnonce) {
						vm.annonces.splice(i,1);
					}
				}
				
				//post pour la modification
				axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees',
						annonceAmodif).then((response)=> {
							console.log(response);
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


var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vb = new Vue({
	el: '#listeMesAnnonceTermines',
	data: {
		annonces: [],
		typeAnnoncesCloturees:'',
		annulations :[],
		nomAnnulation:'',
		 src: "./resources/img/Annonce/",
		 imgtype: ".png",
		 idUtilisateur : id,
	},
	watch : {
		typeAnnoncesCloturees: function (typeAnnonces, ancienTypeAnonces){
			this.chargerTypeAnnoncesCloturees(typeAnnonces)
		}
	},
	created : function () {
		var vb=this
		console.log('mes annonceTermines')
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/'+ vb.idUtilisateur)
		.then(function (response) {
			vb.annonces = response.data
		}),
			axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/annulation')
		.then(function(response) {
			vb.annulations = response.data
		})
	},

	methods: {
		
		getSrc: function(idProduit) {
			return this.src + idProduit + this.imgtype;
		},
		

		chargerTypeAnnoncesCloturees(typeAnnonces)
		{ 
			console.log(typeAnnonces+' = typeAnnonces')
			var vb=this
			if(typeAnnonces =="plop")
			{
				vb.annonce =[]
				console.log(typeAnnonces + 'choix1')
				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/'+ vb.idUtilisateur)
				.then(function (response) {
					vb.annonces = response.data
				})
			}
			if  (typeAnnonces =="mesAnnoncesAnnulees")
			{ 
				vb.annonce =[]
				console.log(typeAnnonces + 'choix2')
				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesAnnoncesTerminesCarAnnulees/'+ vb.idUtilisateur)
				.then(function(response)
						{ 
					vb.annonces = response.data
						})
			}
			else 
			{
				vb.annonce =[]
				axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesAnnoncesTerminesNonAnnulees/'+ vb.idUtilisateur)
				.then(function(response)
						{
					vb.annonces = response.data
						})
			}
		},
		insererMotifAnnulation:function(idAnnonce)
		{
			console.log("debut fonction")
			var vb = this;
		//	alert("Votre motif d'annulation est enregistré, Merci!")
			var idAnnulation = document.getElementById('selectMotifAnnulation').value;
			//récupération de l'annonce à modifier
			//var nomMotif = document.getElement
			var liste, texte;
			liste = document.getElementById("selectMotifAnnulation");
			texte = liste.options[selectMotifAnnulation.selectedIndex].text;
			
			axios.get ('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneAnnonce/'+ idAnnonce)
			.then(function (response){
				var annonceAmodif =response.data;
	
	
				//Instanciation d'une annulation
				var Annulation = {
						"idAnnulation": idAnnulation,
						
				}
				console.log(Annulation)
				annonceAmodif['annulation']= Annulation;
			
			
		
			//post pour la modification
			axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees',
					annonceAmodif).then((response)=> {
						consol.log(response);
			});
			// Afficher la nouvelle liste
			location.reload();
			})
			
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

var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

// PAS UTILISE
var vl = new Vue({
	el: '#listeMesEnviesEnCours',
	data: {
		annonces: [],
		id : idUtilisateur,
	},
	created : function () {
		var vl = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnvies/' + vl.idUtilisateur)
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

var vm = new Vue({

	el: '#listeMesPotentiellesEnvies',
	data: {
		annonces: [],
		id : idUtilisateur
	
	},
	created : function () {
		var vm = this

		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/' + vm.idUtilisateur)
		.then(function (response) {
			vm.annonces = response.data
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
/*
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];
var vl = new Vue({
	el: '#mesEnvieEnAttentes',
	data: {
		repannonces: [],
		id : idUtilisateur,
		src: "./resources/img/Annonce/",
		 imgtype: ".png",	
	
	},
	created : function () {
		var vl = this
		console.log("début")
		
		axios.get('http://localhost:8080/myappWeb/services/rest/reponses/envieAttente/' + vl.idUtilisateur)
		.then(function (response) {
			vl.repannonces = response.data
			console.log(vl.repannonces)
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


var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];
var vl = new Vue({
	el: '#listeMesEnviesTermines',
	data: {
		annonces: [],
		id : idUtilisateur,
	},
	created : function () {
		var vl = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnviesTermines/' + vl.idUtilisateur)
		.then(function (response) {
			console.log("listeMesEnviesTermines"+response.data)
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
*/
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