var IndexRouter = Backbone.Router.extend({
	
	initialize: function(){
		this.userRegistrationView = new UserRegistrationView({model: new User()});
		this.loginView = new LoginView();
	},
	
	routes:{
		'': 'index',
		'register'	: 'register',
		'login'		: 'login'
	},
	
	index: function(){
		this.userRegistrationView.render();
		this.loginView.render();
		
		var $body = $('body');
		$body.append( this.userRegistrationView.el );
		$body.append( this.loginView.el );
	},
	
	register: function(){
		this.userRegistrationView.openModal();
	},
	
	login: function(){
		this.loginView.openModal();
	}

});