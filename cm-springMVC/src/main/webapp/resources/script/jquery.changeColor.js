(function($){
       
	var params={
		oddColor:"#00f"
	};
     
	$.fn.changeColor=function(options){
	          
		 $.extend(params,options||{});
                 this.css("background-color",params.oddColor);
	};
})(jQuery);