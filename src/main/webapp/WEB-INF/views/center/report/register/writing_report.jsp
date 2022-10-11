<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>main</title>
    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/7d82554876.js" crossorigin="anonymous"></script>

    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <!-- CSS : main_header 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_header.css">
    <!-- CSS : main 전체 페이지 설정 -->
    <link rel="stylesheet" href="/resources/common/css/main_page.css">
    <!-- CSS : main 미디어 태그 -->
    <link rel="stylesheet" href="/resources/common/css/main_mediatag.css">

    <link rel="stylesheet" href="/resources/center/report/css/writing.css">
    <!-- <link rel="stylesheet" href="css/모임개설2.css"> -->
    <script>
        let resMessage = '${failMessage}';
	    
	    if(resMessage != null && resMessage.length > 0){
	        alert(resMessage);
	    }

        let user_no = '${__USER__.user_no}'
        
        $().ready(function() {
            $('#user_no').val(user_no);
            let t =  $('#user_no').val();
            console.log(t);
        });

        // 파일명 
        $(()=> {
            $("#input_file").on('change',function(){
                let fileName = $("#input_file").val();

                var fileValue = fileName.split("\\");
                var splitFileName = fileValue[fileValue.length-1]; 
                $(".upload_name").val(splitFileName);
            });
        })

    </script>
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
                        <div class="writing_head"><br>신고합니다</div>
                        <hr>
                        <!-- 게시글 작성 내용부분 -->

                        <form action="/center/report//report-post" method="POST" class="writing_form" enctype="multipart/form-data">

                            <div class="writing_content">

                                <!-- 1. 신고유형 선택 -->
                                유형&nbsp;&nbsp;&nbsp;&nbsp;
                                <select id="select_report" name="report_type" required>
                                    <option value="">선택</option>
                                    <option value="욕설,비방,차별,혐오">욕설,비방,차별,혐오</option>
                                    <option value="광고글">광고글</option>
                                    <option value="음란">음란</option>
                                    <option value="기타">기타</option>
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                
                                <!-- 2-2. (문의 선택시) 공개/비공개 선택 -->
                                <span id="access">
                                    <input type="radio" name="access" value="1"> 공개
                                    <input type="radio" name="access" value="2" checked> 비공개
                                </span><br>

                                <!-- 3. 제목 -->
                                <br>제목&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="text" class="writing_title" name="title" required>
                                <br><br>

                                <!-- 4. 첨부파일 -->
                                첨부&nbsp;&nbsp;&nbsp;
                                <span class="filebox">
                                    <input class="upload_name" placeholder="첨부 이미지" name="report_img_name">

                                    <label class="input_file_button" for="input_file">
                                        파일찾기
                                    </label>

                                    <input type="file" id="input_file"  name="file" class="upload-hidden" accept='image/jpg,image/png,image/jpeg,image/gif' >
                                </span>
                                <br>

                                <input type="text" id="user_no" name="user_no" style="display: none;">

                                <!-- 5. 대상 -->
                                대상&nbsp;&nbsp;&nbsp;
                                <input type="text" class="username" name="report_user" placeholder="닉네임">
                                <input type="button" class="username_search" value="검색"></input>
                                <br><br>
                                
                                <!-- 미리보기 -->
                                <div class="img">
                                    <img src="#" id="uplodeImg"><span><i class="xBtn fa-solid fa-circle-xmark"></i></span>
                                    
                                </div>

                                <!-- 6. 내용 -->
                                내용<br>
                                <textarea class="report_textarea_content" name="text" required></textarea>
																
                            </div>                
                            <!-- 돌아가기 버튼 -->
                            <input type="button" class="report_back" value="취소" onclick="location='/center/report/list'">  
                                      
                            <!-- 작성하기 버튼 -->
                            <input type="submit" class="report_submit" value="신고하기">
							
                        </form>
                        
                    </div>
                    
                </div>
            </div>

        </section>


        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
        
    </div>
    

    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/main/js/main.js"></script>
    <!-- 유저 검색 -->
    <script src="/resources/center/report/js/userSearch.js"></script>
    <!-- 미리보기 -->
    <script src="/resources/center/report/js/reportWriting.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

<script>

</script>