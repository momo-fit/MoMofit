
	/* 이미지 업로드 */
	$("input[type='file']").on("change", function(e){
		
		/* 이미지 존재시 삭제 */
		if($(".imgDeleteBtn").length > 0){
			deleteFile();
		}
		
		let formData = new FormData();

		let fileInput = $('input[name="uploadFile"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];
		
		console.log("fileList : " + fileList);
		console.log("fileObj : " + fileObj);
		
		console.log("fileName : " + fileObj.name);
		console.log("fileSize : " + fileObj.size);
		console.log("fileType(MimeType) : " + fileObj.type);
		
		if(!fileCheck(fileObj.name, fileObj.size)){
			return false;
		}
        formData.append("uploadFile", fileObj);

        $.ajax({
			url: '/group/uploadAjaxAction',     // url : 서버로 요청을 보낼 url
	        processData : false,                // processData : 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
	        contentType : false,                // contentType : 서버로 전송되는 데이터의 content-type
	        data : formData,                    // data : 서버로 전송할 데이터
	        type : 'POST',                      // type : 서버 요청 타입(GET, POST)
	        dataType : 'json',                  // dataType : 서버로부터 반환받을 데이터 타입
	        success : function(result){
	    		console.log(result);
                showUploadImage(result);

	    	},
	    	error : function(result){
	    		alert("이미지 파일이 아닙니다.");
	    	}

		});
	});
	

	/* var, method related with attachFile */
	let regex = new RegExp("(.*?)\.(jpg|png)$");
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
		
		return true;		
		
	}

    /* 이미지 출력 */
	function showUploadImage(uploadResultParam){
		
		/* 전달받은 데이터 검증 */
		if(!uploadResultParam){return}
		
		let uploadResult = $("#uploadResult");	
		
		let str = "";
		
		/* img src 태그 안에 들어가게될 이미지의 총 경로 ( 날짜폴더 경로 + 썸네일 이미지 uuid + 실제 이름 */ 
		let fileCallPath = encodeURIComponent(
            uploadResultParam.path+ "/s_" +uploadResultParam.temp + "_" +uploadResultParam.group_img
            );
		
		str += "<div id='result_card'>";
		str += "<img src='/group/display?fileName=" +fileCallPath+"'>";
		str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
        str += "<input type='hidden' name='group_img' value='"+ uploadResultParam.group_img +"'>";
		str += "<input type='hidden' name='temp' value='"+ uploadResultParam.temp +"'>";
		str += "<input type='hidden' name='path' value='"+uploadResultParam.path +"'>";	
		str += "</div>";		
		
   		uploadResult.append(str);     
        
	}	


    /* 이미지 삭제 버튼 동작 */
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		
		deleteFile();
		
	});

    /* 파일 삭제 메서드 */
	function deleteFile(){
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax({
			url: '/group/deleteFile',
			data : {fileName : targetFile},
			dataType : 'text',
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
	}
	