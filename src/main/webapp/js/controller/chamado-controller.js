appEntra21.controller("chamadoController", function($scope, $http, $routeParams) {

	$scope.listaChamado = [];
	$scope.chamado = {};
	$scope.chamadoSelecionado = {};
	
	var urlApi = 'rest/chamados/';

	$scope.listarChamados = function() {
		$http({
			method : 'GET',
			url : urlApi
		}).then(function(response) {
			$scope.listaChamado = response.data;
		}, function(response) {
			console.log('error - listarChamados');
		});
	};

	$scope.salvarChamado = function() {
		$http({
			method : 'POST',
			url : urlApi,
			data : $scope.chamado
		}).then(function(response) {
			$scope.listaChamado.push(response.data);
			$scope.listarChamados();
		}, function(response) {
			console.log('error - salvarChamado');
		});
	};

	$scope.deleteChamado = function(id) {

		$http({
			method : 'DELETE',
			url : urlApi + id
		}).then(function(response) {
			$scope.listaChamado.splice(id, 1);
			$scope.listarChamados();
		}, function(response) {
			console.log('error - deleteChamado ');
		});
	};	
	
	$scope.cancelarAlteracaoChamado = function(chamado) {
		$scope.chamado = {};
	};

});