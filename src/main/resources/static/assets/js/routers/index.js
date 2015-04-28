define(['jquery', 
		'backbone', 
		'views/login',
		'views/user-registration',
		'utils/events'], function($, Backbone, LoginView, UserRegistrationForm, events){
	
	var IndexRouter = Backbone.Router.extend({
		
		routes:{
			''				: 'index',
			'register'		: 'register',
			'login'			: 'login',
			'invalidUser'	: 'invalidUser'
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