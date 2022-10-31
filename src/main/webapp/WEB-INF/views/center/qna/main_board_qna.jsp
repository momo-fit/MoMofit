<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의</title>
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

    <!-- CSS : main_board_qna -->
    <link rel="stylesheet" href="/resources/center/qna/css/main_board_qna.css">

    <!-- 메인페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/common/css/main_section_card.css">
    <link rel="stylesheet" href="/resources/common/css/main_section_board.css">

</head>

<body>

	<script>
	var qna_no = '<c:out value="${QnaBoard.qna_no}"/>'
	</script>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %> 
        
        <!-- side -->
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %> 


        <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="view">

                    <div class="board_name font-20-500">
                        <img src="/resources/center/qna/img/cs.png" height="25px">
                        문의
                    </div>

                    <div class="title font-28-400">
                        <c:out value="${QnaBoard.title}"/> 
                    </div>
                
                    <div class="info"> 
                        <div class="info2">
                            <span class="font-12-400"> 작성자 <b class="font-12-400"><c:out value="${QnaBoard.nickname}"/></b> </span>
                            <div class="space1"></div>
                            <span class="font-12-400"> 작성시간 <b class="font-12-400"><fmt:formatDate pattern="yyyy/MM/dd" value="${QnaBoard.qna_date}"></fmt:formatDate></b> </span>
                            <div class="space1"></div>
                        </div>
              
                        <div class="edit_delete">
                            <a class="btn" onClick="location.href='/center/qna/modify?qna_no=${QnaBoard.qna_no}&currPage=${cri.currPage}'"><span class="font-12-400"> 수정 </span></a>
                            <div class="space1"></div>
                            
                            <form action="/center/qna/remove?qna_no=${QnaBoard.qna_no}" method="post" style="display: inline;">
                        	    <input type="hidden" id="qna_no" name="qna_no" value='<c:out value="${QnaBoard.qna_no}"/>'>
								<input type="hidden" id="currPage" name="currPage" value='<c:out value="${cri.currPage}"/>'>
                                <button style="border: none; background: none;"><span class="font-12-400"> 삭제 </span></button>
                            </form>
                        </div>
                    </div>

                    <!-- 본문 -->
                    <div class="content_wrap">
                        
                        <!-- 본문 이미지 -->
                        <div>
                            <br><br>
                        </div>
                        
                        <!-- 본문 텍스트 -->
                        <div class="font-14-400">
                      		<c:out value="${QnaBoard.text}"/> <br> <br>
                        </div>


                        <div class="report">
                            <a href="#" onClick="location.href='/center/report/list'" class="link1 float_right font-14-700">신고</a><br>
                                                    
                        </div>
                        
                        <!-- 댓글창 -->

                        <script>
                        	let qnaNo = "${QnaBoard.qna_no}";
                        	let commId = "${sessionScope.__USER__.user_no}";  // 작성자
                        </script>
                        <div class="comment_wrap">
                            <span class="font-14-700">댓글</span> <br><br>
    
                             <!-- 댓글을 담을 빈 div -->
                             <div class="card-body">
                             	<ul style="padding:0" class="chat">
                             	</ul>
                             </div>
                                 
                            


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
             
    
                            <br>
                        </div>

                        <!-- 댓글 작성창 -->
                         <div id="comment_write" class="card">
                         <!-- 댓글 작성창  -->
                             <div class="card-body">
                                 <textarea class="form-control" id="comm_write" name="comm_write" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                 onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'" value=""></textarea>
                              	 <button id="commSubmitBtn" class="btn btn-primary btn_color_blue float_right">작성</button>
                             </div>
                         </div>                             
                            
                        <br>
                        
                	</div> <!-- 댓글마무리 -->
                    
                    <!-- 이전글 목록 다음글 -->
                    <div class="btn_row">
                        <button type="button" class="btn btn-primary btn-sm btn_color_blue" >이전글</button>
                        <button type="button" class="btn btn-secondary btn-sm" onClick="location.href='/center/qna/list?currPage=${cri.currPage}'">목록</button>
                        <button type="button" class="btn btn-primary btn-sm btn_color_blue" >다음글</button>
                    </div>
            
                </div>
            </div>

        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>

    <!-- 문의뷰 자바스크립트 -->
    <script src="/resources/center/qna/js/main_board_qna.js"></script>


    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/include/js/main_header.js"></script>
    
    <!-- 댓글작성 자바스크립트 -->
	<script src="/resources/center/qna/js/comm.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>