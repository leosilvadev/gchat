define(['sockjs', 'stomp'], function(SockJS){

	var stompClient;
	var socket;
	
	var StompConnector = function(){
		
		this.connect = function(){
			var self = this;
			socket = new SockJS('/messages');
		    stompClient = Stomp.over(socket);
		    stompClient.connect({});
		};
		
		this.getConnection = function(){
			return stompClient;
		};
			
	};
	
	return new StompConnector();
	
});