

var vm = new Vue({
	el: '#listeAnnonceReponseSansValidation',
	data: {
		annonces: []
	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/avalidees/3')
		.then(function (response) {
			vm.annonces = response.data
		})
	},
	methods: {
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
		annonces: []
	
	},
	created : function () {
		var vb = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/8')
		.then(function (response) {
			vb.annonces = response.data
		})
	},
	methods: {
		moment: function (date) {
			moment.local('fr');
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


/* Pour RadioButton mais marche pas 
new Vue({
	   el:"#DatesRdv",
	   data(){
	       selected : 'dateRdv1'
	   }   
	 })
	 */
/*var titre = new Vue({
	el : '#pseudoTitre',
	data : {
		annonce 
	},
})*/