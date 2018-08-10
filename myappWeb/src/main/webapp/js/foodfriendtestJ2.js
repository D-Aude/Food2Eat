
var id = document.getElementById('iduser').value;



var mesFoodfriend = new Vue({
  
  el: '#listeFoodfriend',
  data: {
    foodfriend: [],
    iduser: id,
    src: "./resources/img/utilisateur/",
    imgtype: ".jpeg"
    	
  },
  methods: {
	getSrc: function(pseudo) {
		return this.src + pseudo + this.imgtype;
	},
	
	RetirerFF: function(idfoodfriend, pseudo) {

		alert(pseudo + " n'est plus votre foodfriend.")			
		// Instanciation
		var vm = this;

		// Récupération du foodfriend qui nous interesse
		axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
	      .then(function (response) {
	    	  var ffjson = response.data;

	    	  ffjson['dateFinRelation'] = Date.now();
	    	  
	    	  // post			    	  
	    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
	    			  ffjson).then((response) => {
	    				  console.log(response);
	    			  });
	    	  
	    	  // récupérer les nouvelles données
	  	    location.reload();

	      })		      
	    
	}
	
	
  },
   
  created: function() {
    var vm = this
    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=' + id)
      .then(function (response) {
        vm.foodfriend = response.data
      })
  }
  
})

var mesDemandesFFRecues = new Vue({
	  
	  el: '#listeDemandesFoodfriend',
	  data: {
	    foodfriend: [],
	    iduser: id,
	    src: "./resources/img/utilisateur/",
	    imgtype: ".jpeg"
	    	
	  },
	  
	  	  
	  methods: {
		getSrc: function(pseudo) {
			return this.src + pseudo + this.imgtype;
		},
		
		RefuserDemande: function(idfoodfriend) {

			alert("Votre réponse a bien été prise en compte.")			
			// Instanciation
			var vm = this;

			// Récupération du foodfriend qui nous interesse
			axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
		      .then(function (response) {
		    	  var ffjson = response.data;

		    	  ffjson['dateReponse'] = Date.now();
		    	  ffjson['dateFinRelation'] = Date.now();
		    	  
		    	  // post			    	  
		    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
		    			  ffjson).then((response) => {
		    				  console.log(response);
		    			  });
		    	  
		    	  // récupérer les nouvelles données
		  	    location.reload();

		      })		      
		    
		},
		

		AccepterDemande: function(idfoodfriend) {

			alert("Félicitation ! Vous avez un nouveau foodfriend !")			
			// Instanciation
			var vm = this;

			// Récupération du foodfriend qui nous interesse
			axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/' + idfoodfriend)
		      .then(function (response) {
		    	  var ffjson = response.data;

		    	  ffjson['dateReponse'] = Date.now();
		    	  
		    	  // post			    	  
		    	  axios.post('http://localhost:8080/myappWeb/services/rest/foodfriend',
		    			  ffjson).then((response) => {
		    				  console.log(response);
		    			  });
		    	  
		    	  // récupérer les nouvelles données
		  	    location.reload();

		      })		      
		    
		}
	  
	  },
	   
	  created: function() {
	    var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=' + id)
	      .then(function (response) {
	        vm.foodfriend = response.data
	      })
	  }
	  
})


