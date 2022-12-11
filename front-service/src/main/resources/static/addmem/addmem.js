angular.module('portal').controller('addmemController', function ($scope, $http, $location) {


    const contextPath = 'http://localhost:5555/core';


    $scope.fillTable = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.mems = response.data;
                console.log($scope.mems);
            });
    };


    $scope.moder = function (id) {
        $http.get(contextPath + '/' + id)
            .then(function (response) {
                $scope.fillTable();
            });
    }
});