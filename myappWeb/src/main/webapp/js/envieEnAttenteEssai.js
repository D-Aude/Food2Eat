var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vm = new Vue({
  el: '#EssaiEnvieEnAttenteEssai',
  data: {
    repannonces: [],
    src: "./ressources/img/Annonce/",
    imgtype :".png",
    iduser: id,
  },


  created: function () {
	  console.log('Init');
	  var vm = this
      axios.get('http://localhost:8080/myappWeb/services/rest/reponses/envieAttente/' + vm.iduser)
          .then(function (response) {
        	  vm.repannonces = response.data
      })
	  
  },
  methods: 
  {
	  getSrc: function(idProduit) {
			return this.src + idProduit + this.imgtype;
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
