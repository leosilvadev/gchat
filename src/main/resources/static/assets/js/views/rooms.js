define(['jquery',
        'underscore',
        'backbone',
        'collections/rooms',
        'views/room',
        'utils/template'], function($, _, Backbone, RoomList, RoomView, template){
	
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
			'keyup #search-name': 'findRooms',
			'hidden.bs.modal': 'cleanRooms'
		},
		
		findRooms: function(){
			this.cleanRooms();
			var roomName = this.$el.find('#search-name').val();
			this.collection.fetch({data:{roomName:roomName}});
		},
		
		cleanRooms: function(){
			this.rooms.forEach(function(room){
				room.remove();
			});
			this.rooms = [];
		},
		
		render: function(){
			template.render('_rooms', this.$el);
			return this;
		},
		
		addAll: function(){
			this.collection.forEach(this.addOne, this);
		},
		
		addOne: function(room){
			var roomView = new RoomView({model:room});
			this.rooms.push(roomView);
			
			var $modalBody = this.$el.find('.rooms');
			$modalBody.append(roomView.render().el);
		},
	
		closeModal: function(){
			this.$el.modal('hide');
		},
		
		openModal: function(){
			this.$el.modal('show');
		}
		
	});
	
	return RoomListView;

});