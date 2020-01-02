var appEntra21 = angular.module("appEntra21", ['ngRoute']);

appEntra21.config(function($routeProvider, $locationProvider){
    
	$routeProvider
    .when("/chamados/novo",{templateUrl:'view/chamado-novo.html', controller:'chamadoController'})
    .when("/chamados",{templateUrl:'view/chamado.html', controller:'chamadoController'})
    .when("/chamados/edit/:id",{templateUrl:'view/chamado-editar.html', controller:'chamadoDetalheController'})
    .when("/chamados/:id",{templateUrl:'view/chamado-detalhe.html', controller:'chamadoDetalheController'})
    .when("/usuario",{templateUrl:'view/usuario.html', controller:'usuarioController'})
    .when("/login",{templateUrl:'view/login.html', controller:'loginController'})
    .otherwise({redirectTo:'/'});
	
	
	 // enable html5Mode for pushstate ('#'-less URLs)
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
	
}); 