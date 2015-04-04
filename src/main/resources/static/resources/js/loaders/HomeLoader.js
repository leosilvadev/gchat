var HomeLoader = {
	
	load: function(){
		var logoutView = new LogoutView();
		logoutView.render();
		
		var $body = $('body');
		$body.append( logoutView.el );
	}
		
};

$(document).ready(function(){
	HomeLoader.load();
});