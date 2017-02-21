 
var app = angular.module('app', ["ngRoute","ui.router"]);

app.controller('toDoListController', function (toDoService,$http,$scope) {
    $scope.testVar="Hello";
	$scope.tasks=[];
	$scope.isCommentLoaded=false;
	$scope.edit=false;
	$scope.statuses=["Pending", "Completed"]
	$scope.toDo={
			id:null,
			task : "" ,
			dueDate : null ,
			createdDate :new Date(),
			status:"Pending",
			edit:false
	}
	 	
	var init = function (){
		toDoService.getTasks().then(function(response) {
				console.log("response.data "+angular.toJson(response.data));
	 			$scope.tasks  = response.data;
	 			setDate();
	 			console.log("task[] "+angular.toJson($scope.tasks));
			  });
	 	}
	
		function setDate(){
			for(var i = 0 ; i<$scope.tasks.length; i++){
				$scope.tasks[i].dueDate = new Date($scope.tasks[i].dueDate);
				$scope.tasks[i].createdDate = new Date($scope.tasks[i].createdDate);
			}
		}
	
		$scope.addTask=function(toDo){
			$scope.tasks.push({id:toDo.id,task:toDo.task, dueDate:toDo.dueDate, createdDate:toDo.createdDate, status:toDo.status, edit:toDo.edit});
			console.log("Task:"+angular.toJson($scope.tasks));
			$scope.save($scope.toDo);
			$scope.toDo={task : "" , dueDate :new Date() , createdDate :null, status:"Pending",edit:false}
			console.log("toDo:"+angular.toJson($scope.todo));
		};
		
		$scope.enableEdit=function(index){
			//$scope.edit=!$scope.edit;
			$scope.tasks[index].edit = !$scope.tasks[index].edit;
		};
		
		$scope.delete=function(index){
			var t = $scope.tasks[index];
			toDoService.deleteTask(t);
			$scope.tasks.splice(index,1);
			
		}
		
		$scope.save=function(toDo){
			//toDo.edit=false;
			toDoService.saveToDatabase(toDo).then(function(response) {
				init();
			  });
		}		
	
		init();
		
	});

 app.service('toDoService',['$http','$q', toDoService]);
 
 var tasks=[];
 
 function toDoService($http,$q){
	  this.getTasks = function(){
		  return $http.get('getTasks');
	}
	  
	this.saveToDatabase = function(task){
		return $http.post('saveTask', task);
	}
	
	this.deleteTask = function(task){
		  return $http.delete('deleteTask?id='+task.id);
	}
 }
 

	
	 

 