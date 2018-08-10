var pseudo;
var mdp;

var vm = new Vue({
  el: '#loginForm',
  data: {
    utilisateur: []
  },
  methods: 
  {
	  seConnecter()
	  {
		  pseudo = document.getElementById('pseudo').value;
		  mdp = document.getElementById('mdp').value;
		  this.Authentification(pseudo, mdp);

	  },
	  Authentification(pseudo, mdp)
	  {
		  
		  console.log('Utilisateur.Authentification: pseudo='+pseudo+', mdp='+mdp);
		  var vm = this
		  axios.get('http://localhost:8080/myappWeb/services/rest/utilisateur?pseudo='+pseudo+'&mdp='+mdp)
			  	.then(function (response) {
			  		vm.utilisateur = response.data
			  		
			  		//si id à 0 mauvais pseudo/mdp
			  		console.log('id='+vm.utilisateur.idUtilisateur);
					if(vm.utilisateur.idUtilisateur == 0 || vm.utilisateur.idUtilisateur == undefined)
					{
						console.log('Pseudo et/ou mdp érroné !!');	
					}
					else
					{
				        console.log('Authentification OK !!');
					}
			});
			
		  
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
