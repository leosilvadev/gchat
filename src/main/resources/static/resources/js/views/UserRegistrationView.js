$(document).ready(function(){
	
	var view = new UserRegistrationView(indexView);
	view.render();
	
});


var UserRegistrationView = function(indexView){
	
	var self = this;
	var parent = indexView;
	
	self.render = function(){
		registerEvents();
	};
	
	self.register = function(){
		var user = buildUser();
		
		var errors = hasErrors(user);
		if (errors.length) {
			showErrors(errors);
		
		} else {
			var url = 'users';
			var messageJSON = JSON.stringify(user);
			postJSON(url, messageJSON, registerSuccessCallback, registerErrorCallback);
		
		}
	};
	
	self.openModal = function(){
		$('#modal-registration').modal('show');
	};
	
	self.closeModal = function(){
		$('#modal-registration').modal('hide');
	}
	
	var showErrors = function(errors){
		cleanErrors();
		var firstError = errors[0];
		errors.forEach(function(error){
			var label = $('label[for="'+error.prop+'"]');
			label.addClass("error");
			label.after('<span data-prop="'+error.prop+'" class="error-detail">'+error.message+'</span>');
			
		});
		$('input#'+firstError.prop).focus();
	}
	
	var cleanErrors = function(){
		$('.error-detail').remove();
		$('label[for=name]').removeClass('error');
		$('label[for=email]').removeClass('error');
		$('label[for=password]').removeClass('error');
		$('label[for=passwordConfirmation]').removeClass('error');
	};
	
	var cleanAll = function(){
		cleanErrors();
		$('input#name').val('');
		$('input#email').val('');
		$('input#password').val('');
		$('input#passwordConfirmation').val('');
	};
	
	var registerSuccessCallback = function(){
    	self.closeModal();
    	parent.showSuccessMessage('Thank you! You will receive a confirmation at your e-mail');
    };
    
    var registerErrorCallback = function(jqXHR, textStatus, errorThrown){
    	parent.showErrorMessage('Warning! Fill all the fields correctly!<br/>'+jqXHR.responseText);
    };
	
	var buildUser = function(){
		var user = {
			name: $('#name').val(),
			email: $('#email').val(),
			password: $('#password').val(),
			passwordConfirmation: $('#passwordConfirmation').val()
		}
		return user;
	};
	
	var hasErrors = function(user){
		var errors = [];
		
		if (!user.name) errors.push({prop:"name", message:"Name is required"});
		
		if (user.email){
			if (isInvalidEmail(user.email)) errors.push({prop:"email", message:"Invalid e-mail"});
			
		} else {
			errors.push({prop:"email", message:"E-mail is required"});
			
		}
		
		if (!user.password) 			errors.push({prop:"password", message:"Password is required"});
		if (!user.passwordConfirmation) errors.push({prop:"passwordConfirmation", message:"Password Confirmation is required"});
		
		return errors;
	};
	
	var isInvalidEmail = function(email){
		var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		return !regex.test(email);
	};
	
	var registerEvents = function(){
		$('#btn-register').on('click', self.register);
		$('#modal-registration').on('hidden.bs.modal', cleanAll);
	};
	
};