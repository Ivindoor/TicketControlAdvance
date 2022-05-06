function manageServerResponse(data) {
	if (data.show) {
		getNotification(data.type, data.message, getTitle(data.type), data);
	} else {
		manageAction(data);
	}
}

function manageAction(data) {
	switch (data.action) {
	case "reload":
	case "refresh":
		loadInDIVLinks("#contentDiv", getValueFromKey("URL"));
		break;
	case "redirect":
		redirect(data.location);
		break;
	case "load":
		loadInDIVLinks("#contentDiv", data.location, data.show, null);
		break;
	case "hide-modal":
		$('#' + data.location).modal('hide');
		break;
	case "hide-modal-refresh":
		$('#' + data.location).modal('hide');
		$('#' + data.location).on('hidden.bs.modal', function() {
			loadInDIV("#load", getValueFromKey("URL"), true, null);
		});
		break;
	case "hide-modal-redirect":
		console.log('hide-modal-red_location: ' + data.location);
		$('#' + data.location).modal('hide');
		$('#' + data.location).on('hidden.bs.modal', function() {
			$(location).attr('href', window.location.href);
		});
		break;
	case "none":
		break;
	default:
		clearForm();
	}
}

function clearForm() {
	$(':input').not(':button, :submit, :reset, :hidden').val('').removeAttr(
			'checked').removeAttr('selected');
}

function loadInDIV(div, URL, showNotification, func) {
	var notification = "";
	if (showNotification) {
		$('#loading').show();
		// notification = getNotification('progress', 'Cargando...');
	}
	$(div).load(URL, function() {
		if (showNotification) {
			// notification.remove();
			$('#loading').hide();
		}
		if (typeof func == "function")
			func();
	});
}
function loadInDIVLinks(div, URL, showNotification, func) {
	var notification = "";
	console.log('loadInDIV: ' + URL);
	$('#loading').show();
	if (showNotification) {
		// $.blockUI();
		// notification = getNotification('progress','Cargando...');
		$('#loading').show();
	}
	$(div).load(URL, function() {
		if (showNotification) {
			// $.unblockUI();
			// notification.remove();
			$('#loading').hide();
		}
		if (typeof func == "function")
			func();
	});
}
function redirect(url) {
	$(location).attr('href', url);
}

function getTitle(type) {
	var title = '';
	switch (type) {
	case 'success':
		title = 'Success!';
		break;
	case 'error':
		title = 'Failure!';
		break;
	case 'warning':
		title = 'Attention!';
		break;
	}
	return title;
}

function haveClass(element, classname) {
	var classList = $(element).attr('class').split(/\s+/);
	$.each(classList, function(index, item) {
		if (item === classname) {
			return true;
		}
	});
	return false;
}

function submitForm(element) {
	$.ajax({
		url : $(element).attr('action'),
		data : $(element).serialize(),
		dataType : 'json',
		success : function(data) {
			manageServerResponse(data);
		}
	});
}

function deleteEntity(element, event) {
	event.preventDefault();
	$.ajax({
		url : $(element).data('entity'),
		data : $(element).data('attrib') + '=' + $(element).attr('id'),
		dataType : 'json',
		success : function(data) {
			manageServerResponse(data);
		}
	});
}

function reschedule(element) {
	loadInDIV('#rescheduleDiv', $(element).attr('entity') + '?'
			+ $(element).data('attrib') + '=' + $(element).attr('id'));
}

function updateEntity(elements, entity, attrib, event) {
	event.preventDefault();
	$.ajax({
		url : entity,
		data : elements,
		dataType : 'json',
		success : function(data) {
			manageServerResponse(data);
		}
	});
}
function getNotification(type, message, title, data) {
	switch (type) {
	case 'progress':
		swal({
			title : title,
			text : message,
			timer : 1200,
			showConfirmButton : false
		}, function() {
			manageAction(data);
		});
		break;

	case 'confirmation':
		swal({
			title : title,
			text : message,
			showConfirmButton : true
		});
		break;
	default:
		swal({
			title : title,
			text : message,
			timer : 3000,
			type : type,
			showConfirmButton : false
		});
		break;
	}
}
function saveInLocalStorage(key, value) {
	localStorage.setItem(key, value);
}

function removeFromLocalStorage(key) {
	localStorage.removeItem(key);
}

function getValueFromKey(key) {
	return localStorage.getItem(key);
}

function removeAll() {
	for (var i = 0; i < storage.length; i++) {
		localStorage.remove(localStorage.key(i));
	}
}
