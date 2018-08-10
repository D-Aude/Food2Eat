

var vm = new Vue({
	el: '#listeAnnonceEnCours',
	data: {
		annonces: []
	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/enCours/3')
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


var vl = new Vue({
	el: '#listeMesAnnonceTermines',
	data: {
		annonces: []
	},
	created : function () {
		var vl = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/8')
		.then(function (response) {
			vl.annonces = response.data
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

new Vue({
	   el:"#DatesRdv",
	   data(){
	       selected : 'dateRdv1'
	    	 
	   }   
	 })
/*var titre = new Vue({
	el : '#pseudoTitre',
	data : {
		annonce 
	},
})*/