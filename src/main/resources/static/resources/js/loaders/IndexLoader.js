var IndexLoader = {
	
	$userRegistrationContainer: $("#user-registration-container"),
	
	load: function(){
		var userRegistrationView = new UserRegistrationView({model: new User()});
		userRegistrationView.render();
		
		var userLoginView = new UserLoginView();
		userLoginView.render();

		var $body = $('body');
		$body.append( userRegistrationView.el );
		$body.append( userLoginView.el );
	}
		
};

$(document).ready(function(){
	IndexLoader.load();
	
});