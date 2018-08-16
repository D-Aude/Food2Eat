var Session = sessionStorage.getItem('utilisateurCourant');
var id = JSON.parse(Session)["idUtilisateur"];

var vm =new Vue ({
el : '#listeMesEnviesTermines',
data : {
	typeEnvies:'',
	annonces :[],
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
	

axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnviesCloturees/' + vm.idUtilisateur)
.then(function(response){
	vm.annonces = response.data
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
		console.log(typeEnvies +'tut')
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnviesCloturees/' + vm.idUtilisateur)
		.then(function(response){
			vm.annonces = response.data
		})
		}
	if (typeEnvies == "mesEnviesAEval")
		{
		console.log(typeEnvies +'envie a véal')
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/mesEnviesTerminesAEval/' + vm.idUtilisateur)
		.then(function(response){
			vm.annonces = response.data
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
		
		
		console.log(comm)
		console.log(texte )
			
		axios.get('http://localhost:8080/myappWeb/services/rest/mesAnnoncesPostees/uneReponse/'+idAnnonce)
			.then(function (response){
				var annonceAmodif= response.data;
			console.log(annonceAmodif  )
			
				var Repannonce = {
				
				"idReponse" : annonceAmodif.idReponse,
			}
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
			console.log(annonceAmodif)
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

