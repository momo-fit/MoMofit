

/* 이미지업로드 */

// 글작성 버튼을 누를 때 이미지 저장
// $("#uploadBtn").on("click",function(e){
	
// 파일 찾기 누르면 찾은 이미지 미리 보기
let img_no = '<c:out value="${imageList.img_no}"/>';
let uploadResult = $("#uploadResult");

			$.getJSON("/imageList", {img_no:img_no}, function(arr){
				
				console.log(arr);
				
				if(arr.length === 0){
					
					
					let str = "";
					str += "<div id='result_card'>";
					str += "</div>";
					
					uploadResult.html(str);				
					return;
				}
	});


$("input[type='file']").on("change",function(e){	
	let formData = new FormData(); //formData선언해주기 

	let fileInput=$('input[name="uploadFile"]');
	
	let fileList = fileInput[0].files;
	<!--fileList객체가 맞는지 확인하기위해 변수를 선언하고,fileList로 초기화 -->
	
	//formData.append.append("uploadFile",fileObj);
	//사용자가 선택한 파일을 FormData에 "uploadFile"이란 이름(key)로 추가해준다.(1개의 파일만 선택하는경우)

	for(let i=0; i<fileList.length; i++){
		
		$('input[name=img_check]').attr('value',1);
		
		if(!fileCheck(fileList[i].name, fileList[i].size)){ //파일확장자.파일사이즈 모두 조건만족할시(png,jpg 와 1MB) 통과창이 뜬다.
			return false;
		}
		console.log(fileList[i]);
		formData.append("uploadFile",fileList[i]); 
		
	}//사용자가 여러개의 파일을 선택할 수 있는 경우
	
	$.ajax({
		url: '/board/pic/uploadAjaxAction',  //서버로 요청을 보낼 URL
		processData : false,	   //서버로 전송될 데이터를 queryString형태로 변환할지 여부 
		contentType : false,		//서버로 전송되는 데이터의 content-type
		data : formData,			//서버로 전송할 데이터
		type : 'POST',				//서버 요청 타입
		dataType : 'json',			//서버로부터 반환받을 데이터 타입
		success : function(result){
			console.log('*** result - ' + result);
			showUploadImage(result);
		},
		error : function(result){
			alert("이미지 파일이 아닙니다.");
		}
	});
	
});
	
	
//-------------------------------------------------------------//
//파일확장자,파일사이즈 제한두기 
let regex = new RegExp("(.*?)\.(jpg|png)$"); //jpg랑 png파일만 업로드 허용 
let maxSize = 1048576; //1MB

function fileCheck(fileName, fileSize){

	if(fileSize >= maxSize){
		alert("파일 사이즈 초과");
		return false;
	}
			
	if(!regex.test(fileName)){
		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		return false;
	}
	
	return true;	//두가지 다 만족시 ture반환
} // fileCheck END

//이미지 출력하기
function showUploadImage(uploadResultArr){

	// 전달받은 데이터 검증
	if(!uploadResultArr || uploadResultArr.length == 0){return}
	
	let uploadResult = $("#uploadResult");  
	
	let obj = uploadResultArr[0];
	console.log('*** obj : ', obj);
	
	let str = "";
	let fileCallPath = encodeURIComponent(obj.path + "/s_" + obj.temp + "_" + obj.board_img_name);
	str += "<div id='result_card'>";
	str += "<img src='/display?fileName=" + fileCallPath +"'>";
	str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
	// DB에 데이터를 저장하기 위해 input hidden으로 이미지 정보를 폼에 넘겨준다
	str += "<input type='hidden' name='imageList[0].board_img_name' value='"+ obj.board_img_name +"'>";
	str += "<input type='hidden' name='imageList[0].temp' value='"+ obj.temp +"'>";
	str += "<input type='hidden' name='imageList[0].path' value='"+ obj.path +"'>";
	str += "</div>";
	uploadResult.append(str); 
	

	
	/* 이미지 삭제 버튼 동작 */
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		
		deleteFile();
		
	});
	
	$("#uploadResult").on("click", ".cancel_button", function(e){
		
		deleteFile();
		
	});
    
    /* 파일 삭제 메서드 */
	function deleteFile(){
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax({
			url: '/board/pic/deleteFile',
			data : {fileName : targetFile}, //fileName속성명에 targetFile(이미지파일경로)속성갑 부여, 스프링에서 매핑할것
			dataType : 'text',	//전송하는 targetFile은 문자데이터이기때문에 'text'
			type : 'POST',
			success : function(result){
				console.log(result);
				
				targetDiv.remove();
				$("input[type='file']").val("");
				
			},
			error : function(result){
				console.log(result);
				
				alert("파일을 삭제하지 못하였습니다.")
			}
		});
	}//파일삭제메소드 
		
}//showUploadImage END
