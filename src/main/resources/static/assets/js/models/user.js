define(['backbone'], function(Backbone){

	var User = Backbone.Model.extend({
	
		urlRoot : '/users',
	
		defaults : {
			name : '',
			email : '',
			password : '',
			passwordConfirmation : ''
		},
	
		validate : function(attrs, options) {
			if (!attrs.name) 						return "Name is required";
			if (this.isInvalidEmail(attrs.email)) 	return "Invalid e-mail";			
			if (!attrs.password) 					return "Password is required";
			if (!attrs.passwordConfirmation) 		return "Password Confirmation is required";
			if (attrs.password !== attrs.passwordConfirmation) return "Passwords does not match";
		},
		
		isInvalidEmail : function(email){
			var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			return !email || !regex.test(email);
		},
		
		clean : function(){
			this.name = '';
			this.email = '';
			this.password = '';
			this.passwordConfirmation = '';
		}
	
	});
	
	return User;

});