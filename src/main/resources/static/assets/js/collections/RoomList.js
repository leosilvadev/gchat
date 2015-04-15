define(['backbone', 'models/Room'], function(Backbone, Room){
	
	var RoomList = Backbone.Collection.extend({
	
		url : '/rooms',
		model : Room,
	
		parse : function(response) {
			return response;
		}
	
	});
	
	return RoomList;

});