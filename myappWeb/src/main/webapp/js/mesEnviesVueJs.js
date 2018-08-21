var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vm =new Vue ({
el : '#listeMesEnviesTermines',
data : {
	aujourdhui : moment().local('fr'),
	typeEnvies:'rdvAvenir',
	evaluations :[],
	repannonces :[],
	idUtilisateur :id,
	selected:'',
	src: "./resources/img/Annonce/",
	 imgtype: ".png",
	
},  
 
watch : {
	typeEnvies: function (typeEnvies, ancienTypeEnvieCloture){
		this.chargerTypeEnviesCloturees(typeEnvies)
	}
},
created : function() {
	var vm = this
	

axios.get('http://localhost:8080/myappWeb/services/rest/reponses/rdvAVenir/' + vm.idUtilisateur)
.then(function(response){
	vm.repannonces = response.data
})
console.log(response.data)

},

methods: {

	comparerDateRdv ()
	{
		var dateRdv = document.getElementById('dateRdv').value;
		
		if (moment().diff(dateRdv) <=  0)
			{
			console.log("date à venir")
			}
		else 
			{
			console.log("date à passe")
			}
	},
	chargerTypeEnviesCloturees(typeEnvies)
	{
		
		console.log(typeEnvies)
		var vm= this
	if (typeEnvies == "rdvAvenir")
		{
		vm.repannonces=[],
		vm.evaluations=[],
		axios.get('http://localhost:8080/myappWeb/services/rest/reponses/rdvAVenir/'+ vm.idUtilisateur)
		.then(function(response){
			vm.repannonces = response.data
			
		})
		}
	if (typeEnvies == "mesEnviesAEval")
		{
		console.log(typeEnvies +'envie a véal')
		vm.evaluations=[]
		vm.repannonces=[]
		axios.get('http://localhost:8080/myappWeb/services/rest/reponses/reponseSansEval/' + vm.idUtilisateur)
		.then(function(response){
			vm.repannonces = response.data
		})
		
		}
	else
	{
		vm.repannonces=[]
		vm.evaluations=[]
		console.log(typeEnvies +'envie eval')
		axios.get('http://localhost:8080/myappWeb/services/rest/eval/EvalCompleteIdUt/' + vm.idUtilisateur)
		.then(function(response){
			vm.evaluations = response.data
		})
		}
	},

	insererEvaluation:function(idAnnonce, selected)
	{
		//console.log("debut fonction" + idAnnonce)
		var vm = this;
	alert("Votre evaluation est enregistrée,////////////// Merci! note : " + selected)
		
		
		
		//var liste, texte;
		//liste = document.getElementById("selectNoteEvaluation");
		//texte = liste.options[selectNoteEvaluation.selectedIndex].text;
		//console.log(texte)
		//var idReponse =document.getElementById("idReponse").value;
		//console.log (idReponse + " idReponse")
		var comm = document.getElementById("commentaireEval").value;
		
	for (i=0 ; i < vm.repannonces.length ; i++) {
		if (idAnnonce == vm.repannonces[i].annonce.idAnnonce) {
			vm.repannonces.splice(i,1);
		}
	}

		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneReponse/'+idAnnonce)
			.then(function (response){
				var annonceAmodif= response.data;
		
		console.log(annonceAmodif[0].idReponse)
		
				var Repannonce = {
				"idReponse" : annonceAmodif[0]["idReponse"]
			}
			console.log(Repannonce)
			
				//I nstanciation evaluation
				var Evaluation = {
						"idEvaluation" :null,
						"note" : selected,
						"commentaire" : comm,
						"repannonce" : Repannonce,
						"dateEvaluation" :Date.now()
				}
				console.log(vm.repannonces)
					annonceAmodif.evaluations = Evaluation;
		// maj list des repannones. splice(i,1) i 1= idAnnonce a retire à partir de 1)
	
		
		// POST pour evaluation
				axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/insererEvaluation',
				Evaluation).then((response)=>{
					console.log("débutfocntionpost")
					console.log(response);
					
				});
			
			})
	},
	getSrc: function(idProduit) {
		console.log("IMAGE" +idProduit)
		return this.src + idProduit + this.imgtype;
	},
	
	moment: function (date) {

		return moment(date);
	},
	date: function (date) {


		return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
	}
},
filters: {
	moment: function (date) {
		return moment(date).format('MMMM Do YYYY, h:mm:ss a');
	}
}

	
})





var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];
var vm = new Vue({
	el: '#mesEnvieEnAttentes',
	data: {
		repannonces: [],
		id : idUtilisateur,
		src: "./resources/img/Annonce/",
		 imgtype: ".png",	
	
	},
	created : function () {
		var vm = this
		console.log("début")
		
		axios.get('http://localhost:8080/myappWeb/services/rest/reponses/envieAttente/'+ vm.idUtilisateur)
		.then(function (response) {
			vm.repannonces = response.data
		
		})
	},
	methods: {
		moment: function (date) {
			
			return moment(date);
		},
		date: function (date) {
			
			
			return moment(date).locale('fr').format('MMMM Do YYYY, h:mm:ss a');
		}
	},
	filters: {
		    moment: function (date) {
		      return moment(date).format('MMMM Do YYYY, h:mm:ss a');
		    }
		  }

})
