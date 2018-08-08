

var vm = new Vue({
	el: '#Annonces-elements',
	data: {
		annonces: []
	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/terminees/10')
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

/*var titre = new Vue({
	el : '#pseudoTitre',
	data : {
		annonce 
	},
})*/