define(
		[
		 'jquery', 
		 'backbone', 
		 'views/UserRegistration', 
		 'views/LoginView',
		 'models/User'
		], function($, Backbone, UserRegistrationView, LoginView, User){

	var IndexRouter = Backbone.Router.extend({
		
		routes:{
			'': 'index',
			'register'	: 'register',
			'login'		: 'login'
		},
		
		index: function(){
			this.initModals();
			
			var $body = $('body');
			$body.append( this.userRegistrationView.el );
			$body.append( this.loginView.el );
		},
		
		register: function(){
			this.initModals();
			
			this.userRegistrationView.openModal();
		},
		
		login: function(){
			this.initModals();
			
			this.loginView.openModal();
		},
		
		initModals: function(){
			if(this.userRegistrationView){
				this.userRegistrationView.closeModal();
				
			} else {
				this.userRegistrationView = new UserRegistrationView({model: new User()});
				this.userRegistrationView.render();
			}
			
			if(this.loginView){
				this.loginView.closeModal();
				
			} else {
				this.loginView = new LoginView();
				this.loginView.render();
			}
		}
	
	});
	
	return IndexRouter;

});