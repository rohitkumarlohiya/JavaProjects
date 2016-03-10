(function() {
  var app = angular.module('gemStore', ['store-directives']);

  app.controller('StoreController',['$http',function($http){
    var store = this;
    store.products = [];
    
    $http.get("data/products.request").success(function(data)
      {
        store.products = data;    	
      }
      
    );    
  }]);
  
  app.controller('ProductController',['$scope', '$log',function($scope,$log){
	  $scope.addProduct = function()
	  {
		 $scope.store.products.push({
						"name" : $scope.newName,
						"description" : $scope.newDescription
					});
		 
		 $scope.newName ="";
		 $scope.newDescription="";
	  }
	  
  }]);
	  
	  
	 
	  
	 /* $scope.name = [];
	  $scope.description= [];*/
	  
	  
	 /* this.addProduct = function($scope) {
		  
		  alert("ssss");
		  alert("name ==>"+$scope.newName);*/
	    
	   /* $http.post("data/addProduct.request",{
	        name: "Azurite",
	        description: "Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.",
	        shine: 8,
	        price: 110.50,
	        rarity: 7,
	        color: "#CCC",
	        faces: 14,
	        images: [
	          "images/gem-02.gif",
	          "images/gem-05.gif",
	          "images/gem-09.gif"
	        ],
	        reviews: []
	      }).success(function(data)
	      {
	        //store.products = data;    	
	      }
	      
	    );    
	  }*/
	  
  
  
  
  
  /*}]);*/


  app.controller('ReviewController', function() {
    this.review = {};

    this.addReview = function(product) {
      product.reviews.push(this.review);

      this.review = {};
    };
  });
})();