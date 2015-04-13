
var RoomRegistrationView = Backbone.View.extend({
	
	id: 'modal-new-room',
	className: 'modal fade',
	template: _.template(roomRegistrationTemplate),

	openModal: function(){
		this.$el.modal('show');
	},

	closeModal: function(){
		this.$el.modal('hide');
	},
	
	render: function(){
		this.$el.html(this.template());
		return this;
	}
	
});