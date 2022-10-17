<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <!-- CSS : main_header 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_header.css">
    <!-- CSS : main 전체 페이지 설정 -->
    <link rel="stylesheet" href="/resources/common/css/main_page.css">

    <link rel="stylesheet" href="/resources/board/pic/css/writing.css">
    <!-- <link rel="stylesheet" href="css/모임개설2.css"> -->
    
    <!-- CSS : main_footer 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_footer.css">

    

</head>

<body>
	<script type="text/javascript">
	
    let form = $("#infoForm");        // 페이지 이동 form(리스트 페이지 이동, 조회 페이지 이동)
	let mForm= ${"#modifyForm"}; // 페이지 데이터 수정 form
    
    /* 수정 하기 버튼 */
    $("#modify_button").on("click", function(e){
        form.attr("action", "/board/pic/modify");
        
    });
    
    /* 취소 버튼 */
    $("#cancel_botton").on("click", function(e){
        form.attr("action", "/board/pic/view");
        form.submit();
    });    
	
	</script>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp"%>
        <!-- side -->
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>


        <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="s_box_board">
                	
                    <div class="writing">
                        <div class="writing_head"><br>게시글 수정</div>
                        <hr>
                        <!-- 게시글 작성 내용부분 -->

                        <form id="modifyForm" action="modify" method="POST">
                        <input type="hidden" id="board_no" name="board_no" value='<c:out value="${PicBoard.board_no}"/>'>
						<input type="hidden" id="currPage" name="currPage" value='<c:out value="${cri.currPage}"/>'>	

                            <div class="writing_content">

                                <!-- 1. 게시판 선택 -->
                                게시판&nbsp;
                                <select id="select_board" name="category_no" disabled required>
                                    <option value="">선택</option>
                                    <option value="1">자유게시판</option>
                                    <option value="2">운동팁</option>
                                    <option value="3" selected>운동인증 </option>
                                    <option value="4">중고거래</option>
                                    
                                </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                <!-- 2-1. (중고거래 선택시) 팝니다/삽니다/나눔 선택 -->
                                <select id="select_transaction">
                                    <option value="">선택</option>
                                    <option value="팝니다">팝니다</option>
                                    <option value="삽니다">삽니다</option>
                                    <option value="나눔">나눔</option>
                                </select>
                                
                                 <!-- 2-2. (문의 선택시) 공개/비공개 선택 -->
                                <span id="access">
                                    <input type="radio" name="access" value="1"> 공개
                                    <input type="radio" name="access" value="2" checked> 비공개
                                </span><br>

                                <!-- 3. 제목 -->
                                <br>제목&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="text" class="writing_title" value="${PicBoard.title}" required>
                                <br><br>

                                <!-- 4. 첨부파일 -->
                                첨부&nbsp;&nbsp;&nbsp;
                                <span class="filebox">
                                    <input class="upload_name" value="첨부파일" placeholder="첨부 이미지">

                                    <label class="input_file_button" for="input_file">
                                        파일찾기
                                    </label>

                                    <input type="file" id="input_file" class="upload-hidden" accept='image/jpg,image/png,image/jpeg,image/gif' >
                                </span>
                                <br><br>

                                <!-- 5. 내용 -->
                                내용<br>
                                <textarea class="textarea_content" name="text" required>${PicBoard.text}</textarea>

                            </div>

                        <!-- 수정하기/취소하기 버튼 -->
                        <input type="button" class="cancel_button" id="cancel_button" value="취소" onClick="location='/board/pic/view?board_no=${PicBoard.board_no}&currPage=${cri.currPage}'"></input>
                        <input type="submit" class="writing_submit" id="modify_button" value="수정"></input>
                   
                        </form>

                        
                    </div>
                    
                </div>
            </div>

        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>


    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/include/js/main_header.js"></script>
    <script src="/resources/board/pic/js/writing.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>