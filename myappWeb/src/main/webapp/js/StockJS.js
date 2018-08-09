var id = document.getElementById('idUtilisateur').value;

var vm = new Vue({
  el: '#gestionStock',
  data: {
    stocks: [],
    idUser: '1',
  	modeConservation: ''
  },
  watch: {
	  modeConservation: function (mode, oldMode) {
		  console.log('watcher !!');
		  this.chargerStockModeConservation(mode)
	  }
  },
  created: function () {
	  console.log('Init');
	  this.chargerStock()
	  
  },
  methods: 
  {
	  produitSouris()
	  {
		  console.log('mouseOver Ok !!');
	  },
	  chargerStock()
	  {
		  id = document.getElementById('idUtilisateur').value;
		  console.log('Stock.parUtilisateur: '+id);
		  var vm = this
	      axios.get('http://localhost:8080/myappWeb/services/rest/stock/'+id)
	          .then(function (response) {
	        	  vm.stocks = response.data
	      })
	  },
		
	  chargerStockModeConservation(mode)
	  {
		  id = document.getElementById('idUtilisateur').value;
		  console.log('Stock.parModeConservation: id='+id+', modeConservation='+mode);
		  var vm = this
		  if(mode == "")
		  {
			  axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+id)
			  	.then(function (response) {
			  		vm.stocks = response.data
				      })
		  }
		  else
		  {
			  axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+id+'&mode='+mode)
				.then(function (response) {
					vm.stocks = response.data
				 })
		  }
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
