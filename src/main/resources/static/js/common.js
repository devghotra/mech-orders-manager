var authToken;

function init(){
	
	$('#logoDiv').show();
	$('#viewEditOrderFormDiv').hide();
	$('#addOrderSuccessfulMsg').hide();
	$('#adminConsoleDiv').hide();
	
	$( "#addOrderDate" ).datepicker({ dateFormat: "yy-mm-dd"});
	$( "#editOrderDate" ).datepicker({ dateFormat: "yy-mm-dd"});
	$( "input[id=toOrderDate]" ).datepicker({ dateFormat: "yy-mm-dd"});
	$( "input[id=fromOrderDate]" ).datepicker({ dateFormat: "yy-mm-dd"});
	
	$( "#accordion" ).accordion({ heightStyle: "content", active: false, collapsible: true   });

    $("#dialog").dialog({
        modal : true,
        autoOpen : false,
        width : 600,
        resizable : false,
        position : [ 'center', 200 ],
        show : {
            effect : "blind",
            duration : 1000
        },
        hide : {
            effect : "explode",
            duration : 1000
        }
    });

    $( "#dialog-confirm" ).dialog({
        resizable: false,
        width : 400,
        modal: true,
        autoOpen : false,
        position : [ 'center', 200 ],
        hide : {
            effect : "explode",
            duration : 1000
        }
    });

    loadDataIfLogged();
	
}

function loadDataIfLogged(){
    authToken = sessionStorage.authToken;

    if(authToken == null){
        $("#addOrderForm :input:not(:checkbox):not(:button)").prop('disabled', true);
        $("#orderLookUpForm :input:not(:checkbox):not(:button)").prop('disabled', true);
        $("#searchOrderForm :input:not(:checkbox):not(:button)").prop('disabled', true);
    } else{
        $(this).successfulLogin();
        loadLookupData();
        loadOrders();
    }
}

$( "#addEquipmentDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

$( "#removeEquipmentDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

$( "#addMechanicDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

$( "#removeMechanicDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

$( "#addTyreDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

$( "#removeTyreDataForm" ).submit(function( event ) {
    $("#errorMsg").hide();
    $.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $("#dialogErrorMsg").text(data.responseDescription);
                $("#dialogErrorMsg").show();
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
    return false;
});

function loadLookupData(){
    $.get( "api/mech/orders/lookup?at="+authToken, function( data ) {
        if(data.responseCode == 200){

            var equipments = data.lookupData.equipment;
            var option = '';
            for (var i=0;i<equipments.length;i++){
                option += '<option value="'+ equipments[i] + '">' + equipments[i] + '</option>';
            }
            $( "select[id=equipmentNumber]" ).append(option);

            var mechanics = data.lookupData.mechanic;
            var option = '';
            for (var i=0;i<mechanics.length;i++){
                option += '<option value="'+ mechanics[i] + '">' + mechanics[i] + '</option>';
            }
            $( "select[id=mechanicName]" ).append(option);

            var tyres = data.lookupData.tyre;
            var option = '';
            for (var i=0;i<tyres.length;i++){
                option += '<option value="'+ tyres[i] + '">' + tyres[i] + '</option>';
            }
            $( "select[id=tyreType]" ).append(option);

        } else{
            $(this).showErrorMessage(data.responseDescription);
        }
    });
}

$( ".closeAdminConsole" ).click(function( event ) {
	$( "#dialog" ).dialog( "close" );
});

$( ".closeDeleteOrderDialog" ).click(function( event ) {
	$( "#dialog-confirm" ).dialog( "close" );
});

$( ".tabLink" ).click(function( event ) {
	if(authToken != null){
		$("#errorMsg").hide();
	}
});

$( "#editOrderBtn" ).click(function( event ) {
	$("#viewEditOrderForm :input:not(:checkbox):not(:button)").prop('disabled', false);
	$(this).hide();
	$('#deleteOrderBtn').hide();
	$('#saveOrderBtn').show();
	$('#cancelEditBtn').show();
});

$( "#cancelEditBtn" ).click(function( event ) {
	$("#viewEditOrderForm :input:not(:checkbox):not(:button)").prop('disabled', true);
	$(this).hide();
	$('#saveOrderBtn').hide();
	$('#editOrderBtn').show();
	$('#deleteOrderBtn').show();
});

$( "#deleteOrderBtn" ).click(function( event ) {
	$( "#dialog-confirm" ).dialog( "open" );
});

$( "#confirmDeleteOrderBtn" ).click(function( event ) {
	deleteOrder();
});


$( "#loginForm" ).submit(function( event ) {
	$("#errorMsg").hide();
	$.ajax({
        url     : $(this).attr('action'),
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
        	if(data.responseCode == 200){
        		sessionStorage.authToken = data.authToken;
            	location.reload();
        	} else{
        		$(this).showErrorMessage(data.responseDescription);
        	}
        },
        error   : function( xhr, err ) {
        	alert('Error in form submission '+err+xhr);     
        }
    }); 
	return false;
});

$( "#addOrderForm" ).submit(function( event ) {
	$("#errorMsg").hide();
	$.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
        	if(data.responseCode == 200){
            	location.reload();
        	} else{
        		$(this).showErrorMessage(data.responseDescription);
        	}
        },
        error   : function( xhr, err ) {
        	alert('Error in form submission '+err+xhr);     
        }
    }); 
	return false;
});

$( "#addUserForm" ).submit(function( event ) {
	var pwd = $('#addUserForm #password').val();
	var verifyPwd = $('#addUserForm #verifyPwd').val();
	
	if(pwd != verifyPwd){
		$("#dialogErrorMsg").text("Password and verify password don't match");
		$("#dialogErrorMsg").show();
		return false;
	}
	
	$("#errorMsg").hide();
	$.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
        	console.info("Add user response - "+data);
        	if(data.responseCode == 200){
            	location.reload();
        	} else{
        		$("#dialogErrorMsg").text(data.responseDescription);
        		$("#dialogErrorMsg").show();
        	}
        },
        error   : function( xhr, err ) {
        	alert('Error in form submission '+err+xhr);     
        }
    }); 
	return false;
});

