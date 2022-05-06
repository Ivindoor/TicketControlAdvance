$('#selectProject').on('change', function(e) {
	var select = $("#selectProject option:selected").text();
	if (select == "Ticket") {
		$("#labelSearchTicket").text(select);
		$("#txtSearchTicket").prop("disabled", false);
		$("#btnSearchTicket").prop("disabled", false);
		document.getElementById('txtSearchTicket').focus();
		$("#txtSearchTicket").val('');
		ClearList();
	} else {
		$("#labelSearchTicket").text(select);
		$("#txtSearchTicket").prop("disabled", false);
		$("#btnSearchTicket").prop("disabled", false);
		document.getElementById('txtSearchTicket').focus();
		$("#txtSearchTicket").val('');
		ClearList();
	}
});

$("#txtSearchTicket").keypress(function(e) {
	if (e.keyCode == 13)
		searchButton();
});

function searchButton() {
	$
			.blockUI({
				message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
			});
	var select = $("#selectProject option:selected").text();
	var numTicket = $('#txtSearchTicket').val();
	if (select == "Ticket")
		SearchByTicket();
	else
		SearchByContainer();
}

function SearchByTicket() {
	var select = $("#selectProject option:selected").text();
	var numTicket = $('#txtSearchTicket').val();
	if (numTicket) {
		$.ajax({
			url : "obtenerTicketInfo",
			type : "POST",
			data : {
				numTicket : numTicket
			},
			success : function(resultado) {
				$.unblockUI();
				$("#contenidoActualizar").html(resultado);
				ActiveBtnDeleteTicket();
			},
			error : function(errormessage) {
				$.unblockUI();
				swal({
					title : "Atención!",
					text : "Ticket no encontrado!",
					type : "error",
					confirmButtonText : "Entendido!"
				});
			}
		});
		$("#txtSearchTicket").prop("disabled", true);
		$("#btnSearchTicket").prop("disabled", true);
		$("#txtSearchTicket").text(select);
	} else {
		$.unblockUI();
		swal({
			title : "Atención!",
			text : "Ingrese un ticket!",
			type : "error",
			confirmButtonText : "Entendido!"
		});

	}
}

function SearchByContainer() {
	var select = $("#selectProject option:selected").text();
	var numTicket = $('#txtSearchTicket').val();
	if (numTicket) {
		$.ajax({
			url : "obtenerContenedorInfo",
			type : "POST",
			data : {
				numContainer : numTicket
			},
			success : function(resultado) {
				$.unblockUI();
				$("#contenidoActualizar").html(resultado);
				ActiveBtnDeleteTicket();
			},
			error : function(errormessage) {
				$.unblockUI();
				swal({
					title : "Atención!",
					text : "Ticket no encontrado!",
					type : "error",
					confirmButtonText : "Entendido!"
				});
			}
		});
		$("#txtSearchTicket").prop("disabled", true);
		$("#btnSearchTicket").prop("disabled", true);
		$("#labelSearchTicket").text(select);
	} else {
		swal({
			title : "Atención!",
			text : "Ingrese un serial!",
			type : "error",
			confirmButtonText : "Entendido!"
		});
		$.unblockUI();
	}
}

function ClearList() {
	$("#contenedor").val("");
	$.ajax({
		url : 'cleanSearchList',
		type : "POST",
		data : {},
		success : function(resultado) {
			$("#contenidoActualizar").html(resultado);
		},
		error : function(vMsg) {
		}
	});
}

function ActiveBtnDeleteTicket() {
	$.ajax({
		url : 'searchLevelUser',
		type : "POST",
		data : {},
		success : function(data) {
			if (data >= 2) {
				$("#btnDeleteTicket").prop("disabled", false);
			} else
				swal({
					title : "Atención!",
					text : "No tienes permisos para eliminar tickets",
					type : "error",
					confirmButtonText : "Entendido!"
				});
		},
		error : function(vMsg) {
			console.log("Error: " + vMsg);
		}
	});
}

$("#btnDelete").click(function() {
	var select = $("#slcOption option:selected");
	var ticke_or_contrainer = $("#txtSearchTicket").val();
	var comment = $("#txtComment").val();
	if (comment) {
		swal({
			title : "Estas seguro",
			text : "Que deseas eliminar este ticket?",
			type : "warning",
			showCancelButton : true,
			confirmButtonClass : "btn-danger",
			confirmButtonText : "Si",
			closeOnConfirm : false
		}, function() {
			$.ajax({
				url : 'deleteTicket',
				type : "POST",
				data : {
					ticke_or_contrainer : ticke_or_contrainer,
					comment : comment
				},
				success : function(data) {
					if (data == true) {
						swal("Ticket", "Eliminado", "success");
						ClearList();
					} else {
						swal({
							title : "Atención!",
							text : "El ticket no se pudo eliminar",
							type : "error",
							confirmButtonText : "Entendido!"
						});
					}
				},
				error : function(vMsg) {
					swal({
						title : "Atención!",
						text : "El ticket no se pudo eliminar",
						type : "error",
						confirmButtonText : "Entendido!"
					});
				}
			});
		});
	} else {
		swal({
			title : "Atención!",
			text : "Ingrese un comentario antes de eliminar",
			type : "error",
			confirmButtonText : "Entendido!"
		});
	}
});

$("#btnDeleteTicket").click(function() {
	$('#modalDelete').modal();
	$('#modalDelete').modal('show');
});
