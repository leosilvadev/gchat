define(['jquery',
        'underscore',
        'backbone',
        'collections/rooms',
        'views/room',
        'utils/template',
        'utils/events'], function($, _, Backbone, RoomList, RoomView, template, events){
	
	var RoomListView = Backbone.View.extend({
		
		id: 'modal-rooms',
		className: 'modal fade',
		
		initialize: function(){
			this.collection = new RoomList();
			this.collection.on('reset', this.addAll, this);
			this.collection.on('add', this.addOne, this);
			
			this.rooms = [];
		},
		
		events: {
			'keyup #search-name'		: 'listRooms',
			'hidden.bs.modal'			: 'cleanModal'
		},
		
		cleanModal: function(){
			this.$('#search-name').val('');
			this.cleanRooms();
		},
		
		createRoom: function(){
			this.closeModal();
			events.trigger('room:new');
		},
		
		listRooms: function(){
			this.cleanRooms();
			var roomName = this.$('#search-name').val();
			this.collection.fetch({data:{roomName:roomName}});
		},
		
		cleanRooms: function(){
			this.rooms.forEach(function(room){
				room.destroy();
			});
			this.rooms = [];
		},
		
		addAll: function(){
			this.collection.forEach(this.addOne, this);
		},
		
		addOne: function(room){
			var roomView = new RoomView({model:room});
			roomView.render();
			
			this.rooms.push(roomView);
			
			var $modalBody = this.$('.rooms');
			$modalBody.append(roomView.el);
		},
	
		closeModal: function(){
			this.$el.modal('hide');
		},
		
		openModal: function(){
			this.$el.modal('show');
		},
		
		render: function(){
			template.html('_rooms', this.$el);
			return this;
		}
		
	});
	
	return RoomListView;

});