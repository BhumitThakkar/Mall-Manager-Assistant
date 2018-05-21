$( window ).on( "load", function() {
		$('#navigation').show(1000,function(){
			$('body').css({
				'margin-top': $('#fixed_header').height()
			}),
			$('#fixed_header').css({
				'position': 'fixed'
			});
		});
});

$('#logout').click(function(){
		$('#navigation').hide(1000,function(){
			window.location.href="Logout";
		});
});

$('.bg .wrapper .login-body').mouseover(function(){
	$(this).css({
		'opacity' : '1'
	})
});

$('.bg .wrapper .login-body').mouseout(function(){
	$(this).css({
		'opacity' : '0.85'
	})
});

$('.bg #txtMailId').focus(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '1'
	})
});

$('.bg #txtMailId').blur(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '0.85'
	})
});

$('.bg #txtPassword').focus(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '1'
	})
});

$('.bg #txtPassword').blur(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '0.85'
	})
});

$('.bg #txtCheckbox').focus(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '1'
	})
});

$('.bg #txtCheckbox').blur(function(){
	$('.bg .wrapper .login-body').css({
		'opacity' : '0.85'
	})
});

$('#stat,#saleExcel,#productExcel,#product,#partner,#addProductQuantity,#sale,#addProductQuantityFile,#spoil').mouseenter(function(){
	$(this).css({
			'transform':'scale(1.05,1.05)',
			'transition':'0.5s ease',
			'opacity' : '0.6'
	})
});

$('#stat,#saleExcel,#productExcel,#product,#partner,#addProductQuantity,#sale,#addProductQuantityFile,#spoil').mouseout(function(){
	$(this).css({
		'transform':'scale(1,1)',
		'transition':'0.5s ease',
		'opacity' : '1'
	})
});
/*
var now = Date.now();
$("#txtExpiry").datepicker({
	 format: "dd-mm-yyyy",
	    startView: 2,
	    todayBtn: "linked",
	    autoclose: true,
	    todayHighlight: true,
	    minDate: new Date()
});*/

$(document).ready(function() {
    $('#example').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'copy', 'excel', 'pdf', 'print'
        ]
    } );
} );