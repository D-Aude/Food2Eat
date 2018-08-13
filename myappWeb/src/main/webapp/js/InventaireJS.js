
var vm = new Vue({
  el: '#gestionStock',
  data: {
    stocks: [],
    idUser: '',
  	modeConservation: ''
  },
  watch: {
	  modeConservation: function (mode, oldMode) {
		  this.chargerStockModeConservation(mode)
	  }
  },
  created: function () {
	  console.log('Init InventaireJS');
	  
	  var session = sessionStorage.getItem('utilisateurCourant');
	  console.log('session = '+session);
	  
	  if(session == null)
	  {
		  console.log('Utilisateur inconnu, identification necessaire.');
		  window.location.href='http://localhost:8080/myappWeb/login.html';
	  }
	  else
	  {
		  var utilisateur = JSON.parse(session);
		  this.idUser = utilisateur.idUtilisateur;
		  console.log('Stock.parUtilisateur: '+utilisateur.idUtilisateur);
		  var vm = this
	      axios.get('http://localhost:8080/myappWeb/services/rest/stock/'+utilisateur.idUtilisateur)
	          .then(function (response) {
	        	  vm.stocks = response.data
	      })
	  }
	  
  },
  methods: 
  {
	  chargerStock()
	  {
		  console.log('Stock.parUtilisateur: '+idUser.value);
		  var vm = this
	      axios.get('http://localhost:8080/myappWeb/services/rest/stock/'+idUser.value)
	          .then(function (response) {
	        	  vm.stocks = response.data
	      })
	  },
		
	  chargerStockModeConservation(mode)
	  {
		  console.log('Stock.parModeConservation: id='+idUser.value+', modeConservation='+mode);
		  var vm = this
		  if(mode == "")
		  {
			  axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+idUser.value)
			  	.then(function (response) {
			  		vm.stocks = response.data
				      })
		  }
		  else
		  {
			  axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+idUser.value+'&mode='+mode)
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
