function showPopup() {
	var project = $("#selectProject option:selected").text();
	if (!project == "SELECCIONE UN PROYECTO")
		$("#btnSaveProject").prop('disabled', true);
	$('#MyModal').modal();
	$('#myModal').modal('toggle');
	$('#myModal').modal('show');
}

$("#selectProject").change(function() {
	var project = $("#selectProject option:selected").text();
	if (!project == "SELECCIONE UN PROYECTO")
		$("#btnSaveProject").prop('disabled', true);
});

$(document).ready(function() {
	var allProjects = [];
	$.ajax({
		url : 'getProjects',
		success : function(data) {
			for (var i = 0; i < data.length; i++)
				allProjects[i] = data[i].projectName;
			$.each(allProjects, function(key, value) {
				if (value != 'NA' && value != 'EMBARQUES' && value != 'NOKIA') {
					$('#selectProject').append($('<option>', {
						value : data[key].idProject,
						text : value
					}));
				}
			});
		}
	});
});

function restartValues() {
	$("#btnReprintTicket").prop('disabled', true);
	$('#txtTicket').val("");
	$('#lblProject').text("");
	$('#txtPartNumber').val("");
	$("#txtContainer").prop('disabled', true);
	$('#txtQty').val("");
	$("#txtQty").prop('disabled', true);
	$("#btnSaveTicket").prop('disabled', true);
	$("#btnClean").prop('disabled', true);
	$("#btnSelectProject").prop('disabled', false);
	$("#btnOpen").prop('disabled', false);
	$('#btnPoLine').text("");
	$('#btnRequired').text("");
	$('#btnStored').text("");
	$('#dataInfortamtion').hide();
	$('#divInstruction').hide();
	$('#divPartNumber').hide();
	$('#divGlobalValid').hide();
	$('#selectProject').val(0);
	$('#divValid').show();
	$.ajax({
		url : 'restartValues',
		success : function(data) {
			$('#contenidoActualizar').html(data);
		},
		error : function(vMsg) {
			sweetAlertError("Alerta de programacion algo salio mal: " + vMsg
					+ "!!!");
		}
	});

	$.ajax({
		url : 'restartPartial',
		success : function(data) {
			$('#contenidoPartial').html(data);
		},
		error : function(vMsg) {
			sweetAlertError("Alerta de programacion algo salio mal: " + vMsg
					+ "!!!");
		}
	});
	$('#contenidoActualizar').show();
	$('#contenidoPartial').hide();
}

$("#txtQty").keypress(function(e) {
	if (e.keyCode == 13) {
		$("#txtQty").prop('disabled', true);
		$("#btnSaveTicket").prop('disabled', false);
	}
});

