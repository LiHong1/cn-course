
(function($){
	 var initParams={
				usernameId:'username',
				passwordId:'password',
				buttonId:'button',
				formId:'form',
				usernameValue:'',
				passwordValue:'',
				url:'',
				modulus:'',
				exponent:'',

	};
	$.fn.InitRegister=function(options){
		 var params=$.extend(initParams,options);
		    init(params);
		$(params.buttonId).click(function(){
 

			 //对password用MD5进行加密
		       var password=CryptoJS.MD5($(params.passwordId).val());
		      
		       var random=Math.seedrandom();
		       var userInformation=BASE64.encoder($(params.usernameId).val())+":"+password+":"+BASE64.encoder(random.substring(0,30));
		     
		       alert(userInformation);
		       $.getJSON(params.url,function(data) {
		                   params.modulus = data[0].publicKey.modulus;
		                   params.exponent = data[0].publicKey.exponent;
		                   alert(params.modulus);
		                   alert(params.exponent);
		                   //epwd为R2加浏览器段产生的数据数
		                   if (userInformation.length != 256) {
		                	   var publicKey = RSAUtils.getKeyPair(params.exponent, '', params.modulus);           
		                       $(params.passwordId).val(RSAUtils.encryptedString(publicKey, userInformation));
		                        $(params.usernameId).val();
		                       $(params.formId).submit();
		                   }  
		       });
        
		});
	};
	
	
	$.fn.InitLogin=function(options){
		   var R1,mi,MK,R2;
		   var params=$.extend(initParams,options);
		    init(params);
		$(params.buttonId).click(function(){
			
			 $.getJSON(params.url,function(data) {
				 params.modulus = data[0].publicKey.modulus;
  	             params.exponent = data[0].publicKey.exponent;

			       $.getJSON(params.url+"?number="+ $(params.usernameId).val(),function(data) {
			    	           R1 = data[0].R1, mi = data[0].mi;
		                       alert('服务器给的R1='+R1);
		                       alert('服务器给的mi='+mi);
		               
		                       //对password用MD5进行加密
			    	           params.password=CryptoJS.MD5($(params.passwordId).val());
			    	           //用密码与R1加密获得主密钥MK
			                   MK=Encrypt(params.password,R1);
			    	           alert("得到主密钥MK="+MK);
			                   //对密文解密  获得R2
			                   R2=Decrypt(mi,MK.substring(0,16));
			                   alert("解的随机数R2="+R2);
			    	           var random=Math.seedrandom();
			     
			    	           var epwd = R2+BASE64.encoder(random.substring(0,30));
		                       //epwd为R2加浏览器段产生的数据数
		                       if (epwd.length != 256) {
		                           var publicKey = RSAUtils.getKeyPair(params.exponent , '', params.modulus);
		                           var password=RSAUtils.encryptedString(publicKey, epwd);
		                           $(params.passwordId).val(password); 
		                           alert("加密后的R2与随机数="+password);
		                       }
		                     
		                       $(params.formId).submit();
			       });
	       
	           });
			 
			 
		});
		 
	};
	
	 function Decrypt(content,KEY){
         var key = CryptoJS.enc.Utf8.parse(KEY); 
         var iv  = CryptoJS.enc.Utf8.parse('0102030405060708'); 
         var decrypted = CryptoJS.AES.decrypt(content, key, { iv: iv,mode:CryptoJS.mode.CBC});
         return CryptoJS.enc.Utf8.stringify(decrypted).toString();
    };
	  
	   function Encrypt(content,KEY){
         var key = CryptoJS.enc.Utf8.parse(KEY); 
         var iv  = CryptoJS.enc.Utf8.parse('0102030405060708'); 
         var srcs = CryptoJS.enc.Utf8.parse(content);
         var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC});
         return encrypted.toString();
    };
	   function init(params){
		 
			params.usernameId='#'+params.usernameId;
			params.passwordId='#'+params.passwordId;
			params.formId='#'+params.formId;
			params.buttonId='#'+params.buttonId;
	   };

})(jQuery);