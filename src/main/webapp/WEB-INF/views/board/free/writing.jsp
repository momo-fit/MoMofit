<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 작성</title>
 	<!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <!-- jQuery 슬릭 라이브러리 -->
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />


    <!-- Swiper 슬라이드 라이브러리 -->
    <!-- <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script> -->
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />

    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/7d82554876.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/556efa47d7.js" crossorigin="anonymous"></script>

    <!-- 전 페이지 공통 설정 -->
    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <!-- CSS : main_header 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_header.css">
    <!-- CSS : main 전체 페이지 설정 -->
    <link rel="stylesheet" href="/resources/common/css/main_page.css">
    <!-- CSS : main 미디어 태그 -->
    <link rel="stylesheet" href="/resources/common/css/main_mediatag.css">
    <!-- CSS : main footer 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_footer.css">

    
    <link rel="stylesheet" href="/resources/board/free/css/writing.css">

    <!-- 메인페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/common/css/main_section_card.css">
    <link rel="stylesheet" href="/resources/common/css/main_section_board.css">

    
</head>

<body>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %>   
        
        <!-- 사이드 프로필 -->
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>


        <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="s_box_board">
                    <div class="writing">
                        <div class="writing_head"><br>게시글 작성</div>
                        <hr>
                        <!-- 게시글 작성 내용부분 -->

                        <form class="writing_form" 
                            action="/board/free/register" method="post">

                            <div class="writing_content">

                                <!-- 1. 게시판 선택 -->
                                게시판&nbsp;
                                <select id="select_board" name="category_no" required>
                                    <option value="">선택</option>
                                    <option value="1">자유게시판</option>
                                    <option value="2">운동팁</option>
                                    <option value="3">운동인증</option>
                                    <option value="4">중고거래</option>
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <br>

                                <!-- 2. 제목 -->
                                <br>제목&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="text" class="writing_title" name="title" required>
                                <br><br>

                                <!-- 3. 첨부파일 -->
                                첨부&nbsp;&nbsp;&nbsp;
                                <span class="filebox">
                                    <input class="upload_name" value="첨부파일" placeholder="첨부 이미지">

                                    <label class="input_file_button" for="input_file">
                                        파일찾기
                                    </label>

                                    <input type="file" id="input_file" name="uploadFile" class="upload-hidden" multiple>

                                    <div id="uploadResult">
                                        <!-- 여기에 미리보기 생성 -->
                                    </div>
                                </span>
                                <br>

                                <!-- 4. 내용 -->
                                내용<br>
                                <textarea class="textarea_content" name="text" required>안녕하세요</textarea>
                                
                                <input type="hidden" name="board_like" value=0>
                                <input type="hidden" name="hits" value=0>
                                <input type="hidden" name="img_check" value=0>
                                <input type="hidden" name="user_no" value=1>

                            </div>

                        <!-- 목록 버튼 -->
                        <input type="button" class="list_btn" value="취소" onClick="location='/board/free/list?currPage=${param.currPage}'"></input>                         

                        <!-- 작성하기 버튼 -->
                        <input type="submit" class="writing_submit" value="작성"></input>
                        

                        </form>
                        
                    </div>
                    
                </div>
            </div>

        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>

    <script>
        // 이미지 업로드
        $("input[type='file']").on("change", function(e){

            // 이미지 존재시 삭제
            if($(".imgDeleteBtn").length > 0){
                deleteFile();
            }
            let formData = new FormData();

            // let fileInput = $('input[name="uploadFile"]');
            // let fileList = fileInput[0].files;
            // let fileObj = fileList[0];

            let fileInput = $('input[name="uploadFile"]');
            let files = fileInput[0].files;

            
		    for(let i = 0; i < files.length; i++){
                if(!fileCheck(files[i].name, files[i].size)){
                    return false;
                } // if

            	formData.append("uploadFile", files[i]);		  
		    } // for
            
            $.ajax({
                url: '/uploadAjaxAction', // FreeUploadController의 uploadAjaxAction으로 보냄
                processData : false,
                contentType : false,
                data : formData,
                type : 'POST',            // post 방식으로 보냄
                dataType : 'json',
                success : function(result){
                    console.log(result);
                    showUploadImage(result);
                },
                error : function(result){
                    alert("이미지 파일이 아닙니다.");
	    	    }
                
            });

        });

        // 이미지 체크
        let regex = new RegExp("(.*?)\.(jpg|png|JPG|PNG)$");
        let maxSize = 1048576; //1MB	
        
        // 이미지 체크 메서드
        function fileCheck(fileName, fileSize){

            if(fileSize >= maxSize){
                alert("파일 사이즈 초과");
                return false;
            } // if
                
            if(!regex.test(fileName)){
                alert("해당 종류의 파일은 업로드할 수 없습니다.");
                return false;
            } // if            
            return true;		            
        }

        // 이미지 미리보기 출력
	    function showUploadImage(uploadResultArr){
            // 전달받은 데이터 검증
            if(!uploadResultArr || uploadResultArr.length == 0){return}
            
            let uploadResult = $("#uploadResult");  
            let obj = uploadResultArr[0];    
            let str = "";  
            let fileCallPath = encodeURIComponent(obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName);
		
            str += "<div id='result_card'>";
            str += "<img src='/display?fileName=" + fileCallPath +"'>";
            str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
            str += "</div>";

            uploadResult.append(str);     
    	}

    // 이미지 삭제 버튼 동작
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){
		
		deleteFile();
		
	});

    // 파일 삭제 메서드
	function deleteFile(){
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax({
			url: '/deleteFile',
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
    </script>


    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/include/js/main_header.js"></script>
    
    <script src="/resources/board/free/js/writing.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>