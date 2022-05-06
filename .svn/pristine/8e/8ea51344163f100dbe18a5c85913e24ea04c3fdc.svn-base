function showPopup() {
	$('#MyModal').modal();
	$('#myModal').modal('toggle');
	$('#myModal').modal('show');
}

$(document).ready(function() {
	var allProjects = [];
	$.ajax({
		url : 'getProjects',
		success : function(data) {
			for (var i = 0; i < data.length; i++)
				allProjects[i] = data[i].projectName;
			$.each(allProjects, function(key, value) {
				if (value != 'NA') {
					$('#selectProject').append($('<option>', {
						value : data[key].idProject,
						text : value
					}));
				}
			});
		}
	});
	showPopup();
});

$('#selectProject').on('change', function(e) {
	var select = $("#selectProject option:selected").text();
	$("#btnSaveProject").prop('disabled', false);
	if (select == "GB" || select == "GB G6/G2" || select == "GB RMA") {
		$("#PO").show();
		$("#LabelPO").show();
		$("#optionalPanel").show();
	} else {
		$("#PO").hide();
		$("#LabelPO").hide();
	}
});

$("#btnSaveProject").click(function() {
	$("#btnSelect").prop("disabled", true);
	var select = $("#selectProject option:selected").text();
	$("#lblProjectName").text(select);
	$("#btnNewTicket").prop("disabled", false);
	$("#btnSaveTicket").prop("disabled", true);
	$("#btnOpen").prop("disabled", true);
	$("#btnReprint").prop("disabled", true);
	$("#btnClean").prop("disabled", false);
});

function AjaxConatiner() {
	$
			.blockUI({
				message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
			});
	var contenedor = $("#NoContainer").val();
	var proyecto = $("#selectProject option:selected").text();
	$.ajax({
		url : "addContainer",
		type : "POST",
		data : {
			contenedor : contenedor,
			proyecto : proyecto
		},
		success : function(resultado) {
			$.unblockUI();
			$("#contenidoActualizar").html(resultado);
			console.log(resultado);
		},
		error : function(errormessage) {
			$.unblockUI();
			swal({
				title : "Atención!",
				text : "Serial no valido!",
				type : "error",
				confirmButtonText : "Aceptar!"
			});
		}
	});
}

$("#NoContainer").keypress(function(e) {
	if (e.keyCode == 13) {
		addContainers();
	}
});

function addContainers() {
	var select = $("#selectProject option:selected");
	var Serial = $("#NoContainer").val();
	if (Serial) {
		AjaxConatiner();
		$("#btnSaveTicket").prop("disabled", false);
		$("#NoContainer").val("");
	} else {
		swal({
			title : "Atención!",
			text : "Ingrese un serial!",
			type : "error",
			confirmButtonText : "Entendido!"
		});
	}
}

$("#close").click(function() {
	$("#NoContainer").prop("disabled", true);
	$("#añadir").prop("disabled", true);
});

$("#cancel").click(function() {
	$("#NoContainer").prop("disabled", true);
	$("#añadir").prop("disabled", true);
});

$("#btnOpen").click(function() {
	$("#txtOpenTicket").val("");
	document.getElementById('txtOpenTicket').focus();
	$("#txtOpenTicket").focus();
});

$("#btnOpenTicket").click(function() {
	openTicket();
});

$("#btnReprint").click(function() {

});

$("#btnReprint")
		.click(
				function reprintTcicket(ticket) {
					var ticket = $("#txtOpenTicket").val();
					if (ticket) {
						$
								.ajax({
									url : 'reprintTcicket',
									type : "POST",
									data : {
										ticket : ticket
									},
									success : function(data) {
										console.log(data);
										if (data == 1)
											swal("Ticket", "Reimpreso",
													"success");
										else
											swal({
												title : "Atención!",
												text : "El ticket no se puede imprimir porque esta en embarques",
												type : "error",
												confirmButtonText : "Entendido!"
											});
									},
									error : function(vMsg) {
										console.log("Error: " + vMsg);
									}
								});
					} else {
						swal({
							title : "Atención!",
							text : "Ingrese un ticket para reimprimir",
							type : "error",
							confirmButtonText : "Entendido!"
						});
					}
				});

function openTicket() {
	$("#btnSelectProject").prop("disabled", true);
	var txtOpenTicket = $("#txtOpenTicket").val();
	if (txtOpenTicket) {
		$.ajax({
			url : 'openTicket',
			type : "POST",
			data : {
				txtOpenTicket : txtOpenTicket
			},
			success : function(data) {
				$("#contenidoActualizar").html(data);
				$("#txtTicket").val(txtOpenTicket);
				loadProject();
				$("#btnReprint").prop("disabled", false);
			},
			error : function(vMsg) {
				console.log("Error: " + vMsg);
			}
		});
		$("#btnClean").prop("disabled", false);
		$("#btnOpen").prop("disabled", true);
	} else {
		$("#btnClean").prop("disabled", true);
	}

}

function loadProject() {
	var ticket = $("#txtOpenTicket").val();
	$.ajax({
		url : 'loadProject',
		type : "POST",
		data : {
			ticket : ticket
		},
		success : function(data) {
			if (data == "Error")
				swal({
					title : "Atención!",
					text : "Ticket No Encontrado",
					type : "error",
					confirmButtonText : "Entendido!"
				});
			else
				$("#lblProjectName").text(data);
		},
		error : function(vMsg) {
			console.log("Error: " + vMsg);
		}
	});
}

$("#btnNewTicket").click(function() {
	var select = $("#selectProject option:selected").text();
	var buttonSavedPressed = $('#btnSaveTicket').submit();
	$("#btnSaveTicket").prop("disabled", true);
	$("#NoContainer").prop("disabled", false);
	document.getElementById('NoContainer').focus();
	$("#btnClean").prop("disabled", false);
	$.ajax({
		url : 'ticketAvailable',
		type : "POST",
		data : {
			select : select
		},
		success : function(data) {
			console.log('Nuevo serial: ' + data);
			$('#txtTicket').val(data);
			$("#btnNewTicket").prop("disabled", true);
		},
		error : function(vMsg) {
			console.log('Error: ' + vMsg);
		}
	});
});

$("#btnClean").click(function() {
	var newTicket = $("#tciektAvailable").val();
	var buttonEvent = "buttonEvent";
	location.reload();
	$.ajax({
		url : 'clearContainerList',
		type : "POST",
		data : {},
		success : function(data) {
			$("#contenidoActualizar").html(data);
		},
		error : function(vMsg) {
		}
	});
});

$("#btnSaveTicket").click(function() {
	SaveTicket();
});

function SaveTicket() {
	var myRows = [];
	var $headers = $("th");
	var $rows = $("tbody tr").each(function(index) {
		$cells = $(this).find("td");
		myRows[index] = {};
		$cells.each(function(cellIndex) {
			myRows[index][$($headers[cellIndex]).html()] = $(this).html();
		});
	});
	console.log(myRows);
	var project = $("#selectProject option:selected").text();
	var ticket = $("#txtTicket").val();
	$.ajax({
		url : 'saveTicket?project=' + project + '&ticket=' + ticket,
		type : "POST",
		data : JSON.stringify(myRows),
		contentType : "application/json",
		success : function(data) {
			if (data == true) {
				swal("Ticket", "Guerdado", "success")
				location.reload();
			} else
				swal('Oops...', 'El ticket no fue guardado', 'error')
		},
		error : function(vMsg) {
			console.log('Error: ' + vMsg);
		}
	});
}