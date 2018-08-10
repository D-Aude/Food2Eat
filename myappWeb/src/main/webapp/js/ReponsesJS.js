//var id = document.getElementById('idUtilisateur').value;

var vm = new Vue({
	  el: '#pageReponses',
	  data: {
	    reponses: []
	  },
// 	  watch: {
// 		  modeConservation: function (mode, oldMode) {
// 			  console.log('watcher !!');
// 			  this.chargerStockModeConservation(mode)
// 		  }
// 	  },
 	  created: function () {
 		  console.log('Init')
 		  this.chargerReponse()
 	  },
	  methods: 
	  {
		  
		  chargerReponse()
		  {
			  //id = document.getElementById('idUtilisateur').value;
			  //console.log('id='+id);
			  var vm = this
		      axios.get('http://localhost:8080/myappWeb/services/rest/reponses/7')
		          .then(function (response) {
		        	  vm.reponses = response.data
		      })
		  },
			
		  moment: function (date) 
		  {
		      return moment(date);
		  },
		    
		  date: function (date) 
		  {
		      return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		  }
	  },
	  filters: {
		  moment: function (date) 
		  {
		    return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		  }
	  }
	  
	})
