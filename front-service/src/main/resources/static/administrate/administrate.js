angular.module('portal').controller('administrateController', function ($scope, $http, $location) {


    const contextPath = 'http://localhost:5555/auth/administrate';


    $scope.fillTable = function () {
        $http.get(contextPath)
            .then(function (response) {
                $scope.users = response.data;
                console.log($scope.users);
            });
    };

    $scope.deleteUser = function (id) {
        $http.delete(contextPath + "/" + id)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.updateSomeUser = function () {
        $http.put("http://localhost:5555/auth/administrate/modifyRoles", $scope.User)
            .then(function successCallback(response) {
                    $location.path('/');
                $scope.User = null;
                $scope.fillTable();
            })
    }

    $scope.fillTable();

 });

    // $scope.fillTableM = function () {
    //     $http.get('http://localhost:8080/moder')
    //         .then(function (response) {
    //             $scope.mems = response.data;
    //             console.log($scope.mems);
    //         });
    // };


    // $scope.incrementLike = function (id){
    //     $http.get('http://localhost:8081/demo/api/v1/mem/inc/' +id)
    //         .then(function (response){
    //             $scope.fillTable();
    //         });
    // }
    // $scope.decrementLike = function (id){
    //     $http.get('http://localhost:8081/demo/api/v1/mem/dec/' +id)
    //         .then(function (response){
    //             $scope.fillTable();
    //         });
    // }


        // }
        //
        // $scope.createNewStudent = function () {
        //     // console.log($scope.newProduct);
        //     $http.post('http://localhost:8081/interview/api/v1/student', $scope.newStudent)
        //         .then(function (response) {
        //             $scope.newProduct = null;
        //             $scope.fillTable();
        //         });
        // }
        //

        // }


