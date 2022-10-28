$(document).ready( ()=> {
	
	const statusNum = $("#statusNum").val();
	console.log("statusNum : ", statusNum);
    switch(statusNum){
        case 1 :
            $('.inquery_comm').text('처리완료');
            $('.inquery_status').css("background","gray");
            break;
        default :
            $('.inquery_comm').text('처리중');
            $('.inquery_status').css("background","rgb(20 167 255)");
            break;
    }		


})
