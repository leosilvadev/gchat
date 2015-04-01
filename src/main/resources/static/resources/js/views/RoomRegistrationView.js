
var RoomRegistrationView = function(notifier){
	
	var self = this;
	var notifier = notifier;
	
	self.render = function(){
		applyListeners();
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
	
	var registerSuccessCallback = function(){
		self.closeModal();
		notifier.showSuccessMessage('Your Room was created successfully!');
	};
	
	var registerErrorCallback = function(jqXHR, textStatus, errorThrown){
		notifier.showErrorMessage('Warning! Fill all the fields correctly!<br/>'+jqXHR.responseText);
	};
	
	var applyListeners = function(){
		$('#btn-register-room').on('click', self.register);
	};
	
};