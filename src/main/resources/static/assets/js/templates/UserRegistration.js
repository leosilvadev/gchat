define([], function(){

	var UserRegistrationTemplate = 
		['<div class="modal-dialog">',
			 '<div class="modal-content">', 
				 '<div class="modal-header">', 
				 	'<button type="button" class="close" data-dismiss="modal" arial-label="Close">', 
				 		'<span aria-label="true">&times;</span>', 
				 	'</button>', 
				 	'<h4>', 
					 	'<span class="glyphicon glyphicon-star-empty"></span>', 
					 	'<span> Registration </span>', 
					 	'<span class="glyphicon glyphicon-star-empty"></span>', 
				 	'</h4>', 
				 '</div>', 
				 '<div class="modal-body">', 
				 	'<div id="registration-messages"></div>', 
					'<label for="name">Name</label>',
					'<input id="name" class="form-control" />',
					
					'<label for="email">E-mail</label>',
					'<input id="email" class="form-control" />',
					
					'<label for="password">Password</label>',
					'<input id="password" class="form-control" type="password"/>',
					
					'<label for="passwordConfirmation">Password confirmation</label>',
					'<input id="passwordConfirmation" class="form-control" type="password"/>',
				 '</div>', 
				 '<div class="modal-footer">', 
					'<button type="button" class="btn btn-default" data-dismiss="modal">',
				 		'<span class="glyphicon glyphicon-remove"></span> Close',
				 	'</button>', 
				 	'<button id="btn-register" type="button" class="btn btn-primary">',
				 		'<span class="glyphicon glyphicon-ok"></span> Register',
				 	'</button>', 
				 '</div>', 
			 '</div>',
		 '</div>'
		].join("\n");
	
	return UserRegistrationTemplate;

});