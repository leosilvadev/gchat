
var RoomRegistrationView = function(notifier){
	
	var self = this;
	var notifier = notifier;
	
	self.render = function(){
		registerEvents();
	};
	
	self.register = function(){
		var url = 'rooms';
		var messageJSON = JSON.stringify({name:$('#new-room-name').val()});
		

		postJSON(url, messageJSON, registerSuccessCallback, registerErrorCallback);
	};

	self.openModal = function(){
		$('#modal-new-room').modal('show');
	};

	self.closeModal = function(){
		$('#modal-new-room').modal('hide');
	};
	
	self.cleanModal = function(){
		$('#new-room-name').val('');
	}
	
	var registerSuccessCallback = function(){
		self.closeModal();
	};
	
	var registerErrorCallback = function(jqXHR, textStatus, errorThrown){
		notifier.showErrorMessage('Warning! Fill all the fields correctly!<br/>'+jqXHR.responseText);
	};
	
	var registerEvents = function(){
		$('#btn-register-room').on('click', self.register);
		$('#modal-new-room').on('hidden.bs.modal', self.cleanModal);
	};
	
};