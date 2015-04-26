define(['sockjs', 'stomp'], function(SockJS){

	var StompConnector = {
		
		connect: function(){
			var self = this;
			var socket = new SockJS('/messages');
		    this.stompClient = Stomp.over(socket);
		    this.stompClient.connect({});
		},
		
		getConnection: function(){
			return this.stompClient;
		}
			
	};
	
	return StompConnector;
	
});