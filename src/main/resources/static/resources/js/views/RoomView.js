
var RoomView = Backbone.View.extend({
	
	template: _.template(roomTemplate),
	
	className: 'row room',
	
	render: function(){
		var createdAt = new Date(this.model.get('createdAt'));
		this.$el.html(this.template({name:this.model.get('name'), users:this.model.get('users').length, createdAt: createdAt}));
		return this;
	}
	
});