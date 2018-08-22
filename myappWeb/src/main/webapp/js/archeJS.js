// Récupération de la Session
var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];


var vmArche = new Vue({
  el: '#flexContent',
  data: {
    pseudo : '',
    idUtilisateur : id,
    utilisateur: [],
    nbrDonsEffectues : 0,
    nbrDonsTotaux: 0, //Pas mis dans l'arche
    
    notifSouhaitAttenteValidation :0,
    notifnbAnnonceEncours : 0,
	 notifMesSouhait : 0,
	 notifMesAnnonces: 0,
	 notifInvitFF: 0,
	 notifDonTotauxRecus :0,
	 
	 nbStockInventaire : 0,
	 nbStockPresquePerime : 0,
	 nbStockPerime : 0
   
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
				}),
				console.log("init")
				 axios.get('http://localhost:8080/myappWeb/services/rest/reponses/notificationAcceptationReponse/'+ this.utilisateur.idUtilisateur)
				.then(function (response) {
					 { vmArche.notifMesSouhait = response.data }

					}),
				axios.get('http://localhost:8080/myappWeb/services/rest/reponses/NotifReponseAnnonce/'+this.utilisateur.idUtilisateur)
				.then(function (response) {
					 {vmArche.notifMesAnnonces = response.data }

					}),	
				axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/notif/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
					{ vmArche.notifInvitFF = response.data }

					}),	
					axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/CountAnnoncesEnCours/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
					 { vmArche.notifnbAnnonceEncours = response.data }

					}),	
					axios.get('http://localhost:8080/myappWeb/services/rest/reponses/notifDonTotauxRecus/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
					 { vmArche.notifDonTotauxRecus = response.data }

					}),
					axios.get('http://localhost:8080/myappWeb/services/rest/reponses/notifSouhaitEnAttente/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
					 { vmArche.notifSouhaitAttenteValidation = response.data }

					}),

				axios.get('http://localhost:8080/myappWeb/services/rest/stock/'+this.utilisateur.idUtilisateur)
					.then(function (response) {
						var stocks = response.data;
						var now = new Date();
						var semaineProchaine = moment(now).add(7, 'd');
						for(var i=0; i<stocks.length;i++)
						{
							if(stocks[i].dlc < now)
								vmArche.nbStockPerime++;
							else if(stocks[i].dlc < semaineProchaine)
								vmArche.nbStockPresquePerime++;
						}
						vmArche.nbStockInventaire = stocks.length;
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
