var IndexLoader = {
	
	$userRegistrationContainer: $("#user-registration-container"),
	
	load: function(){
		var userRegistrationView = new UserRegistrationView({model: new User()});
		userRegistrationView.render();
		this.$userRegistrationContainer.html( userRegistrationView.el );
	}
		
};

$(document).ready(function(){
	IndexLoader.load();
	
});