/**
 * 
 */
'use strict';
 
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