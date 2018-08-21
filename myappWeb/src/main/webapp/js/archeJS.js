var vmArche = new Vue({
  el: '#flexContent',
  data: {
    pseudo : '',
    utilisateur: [],
    nbrDonsEffectues : 0,
    nbrDonsTotaux: 0,
    notifMesSouhait :0,
    notifMesAnnonces:0
  },
  created: function () {
	  var session = sessionStorage.getItem('utilisateurCourant');
	  
	  if(session == null)
	  {
		 this.pseudo =  'visiteur';
	  }
	  else
	  {
		  this.utilisateur = JSON.parse(session);
		  this.pseudo = (JSON.parse(session)).pseudo;
		  var btnConnexion = document.getElementById('btnConnexion');
		  btnConnexion.style.display = "none";
		  
		  axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/CountparUser/' + this.utilisateur.idUtilisateur)
			.then(function (response) {
				vmArche.nbrDonsEffectues = response.data
			}),
			 axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/CountAnnoncesTotales')
				.then(function (response) {
					vmArche.nbrDonsTotaux = response.data
				})
				 axios.get('http://localhost:8080/myappWeb/services/rest/reponses/notificationAcceptationReponse/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
						vmArche.notifMesSouhait = response.data
					}),
				 axios.get('http://localhost:8080/myappWeb/services/rest/reponses/NotifReponseAnnonce/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
						vmArche.notifMesSouhait = response.data
					})	
	  }
		
	
  },
  methods: 
  {
	  seConnecter: function ()
	  {
		  window.location.href='http://localhost:8080/myappWeb/login.html';
	  },
	  seDeconnecter: function ()
	  {
		  sessionStorage.clear();
		  console.log('session Utilisateur terminée');
		  window.location.href='http://localhost:8080/myappWeb/login.html'; 
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
