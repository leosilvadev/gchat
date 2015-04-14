
var RoomListView = Backbone.View.extend({
	
	template: _.template(roomsTemplate),
	
	id: 'modal-rooms',
	className: 'modal fade',
	
	initialize: function(){
		this.collection.on('reset', this.addAll, this);
		this.collection.on('add', this.addOne, this);
		this.roomRegistrationView = new RoomRegistrationView();
	},
	
	events: {
		'keyup #search-name'	: 'findRooms',
		'click #btn-create-room': 'createRoom'
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
		$('body').append( this.roomRegistrationView.render().el );
	},
	
	addAll: function(){
		this.collection.forEach(this.addOne, this);
	},
	
	addOne: function(room){
		var roomView = new RoomView({model:room});
		
		var $modalBody = this.$el.find('.rooms');
		$modalBody.append(roomView.render().el);
	},
	
	createRoom: function(){
		this.closeModal();
		this.roomRegistrationView.openModal();
	},
	
	closeModal: function(){
		this.$el.modal('hide');
	}
	
});