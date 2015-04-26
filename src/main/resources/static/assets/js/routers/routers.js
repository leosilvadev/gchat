define(['routers/index', 'routers/home'], function(IndexRouter, HomeRouter){
	
	var Routers = {
		Index	: IndexRouter,
		Home	: HomeRouter
	};
	
	return Routers;
	
});