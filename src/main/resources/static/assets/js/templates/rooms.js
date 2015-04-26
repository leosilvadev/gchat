define([], function(){
	
	var RoomsTemplate = 
		['<div class="modal-dialog">',
		 	'<div class="modal-content">', 
		 		'<div class="modal-header">', 
		 			'<button type="button" class="close" data-dismiss="modal" arial-label="Close">', 
				 		'<span aria-label="true">&times;</span>', 
				 	'</button>', 
				 	'<h4>', 
					 	'<span> Rooms </span>', 
				 	'</h4>', 
				'</div>', 
				'<div class="modal-body">', 
					'<div class="input-group">',
						'<div class="input-group-addon">Room name: </div>',
						'<input id="search-name" class="form-control" placeholder="Nome"/>',
						'<div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>',
					'</div>',
					'<div class="rooms">',
					'</div>',
				'</div>',
				'<div class="modal-footer">',
					'<button id="btn-create-room" type="button" class="btn btn-primary">Create room</button>',
				'</div>',
			'</div>',
		'</div>'].join("\n");
	
	return RoomsTemplate;
	
});
