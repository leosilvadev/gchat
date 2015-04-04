var IndexLoader = {
	
	load: function(){
		var userRegistrationView = new UserRegistrationView({model: new User()});
		userRegistrationView.render();
		
		var loginView = new LoginView();
		loginView.render();

		var $body = $('body');
		$body.append( userRegistrationView.el );
		$body.append( loginView.el );
	}
		
};

$(document).ready(function(){
	IndexLoader.load();
});