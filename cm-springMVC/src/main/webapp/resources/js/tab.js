$(document).ready(function(){
	
  $("#tab li").each(function(index){
	$(this).mouseover(function(){
		$("#tab li").removeClass("tabin");
		
		$(this).addClass("tabin");
		
		
	});
	$(this).click(function(){
		$(this).removeClass("tabin");
		$("#tab li").removeClass("tabclick");
		$(this).addClass("tabclick");
		$("#area > div").removeClass("contentin");
		$("#area > div").eq(index).addClass("contentin");
		
			
	});
  });
  

});