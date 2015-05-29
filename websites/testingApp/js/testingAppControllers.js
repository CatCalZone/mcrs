var testingApp = angular.module('testingApp', []);


testingApp.controller('AppointmentRequestListCtrl', function ($scope, $http) {

    $scope.doReload = true;

    $scope.reload = function () {
        if ($scope.doReload) {
            $http.get('http://localhost:8765/appointmentRequest').success(function (data, status, headers, config) {
                    // this callback will be called asynchronously
                    // when the response is available

                    $scope.requests = data;

                }

            );
        }
    }

    $scope.toggleReload = function () {
        $scope.doReload = !$scope.doReload;
    }

    $scope.reload();

    window.setInterval($scope.reload, 1000)

});
testingApp.controller('AppointmentRequestCtrl', function ($scope, $http) {


    $scope.createNewAppointmentRequest = function (appointmentRequest, form) {
        // 52651
        var url = 'http://localhost:8765/appointmentRequest'; // TODO add profiles somehow to determin right url

        var requestToBeSent = appointmentRequest;
        requestToBeSent.attendees = requestToBeSent.attendees.split(",");

        requestToBeSent.minStartDateTimeStamp = $('#minStartDateTimeStampDatePicker').data("DateTimePicker").date().unix();
        requestToBeSent.maxEndDateTimeStamp = $('#maxEndDateTimeStampDatePicker').data("DateTimePicker").date().unix();
        $http({
            method: 'POST',
            url: url,
            data: requestToBeSent,  // pass in data as strings
            headers: { 'Content-Type': 'application/json' }  // set the headers so angular passing info as form data (not request payload)
        }).
            success(function (data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                console.log(status);
                console.log(requestToBeSent);
                $scope.appointmentRequest = {};

            }).
            error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.appointmentRequest = {};
            });

    }


});

