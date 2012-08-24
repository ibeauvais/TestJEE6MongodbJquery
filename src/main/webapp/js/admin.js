

function viewUser(id){
	alert('view '+id);
}



function removeUser(id){

	if(!confirm('Etes vous sur ?'))
		return;
	
	$.ajax({
        type: 'DELETE',
        url: '../rs/user/' + id,
        dataType: 'json',
        cache: false,
        success:renderTableUsers

    });
	
}


function renderTableUsers(){
	$.ajax({
        type: 'GET',
        url: '../rs/user',
        dataType: 'json',
        cache: false,
        success: function(data) {
        $('#tabUser').empty();
        $('#tabUser').append(buildTableHeader());
      var list = data == null ? [] : (data.user instanceof Array ? data.user : [data.user]);

        $.each(list,function(index,value){

            $('#tabUser').append(buildLineForData(value));
        });


        },
		error: function(jqXHR, textStatus, errorThrown){
			if(jqXHR)
				alert(jqXHR.responseText);

		}
    });
	
}

var columns=["id","name","email"];

function buildTableHeader(){
     var result='<tr>';
          columns.forEach(function(value){
             result+="<th>"+value+"</th>";
    });
     result+="<th>Actions</th>";
    result+='</tr>';
    return result;
}



function buildLineForData(data){

    var result='<tr>';

    columns.forEach(function(value){
             result+=getColumn(result,data,value);
    });

   result+=getColumnAction(result,data);


    result+='</tr>';
    return result;
}


function getColumn(result,data,columnName){
    var result='<td>';
    result+=data[columnName];
    result+='</td>';
    return result;
}

function getColumnAction(result,data){
    var id=data.id;
    var result='<td>';
    result+='<a href="javascript:viewUser(\''+id+'\');"><i class="icon-eye-open"/></a>';
    result+='<a href="javascript:removeUser(\''+id+'\');"><i class="icon-trash"/></a>';
    result+='</td>';
    return result;
}


$(document).ready(function() {
			renderTableUsers();
		});