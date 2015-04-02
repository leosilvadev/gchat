
var Notifier = function(){
	
	var self = this;
	
	self.showSuccessMessage = function(message, delay){
		var delay = delay || 3000;
		$.notify({
			message: message
		}, buildMessageOptions('success', delay));
	};
	
	self.showErrorMessage = function(message, delay){
		var delay = delay || 3000;
		$.notify({
			message: message
		}, buildMessageOptions('warning', delay));
	};
	
	var buildMessageOptions = function(type, delay){
		return {
			delay: delay,
			type: type,
			placement: {
				align: 'center'
			}
		};
	};
	
};