$("#btnSaveTicket")
		.click(
				function() {
					var ticket = $('#txtTicket').val();
					var project = $("#selectProject option:selected").text();
					var poLine = $('#btnPoLine').text();
					var qty = $('#txtQty').val();
					var partNumber = $('#txtPartNumber').val();
					if (ticket) {
						block();
						// var myRows = [];
						// var $headers = $("th");
						// var $rows = $("#tblTicket > tbody > tr").each(
						// function(index) {
						// $cells = $(this).find("td");
						// myRows[index] = {};
						// $cells.each(function(cellIndex) {
						// myRows[index][$($headers[cellIndex])
						// .html()] = $(this).html();
						// });
						// });
						$
								.ajax({
									url : 'saveTicket?project=' + project
											+ '&ticket=' + ticket + '&poLine='
											+ poLine + '&qty=' + qty
											+ '&partNumber=' + partNumber,
									type : 'POST',
									// data : JSON.stringify(myRows),
									// contentType : "application/json",
									success : function(data) {
										if (data == "PoLine completa") {
											sweetAlertError("Esta po ya fue completada !!!");
											$.unblockUI();
										} else if (data == "Te estas excediendo de la cantidad requerida") {
											sweetAlertError("El ticket no puede ser guardado puesto que se excede de la cantidad requerida !!!");
											$.unblockUI();
										} else if (data == "Ok") {
											if (project.includes("MCE")) {
												$
														.ajax({
															url : 'buildPDF',
															type : 'POST',
															data : {
																ticket : ticket,
																project : project,
															},
															success : function(
																	data) {
																window
																		.open("buildPDF?project="
																				+ project
																				+ "&ticket="
																				+ ticket);
																swal(
																		"Ticket",
																		"Generado",
																		"success");
																restartValues();
																$.unblockUI();
															},
															error : function(
																	vMsg) {
																$.unblockUI();
																sweetAlertError(vMsg);
															}
														});
											} else if (project
													.includes("ECHOSTAR")
													|| project
															.includes("SMARDTV")
													|| project
															.includes("VERIZON")) {
												$
														.ajax({
															url : 'buildPDFECHO',
															type : 'POST',
															data : {
																ticket : ticket,
																project : project
															},
															success : function(
																	data) {
																window
																		.open(
																				"buildPDFECHO?project="
																						+ project
																						+ "&ticket="
																						+ ticket,
																				'_blank');
																swal(
																		"Ticket",
																		"Generado",
																		"success");
																restartValues();
																$.unblockUI();
															},
															error : function(
																	vMsg) {
																$.unblockUI();
																sweetAlertError(vMsg);
															}
														});
											} else if (project.includes("CREE")) {
												$
														.ajax({
															url : 'buildPDFCEE',
															type : 'POST',
															data : {
																ticket : ticket,
																project : project,
															},
															success : function(
																	data) {
																window
																		.open("buildPDFCEE?project="
																				+ project
																				+ "&ticket="
																				+ ticket);
																swal(
																		"Ticket",
																		"Generado",
																		"success");
																restartValues();
																$.unblockUI();
															},
															error : function(
																	vMsg) {
																$.unblockUI();
																sweetAlertError(vMsg);
															}
														});
											} else if (project
													.includes("COLOR KINETICS PCA")) {
												$
														.ajax({
															url : 'buildPDFColor',
															type : 'POST',
															data : {
																ticket : ticket,
																project : project,
															},
															success : function(
																	data) {
																window
																		.open("buildPDFColor?project="
																				+ project
																				+ "&ticket="
																				+ ticket);
																swal(
																		"Ticket",
																		"Generado",
																		"success");
																restartValues();
																$.unblockUI();
															},
															error : function(
																	vMsg) {
																$.unblockUI();
																sweetAlertError(vMsg);
															}
														});
											} else {
												$
														.ajax({
															url : 'printTicket',
															type : 'POST',
															data : {
																ticket : ticket,
																project : project,
																poLine : poLine
															},
															success : function(
																	data) {
																if (data
																		.includes("Success")) {
																	sweetAlertSucces(
																			"Ticket",
																			"Generado !!!");
																	restartValues();
																	$
																			.unblockUI();
																} else if (data
																		.includes("Fail")) {
																	sweetAlertError("El socket de impresion sigue abierto favor de contactar a IT !!!");
																	$
																			.unblockUI();
																} else if (data
																		.includes("ZEBRA_NOT_FOUND ")) {
																	sweetAlertError("No se pudo imprimir el ticket por que no se detecto la impresora !!!");
																	$
																			.unblockUI();
																} else if (data
																		.includes("Error: ")) {
																	sweetAlertError(data);
																	$
																			.unblockUI();
																}
															},
															error : function(
																	vMsg) {
																$.unblockUI();
																sweetAlertError(vMsg);
															}
														});
											}
										}
									},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError(vMsg);
									}
								});
					} else {
						$.unblockUI();
						sweetAlertError("No se puede realizar esta opcion por que no hay un numero de  ticket cargado !!!");
					}
				});

$("#btnClean").click(
		function() {
			block();
			var ticket = $('#txtTicket').val();
			var myRows = [];
			var $headers = $("th");
			var $rows = $("#tblTicket > tbody > tr").each(
					function(index) {
						$cells = $(this).find("td");
						myRows[index] = {};
						$cells.each(function(cellIndex) {
							myRows[index][$($headers[cellIndex]).html()] = $(
									this).html();
						});
					});
			$.ajax({
				url : 'cleanTicket?ticket=' + ticket,
				type : 'POST',
				data : JSON.stringify(myRows),
				contentType : "application/json",
				success : function(data) {
					if (data == "Ok") {
						restartValues();
						$.unblockUI();
					} else if (data.includes("Error: ")) {
						$.unblockUI();
						sweetAlertError(data);
					}
				},
				error : function(vMsg) {
					$.unblockUI();
					sweetAlertError("Alerta de programacion algo salio mal: "
							+ vMsg + " !!!");
				}
			});

		});

$('#myModalOpen').on('shown.bs.modal', function() {
	$('#txtOpenTicket').val("");
	$('#txtOpenTicket').focus();
})

