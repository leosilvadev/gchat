define(['jquery',
		'backbone', 
		'models/user',
		'utils/template',
		'utils/events'], function($, Backbone, User, template, events){
	
	var UserRegistrationForm = Backbone.View.extend({

		id : 'modal-registration',
		className : 'modal fade',
	
		initialize : function() {
			this.model = new User();
			this.notifications = [];
		},
	
		events : {
			'click #btn-register' : 'register',
			'hidden.bs.modal' : 'clean'
		},
	
		register : function() {
			this.model.set('name', $('#name').val());
			this.model.set('email', $('#email').val());
			this.model.set('password', $('#password').val());
			this.model.set('passwordConfirmation', $('#passwordConfirmation').val());
			if ( this.model.isValid() ) {
				this.model.save({success: this.showSuccess(this)});
				
			} else {
				this.showErrors(this.model.validationError);
			
			}
		},
	
		buildModel : function() {
			var user = new User({
				name : this.$el.$('#name').val(),
				email : this.$el.$('#email').val(),
				password : this.$el.$('#password').val(),
				passwordConfirmation : this.$el.$('#passwordConfirmation').val()
			});
			return user;
		},
	
		showErrors : function(error) {
			events.trigger('notify:warning', error, $('#registration-messages'));
		},
	
		showSuccess : function(model, resp, options) {
			this.closeModal();
			events.trigger('notify:info', 'User registered successfully!');
		},
	
		openModal : function() {
			this.$el.modal('show');
		},
	
		closeModal : function() {
			this.$el.modal('hide');
		},
	
		clean : function() {
			this.model.clean();
			this.render();
		},
	
		render : function() {
			template.render('_user_registration', this.$el, this.model.attributes);
		}
	
	});
	
	return UserRegistrationForm;
	
});