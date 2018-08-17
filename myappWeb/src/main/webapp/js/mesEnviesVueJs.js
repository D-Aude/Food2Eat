var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vm =new Vue ({
el : '#listeMesEnviesTermines',
data : {
	typeEnvies:'plop',
	evaluations :[],
	repannonces :[],
	idUtilisateur :id,
	selected:'',
},
watch : {
	typeEnvies: function (typeEnvies, ancienTypeEnvieCloture){
		this.chargerTypeEnviesCloturees(typeEnvies)
	}
},
created : function() {
	var vm = this
	

axios.get('http://localhost:8080/myappWeb/services/rest/eval/EvalCompleteIdUt/' + vm.idUtilisateur)
.then(function(response){
	vm.evaluations = response.data
})
console.log(response.data)

},

methods: {
	
	chargerTypeEnviesCloturees(typeEnvies)
	{
		console.log(typeEnvies)
		var vm= this
	if (typeEnvies == "plop")
		{
		vm.repannonces = [];
		console.log(typeEnvies +'tut')
		axios.get('http://localhost:8080/myappWeb/services/rest/eval/EvalCompleteIdUt/'+ vm.idUtilisateur)
		.then(function(response){
			vm.evaluations = response.data
		})
		}
	if (typeEnvies == "mesEnviesAEval")
		{
		console.log(typeEnvies +'envie a véal')
		vm.evaluations = [];
		axios.get('http://localhost:8080/myappWeb/services/rest/reponses/reponseSansEval/' + vm.idUtilisateur)
		.then(function(response){
			vm.repannonces = response.data
		})
		
		}
	else
	{
		console.log(typeEnvies +'envie eval')
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnviesTermines/' + vm.idUtilisateur)
		.then(function(response){
			vm.annonces = response.data
		})
		}
	},
	insererEvaluation:function(idAnnonce)
	{
		//console.log("debut fonction" + idAnnonce)
		var vm = this;
	alert("Votre evaluation est enregistrée,////////////// Merci!" + idAnnonce)
		
		
		
		var liste, texte;
		liste = document.getElementById("selectNoteEvaluation");
		texte = liste.options[selectNoteEvaluation.selectedIndex].text;
	
		//var idReponse =document.getElementById("idReponse").value;
		//console.log (idReponse + " idReponse")
		var comm = document.getElementById("commentaireEval").value;
		
		

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
						"note" : texte,
						"commentaire" : comm,
						"repannonce" : Repannonce,
						"dateEvaluation" :Date.now()
				}
				console.log(Evaluation)
					annonceAmodif.evaluations = Evaluation;
				
			// annonceAmodif vide
		
		// POST pour evaluation
				axios.post('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/insererEvaluation',
				Evaluation).then((response)=>{
					console.log("débutfocntionpost")
					console.log(response);
					
				});
				//location.reload();
			})
			
		
	
		
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