$("#btnOpenTicket")
		.click(
				function() {
					block();
					var ticket = $('#txtOpenTicket').val();
					if (ticket) {

						$
								.ajax({
									url : 'validateTicketOpen',
									type : 'POST',
									data : {
										ticket : ticket
									},
									success : function(data) {
										if (data == "1") {
											$
													.ajax({
														url : 'openTicket',
														type : 'POST',
														data : {
															ticket : ticket
														},
														success : function(data) {
															if (data) {
																console
																		.log(data);
																$
																		.ajax({
																			url : 'loadProject',
																			type : 'POST',
																			data : {
																				ticket : ticket
																			},
																			success : function(
																					data) {
																				if (data
																						.includes("Ok")) {
																					$(
																							'#lblProject')
																							.text(
																									data
																											.substring(
																													2,
																													data.length));
																				} else if (data
																						.includes("El ticket no tiene un proyecto asignado")) {
																					restartValues();
																					sweetAlertError(data);
																				} else if (data
																						.includes("El ticket no existe en la base de datos")) {
																					restartValues();
																					sweetAlertError(data);
																				}
																			},
																			error : function(
																					vMsg) {
																				$
																						.unblockUI();
																				sweetAlertError("Alerta de programacion algo salio mal: "
																						+ vMsg
																						+ " !!!");
																			}
																		});
																$("#btnOpen")
																		.prop(
																				'disabled',
																				true);
																$(
																		"#btnReprintTicket")
																		.prop(
																				'disabled',
																				false);
																$('#txtTicket')
																		.val(
																				ticket);
																$(
																		'#contenidoActualizar')
																		.html(
																				data)
															} else {
																restartValues();
																sweetAlertError("Error al buscar el ticket verifique si el ticket realmente existe !!!");
															}
															$.unblockUI();
														},
														error : function(vMsg) {
															$.unblockUI();
															sweetAlertError("Alerta de programacion algo salio mal: "
																	+ vMsg
																	+ " !!!");
														}
													});
										} else if (data == "2") {
											restartValues();
											sweetAlertError("El ticket no se puede abrir por que se encuentra en embarques !!!");
										} else if (data == "3") {
											restartValues();
											sweetAlertError("El ticket no se puede abrir por que se esta cargando a troca !!!");
										} else if (data == "4") {
											restartValues();
											sweetAlertError("El ticket no se puede abrir por que a sido embarcado !!!");
										} else if (data == "0") {
											restartValues();
											sweetAlertError("El ticket no ah sido liberado con unidades asignadas a el !!!");
										} else {
											restartValues();
											sweetAlertError("Error al buscar el ticket verifique si el ticket realmente existe !!!");
										}
										$.unblockUI();
									},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError("Alerta de programacion algo salio mal: "
												+ vMsg + " !!!");
									}
								});
					} else {
						sweetAlertError("Favor de insertar un ticket valido !!!");
						$.unblockUI();
					}
				});

$("#btnReprintTicket")
		.click(
				function() {
					var project = $("#lblProject").text();
					block();
					var ticket = $('#txtTicket').val();
					var myRows = [];
					var $headers = $("th");
					var $rows = $("#tblTicket > tbody > tr")
							.each(
									function(index) {
										$cells = $(this).find("td");
										myRows[index] = {};
										$cells
												.each(function(cellIndex) {
													myRows[index][$(
															$headers[cellIndex])
															.html()] = $(this)
															.html();
												});
									});
					if (project.includes("MCE")) {
						$
								.ajax({
									url : 'buildPDF',
									type : 'POST',
									data : {
										ticket : ticket,
										project : project
									},
									success : function(data) {
										window
												.open("buildPDF?project="
														+ project + "&ticket="
														+ ticket);
										swal("Ticket", "Reimpreso", "success");
										restartValues();
										$.unblockUI();
									},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError("Alerta de programacion algo salio mal: "
												+ vMsg + " !!!");
									}
								});
					} else if (project.includes("CREE")) {
						$.ajax({
							url : 'buildPDFCEE',
							type : 'POST',
							data : {
								ticket : ticket,
								project : project,
							},
							success : function(data) {
								window.open("buildPDFCEE?project=" + project
										+ "&ticket=" + ticket);
								swal("Ticket", "Reimpreso", "success");
								restartValues();
								$.unblockUI();
							},
							error : function(vMsg) {
								$.unblockUI();
								sweetAlertError(vMsg);
							}
						});
					} else if (project.includes("ECHOSTAR")
							|| project.includes("SMARDTV")
							|| project.includes("VERIZON")) {
						$.ajax({
							url : 'buildPDFECHO',
							type : 'POST',
							data : {
								ticket : ticket,
								project : project,
							},
							success : function(data) {
								window.open("buildPDFECHO?project=" + project
										+ "&ticket=" + ticket);
								swal("Ticket", "Reimpreso", "success");
								restartValues();
								$.unblockUI();
							},
							error : function(vMsg) {
								$.unblockUI();
								sweetAlertError(vMsg);
							}
						});
					} else if (project.includes("COLOR KINETICS PCA")) {
						$.ajax({
							url : 'buildPDFColor',
							type : 'POST',
							data : {
								ticket : ticket,
								project : project,
							},
							success : function(data) {
								window.open("buildPDFColor?project=" + project
										+ "&ticket=" + ticket);
								swal("Ticket", "Generado", "success");
								restartValues();
								$.unblockUI();
							},
							error : function(vMsg) {
								$.unblockUI();
								sweetAlertError(vMsg);
							}
						});
					} else {
						$
								.ajax({
									url : 'reprintTicket?ticket=' + ticket,
									type : 'POST',
									data : JSON.stringify(myRows),
									contentType : "application/json",
									success : function(data) {
										if (data.includes("Success")) {
											sweetAlertSucces("Ticket",
													"Generado !!!");
											restartValues();
											$.unblockUI();
										} else if (data.includes("Fail")) {
											sweetAlertError("El socket de impresion sigue abierto favor de contactar a IT !!!");
											$.unblockUI();
										} else if (data
												.includes("ZEBRA_NOT_FOUND ")) {
											sweetAlertError("No se pudo imprimir el ticket por que no se detecto la impresora !!!");
											$.unblockUI();
										} else if (data
												.includes("no tiene asignada una")) {
											sweetAlertError("Este ticket no se puede imprimir por que "
													+ data + " !!!");
											$.unblockUI();
										} else if (data.includes("Error:")) {
											sweetAlertError(data);
											$.unblockUI();
										}
									},
									error : function(vMsg) {
										$.unblockUI();
										sweetAlertError("Alerta de programacion algo salio mal: "
												+ vMsg + " !!!");
									}
								});
					}
				});

