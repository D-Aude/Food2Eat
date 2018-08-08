var filtres = new Vue({
  el: '#filtres',
  data: {
	  	id: '',
	  	modeConservation: '',
	    stocks: []
	  },
  watch: {
	  modeConservation: function (mode, oldMode) {
		  var id = document.getElementById('idUtilisateur').value;
		  console.log('Stock.parModeConservation: id='+id+', modeConservation='+mode);
		  	var filtres = this
		  	if(mode == "")
		  	{
		  		axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+id)
			      .then(function (response) {
			    	  filtres.stocks = response.data
			      })
		  	}
		  	else
		  	{
		  		axios.get('http://localhost:8080/myappWeb/services/rest/stock?id='+id+'&mode='+mode)
			      .then(function (response) {
			    	  filtres.stocks = response.data
			      })
		  	}
		    
	  }
  },
  methods : {   
    chargerStock: function ()
	{
    	var id = document.getElementById('idUtilisateur').value;
		console.log('Stock.parUtilisateur: '+id);
    	var filtres = this
        axios.get('http://localhost:8080/myappWeb/services/rest/stock/'+id)
          .then(function (response) {
        	  filtres.stocks = response.data
          })
	}
  }
})

var vm = new Vue({
  el: '#app',
  data: {
    stocks: []
  },
  created () {
    var vm = this
    axios.get('http://localhost:8080/myappWeb/services/rest/stock/1')
      .then(function (response) {
        vm.stocks = response.data
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