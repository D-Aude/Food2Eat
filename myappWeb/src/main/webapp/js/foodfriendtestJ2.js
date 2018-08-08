/*export default {
	data() {
		return {
			
		}
	},
	methods: {
		
	},
	created() {
		this.$http.get('http://localhost:8080/myappWeb/services/rest/stock/1')
		.then(function(data){
			console.log(data);
		})
	}
}*/

var id = document.getElementById('iduser').value;

var mesFoodfriend = new Vue({
  
  el: '#listeFoodfriend',
  data: {
    foodfriend: [],
    titre:"Liste de mes foodfriend",
    iduser: id
  },
  
  
  created: function() {
    var vm = this
    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesfoodfriend?iduser=' + id)
      .then(function (response) {
        vm.foodfriend = response.data
      })
  },
  
  request: function() {
	    var vm = this
	    axios.get('http://localhost:8080/myappWeb/services/rest/foodfriend/mesdemandesrecues?iduser=' + id)
	      .then(function (response) {
	        vm.foodfriend = response.data
	      })
	  }
  
})


