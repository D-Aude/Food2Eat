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


