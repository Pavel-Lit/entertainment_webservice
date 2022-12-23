angular.module('portal').controller('contentController', function ($scope, $http, $localStorage, $rootScope) {

    const contextPath = 'http://localhost:5555/core/api/v1/mem/';

    $scope.fillTable = function (page) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                page: page,
            }
        }).then(function (response) {
            $scope.mems = response.data;
            $scope.counts = Object.keys(response.data).length;
        });
    };


    const $button_inc = document.querySelector('.increment-btn');
    const $button_dec = document.querySelector('.decrement-btn');
    const $counter = document.querySelector('.counter');

    $button_inc.addEventListener('click', function () {

        if ($scope.counts > 1){
            $counter.value = parseInt($counter.value) + 1
        }
        $scope.fillTable($counter.value);
    }, false);

    $button_dec.addEventListener('click', function () {
        if ($counter.value > 1){
            $counter.value = parseInt($counter.value) - 1;
        } else $counter.value = 1
        $scope.fillTable($counter.value);
    }, false);

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.memPortalUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.counterLike = function (id) {
        $http.get(contextPath + 'likes/' + id)
            .then(function (response) {
                $scope.fillTable($counter.value);
            });
    }

    $scope.fillTable();
});