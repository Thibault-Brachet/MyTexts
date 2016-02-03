'use strict';
 
App.factory('textService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllTexts: function() {
                    return $http.get('http://localhost:8080/MyTexts/text/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching texts');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createText: function(text){
                    return $http.post('http://localhost:8080/MyTexts/text/', text)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating text');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateText: function(text, id){
                    return $http.put('http://localhost:8080/MyTexts/text/'+id, text)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating text');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);