$( "#deactivateUserForm" ).submit(function( event ) {
	$("#errorMsg").hide();
	$.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
        	if(data.responseCode == 200){
            	location.reload();
        	} else{
        		$("#dialogErrorMsg").text(data.responseDescription);
        		$("#dialogErrorMsg").show();
        	}
        },
        error   : function( xhr, err ) {
        	alert('Error in form submission '+err+xhr);     
        }
    }); 
	return false;
});

$( "#orderLookUpForm" ).submit(function( event ) {
	$("#errorMsg").hide();
	var orderId = $('#lookUpOrderId').val();

	if(orderId == null || orderId.trim().length == 0 ){
		$(this).showErrorMessage("Enter order number to view order");
		return false;
	}
	
	loadOrder(orderId);
	return false;
});

$( "#viewEditOrderForm" ).submit(function( event ) {
	$("#errorMsg").hide();
	$.ajax({
        url     : $(this).attr('action') + "?at="+authToken,
        type    : $(this).attr('method'),
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        data    : JSON.stringify($(this).serializeObject()),
        success : function( data ) {
        	if(data.responseCode == 200){
            	location.reload();
        	} else{
        		$(this).showErrorMessage(data.responseDescription);
        	}
        },
        error   : function( xhr, err ) {
        	alert('Error in form submission '+err+xhr);     
        }
    }); 
	return false;
});

function searchOrders(){

	console.debug("submitting search form");

    var reqParams = "at="+authToken;

    var from = $('#searchOrderForm #fromOrderDate').val();
    if(from != null && from.length > 0){
        reqParams = reqParams + "&from="+from;
	}

    var to = $('#searchOrderForm #toOrderDate').val();
    if(to != null && to.length > 0){
        reqParams = reqParams + "&to="+to;
    }

    var equipmentNumber = $('#searchOrderForm #equipmentNumber').val();
    if(equipmentNumber != null&& equipmentNumber.length > 0){
        reqParams = reqParams + "&equipment="+equipmentNumber;
    }

    var mechanicName = $('#searchOrderForm #mechanicName').val();
    if(mechanicName != null&& mechanicName.length > 0){
        reqParams = reqParams + "&mechanic="+mechanicName;
    }

	$("#errorMsg").hide();

    console.debug("Req params: "+reqParams);

    $.get( "api/mech/orders/search?"+reqParams, function( data ) {
        if(data.responseCode == 200){
            $("#ordersTableHeading").text("Search Results");
            loadOrdersDataTable(data.ordersList, true);
        } else{
            $(this).showErrorMessage(data.responseDescription);
        }
    });
}


function loadOrders(){
	$.get( "api/mech/orders/search?at="+authToken, function( data ) {
		//orderListJson = data.ordersList;
		
		/*$('#ordersTable').dynatable({
	  		dataset: {
	    		records: orderListJson
	  		}
		});
		
		dynatable = $('#ordersTable').dynatable({
			  dataset: {
			    records: orderListJson
			  }
			}).data('dynatable');*/
		
		if(data.responseCode == 200){
			loadOrdersDataTable(data.ordersList, false);
    	} else{
    		$(this).showErrorMessage(data.responseDescription);
    	}
		
	});
}

