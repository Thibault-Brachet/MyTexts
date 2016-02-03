<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<style>
.title.ng-valid {
	background-color: lightgreen;
}

.title.ng-dirty.ng-invalid-required {
	background-color: red;
}

.text.ng-valid {
	background-color: lightgreen;
}

.text.ng-dirty.ng-invalid-required {
	background-color: red;
}
</style>
<!-- SCROLLS -->
<!-- load bootstrap and fontawesome via CDN -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>

<script type="text/javascript">
/**
 * 
 */
'use strict';
 
var App = angular.module('myApp',[]);

App.controller('TextController', ['$scope', 'TextService', function($scope, TextService) {
    var self = this;
    self.text={id:null,title:'',text:'',score:''};
    self.texts=[];
         
    self.fetchAllTexts = function(){
        TextService.fetchAllTexts()
            .then(
                         function(d) {
                              self.texts = d;
                         },
                          function(errResponse){
                              console.error('Error while fetching Currencies');
                          }
                 );
    };
      
    self.createText = function(text){
        TextService.createText(text)
                .then(
                self.fetchAllTexts, 
                        function(errResponse){
                             console.error('Error while creating Text.');
                        } 
            );
    };

   self.updateText = function(text, id){
        TextService.updateText(text, id)
                .then(
                        self.fetchAllTexts, 
                        function(errResponse){
                             console.error('Error while updating Text.');
                        } 
            );
    };

    self.fetchAllTexts();

    self.submit = function() {
        if(self.text.id===null){
            console.log('Saving New Text', self.text);    
            self.createText(self.text);
        }else{
            self.updateText(self.text, self.text.id);
            console.log('Text updated with id ', self.text.id);
        }
        self.reset();
    };
         
    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.texts.length; i++){
            if(self.texts[i].id === id) {
               self.text = angular.copy(self.texts[i]);
               break;
            }
        }
    };

     
    self.reset = function(){
        self.text={id:null,title:'',text:'',score:''};
        $scope.myForm.$setPristine(); //reset Form
    };

}]);


App.factory('TextService', ['$http', '$q', function($http, $q){
	 
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

</script>
<!-- For an unknown reason, my external scripts are not loading at all, that is why I had to include my JS code on this page... -->
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/app.js"></script> -->
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/service/text_service.js"></script> -->
<!-- <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/controller/text_controller.js"></script> -->
</head>
<body ng-app="myApp" class="ng-cloak">

	<div class="generic-container" ng-controller="TextController as ctrl">

		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Edit Text</span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.text.id" />
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="title">Title</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.text.title" id="title" class="form-control input-sm" placeholder="Enter title" ng-required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.title.$error.required">This is a required field</span>
									<span ng-show="myForm.title.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12 ">
							<label class="col-md-2 control-lable" for="textContent">Text</label>
							<div class="col-md-7">
								<textarea ng-model="ctrl.text.text" name="text" id="textContent" class="form-control" rows="10" placeholder="Write your text" ng-required ></textarea>
							</div>
						</div>
					</div>

					<div >
						<div class="form-actions text-center">
							<input type="submit" value="{{!ctrl.text.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset	Text</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Texts </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Title</th>
							<th>Score</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="t in ctrl.texts">
							<input type="hidden" ng-model="t.id" />
							<td><a href="" ng-click="ctrl.edit(t.id)"><span ng-bind="t.title" ></span></a></td>
							<td><span ng-bind="t.score"></span></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


	</div>


</body>
</html>
