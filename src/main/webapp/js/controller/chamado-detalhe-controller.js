appEntra21.controller("chamadoDetalheController", function ($scope, $http, $routeParams) {

    $scope.chamadoDetalhe = {};
    var url = 'rest/chamados/';

    $http.get(url + $routeParams.id).then(function (response) {
        $scope.chamadoDetalhe = response.data;
    }, function (response) {
        console.log('error - chamadoDetalheController');
    });
    
    
    $scope.editarChamado = function() {
		$http({
			method : 'PUT',
			url : url,
			data : $scope.chamadoDetalhe
		}).then(function(response) {
			console.log('salvar - salvarChamado');
		}, function(response) {
			console.log('error - salvarChamado');
		});
	};
});