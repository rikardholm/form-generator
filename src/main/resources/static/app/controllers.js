angular.module('activitiApp', [])
    .controller('ProcessController', function ($scope, $http) {
        $http.get('/descriptions').success(function (data) {
            $scope.tasks = [];
            data.forEach(function (processInfo) {
                $scope.tasks.push(processInfo);
            });

        });

        $scope.showForm = function (process) {
            $http.get('/processes/' + process.info.key + '/form')
                .success(function (data) {
                    process.form = data;
                });
        };

        $scope.templateUrlOf = function (field) {
            return 'app/' + field.type + '.html';
        };
    }).service('ProcessService', function ($http) {

    });