angular.module('portal').controller('addmemController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:5555/core/api/v1/mem/addmem';

    $scope.addmem = function () {
        $http.post(contextPath , $scope.newMem)
            .then(function (response) {
                $location.path('/');
            });
    }
});