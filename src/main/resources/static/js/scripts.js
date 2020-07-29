var isMobile = window.matchMedia("only screen and (max-width: 750px)").matches;

$(document).ready(function(){

  // TODO: modificare css chosen select 
//  $("select").chosen();
	
  // seleziona/deseleziona tutti i check
  $("#checkAll").click(function(){
	$('input:checkbox').not(this).prop('checked', this.checked);
  });

  if (isMobile){

	  // se mobile side bar open e il click avvien al di fuori della side bar, disabilito il click (in modo che funzioni solo come chiusura menu side bar)
	  $("a").on('click', function(event) {
		if (mobileSidebarOpened){
		  var elementoTarget = event.target;
		  if (elementoTarget!=null && elementoTarget.id=="spanOpenMobileSideBar"){
			  return;
		  }

		  // verifico che il click sia stato fatto al di fuori della side bar
		  var contatoreLoop = 0;
		  var divTrovato = false;
  		  var parent = $(this);
		  while (!divTrovato && contatoreLoop<5){
			parent = parent.parent();
			if (parent!=null){
			    if (parent.attr('id')=="mySidebar"){
		    		divTrovato = true;
			    }
			} else {
				break;
			}
		    contatoreLoop++;
		  }
		  if (!divTrovato){
		    event.preventDefault();
		  }
		}
	  });

  }
 
  if (isMobile){
	  // nascondere sidebar mobile al click nella pagina (fuori dalla sidebar)
	  $(document).on("click","#mainContainer", function (event) {
		  var eventTargetId = event.target.id;
		  if (eventTargetId==null || eventTargetId!="spanOpenMobileSideBar"){
			  closeMobileSidebar();
		  }
	  });	  
  } else {
	  // nascondere dropdown menu user al click nella pagina (fuori dal dropdown menu)
	  $(document).on("click","#mainContainer", function (event) {
		  var elementoTarget = event.target;
		  var contatoreLoop = 0;
		  var divTrovato = false;
		  while (!divTrovato && contatoreLoop<100){
		    var parentDiv = $(elementoTarget).parent().closest('div');
		    if (parentDiv!=null){
		    	var idParentDiv = $(parentDiv).attr('id');
		    	if (idParentDiv!=null && idParentDiv=="userInfoDivMain"){
		    		divTrovato = true;
		    	}
		    } else {
		    	break;
		    }
		    contatoreLoop++;
		  }
		  if (!divTrovato){
  			  // in questo caso il click è stato fatto al di fuori del div dropdown, quindi se il div è aperto bisogna chiuderlo
			  var x = document.getElementById("userInfoDiv");
			  if (x!=null && x.className.indexOf("w3-show") != -1) {
				// chiudo il div
			    x.className = x.className.replace(" w3-show", "");
			  }
		  }
	  });	  
  }
  
});

// open/close drop down menu user logged
function openCloseUserInfoDiv() {
  var x = document.getElementById("userInfoDiv");
  if (x.className.indexOf("w3-show") == -1) { 
    x.className += " w3-show";
  } else {
    x.className = x.className.replace(" w3-show", "");
  }
}

//simula l'onclick del pulsante nascosto "submit" all'interno del form. Per la validazione automatica HTML5 il form deve essere "submittato" tramite l'input submit 
function submitForm(type, idForm, idSubmitButtonHidden, action) {
  var azione = urlBaseController + "/" + action;
  document.forms[idForm].action=azione;

  if(type=='form'){
	  $('#'+idSubmitButtonHidden).click();
	  // prima di visualizzare il div attesa, verifico che tutti i campi input siano validi (controlli HTML5)
	  var validazioneOk = true;
	  $("#"+idForm+" *").filter(':input').each(function(){
	    if (!this.validity.valid){
	      validazioneOk = false;
	      // per le select ('chosen'), la validazione html5 non visualizza l'errore. Bisogna farlo manualmente
	      if (this.type=="select-one"){
	    	// visualizzo bordo select colorato
	    	var idCampo = this.id + "_chosen";
	    	var selectorField = "#"+idCampo+" .chosen-single";
	    	
	        $(selectorField).css({"border-color": "#ffaaaa", 
	        					  "border-width":"3px", 
	        					  "border-style":"solid"});
	        $(selectorField).prop('title', 'Compilare questo campo');
	      }
	      
	      // se l'elemento si trova all'interno di un tab, bisogna visualizzarlo
	      var arrInfoTrovate = elementoInTab(this);
	      var idTabTrovato = arrInfoTrovate[0];
	      if (idTabTrovato!=null && idTabTrovato!=""){
	        var categoryTabTrovata = arrInfoTrovate[1];
		    if (categoryTabTrovata!=null && categoryTabTrovata!=""){
  	    	  openTab(categoryTabTrovata, idTabTrovato);
		    }
	      }
	    }
	  });
	  if (validazioneOk){
		  showAttesa();
	  }
  } else if(type=='view'){
	var $form = $("#"+idForm);
	if(table!=null){
	  table.$('input[type="checkbox"]').each(function(){
	    if(!$.contains(document, this)){
	       if(this.checked){
	         $form.append(
	           $('<input>')
	             .attr('type', 'hidden')
	             .attr('name', this.name)
	             .val(this.value)
	         );
	      }
	    }
	  });
	}
	$('#'+idSubmitButtonHidden).click();
  }
}

var mobileSidebarOpened = false;
function openCloseMobileSidebar() {
  if (mobileSidebarOpened){
	  mobileSidebarOpened = false;
	  document.getElementById("mySidebar").style.display = "none";
	  // rendo il footer di nuovo in primo piano (aggiungendo) la classe w3-bottom)
	  $("#barraFooter").addClass('w3-bottom');
  } else {
	  mobileSidebarOpened = true;
	  document.getElementById("mySidebar").style.display = "block";
	  // rendo il footer in secondo piano (togliendo la classe w3-bottom)
	  $("#barraFooter").removeClass('w3-bottom');
  }
}

function closeMobileSidebar() {
  mobileSidebarOpened = false;
  document.getElementById("mySidebar").style.display = "none";
  $("#barraFooter").addClass('w3-bottom');
}  


//funzione utilizzata per l'inizializzazione della datatable client side
function initDataTable(idTable, altezzaTabella){
  var table = $('#'+idTable).DataTable( {
  columnDefs: [
	  {
	    targets: "noSort", 
	    orderable: false
	  }
      ],
	  scrollY: altezzaTabella,
	  "language": {
		    search: '<i class="fas fa-search" aria-hidden="true"></i>',
		    searchPlaceholder: 'filter records'
	  },
	  "dom": '<"top">lft<"bottom"irp <"#buttonRefresh">><"clear">',
  	  "initComplete": function(settings, json) {
	  		$('#'+idTable + "_filter input").focus();

			// pulsante refresh datatable
			$('#buttonRefresh').html('');
			$('#buttonRefresh').append(
		    '<a href="javascript:refreshPage();"> ' +
		    '  <span id="spanRefresh" class="fa fa-refresh w3-right"></span> ' +
		    '</a> ');

	  		
  	  }
	});
  return table;
}

function refreshPage(){
	window.location.href = urlBaseController;
}
