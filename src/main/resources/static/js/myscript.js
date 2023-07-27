console.log("HI>>>>>>>")

const phoneNumber=/^([6][3]|[7-9][023456789])[0-9]{8}$/
const emailCheck=/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/
$('.crossbtn').click(function(){
	if($(".slidebar").is(":visible")){
		$(".slidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}
	else{
		$(".slidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
});

$('.fa-bars').click(function(){
	if($(".slidebar").is(":visible")){
		$(".slidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}
	else{
		$(".slidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
});

$('#addContact').click(function()
{
	console.log($('#firstname').val());
	if($('#firstname').val()==''){
		console.log($('.firstname').next('p').text()+"aryan");
		if($('.firstname').next('p').text()==""){
			console.log("hi there..........");
			$('.firstname').next('p').text('*Please enter name here');
		}
		
		return false;
		
	}
	else{
			$('.firstname').next('p').text('');
		}
	
	if($('#secondname').val()==''){
		console.log($('.secondname').next('p').text()+"aryan");
		if($('.secondname').next('p').text()==""){
			console.log("hi there..........");
			$('.secondname').next('p').text('*Please enter nickname here.If not,enter name only');
		}
		return false;
		
	}
	
	else{
		$('.secondname').next('p').text('');
	}
	
	if($('#phone').val()==''){
		
		
		console.log($('.phone').next('p').text()+"aryan");
		if($('.phone').next('p').text()==""){
			console.log("hi there..........");
			$('.phone').next('p').text('*Please enter Contact number');
			
		}
		
		return false;
		
		
	}
	if(!$('#phone').val()==''){
	  if(!phoneNumber.test($('#phone').val())){
			console.log("hi inside regex..........");
			$('.phone').next('p').text('*Please enter Valid  number');
			return false;
		}
	
	else{
		$('.phone').next('p').text('');
	}
	}
	
	if($('#email').val()==''){
		
		
		console.log($('.email').next('p').text()+"aryan");
		if($('.email').next('p').text()==""){
			console.log("hi there..........");
			$('.email').next('p').text('*Please enter Email id ');
			
		}
		
		return false;
		
		
	}
	if(!$('#email').val()==''){
	  if(!emailCheck.test($('#email').val())){
			console.log("hi inside regex..........");
			$('.email').next('p').text('*Please enter Valid  Email id');
			return false;
		}
	
	else{
		$('.email').next('p').text('');
	}
	}
	
	if($('#work').val()==''){
		console.log($('.firstname').next('p').text()+"aryan");
		if($('.work').next('p').text()==""){
			console.log("hi there..........");
			$('.work').next('p').text('*Please enter Work status (e.g. Programmer,Businessman,Student..etc)');
		}
		
		return false;
		
	}
	else{
			$('.work').next('p').text('');
		}
	
	
	
	
	
	return true;
	
	
});
