

var vm = new Vue({
	el: '#app',
	data: {
		annonces: []
	},
	created () {
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
