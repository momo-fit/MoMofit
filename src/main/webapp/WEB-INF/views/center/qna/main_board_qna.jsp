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
        
    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/favicon.ico" type="image/x-icon">
    
</head>

<body>
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
                        문의합니다 글 제목 입니다 
                    </div>
                
                    <div class="info"> 
                        <div class="info2">
                            <span class="font-12-400"> 작성자 <b class="font-12-400">홍길동</b> </span>
                            <div class="space1"></div>
                            <span class="font-12-400"> 조회수 <b class="font-12-400">1</b> </span>
                            <div class="space1"></div>
                            <span class="font-12-400"> 작성시간 <b class="font-12-400">2022.08.15 12:37</b> </span>
                            <div class="space1"></div>
                        </div>
              
                        <div class="edit_delete">
                            <a href="#" onClick="location.href='/center/qna/modify'"><span class="font-12-400"> 수정 </span></a>
                            <div class="space1"></div>
                            <a href="#" onClick="location.href='/center/qna/list'"><span class="font-12-400"> 삭제 </span></a>
                        </div>
                    </div>

                    <!-- 본문 -->
                    <div class="content_wrap">
                        
                        <!-- 본문 이미지 -->
                        <div>
                            <img src="https://picsum.photos/id/684/600/400" alt="picsum img"> <br><br>
                        </div>
                        
                        <!-- 본문 텍스트 -->
                        <div class="font-14-400">
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br>
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br>
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br> <br>
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br>
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br>
                            글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 글 내용이 들어갑니다 문의를 할 수 있습니다 <br> <br>
                        </div>


                        <div class="report">
                            <a href="" class="link1 float_right font-14-700">신고</a><br>
                                                    
                        </div>
                        
                        <!-- 댓글창 -->
                        <div class="comment_wrap">
                            <span class="font-14-700">댓글</span> <br><br>
    
                            <!-- 댓글1 -->
                            <form action="" class="comment font-14-400">
                                <span class="font-16-500">닉네임1</span>
                                <input type="text" class="comment_input_text" name="" id="" value="댓글내용입니다1" disabled>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
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
                            <form action="" class="comment font-14-400">
                                <span class="font-16-500">닉네임1</span>
                                <input type="text" class="comment_input_text" name="" id="" value="댓글내용입니다1" disabled>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </form>
    
                            <!-- 댓글3 -->
                            <form action="" class="comment font-14-400">
                                <span class="font-16-500">닉네임1</span>
                                <input type="text" class="comment_input_text" name="" id="" value="댓글내용입니다1" disabled>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </form>
    
                            <!-- 댓글4 -->
                            <form action="" class="comment font-14-400">
                                <span class="font-16-500">닉네임1</span>
                                <input type="text" class="comment_input_text" name="" id="" value="댓글내용입니다1" disabled>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </form>
    
                            <br>
                        </div>

                        <!-- 댓글 작성창 -->
                        <form action="">
                            <div id="comment_write" class="card">
                                <div class="card-body">
                                    <textarea class="form-control" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                    onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'"></textarea>
                                    <button type="submit" class="btn btn-primary btn_color_blue float_right">작성</button>
                                </div>
                            </div>                             
                        </form>
                            
                        <br>
                        
                    </div>
                    
                    <!-- 이전글 목록 다음글 -->
                    <div class="btn_row">
                        <a href=""><button type="button" class="btn btn-primary btn-sm btn_color_blue">이전글</button></a>
                        <a href="#" onClick="location.href='/center/qna/list'"><button type="button" class="btn btn-secondary btn-sm">목록</button></a>
                        <a href=""><button type="button" class="btn btn-primary btn-sm btn_color_blue" >다음글</button></a>    
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

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>