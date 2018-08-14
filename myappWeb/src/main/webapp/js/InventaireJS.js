
var vm = new Vue({
  el: '#gestionStock',
  data: {
    stocks: [],
    idUser: '',
  	modeConservation: '',
  	searchKey: ''
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

var vmAjouter = new Vue({
	  el: '#AjouterForm',
	  data: {
	    produits: [],
	    selectProduit: 0,
	    inputDlc: '',
	    inputQuantite: '1',
	    nombreUnite: '',
	    idMesure: 0,
	    restant: ''
	  },
	  created: function () {
		  console.log('Init Ajouter');
		  
		  var vmAjouter = this
	      axios.get('http://localhost:8080/myappWeb/services/rest/produit/All')
	          .then(function (response) {
	        	  vmAjouter.produits = response.data
	      })
		  
	  },
	  computed: {
		  orderedProduits: function () {
		    return _.orderBy(this.produits, 'nomProduits');
		  },
		  estDenombrable: function () {
		    return this.idMesure == 2 || this.idMesure == 3 || this.idMesure == 4 || this.idMesure == 5;
		  }
	  },
	  watch: {
		  selectProduit: function (val, oldVal) {
			 
			  
			  if(val==0)
			  {
				  var nbUnite = 0;
				  var idMesure = 0;
			  }
			  else
			  {
				  var nbUnite = this.produits[val-1].nombreUnite;
				  var idMesure = this.produits[val-1].mesure.idMesure;
			  }
			  
			  this.idMesure=idMesure;
			  this.nombreUnite=nbUnite;
			  this.restant = nbUnite;
			  console.log("Selection produit n°"+val+", idMesure="+idMesure+", nombre d'unite="+nbUnite);
		  }
	  },
	  methods: {
			Ajouter: function () {
				var produit = document.getElementById('produit').value;
				var quantite = document.getElementById('quantite').value;
				var dlc = document.getElementById('dlc');
				
				console.log('Ajouter Stock: produit='+produit+'; quantite='+quantite+'; dlc='+JSON.stringify(dlc.value)+';');
				
				
				if(produit == 0)
				{
					alert('Veuillez rentrer un produit à ajouter !')
				}
				else
				{
					
					var stock = {
							idStock : 0,
							dateConsoPref : new Date(),
							dateJeter : null,
							dateManger : null,
							dlc : dlc.valueAsNumber,
							fractionRestante : this.restant,
							quantite : quantite,
					};
					
			      //recuperation info stock
					if(dlc.value == "")
					{
						var now = Date.now();
						var nowNumber = Date.parse(now)/1000;
						var dateConsoPref = moment(now).add(this.produits[produit-1].modeConservation.joursExtensionConservation, 'd');
					    stock.dateConsoPref = Date.parse(dateConsoPref)/1000;
					}
					else
					{
						var dateConsoPref = moment(dlc.valueAsNumber).add(this.produits[produit-1].modeConservation.joursExtensionConservation, 'd');
					      stock.dateConsoPref = Date.parse(dateConsoPref)/1000;
					}
				      
				      if(this.restant == this.nombreUnite)
				      {
				    	  stock.entame = 0;
				      }
				      else
				      {
				    	  stock.entame = 1;
				      }
	
				      stock.dateAchat = Date.now();
	
				      stock.produit = this.produits[produit-1];
	
				      var session = sessionStorage.getItem('utilisateurCourant');
				      stock.utilisateur = JSON.parse(session);
	
				      var stockAsJsonString = JSON.stringify(stock);
				      
				      console.log(stockAsJsonString);
				      
				    //declenchement de la requête:
				      var httpRequest = new XMLHttpRequest();
				      httpRequest.open("POST", "./services/rest/stock/postStock");
				      httpRequest.setRequestHeader("Content-Type" , "application/json");
				      httpRequest.send(stockAsJsonString);
				      console.log ("donnees de la requete envoyee : " + stockAsJsonString);
				      
				      //enlever modal + recharger page
				      document.getElementById('AjouterForm').style.display='none';
				      vm.chargerStock();
					}
					
			}  
	  
		}
	  
	})
