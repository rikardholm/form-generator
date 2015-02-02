angular.module('activitiApp', [])
    .controller('TaskController', function ($scope, $http) {
        $http.get('/descriptions').success(function (data) {
            $scope.tasks = [];
            data.forEach(function (processInfo) {
                $scope.tasks.push(processInfo);

                processInfo.fields.forEach(enrich);
            });

        });

        $scope.post = function (task) {
            alert(task.key);
        };

        $scope.templateUrlOf = function (field) {
            return 'app/input.html';
        };

        var enrich = function (field) {
            var typeMap = {
                'java.time.Year': 'number',
                'java.time.LocalDate': 'date',
                'javax.mail.internet.InternetAddress': 'email'
            };

            console.log(field.name);

            field.inputType = 'text';
            if (typeMap.hasOwnProperty(field.type)) {
                field.inputType = typeMap[field.type];
            }
        };
    }).service('TaskService', function ($http) {

    });