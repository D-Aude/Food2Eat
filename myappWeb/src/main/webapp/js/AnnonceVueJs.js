

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

var vb = new Vue({
	el: '#listeMesAnnonceTermines',
	data: {
		annonces: []
	},
// Ecoute la valeur de la liste déroulante
	watch :{							
		typeAnnonceTerminees : function (typeAnnoncesTerminees,AncienTypeDemandé)
		{
			this.chargerTypeAnnoncesTerminees(typeAnnoncesTerminees)
		}
	},
// Instancier automatique lorsqu'on arrive au div
	created : function () {
		var vb = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/8')
		.then(function (response) {
			vb.annonces = response.data
		})
	},
// Afficher les date correctement et en français
	methods: {
		moment: function (date) {
			moment.local('fr');
			return moment(date);
		},
		date: function (date) {
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
			
		}
	},
	chargerTypeAnnoncesTerminees(typeAnnoncesTerminees){
		
	},
	filters: {
		    moment: function (date) {
		      return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		    }
		  }

})

var vplop = new Vue({
	el: '#listeMesAnnonceEnCours',
	data: {
		annonces: []
	},
	created : function () {
		var vplop = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/enCours/3')
		.then(function (response) {
			vplop.annonces = response.data
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
//	   el:"#DatesRdv",
//	   data : {
//		   picked : ''
//	   }   
//	 })
	 
/*var titre = new Vue({
	el : '#pseudoTitre',
	data : {
		annonce 
	},
})*/