define([], function(){

	var LoginTemplate = 
		['<div class="modal-dialog">',
		 	'<div class="modal-content">', 
		 		'<form action="authenticate" method="POST">',
		 			'<div class="modal-header">', 
		 				'<button type="button" class="close" data-dismiss="modal" arial-label="Close">', 
					 		'<span aria-label="true">&times;</span>', 
					 	'</button>', 
					 	'<h4>', 
						 	'<span> User Login </span>', 
					 	'</h4>', 
					'</div>', 
					'<div class="modal-body">', 
						'<div id="login-messages"></div>', 
						'<label for="username">Username</label>',
						'<input id="username" name="username" class="form-control" required/>',
						
						'<label for="password">Password</label>',
						'<input id="password" name="password" class="form-control" type="password" required/>',
					'</div>', 
					'<div class="modal-footer">', 
						'<button type="button" class="btn btn-default" data-dismiss="modal">',
					 		'<span class="glyphicon glyphicon-remove"></span> Close',
					 	'</button>', 
					 	'<button type="submit" class="btn btn-primary">',
					 		'<span class="glyphicon glyphicon-ok"></span> Log in',
					 	'</button>', 
					'</div>', 
				'</form>',
			'</div>',
		'</div>'].join("\n");
	
	return LoginTemplate;

});