function loadOrdersDataTable(orderListArray, refresh){
	var aaData="[";
	for (var key in orderListArray){
		var shift = orderListArray[key].shift == null ? "" : orderListArray[key].shift;
		
		aaData+=(key>0?",":"")+"[";
	 
		aaData+="\""+orderListArray[key].id+"\","+
            	"\""+orderListArray[key].orderDate+"\","+
            	"\""+orderListArray[key].equipmentNumber+"\","+
				"\""+orderListArray[key].equipmentType+"\","+
            	"\""+orderListArray[key].mechanicName+"\","+
            	"\""+orderListArray[key].laborHours+"\","+
            	"\""+orderListArray[key].laborHourlyRate+"\","+
            	"\""+orderListArray[key].tyreType+"\","+
            	"\""+orderListArray[key].numOfTyres+"\","+
            	"\""+orderListArray[key].tyrePrice+"\","+
            	"\""+orderListArray[key].partCost+"\","+
            	"\""+orderListArray[key].partDescription+"\","+
            	"\""+orderListArray[key].lubricantType+"\","+
				"\""+orderListArray[key].lubricantQuantity+"\","+
				"\""+orderListArray[key].lubricantPrice+"\"";

	  aaData+="]";
	}
	aaData+="]";

	if(refresh){
		var oTable = $('#ordersTable').dataTable();
		oTable.fnClearTable();
		console.debug("Search Results Datatable: "+aaData);
		oTable.fnAddData(JSON.parse(aaData));
		oTable.fnDraw();
	} else{
		$('#ordersTable').dataTable( {
	        "aaData": JSON.parse(aaData),
	        //"sDom": 'Tfrtlip ',
	        "sDom": 'T<"clear">lfrtip',
	        //"sPaginationType": "full_numbers",
	        "bAutoWidth": false,
	        "aaSorting": [[ 0, "desc" ]],
	        "oLanguage": {
	            "sLengthMenu": "Display _MENU_ orders per page"
	        },
			"oTableTools": {
				"aButtons": [
								"copy",
								"print",
								{ "sExtends":"xls", "sFileName" : "jassboys-orders.xls"},
								{ "sExtends":"pdf", "sFileName" : "jassboys-orders.pdf", "sPdfOrientation": "landscape"},
							],
							"sSwfPath": "media/copy_csv_xls_pdf.swf"
			}
	    } );
		$(".dataTables_filter input").addClass("searchInputBox");
		//$(".DTTT_button").removeClass("DTTT_button");
		
	}
}

function loadOrderFromLink(orderId){
	$( "#viewEditOrderLink" ).trigger( "click" );
	$( "#lookUpOrderId" ).val(orderId);
	loadOrder(orderId, null);
	$('html, body').animate({ scrollTop: 0 }, 'slow');
}

function loadOrder(orderId){
	var url = "api/mech/orders?id="+orderId.trim()+"&at="+authToken;

	$.get( url, function( data ) {
		if(data.responseCode == 200){
			$('#viewEditOrderFormDiv').show();
			$('#viewEditOrderForm #id').val(data.order.id);
			$('#viewEditOrderForm #editOrderDate').val(data.order.orderDate);
			$('#viewEditOrderForm #equipmentNumber').val(data.order.equipmentNumber);
			$('#viewEditOrderForm #equipmentType').val(data.order.equipmentType);
			$('#viewEditOrderForm #laborHours').val(data.order.laborHours);
			$('#viewEditOrderForm #laborHourlyRate').val(data.order.laborHourlyRate);
			$('#viewEditOrderForm #mechanicName').val(data.order.mechanicName);
			$('#viewEditOrderForm #tyreType').val(data.order.tyreType);
			$('#viewEditOrderForm #numOfTyres').val(data.order.numOfTyres);
			$('#viewEditOrderForm #tyrePrice').val(data.order.tyrePrice);
			$('#viewEditOrderForm #partCost').val(data.order.partCost);
			$('#viewEditOrderForm #partDescription').val(data.order.partDescription);
			$('#viewEditOrderForm #lubricantType').val(data.order.lubricantType);
			$('#viewEditOrderForm #lubricantQuantity').val(data.order.lubricantQuantity);
			$('#viewEditOrderForm #lubricantPrice').val(data.order.lubricantPrice);
			$('#viewEditOrderForm #notes').val(data.order.notes);
    	} else{
    		$(this).showErrorMessage(data.responseDescription);
    	}
	});
}

function deleteOrder(){
	var orderId = $("#viewEditOrderForm #id").val();
	var url = "api/mech/orders?at="+authToken+"&id="+orderId;

    $.ajax({
        url     : url,
        type    : 'DELETE',
        contentType : "application/json; charset=utf-8",
        dataType: 'json',
        success : function( data ) {
            if(data.responseCode == 200){
                location.reload();
            } else{
                $(this).showErrorMessage(data.responseDescription);
            }
        },
        error   : function( xhr, err ) {
            alert('Error in form submission '+err+xhr);
        }
    });
}

$.fn.successfulLogin = function()
{
	$("#adminConsoleItem").show();
	$("#signOutItem").show();
	$("#signInItem").hide();
	$("#errorMsg").hide();
};

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
} else {
    o[this.name] = this.value || '';
        }
    });
    return o;
};

$.fn.showErrorMessage = function(errorMessage)
{
	$("#errorMsg").text(errorMessage);
	$("#errorMsg").show();
	
	if(errorMessage.trim() == "Session Expired" || errorMessage.trim() == "Unauthorized User"){
		$("#adminConsoleItem").hide();
		$("#signInItem").show();
	}
};

function adminConsole()
{
	$("#dialogErrorMsg").hide();
	$( "#dialog" ).dialog( "open" );
};

function signOut()
{
	sessionStorage.removeItem('authToken');
	location.reload();
};