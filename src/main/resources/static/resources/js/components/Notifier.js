
var Notifier = function(){
	
	var self = this;
	
	self.showSuccessMessage = function(message){
		$.notify({
			message: message
		}, buildMessageOptions('success', 3000));
	};
	
	self.showErrorMessage = function(message){
		$.notify({
			message: message
		}, buildMessageOptions('warning', 3000));
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