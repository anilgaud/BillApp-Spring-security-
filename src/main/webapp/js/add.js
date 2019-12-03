/**
 * 
 */
$(document).ready(function() {
	debugger;
	var a=$('#msg').val();
	if(a!='' && a!=undefined)
   alert("group saved successfully");
});
function fs()
{ 
	
	debugger;
	 var x = document.getElementById("m1").innerHTML ;
  document.getElementById("nm").innerHTML +=  x ;
	
}

function chkGroup()
{
	debugger;
	
	$.ajax({
		url : "chkGroupByUsername",
		async : false,
		success : function(result) {

			console.log("SUCCESS: ", result);
		
			if (result > 0) {

				alert("You don't have a group");
				
				return false
			} 
			else
				{
				return true;

				}
			
		}
	});
}