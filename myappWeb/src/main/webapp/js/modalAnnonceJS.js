//Get the modal
var modal = document.getElementById('modal');
var modalAnnuler = document.getElementById('myModalAnnuler')
var modalAnnulerRaisin = document.getElementById('myModalAnnulerRaisin')

//Get the button that opens the modal
var btn = document.getElementById("btnDetailsAnnonce");
var btnPasteque = document.getElementById("lblModifierPasteque");
var btnAnnulerPasteque = document.getElementById("lblAnnulerPasteque")
var btnAnnulerRaisin = document.getElementById("lblAnnuler")
//Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var spanAnnuler = document.getElementsByClassName("closePasteque")[0];
var spanAnnulerRaisin = document.getElementsByClassName("closeRaisin")[0];
var btnNonPasteque = document.getElementsByClassName("lbl-modal-pasteque-annuler")[0];
var btnNonRaisin = document.getElementsByClassName("lbl-modal-raisin-annuler")[0];

//When the user clicks the button, open the modal 
btn.onclick = function() {
 modal.style.display = "block";
}

btnPasteque.onclick = function() {
	modal.style.display = "block";
}

btnAnnulerPasteque.onclick = function() {
	modalAnnuler.style.display = "block";
}

btnAnnulerRaisin.onclick = function(){
	modalAnnulerRaisin.style.display = "block";
}

//When the user clicks on <span> (x), close the modal
span.onclick = function() {
 modal.style.display = "none";
}

spanAnnuler.onclick = function() {
	 modalAnnuler.style.display = "none";
	}

spanAnnulerRaisin.onclick = function() {
	modalAnnulerRaisin.style.display = "none";
}

btnNonPasteque.onclick = function() {
	modalAnnuler.style.display = "none";
}

btnNonRaisin.onclick = function() {
	modalAnnulerRaisin.style.display = "none";
}

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
 if (event.target == modal) {
     modal.style.display = "none";
 }
}
 window.onclick = function(event) {
	 if (event.target == modalAnnuler) {
	     modalAnnuler.style.display = "none";
	 }
}
 window.onclick = function(event) {
	 if (event.target == modalAnnulerRaisin) {
	     modalAnnulerRaisin.style.display = "none";
	 }
} 

 
 