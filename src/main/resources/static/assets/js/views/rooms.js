define(['jquery', 'underscore', 'backbone', 'templates/rooms', 'collections/rooms', 'views/room'], function($, _, Backbone, RoomsTemplate, RoomList, RoomView){
	
	var RoomListView = Backbone.View.extend({
		
		template: _.template(RoomsTemplate),
		
		id: 'modal-rooms',
		className: 'modal fade',
		
		initialize: function(){
			this.collection = new RoomList();
			this.collection.on('reset', this.addAll, this);
			this.collection.on('add', this.addOne, this);
		},
		
		events: {
			'keyup #search-name': 'findRooms',
		},
		
		findRooms: function(){
			console.log('findRooms...');
			this.cleanRooms();
			var roomName = this.$el.find('#search-name').val();
			this.collection.fetch({data:{roomName:roomName}});
			console.log(this.collection);
		},
		
		cleanRooms: function(){
			this.$el.find('.rooms').html('');
		},
		
		render: function(){
			this.$el.html(this.template({rooms:this.collection}));
			this.addAll();
			return this;
		},
		
		addAll: function(){
			this.collection.forEach(this.addOne, this);
		},
		
		addOne: function(room){
			var roomView = new RoomView({model:room});
			
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