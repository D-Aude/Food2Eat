var id = document.getElementById('idUtilisateur').value;

var vm = new Vue({
	el: '#listeAnnonceReponseSansValidation',
	data: {
		annonces: []
	},
	created : function () {
		var vm = this
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/avalidees/' +id)
		.then(function (response) {
			vm.annonces = response.data
		})
	},
	methods: {
		  
		chargerAnnonce()
		  {
			  id = document.getElementById('idUtilisateur').value;
			  console.log('Stock.parUtilisateur: '+id);
			  var vm = this
		      axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/avalidees/' +id)
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