$("#btnSaveProject").click(
		function() {
			var project = $("#selectProject option:selected").text();
			$("#btnOpen").prop('disabled', true);
			$("#lblProject").text(project);
			if (project.includes("DIALIGHT") || project.includes("CREE")) {
				$("#btnNewTicket").prop('disabled', false);
				// loadAllInstruction(project);
				// $('#divInstruction').show();
				// $('#dataShippingInstruction').show();
				$("#divPartNumber").show();
				$("#divPartNumber").prop('disabled', false);
				$("#divPartNumber").focus();
				$('#divPO').hide();
				$("#txtPo").prop('disabled', true);
				// } else if(project.includes("SIEMENS")){
				// $("#btnNewTicket").prop('disabled', true);
				// loadAllInstruction(project);
				// $('#divInstruction').show();
				// $('#dataShippingInstruction').show();
				// $("#divPartNumber").hide();
				// $('#divPO').hide();
				// $("#txtPo").prop('disabled', true);
			} else if (project.includes("GB RMA")
					|| project.includes("GB G6/G2")
					|| project.includes("GB LEGACY") || project.includes("GB")
					|| project.includes("GB CVAS")
					|| project.includes("GB FRAME")) {
				$("#btnNewTicket").prop('disabled', false);
				$('#divPO').show();
				$("#txtPo").prop('disabled', false);
				$("#txtPo").focus();
			} else {
				$('#divPO').hide();
				$("#btnNewTicket").prop('disabled', false);
				$("#divPartNumber").hide();
				$('#divInstruction').hide();
				$('#dataShippingInstruction').hide();
				$('#dataInfortamtion').hide();
				$("#txtPo").prop('disabled', true);
			}
		});

$('#selectProject').on('change', function(e) {
	$('#btnSaveProject').prop('disabled', false);
});

$("#txtPartNumber").keypress(function(e) {
	if (e.keyCode == 13) {
		$("#txtPartNumber").prop("disabled", true);
		$("#txtContainer").prop("disabled", false);
		$("#txtContainer").focus();
	}
});

$("#txtPo").keypress(function(e) {
	var po = $("#txtPo").val();
	if (e.keyCode == 13) {
		if (po) {
			$("#txtPo").prop('disabled', true);
			$("#txtContainer").prop('disabled', false);
			$("#txtContainer").focus();
		} else {
			sweetAlertError("Favor de ingresar una po.");
		}
	}
});

