
var chatRoomTemplate = 
		['<form>',
			 '<div class="chat" data-code="<%= code %>">', 
				'<div class="chat-content"></div>',
				'<div class="chat-message">',
					'<div><textarea class="txt-message form-control"></textarea></div>', 
					'<div>',
						'<button class="btn-send-message btn btn-primary" disabled="disabled">',
							'<span class="glyphicon glyphicon-send"><span> Send', 
						'</button>',
					'</div>', 
				'</div>',
				'<div class="chat-users"></div>',
			'</div>',
		'</form>'].join("\n");
