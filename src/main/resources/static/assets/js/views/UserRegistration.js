define(
		['jquery',
		 'underscore',
		 'backbone', 
		 'templates/UserRegistration', 
		 'models/Notification', 
		 'views/Notification'
		], function($, _, Backbone, UserRegistrationTemplate, Notification, NotificationView){

	var UserRegistrationView = Backbone.View.extend({

		template : _.template(UserRegistrationTemplate),
		id : 'modal-registration',
		className : 'modal fade',
	
		initialize : function() {
			this.model.on('invalid', this.showErrors, this);
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
			this.model.save({}, {
				success : this.showSuccess(this)
			});
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
	
		showErrors : function(model, error) {
			var alert = new Alert({
				message : error,
				type : 'warning'
			});
			var alertView = new AlertView({
				model : alert
			});
			alertView.render();
			$('#registration-messages').html(alertView.el);
		},
	
		showSuccess : function(model, resp, options) {
			this.closeModal();
			
			var notification = new Notification({type:'success', message: 'You were successfully registered!</br>We are sending a confirmation e-mail to you...', delay:3000});
			new NotificationView({model:notification}).render();
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
			this.$el.html(this.template(this.model.attributes));
		}
	
	});
	
	return UserRegistrationView;
	
});