$("#txtContainer")
		.keypress(
				function(e) {
					if (e.keyCode == 13) {
						block();
						var ticket = $('#txtTicket').val();
						var container = $("#txtContainer").val();
						var partNumber = $("#txtPartNumber").val();
						var project = $("#selectProject option:selected")
								.text();
						var poLine = $('#btnPoLine').text();
						if (container) {
							if (project.includes("IUSA")) {
								$
										.ajax({
											type : 'POST',
											url : 'validateSerialMES',
											data : {
												container : container,
												ticket : ticket
											},
											success : function(data) {
												if (data.includes("Ok")) {
													$
															.ajax({
																type : 'POST',
																url : 'comparePartNumber',
																data : {
																	container : container,
																	partNumber : partNumber,
																	ticket : ticket,
																	project : project
																},
																success : function(
																		data) {
																	if (data == "El numero de parte no coincide") {
																		sweetAlertError("El numero de parte no coincide !!!");
																		$
																				.unblockUI();
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																	} else if (data == "OK") {
																		$
																				.ajax({
																					type : 'POST',
																					url : 'addContainer',
																					data : {
																						container : container,
																						ticket : ticket
																					},
																					success : function(
																							data) {
																						$(
																								"#txtContainer")
																								.val(
																										"");
																						$(
																								"#txtContainer")
																								.focus();
																						$(
																								'#contenidoActualizar')
																								.html(
																										data);
																						$(
																								"#btnSaveTicket")
																								.prop(
																										"disabled",
																										false);
																						$(
																								"#btnClean")
																								.prop(
																										"disabled",
																										false);
																						$
																								.unblockUI();
																					},
																					error : function(
																							vMsg) {
																						sweetAlertError(vMsg);
																						$
																								.unblockUI();
																					}
																				});
																	} else if (data
																			.includes("Error:")) {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError(data);
																		$
																				.unblockUI();
																	}
																},
																error : function(
																		vMsg) {
																	$
																			.unblockUI();
																	sweetAlertError(vMsg);
																	$(
																			"#txtContainer")
																			.val(
																					"");
																	$(
																			"#txtContainer")
																			.focus();
																}
															});
												} else if (data
														.includes("Seriales sin shop order")) {
													$.unblockUI();
													sweetAlertError(data);
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("This container haven't serials")) {
													$.unblockUI();
													sweetAlertError("No se encontraron registros de los seriales al realizar la consulta a 42Q!!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Ticket ")) {
													$.unblockUI();
													sweetAlertError("El serial ya existe en el "
															+ data + " !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Este numero de parte no esta adaptado para el ticket control advance")) {
													$.unblockUI();
													sweetAlertError(data);
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Serial error:")) {
													$.unblockUI();
													sweetAlertError(data);
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Serial not found")) {
													$.unblockUI();
													sweetAlertError("Serial no encontrado !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Error: ")) {
													$.unblockUI();
													sweetAlertError(data
															+ " !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												}
											},
											error : function(vMsg) {
												$.unblockUI();
												sweetAlertError(vMsg);
												$("#txtContainer").val("");
												$("#txtContainer").focus();
											}
										});
							} else if (project.includes("MCE")) {
								$
										.ajax({
											type : 'POST',
											url : 'validateSerialMCE',
											data : {
												container : container,
												ticket : ticket,
												project : project
											},
											success : function(data) {
												if (data.includes("Ok")) {
													$
															.ajax({
																type : 'POST',
																url : 'addContainer',
																data : {
																	container : container,
																	ticket : ticket
																},
																success : function(
																		data) {
																	$(
																			"#txtContainer")
																			.val(
																					"");
																	$(
																			"#txtContainer")
																			.focus();
																	$(
																			'#contenidoActualizar')
																			.html(
																					data);
																	$(
																			"#btnSaveTicket")
																			.prop(
																					"disabled",
																					false);
																	$(
																			"#btnClean")
																			.prop(
																					"disabled",
																					false);
																	$
																			.unblockUI();
																},
																error : function(
																		vMsg) {
																	$(
																			"#txtContainer")
																			.val(
																					"");
																	$(
																			"#txtContainer")
																			.focus();
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												} else if (data
														.includes("Error: ")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("El skid ya extiste en el ticket: ")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("El skid no existe en la base de datos del skid control")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("El skid no tiene seriales asignados en el skid control")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("El numero de parte de este skid no coincide con el resto")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												}
											},
											error : function(vMsg) {
												sweetAlertError(vMsg);
												$.unblockUI();
											}
										});
							} else if (project.includes("CREE")) {
								$
										.ajax({
											type : 'POST',
											url : 'coreCree',
											data : {
												container : container,
												ticket : ticket,
												project : project,
												identifierPart : partNumber
											},
											success : function(data) {
												if (data.includes("Ok")) {
													$
															.ajax({
																type : 'POST',
																url : 'comparePartNumber',
																data : {
																	container : container,
																	partNumber : partNumber,
																	ticket : ticket,
																	project : project
																},
																success : function(
																		data) {
																	if (data == "El numero de parte no coincide") {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError(data);
																		$
																				.unblockUI();
																	} else if (data
																			.includes("Error: ")) {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError(data);
																		$
																				.unblockUI();
																	} else if (data
																			.includes("Serial not found")) {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError("Serial no encontrado");
																		$
																				.unblockUI();
																	} else if (data
																			.includes("Este serial no tiene asignado un numero de parte")) {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError("Este serial no tiene asignado un numero de parte");
																		$
																				.unblockUI();
																	} else if (data
																			.includes("El numero de parte no coincide")) {
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																		sweetAlertError("El numero de parte no coincide");
																		$
																				.unblockUI();
																	} else if (data == "OK") {
																		if (project
																				.includes("CREE PCA")) {
																			swal(
																					"Validacion de seriales!",
																					"Favor de escanear ciertos seriales al azar que esten dentro del contenedor");
																			$(
																					'#divValid')
																					.hide();
																			$
																					.ajax({
																						url : 'searchSerialByContainer',
																						type : 'POST',
																						data : {
																							container : container
																						},
																						success : function(
																								data) {
																							$(
																									'#divGlobalValid')
																									.show();
																							$(
																									'#txtValid')
																									.focus();
																							$(
																									'#divValid')
																									.html(
																											data);
																							$
																									.unblockUI();
																						},
																						error : function(
																								vMsg) {
																							$
																									.unblockUI();
																							sweetAlertError(vMsg);
																						}
																					});
																		} else {
																			$
																					.ajax({
																						type : 'POST',
																						url : 'addContainer',
																						data : {
																							container : container,
																							ticket : ticket
																						},
																						success : function(
																								data) {
																							$(
																									"#txtContainer")
																									.val(
																											"");
																							$(
																									"#txtContainer")
																									.focus();
																							$(
																									'#contenidoActualizar')
																									.html(
																											data);
																							$(
																									"#btnSaveTicket")
																									.prop(
																											"disabled",
																											false);
																							$(
																									"#btnClean")
																									.prop(
																											"disabled",
																											false);
																							$
																									.unblockUI();
																						},
																						error : function(
																								vMsg) {
																							$(
																									"#txtContainer")
																									.val(
																											"");
																							$(
																									"#txtContainer")
																									.focus();
																							sweetAlertError(vMsg);
																							$
																									.unblockUI();
																						}
																					});
																		}
																	}
																},
																error : function(
																		vMsg) {
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												} else if (data
														.includes("El numero de parte del contenedor")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("This ticket only allows one container")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError("Solo esta permitido un contenedor por ticket");
													$.unblockUI();
												} else if (data
														.includes("Ticket")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError("El contenedor se encuentra asignado en el "
															+ data);
													$.unblockUI();
												} else if (data
														.includes("El numero de parte del serial no pertenece a la familia")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("Serial not found")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError("Serial no encontrado");
													$.unblockUI();
												} else if (data
														.includes("Este serial no tiene asignado un numero de parte")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("Seriales sin shop order ")) {
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													sweetAlertError(data);
													$.unblockUI();
												}
											},
											error : function(vMsg) {
												sweetAlertError(vMsg);
												$.unblockUI();
											}
										});
							} else if (project.includes("ECHOSTAR")
									|| project.includes("SMARDTV")
									|| project.includes("VERIZON")) {
								$
										.ajax({
											type : 'POST',
											url : 'coreEchoStar',
											data : {
												container : container,
												ticket : ticket,
												project : project
											},
											success : function(data) {
												if (data.includes("Ok")) {
													$
															.ajax({
																type : 'POST',
																url : 'addContainer',
																data : {
																	container : container,
																	ticket : ticket
																},
																success : function(
																		data) {
																	$(
																			"#txtContainer")
																			.val(
																					"");
																	$(
																			"#txtContainer")
																			.focus();
																	$(
																			'#contenidoActualizar')
																			.html(
																					data);
																	$(
																			"#btnSaveTicket")
																			.prop(
																					"disabled",
																					false);
																	$(
																			"#btnClean")
																			.prop(
																					"disabled",
																					false);
																	$
																			.unblockUI();
																},
																error : function(
																		vMsg) {
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												} else if (data
														.includes("Inserccion de multiples tickets")) {
													$
															.ajax({
																type : 'POST',
																url : 'multipleContainer',
																data : {
																	container : container,
																	ticket : ticket,
																	project : project
																},
																success : function(
																		data) {
																	$(
																			"#txtContainer")
																			.val(
																					"");
																	$(
																			"#txtContainer")
																			.prop(
																					"disabled",
																					true);
																	$(
																			'#contenidoActualizar')
																			.hide();
																	$(
																			'#contenidoPartial')
																			.html(
																					data);
																	$(
																			"#btnSaveTicket")
																			.prop(
																					"disabled",
																					false);
																	$(
																			"#btnClean")
																			.prop(
																					"disabled",
																					false);
																	$
																			.unblockUI();
																},
																error : function(
																		vMsg) {
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												} else if (data
														.includes("validation")) {
													$("#txtContainer").prop(
															"disabled", true);
													$
															.ajax({
																type : 'POST',
																url : 'echoValid',
																data : {
																	container : container,
																},
																success : function(
																		data) {
																	$(
																			'#divGlobalValid')
																			.show();
																	$(
																			'#txtValid')
																			.focus();
																	$(
																			'#divValid')
																			.html(
																					data);
																	$
																			.unblockUI();
																},
																error : function(
																		vMsg) {
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												} else if (data
														.includes("El skid ya extiste en el ticket:")) {
													sweetAlertError(data);
													$.unblockUI();
												} else if (data
														.includes("Serial not found in MESR 42Q")) {
													sweetAlertError("El serial no fue encontrado en 42Q por el WebService");
													$.unblockUI();
												} else if (data
														.includes("This ticket only allows one skid")) {
													sweetAlertError("Solo esta permitido un skid por ticket");
													$.unblockUI();
												} else if (data
														.includes("This ticket is null")) {
													sweetAlertError("El ticket no fue encontrado, favor de validar que el ticket disponible haya sido generado correctamente");
													$.unblockUI();
												} else if (data
														.includes("Seriales sin shop order")) {
													sweetAlertError(data);
													$("#txtContainer").val("");
													$("#txtContainer").focus();
													$.unblockUI();
												}
											},
											error : function(data) {
												sweetAlertError(vMsg);
												$.unblockUI();
											}
										});
							} else if (project.includes("GB RMA")) {
								var po = $('#txtPo').val;
								$.ajax({
									type : 'POST',
									url : 'coreGBRMA',
									data : {
										container : container,
										ticket : ticket,
										project : project,
										po : po
									},
									success : function(data) {

									},
									error : function(vMsg) {

									}
								});
							} else {
								$
										.ajax({
											type : 'POST',
											url : 'validateSerial',
											data : {
												container : container,
												ticket : ticket,
												partNumber : partNumber,
												project : project
											},
											success : function(data) {
												if (data == "Serial not found") {
													$.unblockUI();
													sweetAlertError("El serial no fue encontrado !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Error: ")) {
													$.unblockUI();
													sweetAlertError(data
															+ " !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "Ya no puedes agregar mas seriales a este ticket") {
													$.unblockUI();
													sweetAlertError("Ya no puedes agregar mas seriales a este ticket !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "Ya no puedes agregar mas contenedores a este ticket") {
													$.unblockUI();
													sweetAlertError("Ya no puedes agregar mas contenedores a este ticket !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "Este serial no tiene asignado un numero de parte") {
													$.unblockUI();
													sweetAlertError("Este serial no tiene asignado un numero de parte !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "Este serial no tiene asignada una ubicacion") {
													$.unblockUI();
													sweetAlertError("Este serial no tiene asignada una ubicacion !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "El serial no se encuentra en complete") {
													$.unblockUI();
													sweetAlertError("El serial no se encuentra en complete !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Ticket ")) {
													$.unblockUI();
													sweetAlertError("El serial ya existe en el "
															+ data + " !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Este serial no esta asignado a una shop order")) {
													$.unblockUI();
													sweetAlertError("Este serial no esta asignado a una shop order !!!");
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data
														.includes("Seriales sin shop order")) {
													$.unblockUI();
													sweetAlertError(data);
													$("#txtContainer").val("");
													$("#txtContainer").focus();
												} else if (data == "Ok") {
													$
															.ajax({
																type : 'POST',
																url : 'comparePartNumber',
																data : {
																	container : container,
																	partNumber : partNumber,
																	ticket : ticket,
																	project : project,
																	poLine : poLine
																},
																success : function(data) {
																	if (data == "OK") {
																		
																		if (project.includes("DIGITAL LUMENS CONTAINER")) {
																			swal("Validacion de seriales!",
																					"Favor de escanear ciertos seriales al azar que esten dentro del contenedor");
																			$('#divValid').hide();
																			$.ajax({
																				url : 'searchSerialByContainer',
																				type : 'POST',
																				data : {
																					container : container
																				},
																				success : function(data) {
																					$('#divGlobalValid').show();
																					$('#txtValid').focus();
																					$('#divValid').html(data);
																					$.unblockUI();
																				},
																				error : function(vMsg) {
																					$.unblockUI();
																					sweetAlertError(vMsg);
																				}
																			});
																		} else {
																		
																	
																		$
																				.ajax({
																					type : 'POST',
																					url : 'addContainer',
																					data : {
																						container : container,
																						ticket : ticket
																					},
																					success : function(
																							data) {
																						$(
																								"#txtContainer")
																								.val(
																										"");
																						$(
																								"#txtContainer")
																								.focus();
																						$(
																								'#contenidoActualizar')
																								.html(
																										data)
																						$(
																								"#btnSaveTicket")
																								.prop(
																										"disabled",
																										false);
																						$(
																								"#btnClean")
																								.prop(
																										"disabled",
																										false);
																						$
																								.unblockUI();
																					},
																					error : function(
																							vMsg) {
																						sweetAlertError("Alerta de programacion algo salio mal: "
																								+ vMsg
																								+ " !!!");
																						$
																								.unblockUI();
																					}
																				});
																	}	
																	} else {
																		sweetAlertError(data);
																		$
																				.unblockUI();
																		$(
																				"#txtContainer")
																				.val(
																						"");
																		$(
																				"#txtContainer")
																				.focus();
																	}
																},
																error : function(
																		vMsg) {
																	sweetAlertError(vMsg);
																	$
																			.unblockUI();
																}
															});
												}
											},
											error : function(vMsg) {
												sweetAlertError(vMsg);
												$.unblockUI();
											}
										});
							}
						} else {
							sweetAlertError("Favor de insertar un serial valido !!!");
							$.unblockUI();
						}
					}
				});

$('#txtValid').keypress(
		function(e) {
			console.log("code " + e.keyCode)
			if (e.keyCode == 13) {
				block();
				var project = $("#selectProject option:selected").text();
				var containerValid = $('#txtValid').val();
				$('#txtValid').val("");
				var count = 0;
				$("#tblValid tr").each(function() {
					var $tds = $(this).find('td');
					if ($tds.eq(0).text() == $.trim(containerValid)) {
						$(this).find('td').css('background-color', '#ff6666');
						$(this).find('td').eq(3).html('<h4>Si</h4>');
					}
					if ($tds.eq(3).text() == "Si") {
						count++;
					}
				});
				var rowCount = $('#tblValid tr').length;
				if (project.includes("CREE PCA")) {
					console.log("rowCount " + rowCount);
					// Cambio para cree
					if (rowCount <= 3) {
						// //Codigo para validar un solo serial si es igual o
						// menor a 3
						if (count >= 1) {
							console.log("Es mayor a uno el count")
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
					} else {
						if (count >= 3) {
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
					}

				} else if(project.includes("DIGITAL LUMENS CONTAINER")){
					if(rowCount >= 40){
						//validacion por 20
						console.log("validacion por 20");
						if (count >= 20) {
							console.log("Es mayor a uno el count")
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
					}	else if(  rowCount >= 30){
						//validacion por 10					
						console.log("validacion por 10");
						if (count >= 10) {
							console.log("Es mayor a uno el count")
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
					}	else if( rowCount >= 4){
						//validacion por 3
						console.log("validacion por 3");
						if (count >= 3) {
							console.log("Es mayor a uno el count")
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
						
					}	else if( rowCount >= 2){
						//validacion por 2
						console.log("validacion por 2");
						if (count >= 2) {
							console.log("Es mayor a uno el count")
							$('#divGlobalValid').hide();
							$('#divValid').html("");
							var skid = $('#txtContainer').val();
							var ticket = $('#txtTicket').val();
							console.log("Serial y ticket " + skid + " " + ticket);
							$
									.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
						} else {
							$.unblockUI();
						}
					}
					
				} else {
					if (count >= 5) {
						var skid = $('#txtContainer').val();
						var ticket = $('#txtTicket').val();
						$.ajax({
							type : 'POST',
							url : 'skidInsert',
							data : {
								skid : skid,
								ticket : ticket
							},
							success : function(data) {
								if (data.includes("Ok")) {
									$.ajax({
										type : 'POST',
										url : 'addContainer',
										data : {
											skid : skid,
											ticket : ticket
										},
										success : function(data) {
											$("#txtContainer").val("");
											$("#txtContainer").focus();
											$('#contenidoActualizar')
													.html(data)
											$("#btnSaveTicket").prop(
													"disabled", false);
											$("#btnClean").prop("disabled",
													false);
											$.unblockUI();
										},
										error : function(vMsg) {
											sweetAlertError(vMsg);
											$.unblockUI();
										}
									});
								} else if (data.includes("Error: ")) {
									sweetAlertError(data);
									$.unblockUI();
								}
							},
							error : function(vMsg) {
								sweetAlertError(vMsg);
								$.unblockUI();
							}
						});
					} else {
						$.unblockUI();
					}
				}
			}
		});

$("#close").click(function() {
	$("#txtContainer").prop("disabled", true);
	$("#añadir").prop("disabled", true);
});

$("#cancel").click(function() {
	$("#txtContainer").prop("disabled", true);
	$("#añadir").prop("disabled", true);
});

$("#btnNewTicket").click(function() {
	$("#btnNewTicket").prop("disabled", true);
	$("#btnOpen").prop('disabled', true);
	var project = $("#selectProject option:selected").text();
	$("#btnSelectProject").prop("disabled", true);
	$.ajax({
		url : 'ticketAvailable',
		success : function(data) {
			$('#txtTicket').val(data);
		},
		error : function(vMsg) {
			sweetAlertError(vMsg);
			$.unblockUI();
		}
	});

	if (project.includes("DIALIGHT") || project.includes("CREE")) {
		$('#txtPartNumber').prop("disabled", false);
		$('#txtPartNumber').val("");
		$('#txtPartNumber').focus();
		$("#txtContainer").prop("disabled", true);
		$('#divQty').hide();
	} else {
		$("#txtContainer").prop("disabled", false);
		$("#txtContainer").focus();
	}
});

$('#txtQty').keypress(function(e) {
	var project = $("#selectProject option:selected").text();
	var part = $("#txtPartNumber").val();
	var qty = $('#txtQty').val();
	var ticket = $('#txtTicket').val();
	if (e.keyCode == 13) {
		if (project.includes("DIALIGHT PO ACCESORIOS")) {
			$.ajax({
				url : 'addTicketAccessories',
				type : 'POST',
				data : {
					ticket : ticket,
					part : part,
					qty : qty
				},
				success : function(data) {

				},
				error : function(vMsg) {
					sweetAlertError(vMsg);
				}
			});
		}
	}
});

function sweetAlertError(data) {
	swal({
		title : "Advertencia !!!",
		text : data,
		type : "error",
		confirmButtonText : "Aceptar"
	});
}

function sweetAlertSucces(title, data) {
	swal(title, data, "success")
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
				swal('footer' + 'Deleted!', 'footerbody'
						+ 'Your file has been deleted.', 'success')
			});
}

function block() {
	$
			.blockUI({
				message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
			});
}