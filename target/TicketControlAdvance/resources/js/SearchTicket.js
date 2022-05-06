$(document).ready(
		function() {
			$.ajax({
				url : "enabledDelete",
				success : function(data) {
					if (data > 1) {
						$("#btnDeleteTicket").prop("disabled", false);
					} else {
						$("#btnDeleteTicket").prop("disabled", true);
					}
				},
				error : function(vMsg) {
					sweetAlertError("Alerta de programacion algo salio mal "
							+ vMsg + " !!!");
				}
			});
		});

$('#slcProject').on('change', function(e) {
	var select = $("#slcProject option:selected").text();
	$("#txtSearch").prop("disabled", false);
	$("#btnSearch").prop("disabled", false);
	$("#txtSearch").focus();
	$("#txtSearch").val("");
});

$("#txtSearch").keypress(function(e) {
	if (e.keyCode == 13) {
		search();
	}
});

function search() {
	block();
	var option = $("#slcProject option:selected").text();
	var value = $('#txtSearch').val();
	if (value) {
		if (option == "Ticket")
			SearchByTicket(value);
		else
			SearchBySerial(value);
	} else {
		$.unblockUI();
		sweetAlertError("Favor de ingresar un Ticket/Serial valido !!!");
	}

	var ticketOrSerial = $('#txtSearch').val();
		$.ajax({
				url : "compareUser",
				type : "POST",
				data : {
					ticketOrSerial : ticketOrSerial
				},
				success : function(data) {
					// Comparacion del usuario y ticket para su eliminacion
					if ((data.department.includes("Calidad")
							&& data.enabled == 1) || (data.department.includes("IT")
									&& data.enabled == 1)) {
						$("#btnDeleteTicket").prop("disabled", false);
					} else if (data.department.includes("Calidad")
							&& data.enabled > 1) {
						sweetAlertError("No puedes eliminar este ticket debido a que esta en embarques !!!");
						$("#btnDeleteTicket").prop("disabled", true);
					} else if ((data.department.includes("Embarques")
							&& data.enabled > 1) || (data.department.includes("IT")
									&& data.enabled > 1)) {
						$("#btnDeleteTicket").prop("disabled", false);
					} else if (data.department.includes("Embarques")
							&& data.enabled < 2) {
						sweetAlertError("No puedes eliminar este ticket debido a que esta en piso !!!");
						$("#btnDeleteTicket").prop("disabled", true);
					} else {
						$("#btnDeleteTicket").prop("disabled", true);
					}
				},
				error : function(vMsg) {
					$.unblockUI();
					sweetAlertError("Alerta de programacion algo salio mal "
							+ vMsg + " !!!");
				}
			});
}

function hideRemoveSerials (){
	var ticketOrSerial = $('#txtSearch').val();
	$.ajax({
			url : "compareUser",
			type : "POST",
			data : {
				ticketOrSerial : ticketOrSerial
			},
			success : function(data) {
				// Comparacion del usuario y ticket para su eliminacion
				if ((data.department.includes("Embarques")
						&& data.enabled > 1) || (data.department.includes("IT")
								&& data.enabled > 1)) {
					$('.removeSerial').prop("disabled",false)
				} else if (data.department.includes("IT") && data.enabled < 2) {
					$('.removeSerial').prop("disabled",false)
				} else if (data.department.includes("Embarques")
						&& data.enabled < 2) {
					$('.removeSerial').prop("disabled",true)
				} else {
					$('.removeSerial').prop("disabled",true)
				}
			},
			error : function(vMsg) {
				$.unblockUI();
				sweetAlertError("Alerta de programacion algo salio mal "
						+ vMsg + " !!!");
			}
		});
}

function SearchByTicket(value) {
	$.ajax({
		url : "searchByTicket",
		type : "POST",
		data : {
			value : value
		},
		success : function(data) {
			if (data == "Ok") {
				$.ajax({
					url : "loadInfoByTicket",
					type : "POST",
					data : {
						value : value
					},
					success : function(data) {
						$("#contenidoActualizar").html(data);
						hideRemoveSerials();
						$.unblockUI();
					},
					error : function(vMsg) {
						$.unblockUI();
						$('#txtSearch').val("");
						sweetAlertError("Alerta de programacion algo salio mal "
						+ vMsg + " !!!");
					}
				});
			} else if (data == "Ticket don't exist") {
				$.unblockUI();
				sweetAlertError("El ticket no existe !!!");
				$('#txtSearch').val("");
			} else if (data == "Serials don't exist") {
				$.unblockUI();
				sweetAlertError("Este ticket no tiene asignado ningun serial !!!");
				$('#txtSearch').val("");
			}
		},
		error : function(vMsg) {
			$.unblockUI();
			sweetAlertError("Alerta de programacion algo salio mal "
			+ vMsg + " !!!");
			$('#txtSearch').val("");
		}
	});
}

