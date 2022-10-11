<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
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

    <!-- CSS : main_board_freeboard -->
    <link rel="stylesheet" href="/resources/board/free/css/main_board_freeboard.css">

    <!-- 메인페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/common/css/main_section_card.css">
    <link rel="stylesheet" href="/resources/common/css/main_section_board.css">

    

</head>

<body>
	<script>
	var board_no = '<c:out value="${PicBoard.board_no}"/>'
	</script>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %>   

        <!-- 사이드 프로필 -->
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>


        <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="view">

                    <div class="board_name font-20-500">
                        <img src="/resources/board/free/img/workout.png" height="20px">
                        운동인증
                    </div>

                    <div class="title font-28-400">
                        <c:out value="${PicBoard.title}"/>
                    </div>
                
                    <div class="info"> 
                          <div class="info2">
                            <span class="font-12-400"> 작성자 <b class="font-12-400"><c:out value="${PicBoard.user_no}"/></b> </span>
                            <div class="space1"></div>
                            <span class="font-12-400"> 조회수 <b class="font-12-400"><c:out value="${PicBoard.hits}"/></b> </span>
                            <div class="space1"></div>
                            <span class="font-12-400"> 작성시간 <b class="font-12-400"><fmt:formatDate pattern="yyyy/MM/dd" value="${PicBoard.board_date}"></fmt:formatDate></b> </span>
                            <div class="space1"></div>
                        </div>
              
                        <div class="edit_delete">
                            <a class="btn" onClick="location.href='/board/pic/modify?board_no=${PicBoard.board_no}&currPage=${cri.currPage}'"><span class="font-12-400">수정 </span></a>
                            
                            <div class="space1"></div>

                            <form action="/board/pic/remove?board_no=${PicBoard.board_no}" method="post" style="display: inline;">
                        	    <input type="hidden" id="board_no" name="board_no" value='<c:out value="${PicBoard.board_no}"/>'>
								<input type="hidden" id="currPage" name="currPage" value='<c:out value="${cri.currPage}"/>'>
                                <button style="border: none; background: none;"><span class="font-12-400"> 삭제 </span></button>
                            </form>
                        </div>
                    </div>

                    <!-- 본문 -->
                    <div class="content_wrap">
                        
                        <!-- 본문 이미지 -->

						<div id="uploadResult">
						
						</div>
                    	
                        
                        <!-- 본문 텍스트 -->
                        <div class="font-14-400">
                             <c:out value="${PicBoard.text}"/> <br> <br>
                        </div>

                        <!-- 좋아요 -->
                        <div class="like_div">
                            <img src="/resources/board/free/img/like.png" height="70" class="like_btn">
                            <div id="like_cnt" class="font-14-700">0</div>                            
                        </div>

                        <!-- 좋아요 체크박스로 만든거 -->
                        <!-- <input type="checkbox" name="" id="like_checkbox" style="visibility: hidden;">
                        <label for="like_checkbox"><img src="img/like.png" height="70"></label> -->

                        <!-- 신고하기 -->
                        <div class="report">
                            <a href="" class="link1 float_right font-14-700">신고</a><br>
                                                    
                        </div>
                        
                        <!-- 댓글창 -->
                        <div class="comment_wrap">
                            <span class="font-14-700">댓글</span> <br><br>
    
                            
                            <!-- 댓글작성버튼누르면 실행되게 form에 연결 -->
                            <form action="" method="" class="comment font-14-400">
                            
                            <!-- 댓글작성컨트롤러에 쿼리스트링으로 board_no값을 받아오게했으니, 넘겨주게하기 (사용자에게는 안보이게) -->
                            <input type="hidden" name="board_no" value="${commDto.board_no}">
                            <input type="hidden" name="parent_no" value="${commDto.parent_no}">

								<!-- 댓글1 -->
								<c:forEach var="commList" items="${commList}">
                                <span class="font-16-500"><c:out value="${commList.user_no}"/></span>
                                <input type="text" class="comment_input_text" name="" id="" value=<c:out value="${commList.text}"/> disabled>
                                <span><fmt:formatDate pattern="yyyy/MM/dd" value="${commList.comm_date}"/></span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                                 </c:forEach>  
                            </form>

                            <!-- 대댓글 작성창 -->  
                            <form action="">
                                <div id="recomment_write" class="card">
                                    <div class="card-body">
                                        <textarea class="form-control" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                        onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'"></textarea>
                                        <button type="submit" class="btn btn-primary btn_color_blue float_right">작성</button>
                                    </div>
                                </div>  
                            </form>                          
                                
                            <!-- 댓글2 -->
                            <div class="comment font-14-400">
                                <span class="font-16-500">닉네임2</span>
                                <span>댓글내용입니다2</span>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <span class="cursor_pointer font-12-400"> 수정 </span>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </div>
    
                            <!-- 댓글3 -->
                            <div class="comment font-14-400">
                                <span class="font-16-500">닉네임3</span>
                                <span>댓글내용입니다3</span>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <span class="cursor_pointer font-12-400"> 수정 </span>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </div>
    
                            <!-- 댓글4 -->
                            <div class="comment font-14-400">
                                <span class="font-16-500">닉네임4</span>
                                <span>댓글내용입니다4</span>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <span class="cursor_pointer font-12-400"> 수정 </span>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </div>
    
                            <br>
                        </div>

                        <!-- 댓글 작성창 -->
                        <form action="/board/pic/registerComm" method="POST"">
                            <div id="comment_write" class="card">
                                <div class="card-body">
                                    <textarea class="form-control" name="" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                    onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'"></textarea>
                                    <button type="submit" class="btn btn-primary btn_color_blue float_right">작성</button>
                                </div>
                            </div>                             
                        </form>
                            
                        <br>
                        
                    </div> <!-- 댓글마무리 -->
                    
                    <!-- 이전글 목록 다음글 -->
                    <div class="btn_row">
                        <button type="button" class="btn btn-primary btn-sm btn_color_blue" >이전글</button>
                        <button type="button" class="btn btn-secondary btn-sm" onClick="location.href='/board/pic/list?currPage=${cri.currPage}'">목록</button>
                        <button type="button" class="btn btn-primary btn-sm btn_color_blue" >다음글</button>
                    </div>
            
                </div>
            </div>

        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>
  

    <!-- 메인화면 섹션 자바스크립트` -->
    <!--<script src="/resources/common/js/main_section.js"></script>-->
	
	<!-- 상세 조회 페이지 자바스크립트 -->
	<script src="/resources/board/pic/js/main_board_pic.js"></script>

    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/include/js/main_header.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>

