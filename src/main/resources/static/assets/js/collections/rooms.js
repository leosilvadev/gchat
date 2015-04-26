define(['backbone', 'models/room'], function(Backbone, Room){
	
	var RoomList = Backbone.Collection.extend({
	
		url : '/rooms',
		model : Room,
	
		parse : function(response) {
			return response;
		}
	
	});
	
	return RoomList;

});