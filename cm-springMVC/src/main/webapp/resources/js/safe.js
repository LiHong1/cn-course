/**
 * 
 */

(function(){
	
	var Encrypt={
			username:'username',
			password:'password',
			usernameValue:'',
			passwordValue:'',
		init:function(usernameId,passwordId){
			if(usernameId)
			   this.username=usernameId;
			if(passwordId)
			   this.password=usernameId;
			this.usernameValue=document.getElementById(this.username).value;
			this.passwordValue=document.getElementById(this.password).value;
		}
	};
	
	
	
	
	window.Encrypt=Encrypt;
})();