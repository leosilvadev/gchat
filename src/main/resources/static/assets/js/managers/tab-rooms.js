define(['backbone', 'views/tab-room', 'utils/events'], function(Backbone, TabRoomView, events){
	
	var tabs = [];
	
	var TabRoomsManager = Backbone.View.extend({
		
		initialize: function(options){
			events.on('room:tab-new'	, this.newTab);
			events.on('room:chat-switch', this.switchTab);
			events.on('room:tab-destroy', this.destroy);
		},
		
		newTab: function(room, callback){
			var tab = new TabRoomView({model: room});
			tab.render();
			$('#navbar-active-rooms').append(tab.el);
			
			tabs.push(tab);
			
			if(callback) callback();
		},

		switchTab: function(room, callback){
			for(var it in tabs){
				var tab = tabs[it];
				tab.model.get('code') === room.get('code') ? tab.show() : tab.hide();
			}
			if(callback) callback();
		},
		
		destroy: function(room, callback){
			for(var it in tabs){
				var tab = tabs[it];
				if ( tab.model.get('code') === room.get('code') ){
					tabs.splice(it, 1);
				}
			}
		}
		
	});
	
	return new TabRoomsManager();
	
});