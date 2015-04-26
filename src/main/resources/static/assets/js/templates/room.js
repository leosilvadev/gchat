define([], function(){
	
	var RoomTemplate = 
		[
		 '<div class="col-sm-5">',
		 	'<%= name %>',
		 '</div>',

		 '<div class="col-sm-3 to-right">',
		 	'Since <%= createdAt %> ',
		 	'<span class="glyphicon glyphicon-time"></span>',
		 '</div>',
		 
		 '<div class="col-sm-2 to-right">',
		 	'<span class="glyphicon glyphicon-user"></span>',
		 	'<%= users %>',
		 '</div>',

		 '<div class="col-sm-2 to-right">',
		 	'<button class="btn btn-primary btn-enter-room">',
		 		'<span class="glyphicon glyphicon-share-alt"></span>',
		 		'Enter',
		 	'</button>',
		 '</div>'
		 ].join("\n");
	
	return RoomTemplate;

});