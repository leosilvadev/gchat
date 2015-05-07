define(['backbone',
        'underscore',
        'models/room',
		'models/chat-message',
		'connectors/stomp-connector',
        'utils/template',
        'utils/events',
		'managers/tab-rooms',
		'managers/chat-rooms'], function(Backbone, _, Room, ChatMessage, StompConnector, template, events){
	
	var RoomRegistrationView = Backbone.View.extend({
		
		id: 'modal-new-room',
		className: 'modal fade',
		
		events: {
			'keyup #new-room-name' 		: 'checkName',
			'click #btn-register-room' 	: 'saveRoom',
			'hidden.bs.modal'			: 'cleanModal'
		},
		
		checkName: function(event){
			var $btnRegister = this.$('#btn-register-room');
			var roomName = $(event.currentTarget).val();
			if ( roomName ) {
				$btnRegister.removeAttr('disabled');
			} else {
				$btnRegister.attr('disabled', 'disabled');
			}
		},
		
		saveRoom: function(){
			var view = this;
			
			var roomName = this.$('#new-room-name').val();
			var room = new Room({name: roomName});
			room.save(null, {
			    success: function (model, response) {
			    	view.closeModal();
			    	view.enterRoom(room);
			    },
			    error: function (model, response) {
			        alert('Error! '+response.message);
			    }
			});
		},
		
		enterRoom: function(room){
			events.trigger('room:chat-new', room, function(){
				$('#modal-rooms').modal('hide');
			});

			events.trigger('room:tab-new', room, function(){
				$('#modal-rooms').modal('hide');
			});
		},
		
		cleanModal: function(){
			this.$('#new-room-name').val('');
			this.$('#btn-register-room').attr('disabled', 'disabled');
		},
		
		closeModal: function(){
			this.$el.modal('hide');
		},
		
		openModal: function(){
			this.$el.modal('show');
		},
		
		render: function(){
			template.html('_room_registration', this.$el);
			return this;
		}
		
	});
	
	return RoomRegistrationView;
	
});