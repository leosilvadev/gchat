define(['jquery', 
		'backbone', 
		'views/login',
		'views/user-registration',
		'utils/events'], function($, Backbone, LoginView, UserRegistrationForm, events){
	
	var LOCKED_USER_MESSAGE = ['Your access is blocked, please check your e-mail and validate your account.',
	                           'If you did not receive an e-mail <a href="#unlock">please click here</a> and we will send you again'].join('<br/>');
	
	var IndexRouter = Backbone.Router.extend({
		
		routes:{
			''				: 'index',
			'register'		: 'register',
			'login'			: 'login',
			'error'			: 'invalidUser',
			'locked'		: 'lockedUser',
		},
		
		index: function(){
			this.renderChildren();
			
			var $body = $('body');
			$body.append( this.userView.el );
			$body.append( this.loginView.el );
		},
		
		register: function(){
			this.renderChildren();
			this.userView.openModal();
		},
		
		login: function(){
			this.renderChildren();
			this.loginView.openModal();
		},

		invalidUser: function(){
			events.trigger('notify:warning', 'Invalid email/password');
		},

		lockedUser: function(){
			events.trigger('notify:warning', LOCKED_USER_MESSAGE);
		},
		
		renderChildren: function(){
			if(!this.userView) {
				this.userView = new UserRegistrationForm();
				this.userView.render();
			}
			
			if(!this.loginView) {
				this.loginView= new LoginView();
				this.loginView.render();
			}
		}
	
	});
	
	return IndexRouter;

});