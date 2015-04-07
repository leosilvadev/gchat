
var roomTemplate = 
		[
		 '<div class="col-sm-1">',
		 	
		 '</div>',

		 '<div class="col-sm-5">',
		 	'<%= name %>',
		 '</div>',

		 '<div class="col-sm-4">',
		 	'Created at: <%= createdAt %>',
		 '</div>',
		 
		 '<div class="col-sm-2">',
		 	'Current users: <%= users %>',
		 '</div>'
		 ].join("\n");
