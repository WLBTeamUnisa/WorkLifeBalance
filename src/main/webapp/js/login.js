
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

	    function validate(input) {
		if ($(input).attr('type') == 'text' || $(input).attr('name') == 'email') {
			if (($(input).val().trim().match(/^[a-z]{1}\.[a-z]+[0-9]+\@wlb.it/) == null)
					&& ($(input).val().trim().match(
							/^[a-z]{1}\.[a-z]+[0-9]+\@wlbadmin.it/) == null)) {
				return false;
			}
	

		} 
			if($(input).attr('type')=='password' || $(input).attr('name')=='password'){
			
				if($(input).val().trim().match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\.!@#\$%\^&\*])(?=.{8,20})/)==null){
					
					return false;
				}
}			
		}
		
		

	
	    
	   
        
        	
        		
        
        
       

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    
})(jQuery);