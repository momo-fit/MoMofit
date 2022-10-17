


//$(function () {
function loadImg(board_no) {
	let uploadResult=$(".uploadResult");
	
	console.log(" *** 무조건 출력되어야함! \n " + String(board_no) );
	
	// getJSON(url[,data][,success]) 형식으로 씀
	// DB에서 이미지 정보 불러오기
	$.getJSON("/board/pic/imageList", {board_no : board_no}, function(arr) {  
		
		console.log('board_no : ' + String(board_no) + ' / arr.length : ' + arr.length);
		
		// 이미지가 없는 경우 바로 리턴
		if(arr.length === 0) {
			console.log("이미지가 없습니다." + String(board_no));
			return;
		}
		
		let str = "";
		let obj = arr[0];

		let fileCallPath = encodeURIComponent(obj.path + "/s_" + obj.temp + "_" + obj.board_img_name);
		
		//str += "<div="
		//str += "data-path='" + obj.path + "' data-uuid='" + obj.temp + "' data-filename='" + obj.board_img_name + "'";
		//str += ">";
		//str += "아아~ 테스트 하겠습니다~" + board_no; //div안에 들어가고있는 내용
		//str += "<img class='sport_check_img' src='/display?fileName=" + fileCallPath +"'>";
		
		//str += "</div>";
		

		uploadResult.append(str);
		//uploadResult.empty();
	 });
}

//});

// 게시물 클릭하면 바로 첨부된 이미지 보이기 끝

