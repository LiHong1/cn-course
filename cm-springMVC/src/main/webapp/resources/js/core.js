(function($){
	$.fn.myaccordion = function(opts) {
		var settings = $.extend({
			selectedClz:"navSelected",
			titleTagName:"h3"
		},opts||{});
		var titleNode = $(this).find("ul>"+settings.titleTagName);
		var selectedNode = $(this).find("ul."+settings.selectedClz+">"+settings.titleTagName);
		titleNode.css("cursor","pointer");
		titleNode.nextAll().css("display","none");
		selectedNode.nextAll().css("display","block");
		titleNode.click(function(){
			var checked = $(this).parent().hasClass(settings.selectedClz);
			if(checked) {
				$(this).parent().removeClass(settings.selectedClz);
				$(this).nextAll().slideUp();
			} else {
				$(this).parent().addClass(settings.selectedClz);
				$(this).nextAll().slideDown();
			}
		});
	};
	
	$.fn.trColorChange = function(opts) {
		var settings = $.extend({
			overClz:"trMouseover",
			evenClz:"trEvenColor"
		},opts||{});
		$(this).find("tbody tr:even").addClass(settings.evenClz);
		$(this).find("tbody tr").on("mouseenter mouseleave",function(){
			$(this).toggleClass(settings.overClz);
		});
	};
	
	$.fn.confirmOperator = function(opts) {
		var settings = $.extend({
			msg:"确认删除吗？",
			eventName:"click"
			
		},opts||{});
		$(this).on(settings.eventName,function(event){
			if(!confirm(settings.msg)) {
				event.preventDefault();
			}
		});
	}
	$.fn.setHeight = function(opts) {
		var settings = $.extend({
			right:"#right",
			left:"#left"
		},opts||{});
		var right=$("#right").css("height");
		var left=$("#left").css("height");
		if(right!=undefined&&left!=undefined){
			right_height=right.replace("px","");
			left_height=left.replace("px","");
			if(parseInt(right_height)>parseInt(left_height))
			 $("#left").css("height",right);
		}
	}
	//表格排序代码开始-----------------------------------------------
	$.fn.sortTable=function(opts){
		var settings = $.extend({
			begin:"#beginOrder",
			save:"#saveOrder"
		},opts||{});
		var _isOrder=false;
		var sortEle=$(this).find("tbody");
		var _that=$(this);
		sortEle.sortable({
			axis:"y",
			helper:function(e,ele){
				//原始的td对象
				var _original=ele.children();
				var _helper=ele.clone();
				$(_helper).children().each(function(index){
					$(this).width(_original.eq(index).width());
				});
				_helper.css("background","#aaf");
				return _helper;
			},
			update:function(e,ui){
				setOrders();
			}
		});
		var setOrders=function(){
			$(_that).find("tbody tr").each(function(index){
				 $(this).find("td:first").html(index+1);
				 $(".add").parent().remove();

			});
			$(_that).find("tbody tr:last").find("td:last").append("<span><img src='"+settings.path+"/resources/style/images/menu/add.png' alt='增加' title='增加' class='add'></span>");
		};
		sortEle.sortable("disable");
		$(settings.begin).click(function(){
			if(!_isOrder){
				setOrders();
				$(".sortable tbody").sortable("enable");
				_isOrder=true;
			}else alert("已经处于排序状态");

		});
		$(settings.save).click(function(){

			var ids=getIds();
			if(_isOrder){
				$.get("menu/updateSort?"+ids,function(date){
					sortEle.sortable("disable");
					_isOrder=false;
				});

			}else alert("不处于排序状态");
		});
		var getIds = function (){
			var string='';
			var trs = $("tbody").children();
			var ids = new Array([trs.size()]);
			trs.each(function(index){
				ids[index]=$(this).attr("id");
				string += 'ids='+ids[index]+'&';
			});
			return string;
		};

	};
	//表格排序代码结束----------------------------------------------------
})(jQuery)