define(['jquery',
		'backbone', 
		'models/user',
		'models/notification',
		'views/notification',
		'utils/template'], function($, Backbone, User, Notification, NotificationView, template){
	
	var UserRegistrationForm = Backbone.View.extend({

		id : 'modal-registration',
		className : 'modal fade',
	
		initialize : function() {
			this.model = new User();
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
			var view = new NotificationView({model:new Notification({message:error})});
			view.render();
		},
	
		showSuccess : function(model, resp, options) {
			this.closeModal();
			var notification = new Notification({type:'success', message: 'You were successfully registered!</br>We are sending a confirmation e-mail to you...', delay:3000});
			var view = new NotificationView({model: notification});
			view.render();
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