<%@page contentType="text/html; charset=utf-8"%>

<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta name="keyword" content="">
	
	<title>ระบบคลังข้อสอบ</title>

	<!-- Bootstrap CSS -->
	<link href="${pageContext.request.contextPath}/ui/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/css/bootstrap-theme.css" rel="stylesheet"/>
	<!-- font -->
	<link href="${pageContext.request.contextPath}/ui/fonts/webfont.css" rel="stylesheet" />
	<!-- font icon -->
	<link href="${pageContext.request.contextPath}/ui/css/elegant-icons-style.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/ui/css/font-awesome.min.css" rel="stylesheet" />
	<!-- datatable -->
	<link href="${pageContext.request.contextPath}/ui/css/dataTables.bootstrap.css" rel="stylesheet"/>
	<!-- Custom styles -->
	<link href="${pageContext.request.contextPath}/ui/css/style.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/css/style-responsive.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/ui/css/jquery-ui-10.12.1.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/css/style-button.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/css/bootstrap-select.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/css/materialize-color.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/ui/sweetalert/css/sweetalert.css" rel="stylesheet"/>

	<!-- javascripts -->
	<script src="${pageContext.request.contextPath}/ui/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery-ui-1.10.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery-1.8.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
	<!-- for set format thai -->
	<script src="${pageContext.request.contextPath}/ui/js/jquery.ui.1.8.10.offset.datepicker.min.js" type="text/javascript"></script>
	
	<!-- bootstrap -->
	<script src="${pageContext.request.contextPath}/ui/js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="${pageContext.request.contextPath}/ui/js/jquery.scrollTo.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- custom select -->
	<script src="${pageContext.request.contextPath}/ui/js/jquery.customSelect.min.js"></script>
	
	<!--custome script for all page-->
	<script src="${pageContext.request.contextPath}/ui/js/scripts.js"></script>
	
	<!-- custom script for this page-->
	<script src="${pageContext.request.contextPath}/ui/js/jquery.autosize.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery.placeholder.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/bootbox.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/bootstrap-select.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/bootstrap-typeahead.js"></script>
	
	<!-- datatable -->
	<script src="${pageContext.request.contextPath}/ui/js/jquery.dataTables.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/ui/js/dataTables.bootstrap.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/ui/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/ui/js/quotwaiting-forquot-modal-dialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/ui/sweetalert/js/sweetalert.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
 		$(document).ready(function () {
 			
 			$("#msgSuccess").delay(800).fadeOut(1000);
 			$("#msgWarning").delay(800).fadeOut(1000);
 			
			try {
				var variables = {};
		      	variables = {
						"sProcessing": "กำลังดำเนินการ...",
						"sLengthMenu": "แสดง _MENU_  แถว",
						"sZeroRecords": "ไม่พบข้อมูล",
						"sInfo": "แสดง _START_ ถึง _END_ จาก _TOTAL_ แถว",
						"sInfoEmpty": "แสดง 0 ถึง 0 จาก 0 แถว",
						"sInfoFiltered": "(กรองข้อมูล _MAX_ ทุกแถว)",
						"sInfoPostFix": "",
						"sSearch": "ค้นหา: ",
						"sUrl": "",
						"oPaginate": {
						    "sFirst": "เริ่มต้น",
						    "sPrevious": "ก่อนหน้า",
						    "sNext": "ถัดไป",
						    "sLast": "สุดท้าย"
						}
				};
			
				var options = { "aoColumnDefs": [{ bSortable: false, aTargets: ['no-sort'] }],"oLanguage": variables};
				var optionsHundred = { "aoColumnDefs": [{ bSortable: false, aTargets: ['no-sort'] }],"iDisplayLength": 100,"oLanguage": variables};
				var optionsFive = { "aoColumnDefs": [{ bSortable: false, aTargets: ['no-sort'] }],"iDisplayLength": 5,"oLanguage": variables};
			
				$(".grid-default").dataTable(options);
				$(".grid-hundred").dataTable(optionsHundred);
				$(".grid-five").dataTable(optionsFive);
			} catch (e) {
			}
			
			$.datepicker.isBuddhist ={
				isBuddhist: true,
				changeMonth: true,
		        changeYear: true,
		        buttonImageOnly: false,
		        dateFormat: 'dd/mm/yy',
		        dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
		        dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
		        monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        //monthNamesShort: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        monthNamesShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
		        constrainInput: true,	
		        prevText: 'ก่อนหน้า',
		        nextText: 'ถัดไป',
		        yearRange: '-200:+10',
		        buttonText: 'เลือก'
		    };
			
			$.datepicker.twomonth ={
				isBuddhist: true,
				changeMonth: false,
		        changeYear: false,
		        buttonImageOnly: false,
		        dateFormat: 'dd/mm/yy',
		        dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
		        dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
		        monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        //monthNamesShort: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        monthNamesShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
		        constrainInput: true,	
		        prevText: 'ก่อนหน้า',
		        nextText: 'ถัดไป',
		        yearRange: '-200:+10',
		        buttonText: 'เลือก',
		        numberOfMonths: 2
		    };
 			$('.i-calendar').datepicker($.datepicker.isBuddhist);
			$('.t-calendar').datepicker($.datepicker.twomonth);
			
			$( "#dateStart" ).datepicker({
		    	isBuddhist: true,
				changeMonth: true,
		        changeYear: true,
		        buttonImageOnly: false,
		        dateFormat: 'dd/mm/yy',
		        dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
		        dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
		        monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        monthNamesShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
		        constrainInput: true,	
		        prevText: 'ก่อนหน้า',
		        nextText: 'ถัดไป',
		        yearRange: '-200:+5',
		        buttonText: 'เลือก',
		        numberOfMonths: 2,
		      	onClose: function( selectedDate ) {
		        	$( "#dateStop" ).datepicker( "option", "minDate", selectedDate );
		      	}
		    });
			
		    $( "#dateStop" ).datepicker({
		    	isBuddhist: true,
				changeMonth: true,
		        changeYear: true,
		        buttonImageOnly: false,
		        dateFormat: 'dd/mm/yy',
		        dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
		        dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
		        monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
		        monthNamesShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
		        constrainInput: true,	
		        prevText: 'ก่อนหน้า',
		        nextText: 'ถัดไป',
		        yearRange: '-200:+5',
		        buttonText: 'เลือก',
		        numberOfMonths: 2,
		      onClose: function( selectedDate ) {
		        $( "#dateStart" ).datepicker( "option", "maxDate", selectedDate );
		      }
		    });
        });
 		
 		function showDialogContent(url,title,size){
 			
 			$.post(url,{}, 
 				function(results) {	 
 				var textAndPic = $('<div id="dialogs">'+results+'</div>');	
 				var bootdialog = bootbox.dialog({
 				    title: title,
 				    message: textAndPic,
 				    className: size
 				});
 				
 				$('.selectpicker').selectpicker('refresh');
 				
 				/* dialog.init(function(){
 				    setTimeout(function(){
 				        dialog.find('.bootbox-body').html(textAndPic);
 				    }, 3000);
 				}); */	
 				
 			});	
 		}
 		
 		function showDialog(url,t,w,h){    		
 	        $('#dialogs').dialog({
 	        	modal: true,
 	        	show: {
 					effect: "fade",
 					duration: 200
 				},
 	        	title: t,
 	            width: w,
 	            height: h,
 	            closeOnEscape: true,
 	            open: function () {
 	                $.post(url,{}, 
 	              	function(results) {	 
 	              		$('#dialogs').html(results);	
 	              		$('#dialogs').siblings('.ui-dialog-titlebar').find('button').blur(); 
 	              		

 	             	});
 	            }
 	        });
 		}
 		
 		function showLoadingSwal() {
 			swal({
      		  	title: "กำลังโหลดข้อมูล...",
      		  	imageUrl: '${pageContext.request.contextPath}/ui/sweetalert/image/entering-comment-loader.gif',
      			closeOnConfirm: false,
      			showCancelButton: false,
      			showConfirmButton: false,
      		});
 		}
 		function success() {
 	        swal.close()
 	    }
	</script>
<style type="text/css">
	.popup-dialog .modal-dialog { width: 700px; }
	.popuplg-dialog .modal-dialog { width: 1000px; }
</style>
</head>