function SearchBySerial(value) {
	$.ajax({
		url : "searchBySerial",
		type : "POST",
		data : {
			value : value
		},
		success : function(data) {
			if (data == "Ok") {
				$.ajax({
					url : "loadInfoBySerial",
					type : "POST",
					data : {
						value : value
					},
					success : function(data) {
						$("#contenidoActualizar").html(data);
						hideRemoveSerials();
						$.unblockUI();
					},
					error : function(vMsg) {
						$.unblockUI();
						$('#txtSearch').val("");
						sweetAlertError("Alerta de programacion algo salio mal "
						+ vMsg + " !!!");
					}
				});
			} else if (data == "Ticket don't exist") {
				$.unblockUI();
				sweetAlertError("El ticket no existe !!!");
				$('#txtSearch').val("");
			} else if (data == "Serials don't exist") {
				$.unblockUI();
				sweetAlertError("Este ticket no tiene asignado ningun serial !!!");
				$('#txtSearch').val("");
			} else if (data == "Serial don't exist") {
				$.unblockUI();
				sweetAlertError("El serial no existe !!!");
				$('#txtSearch').val("");
			}
		},
		error : function(vMsg) {
			$.unblockUI();
			$('#txtSearch').val("");
			sweetAlertError("Alerta de programacion algo salio mal "
			+ vMsg + " !!!");
		}
	});
}

$("#btnDeleteTicket")
		.click(
				function() {
					var option = $("#slcProject option:selected").text();
					var value = $('#txtSearch').val();
					if (option && value) {
						$('#modalDelete').modal();
						$('#modalDelete').modal('show');
					} else {
						sweetAlertError("Para poder eliminar debes de ingresar un Ticket/Serial !!!");
					}
				});

$("#btnRemoveSerials").click(function() {
	$('#modalRemove').modal();
	$('#modalRemove').modal('show');
});

$("#btnDelete")
		.click(function() {
					var option = $("#slcProject option:selected").text();
					var value = $('#txtSearch').val();
					var comment = $('#txtComment').val();
					if (comment) {
						swal({
							title : "Estas seguro",
							text : "Que deseas eliminar este ticket?",
							type : "warning",
							showCancelButton : true,
							confirmButtonClass : "btn-danger",
							confirmButtonText : "Si",
							closeOnConfirm : false
						},function() {
							if (option.includes("Ticket")) {
								$.ajax({
									url : "deleteByTicket",
									type : "POST",
									data : {
										value : value,
										comment : comment
									},
									success : function(data) {
										if (data == "Ticket has deleted") {
											sweetAlertSucces("Ticket","Eliminado");
											cleanUI();
											$.unblockUI();
										} else if (data.includes("Ticket don't exist")) {
											$.unblockUI();
											sweetAlertError("El ticket no existe !!!");
										} else if (data.includes("Error: ")) {
											$.unblockUI();
											sweetAlertError(data + " !!!");
										}													},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError("Alerta de programacion algo salio mal " + vMsg + " !!!");
									}
								});
							} else {
								$.ajax({
									url : "deleteBySerial",
									type : "POST",
									data : {
										value : value,
										comment : comment
									},
									success : function(data) {
										if (data == "Ticket has deleted") {
											sweetAlertSucces("Ticket","Eliminado");
											cleanUI();
											$.unblockUI();
										} else if (data == "Serial don't exist") {
											$.unblockUI();
											sweetAlertError("El Ticket no tiene seriales asignados !!!");
										} else if (data == "Ticket don't exist") {
											$.unblockUI();
											sweetAlertError("El ticket no existe !!!");
										} else if (data == "The ticket has not been assigned to a project") {
											$.unblockUI();
											sweetAlertError("El ticket no esta asignado a un proyecto !!!");
										} else if (data.includes("Error: ")) {
											$.unblockUI();
											sweetAlertError(data + " !!!");
										}
									},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError("Alerta de programacion algo salio mal " + vMsg + " !!!");
									}
								});
							}
						});
					} else {
						sweetAlertError("Ocupas agregar un comentario del por que eliminaras el ticket, favor de ingresar un comentario para poder eliminar !!!");
					}
				});

//busqueda del serial para remover del ticket
$("#txtRmvSerial").keypress(function(e) {
	if (e.keyCode == 13) {
		var serial = $("#txtRmvSerial").val();
		$.ajax({
			url : "serialLocated",
			type : "POST",
			data : {
				serial : serial
			},
			success : function(data) {
			},
			error : function(vMsg) {
			}
		});
	}
});

function cleanUI() {
	$("#slcProject").val(0);
	$("#txtSearch").val("");
	$('#txtComment').val("");
	$("#txtSearch").prop("disabled", true);
	$("#btnSearch").prop("disabled", true);
	$.ajax({
		url : "cleanTicket",
		success : function(data) {
			$("#contenidoActualizar").html(data);
		},
		error : function(vMsg) {
			sweetAlertError("Alerta de programacion algo salio mal " + vMsg + " !!!");
		}
	});
}

function sweetAlertError(data) {
	swal({
		title : "Advertencia !!!",
		text : data,
		type : "error",
		confirmButtonText : "Aceptar"
	});
}

function sweetAlertSucces(title, data) {
	swal(title, data, "success");
}

function sweetAlertConfirm(body, footer, footerbody) {
	swal({
		title : 'Are you sure?',
		text : "body" + "it's the amount you want to save!",
		type : 'warning',
		showCancelButton : true,
		confirmButtonColor : '#3085d6',
		cancelButtonColor : '#d33',
		confirmButtonText : 'Yes!'
	}).then(
			function() {
				swal('footer' + 'Deleted!', 'footerbody' + 'Your file has been deleted.', 'success')
	});
}

function block() {
	$.blockUI({message : '<img src="resources/img/loading.gif" width="150" height="150"/>'});
}