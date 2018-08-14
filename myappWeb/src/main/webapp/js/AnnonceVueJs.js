

var vm = new Vue({
	el: '#listeAnnonceEnCours',
	data: {
		annonces: [],
		

	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/enCours/8')
		.then(function (response) {
			vm.annonces = response.data
		})
	
	},
	methods: {
		AnnulerMonAnnonce: function(idannonce){

			var vm = this;
			alert(" Votre annonce est annulée")
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
		typeAnnoncesCloturees:'',
		annulations :[],
		nomAnnulation:''
	},
	watch : {
		typeAnnoncesCloturees: function (typeAnnonces, ancienTypeAnonces){
			this.chargerTypeAnnoncesCloturees(typeAnnonces)
		}
	},
	created : function () {
		var vb = this
		console.log('init');
		this.chargerAnnonces(),

			axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/annulation')
		.then(function(response) {
			vb.annulations = response.data
		})
	},

	methods: {
		chargerAnnonces()
		{
			
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
var vm = new Vue({

	el: '#listeMesPotentiellesEnvies',
	data: {
		annonces: []
	},
	created : function () {
		var vm = this

		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/autresAnnonces/3')
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