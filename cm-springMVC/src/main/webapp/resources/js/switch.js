(function($){
	$.fn.switch = function(options){
		var setting = $.extend({
		},options||{});
		var s = this;
		init(this,$(setting.initValueId).val());
		$(this).click(function(){
			if($(setting.initValueId).val() == 'true')
				$(setting.initValueId).val('false');
			else $(setting.initValueId).val('true');
			switchChange(s);
		});
		return this;
		function init(s,state){
			$(s).append("<div  class='switch_on'><span>ON</span></div><div class='switch_state1'></div>")
			$(s).append("<div  class='switch_off'><span>OFF</span></div> <div class='switch_state2'>  </div>")
			if(state == 'true')
			{
				switchON(s);
			}else{
				switchOFF(s);
			}
		}
		function switchON(s){
			$(s).children(".switch_on").css("display","block");
			$(s).children(".switch_state1").css("display","block");
			$(s).children(".switch_off").css("display","none");
			$(s).children(".switch_state2").css("display","none");
		}
		function switchOFF(s){
			$(s).children(".switch_on").css("display","none");
			$(s).children(".switch_state1").css("display","none");
			$(s).children(".switch_off").css("display","block");
			$(s).children(".switch_state2").css("display","block");
		}
		function switchChange(s){
			if(s.children(".switch_on").css("display")=="none")
				switchON(s);
			else switchOFF(s);
		}
	}
})(jQuery);