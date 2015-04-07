
var RoomView = Backbone.View.extend({
	
	template: _.template(roomTemplate),
	
	className: 'row room',
	
	render: function(){
		var createdAt = new Date(this.model.get('createdAt'));
		this.$el.html(this.template({name:this.model.get('name'), users:2, createdAt: createdAt}));
		return this;
	}
	
});