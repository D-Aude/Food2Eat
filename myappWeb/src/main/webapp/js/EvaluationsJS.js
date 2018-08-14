var id = document.getElementById('idAnnonce').value;

var vm = new Vue({
	el: '#listeAnnonceAvecEvaletNote',
	data: {
		annonces: []
	},
	created : function () {
//		var vm = this
//		axios.get('http://localhost:8080/myappWeb/services/rest/eval/4')
//		.then(function (response) {
//			vm.annonces = response.data
//		})
		  this.chargerAnnonce()

	},
	methods: {
		  
		chargerAnnonce()
		  {
			  id = document.getElementById('idAnnonce').value;
			  console.log('Evaluations.avecNoteEtCommentaire: '+id);
			  var vm = this
		      axios.get('http://localhost:8080/myappWeb/services/rest/eval/'+id)
		          .then(function (response) {
		        	  vm.annonces = response.data
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

