'use strict';

// search by name
myApp.filter('customSearchFilter', function () {
    return function (input, term) {
        var regex = new RegExp(term || '', 'i');
        var obj = {};
        angular.forEach(input, function (v, i) {
            if (regex.test(v.fullName + '')) {
                obj[i] = v;
            }
        });
        return obj;
    };
});