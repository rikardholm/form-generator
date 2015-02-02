angular.module('activitiApp', [])
    .controller('TaskController', function ($scope, $http) {
        $http.get('/descriptions').success(function (data) {
            $scope.tasks = [];
            data.forEach(function (processInfo) {
                $scope.tasks.push(processInfo);

                processInfo.fields.forEach(enrich);
            });

        });

        $scope.templateUrlOf = function (field) {
            if (field.options.length > 0) {
                return 'app/select.html';
            }
            return 'app/input.html';
        };

        var enrich = function (field) {
            var typeMap = {
                'java.time.Year': 'number',
                'java.time.LocalDate': 'date',
                'javax.mail.internet.InternetAddress': 'email'
            };

            field.inputType = 'text';
            if (typeMap.hasOwnProperty(field.type)) {
                field.inputType = typeMap[field.type];
            }
        };
    }).controller('FormController', function ($scope, $http) {
        $scope.post = function (task, values) {
            $http.post('/start/' + task.key, values);
        };
    }).service('TaskService', function ($http) {

    });