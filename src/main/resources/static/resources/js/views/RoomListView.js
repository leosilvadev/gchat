
var RoomListView = Backbone.View.extend({
	
	template: _.template(roomsTemplate),
	
	id: 'modal-rooms',
	className: 'modal fade',
	
	initialize: function(){
		this.collection.on('reset', this.addAll, this);
		this.collection.on('add', this.addOne, this);
//		this.listenToNewRooms();
	},
	
	events: {
		'keyup #search-name': 'findRooms'
	},
	
	findRooms: function(){
		this.cleanRooms();
		var roomName = $('#search-name').val();
		this.collection.fetch({data:{roomName:roomName}});
	},
	
	cleanRooms: function(){
		this.$el.find('.rooms').html('');
	},
	
	render: function(){
		this.$el.html(this.template({rooms:this.collection}));
		this.addAll();
	},
	
	addAll: function(){
		this.collection.forEach(this.addOne, this);
	},
	
	addOne: function(room){
		var roomView = new RoomView({model:room});
		
		var $modalBody = this.$el.find('.rooms');
		$modalBody.append(roomView.render().el);
	}
	
//	listenToNewRooms: function(){
//	    var self = this;
//		var socket = new SockJS('/messages');
//	    this.stompClient = Stomp.over(socket);
//	    this.stompClient.connect({}, function(frame){
//	    	self.stompClient.subscribe('/topic/rooms', function(message){
//	    		var room = JSON.parse(message.body);
//	    		self.addOne(new Room({name:room.name, createdAt:room.createdAt}));
//			});
//	    });
//	}
	
});