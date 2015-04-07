
var RoomListView = Backbone.View.extend({
	
	template: _.template(roomsTemplate),
	
	id: 'modal-rooms',
	className: 'modal fade',
	
	initialize: function(){
		this.collection.on('reset', this.addAll, this);
		this.collection.on('add', this.addOne, this);
		this.listenToNewRooms();
	},
	
	render: function(){
		this.$el.html(this.template());
		this.collection.fetch();
		this.addAll();
	},
	
	addAll: function(){
		this.collection.forEach(this.addOne, this);
	},
	
	addOne: function(room){
		console.log(room);
		var roomView = new RoomView({model:room});
		
		var $modalBody = this.$el.find('.modal-body');
		$modalBody.append(roomView.render().el);
	},
	
	listenToNewRooms: function(){
	    var self = this;
		var socket = new SockJS('/messages');
	    this.stompClient = Stomp.over(socket);
	    this.stompClient.connect({}, function(frame){
	    	self.stompClient.subscribe('/topic/rooms', function(message){
	    		var room = JSON.parse(message.body);
	    		self.addOne(new Room({name:room.name, createdAt:room.createdAt}));
			});
	    });
	}
	
});