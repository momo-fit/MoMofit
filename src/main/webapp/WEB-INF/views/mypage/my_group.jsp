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
    <!-- CSS : 사이드 프로필 -->
    <link rel="stylesheet" href="/resources/include/css/side_profile.css">
    
    <!-- CSS : 마이페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/include/css/mypage_left_side.css">
    
    <!-- CSS : 마이페이지 컨텐츠 영역 CSS -->
    <link rel="stylesheet" href="/resources/mypage/css/mypage_content1.css">

            
    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/resources/favicon.ico" type="image/x-icon">
    

</head>

<body>
    <div class="page">

        <!-- header -->
        <%@include file="../include/header.jsp" %>

        <!-- 사이드메뉴 -->
        <%@include file="../include/sideLogin.jsp" %>


        <!-- 메인 컨텐츠부 -->
        <!-- 메인 컨텐츠부 -->
        <section>
            <div class="section_inner">
                <div class="section_wrap">
                    
                    <%@include file="../include/mypage_left_side.jsp" %>

                    <div class="s_box_content">
                        <!-- 각 페이지 제목 -->
                        <div class="mypage-content-title font-24-700">나의 모임</div>
                        <div class="mypage-border"></div><br>
                        <!-- 진행중인 모임 버튼 -->
                        <button class="mypage-group-btn mypage-blue-btn" onClick="change_ing();"> 
                            <span>진행중인 모임</span>
                        </button>
                        <!-- 종료된 모임 버튼 -->
                        <button class="mypage-group-btn" onClick="change_end();"> 
                            <span>종료된 모임</span>
                        </button>	
    
                        <!-- 진행중인 모임 박스 -->
                        <div class="mypage-group-ing font-14-200">
                                <!-- 진행중 모임의 대문 사진 -->
                                <img src="/resources/mypage/img/sport.png" class="mypage-group-ing-photo"/>
                                <div class="mypage-group-info">
                                    <!-- 내가 만든 모임 왕관 뱃지 -->
                                    <img src="/resources/mypage/img/crown.png" class="mypage-crown-photo"/>
                                    <!-- 모임 정보 -->
                                    <p class="mypage-group-p font-20-700">3대 500까지</p>
                                    <p class="mypage-group-p">서울시 강남구 20시</p>
                                    <p class="mypage-group-p">4/10 참가중</p>
                                </div>
                                <!-- 채팅, 나가기, 수정 이미지 -->
                                <div class="mypage-group-func-wrapper">
                                    <div><img src="/resources/mypage/img/edit.png" class="mypage-group-edit-photo"/></div>
                                    <button class="mypage-group-func-btn mypage-blue-btn" onClick="change_end();"> 
                                        <span>채팅</span>
                                    </button>
                                    <button class="mypage-group-func-btn mypage-gray-btn"> 
                                        <span>나가기</span>
                                    </button>			                        	                        		                        
                                </div>
                        </div>
                        
                        <!-- 진행중인 모임 박스 (반복) -->
                        <div class="mypage-group-ing font-14-200">
                                <img src="/resources/mypage/img/sport.png" class="mypage-group-ing-photo"/>
                                <div class="mypage-group-info">
                                    <img src="/resources/mypage/img/crown.png" class="mypage-crown-photo mypage-hidden-class"/>
                                    <p class="mypage-group-p font-20-700">3대 500까지</p>
                                    <p class="mypage-group-p">서울시 강남구 20시</p>
                                    <p class="mypage-group-p">4/10 참가중</p>
                                </div> 
                                <div class="mypage-group-func-wrapper">
                                    <div><img src="/resources/mypage/img/edit.png" class="mypage-group-edit-photo mypage-hidden-class "/></div>
                                    <button class="mypage-group-func-btn mypage-blue-btn" onClick="change_end();"> 
                                        <span>채팅</span>
                                    </button>
                                    <button class="mypage-group-func-btn mypage-gray-btn"> 
                                        <span>나가기</span>
                                    </button>			                        	                        		                        
                                </div>                  		
                        </div>
                        
                        <!-- 종료된 모임 박스 -->
                        <div class="mypage-group-end font-14-200">
                            <!-- 종료된 모임의 대문 사진 -->
                            <img src="/resources/mypage/img/sport.png" class="mypage-group-end-photo"/>
                            <div class="mypage-group-info">
                                <img src="/resources/mypage/img/crown.png" class="mypage-crown-photo"/>
                                <img src="/resources/mypage/img/closed.png" class="mypage-closed-photo"/>
                                <p class="mypage-group-p font-20-700">종료된 모임입니다!!!</p>
                                <p class="mypage-group-p">서울시 강남구 20시</p>
                                <p class="mypage-group-p">4/10 참가중</p>
                            </div>
                            <!-- 종료된 모임은 채팅, 나가기 버튼에 hidden 속성 적용 -->
                            <div class="mypage-group-func-wrapper">
                                <div><img src="/resources/mypage/img/edit.png" class="mypage-group-edit-photo mypage-hidden-class"/></div>
                                <button class="mypage-group-func-btn mypage-blue-btn mypage-hidden-class" onClick="change_end();"> 
                                    <span>채팅</span>
                                </button>
                                <button class="mypage-group-func-btn mypage-gray-btn mypage-hidden-class" onClick="change_end();"> 
                                    <span>나가기</span>
                                </button>			                        	                        		                        
                            </div>
                        </div>   
                        
                        <!-- 진행중인 모임 박스 (반복) -->
                        <div class="mypage-group-ing font-14-200">
                                <img src="/resources/mypage/img/sport.png" class="mypage-group-ing-photo"/>
                                <div class="mypage-group-info">
                                    <img src="/resources/mypage/img/crown.png" class="mypage-crown-photo"/>
                                    <p class="mypage-group-p font-20-700">3대 500까지</p>
                                    <p class="mypage-group-p">서울시 강남구 20시</p>
                                    <p class="mypage-group-p">4/10 참가중</p>
                                </div>
                                <div class="mypage-group-func-wrapper">
                                    <div><img src="/resources/mypage/img/edit.png" class="mypage-group-edit-photo"/></div>
                                    <button class="mypage-group-func-btn mypage-blue-btn"> 
                                        <span>채팅</span>
                                    </button>
                                    <button class="mypage-group-func-btn mypage-gray-btn"> 
                                        <span>나가기</span>
                                    </button>			                        	                        		                        
                                </div>
                        </div>
                                        
                        <!-- 종료된 모임 박스 (반복) -->
                        <div class="mypage-group-end font-14-200">
                            <img src="/resources/mypage/img/sport.png" class="mypage-group-end-photo"/>
                            <div class="mypage-group-info">
                                <img src="/resources/mypage/img/crown.png" class="mypage-crown-photo"/>
                                <img src="/resources/mypage/img/closed.png" class="mypage-closed-photo"/>
                                <p class="mypage-group-p font-20-700">종료된 모임입니다!!!</p>
                                <p class="mypage-group-p">서울시 강남구 20시</p>
                                <p class="mypage-group-p">4/10 참가중</p>
                            </div>
                            <div class="mypage-group-func-wrapper">
                                <div><img src="/resources/mypage/img/edit.png" class="mypage-group-edit-photo mypage-hidden-class"/></div>
                                <button class="mypage-group-func-btn mypage-blue-btn mypage-hidden-class" onClick="change_end();"> 
                                    <span>채팅</span>
                                </button>
                                <button class="mypage-group-func-btn mypage-gray-btn mypage-hidden-class" onClick="change_end();"> 
                                    <span>나가기</span>
                                </button>			                        	                        		                        
                            </div>
                        </div>    
                                        
                    </div> 
                </div>
            </div>
        </section>

        <!-- 하단 Footer -->
 		<%@include file="../include/footer.jsp" %>
    </div>
    
    <!-- 마이페이지 그룹화면 자바스크립트 -->
    <script src="/resources/mypage/js/mypage_group.js"></script>

    <!-- 마이페이지 LEFT SIDE 메뉴 자바스크립트 -->
    <script src="/resources/include/js/mypage_left_side.js"></script>

    <!-- 메인-Header 자바스크립트 (공통) -->
    <script src="/resources/include/js/main_header.js"></script>

    <!-- sweetalert2 자바스크립트 -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>