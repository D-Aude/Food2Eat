 
var vm = new Vue({
  el: '#gestionStock',
  data: {
    stocks: [],
    idUser: '',
  	modeConservation: '',
  	consommation: 0,
  	selectedStock : []
  },
  watch: {
	  modeConservation: function (mode, oldMode) {
		  this.chargerStockModeConservation(mode)
	  }
  },
  created: function () {
	  console.log('Init InventaireJS');
	  document.getElementById("btnEnregistrer").style.display="none";
	  document.getElementById("btnAnnuler").style.display="none";
	  document.getElementById("detailStock").style.display="none";
	  var session = sessionStorage.getItem('utilisateurCourant');
	  console.log('session = '+session);
	  document.getElementById("detailStock").style.display="none";
	  
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
	  clickStock(stock)
	  {
		  var liste = document.getElementsByClassName("infosStock");
		  for(var i =0; i<liste.length; i++)
		  {
			  liste[i].style.border = 'none';
			  if(this.stocks[i].idStock==stock.idStock)
				  liste[i].style.border = 'solid #245B55';
		  }
		  
		  //Affichage div detailStock
		  if(document.getElementById("detailStock").style.display == 'none') 
			  document.getElementById("detailStock").style.display="block";
		  
		//Affichage Boutton creation Annonce
		  if(document.getElementById("btnManger").style.backgroundColor != document.getElementById("btnJeter").style.backgroundColor)
				document.getElementById("btnAnnonce").style.display="none";
		  else
			  document.getElementById("btnAnnonce").style.display="block";
		  
		  //recuperation id Stock
		  var idSelectedStockOld =  document.getElementById("idSelectedStock").value;
		  var idSelectedStock = stock.idStock;
		  //Si nouveau produit cliqué
		  if(idSelectedStockOld == "" || idSelectedStockOld != idSelectedStock)
		  {
			  console.log('Affichage stock : '+stock.produit.nomProduit+', id : '+idSelectedStock);
			  document.getElementById("idSelectedStock").value = stock.idStock;
			  this.selectedStock = stock;
			  
			  //Modification image Stock
			  var idProduit = stock.produit.idProduit;
			  var lienImageOld = document.getElementById('imageDetail').src;
			  //var lienImage = "./resources/img/stock/produit_"+idProduit+".jpg";
			  var lienImage = "./resources/img/annoncescom/"+idProduit+".png";
			  if(lienImageOld != lienImage)
				  document.getElementById('imageDetail').src = lienImage;
				  
			  //Modification detail Stock
			  
			  
		  }
		  
	  },
	  btnAjouter()
	  {
		  this.btnAnnuler();
		  document.getElementById("btnAnnonce").style.display="none";
		  document.getElementById("detailStock").style.display="none"
		  document.getElementById('AjouterForm').style.display='block'; 
	  },
	  btnAnnonce()
	  {
		  console.log('Donner '+this.selectedStock.produit.nomProduit);
		  sessionStorage.setItem('publicationAnnonce', JSON.stringify(this.selectedStock));
		  $("#flexContent").load("./publierAnnonce.html");
	  },
	  btnManger()
	  {
		  console.log('Mode Manger');
		  //Si bouton deja appuyé
		  if(document.getElementById("btnManger").style.backgroundColor == "rgb(33, 37, 41)")
		  {
			  console.log('   => OFF');
			  this.desactiverManger();
			  document.getElementById("btnEnregistrer").style.display="none";
			  document.getElementById("btnAnnuler").style.display="none";
			  document.getElementById("btnAnnonce").style.display="block";
		  }
		  else
		  {
			  console.log('   => ON');
			  this.desactiverJeter();
			  this.activerManger();
			  document.getElementById("btnEnregistrer").style.display="block";
			  document.getElementById("btnAnnuler").style.display="block";
			  document.getElementById("btnAnnonce").style.display="none";
		  }
		  
	  },
	  btnJeter()
	  {
		  console.log('Bouton Jeter');
		  //Si bouton deja appuyé
		  if(document.getElementById("btnJeter").style.backgroundColor == "rgb(33, 37, 41)")
		  {
			  console.log('   => OFF');
			  this.desactiverJeter();
			  document.getElementById("btnEnregistrer").style.display="none";
			  document.getElementById("btnAnnuler").style.display="none";
			  document.getElementById("btnAnnonce").style.display="block";
		  }
		  else
		  {
			  console.log('   => ON');
			  this.desactiverManger();
			  this.activerJeter();
			  document.getElementById("btnEnregistrer").style.display="block";
			  document.getElementById("btnAnnuler").style.display="block";
			  document.getElementById("btnAnnonce").style.display="none";
		  }
		  
		  
	  },
	  btnEnregistrer()
	  {
		  console.log('Bouton Enregistrer');
		  var liste = document.getElementsByClassName("infosStock");
		  
		  
		  for(var i =0; i<liste.length; i++)
		  {
			 var info = liste[i].children;
			 var stockModif = info[3].children;
			 var newFractionRestante = stockModif[0].value;
			 
			 var stockEnBase = this.stocks[i];
			 var oldFractionRestante = stockEnBase.fractionRestante;
			 
			 if(oldFractionRestante > newFractionRestante)
			 {
				 console.log('Modification du Stock : '+stockEnBase.produit.nomProduit);
				 console.log('Ancienne valeur fraction dans base = '+ oldFractionRestante);
				 console.log('Nouvelle valeur fraction en base = '+newFractionRestante);
				 
				 
				 
				 if(newFractionRestante == 0)
				 {
					 console.log('Quantite du stock réduit de 1');
					 stockEnBase.quantite--;
					 stockEnBase.entame = 0;
					 
					 if(stockEnBase.quantite == 0)
					 {
						 if(document.getElementById("btnManger").style.backgroundColor == "rgb(33, 37, 41)") //si produit manger
						 {
							 console.log('Stock entierement mangé');
							 stockEnBase.dateManger = Date.now();
						 }
						 else
						 {
							 console.log('Stock entierement jeté');
							 stockEnBase.dateJeter = Date.now();
						 }
					 }
					 else
					 {
						 stockEnBase.fractionRestante = stockEnBase.produit.nombreUnite;
					 }
				 }
				 else
				 {
					 stockEnBase.entame = 1;
					 stockEnBase.fractionRestante = newFractionRestante;
				 }
				 
				 var newStock = JSON.stringify(stockEnBase);
				 
				 //declenchement de la requête:
				 var httpRequest = new XMLHttpRequest();
			     httpRequest.open("POST", "./services/rest/stock/postStock");
			     httpRequest.setRequestHeader("Content-Type" , "application/json");
			     httpRequest.send(newStock);
			     console.log ("donnees de la requete envoyee : " + newStock + '/n');
			     
			     httpRequest.onreadystatechange = function() {
				  		if (this.readyState == 4 && this.status == 200) {
				  		//si status HTTP en retour == 200 : OK 
				  		vm.chargerStockModeConservation("");
				  		}
				   };
			 }
			 document.getElementById("detailStock").style.display="none";
			 this.btnAnnuler();
			 stockModif[0].value = oldFractionRestante;
			 liste[i].style.border = 'none';
		  }
	  },
	  btnAnnuler()
	  {
		  this.desactiverJeter();
		  this.desactiverManger();
		  document.getElementById("btnEnregistrer").style.display="none";
		  document.getElementById("btnAnnuler").style.display="none";
	  },
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
	  activerManger(){
		  document.getElementById("btnManger").style.backgroundColor = "#212529";
		  var vm = this
		  vm.consommation = 1;
	  },
	  desactiverManger(){
		  document.getElementById("btnManger").style.backgroundColor = "#49b6a8";
		  this.consommation = 0;
	  },
	  activerJeter(){
		  document.getElementById("btnJeter").style.backgroundColor = "#212529";
		  this.consommation = 2;
	  },
	  desactiverJeter(){
		  document.getElementById("btnJeter").style.backgroundColor = "#49b6a8";
		  this.consommation = 0;
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
						console.log('DLC non renseigné');
						
						var now = Date.now();
						stock.dlc = moment(now).add(7, 'd');
						var dateConsoPref = moment(stock.dlc).add(this.produits[produit-1].modeConservation.joursExtensionConservation, 'd');
					    stock.dateConsoPref = dateConsoPref
					}
					else
					{
						var dateConsoPref = moment(dlc.valueAsNumber).add(this.produits[produit-1].modeConservation.joursExtensionConservation, 'd');
					      stock.dateConsoPref = dateConsoPref;
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
				      
				      //enlever modal 
				      document.getElementById('AjouterForm').style.display='none';
				      
				      //reinitialiser modal
				      vmAjouter.selectProduit = 0;
				      vmAjouter.inputDlc = "";
				      vmAjouter.inputQuantite = '1';
				      
				      //Recharger page apres requete
				      httpRequest.onreadystatechange = function() {
				  		if (this.readyState == 4 && this.status == 200) {
				  		//si status HTTP en retour == 200 : OK 
				  		vm.chargerStockModeConservation("");
				  		}
				      };
				      
				}
					
			}  
	  
		}
	  
	})
