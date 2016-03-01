$(function(){
	$("#shopping_fast").mouseover (function(){
		$("#shopping_fast").css("background-color","white");
		
		$("#perspective").css("background-color","#EEEEEE");
		$("#shopping_guide").css("background-color","#EEEEEE");
		
		$("#shopping_fast_c").css("display","block");
		$("#perspective_c").css("display","none");
		$("#shopping_guide_c").css("display","none");
	})
	$("#perspective").mouseover (function(){
		$("#perspective").css("background-color","white");
		
		$("#shopping_fast").css("background-color","#EEEEEE");
		$("#shopping_guide").css("background-color","#EEEEEE");
		
		$("#shopping_fast_c").css("display","none");
		$("#perspective_c").css("display","block");
		$("#shopping_guide_c").css("display","none");
	})	
	$("#shopping_guide").mouseover (function(){
		$("#shopping_guide").css("background-color","white");
		
		$("#perspective").css("background-color","#EEEEEE");
		$("#shopping_fast").css("background-color","#EEEEEE");
		
		$("#shopping_fast_c").css("display","none");
		$("#perspective_c").css("display","none");
		$("#shopping_guide_c").css("display","block");
	})	
	
	
})