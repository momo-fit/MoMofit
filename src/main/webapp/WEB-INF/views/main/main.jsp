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

    <!-- 메인페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/main/css/main_section_card.css">
    <link rel="stylesheet" href="/resources/main/css/main_section_board.css">

    <!-- CSS : 사이드 프로필 -->
    <link rel="stylesheet" href="/resources/include/css/side_profile.css">

</head>

<body>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %>

        <!-- 사이드 프로필 -->
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>         

        <!-- 메인 컨텐츠부 -->
        <section>
            <div class="section_inner">
                    <div class="section_banner">
                        <!-- <div></div> -->
                        <div class="wrap_banner_img">
                            <div class="box_banner_img"><div><img src="/resources/main/img/banner_tmp_img_1.jpg" alt=""></div></div>
                            <div class="box_banner_img"><div><img src="/resources/main/img/banner_tmp_img_2.jpg" alt=""></div></div>
                        </div>
                        <!-- <div></div> -->
                    </div>

    
                <!-- Best모임 -->
                <div class="section_group">
                    <div class="group_title">
                        <a href="/group/list" class="font-26-500">Best 모임</a>
                    </div>
    
                    <div class="wrap_group">
                        <div class="wrap_group_inner">
                            
                            <c:forEach var="bestGroup" items="${BestGroupList}">
                                <div class="group_box">
                                    <a href="/group/detail/?group_no=${bestGroup.group_no}">
                                        <div class="group_box_inner">
                                            <c:if test="${bestGroup.group_img != null}">
                                                <img src="/display?fileName=${bestGroup.path}/${bestGroup.temp}_${bestGroup.group_img}" class="group_img">
                                            </c:if> 
                                            <c:if test="${bestGroup.group_img == null}">
                                                <img src="/resources/group/img/basket.jpg" class="group_img">
                                            </c:if>
                                        </div>
                                        <div class="group_box_title">
                                            <span class="font-16-700">${bestGroup.group_name}</span>
                                        </div>
                                        <div class="group_box_text">
                                            <span class="font-12-500">${bestGroup.info}</span>
                                        </div>
                                        <div class="group_box_loc">
                                            <span class="font-12-400">${bestGroup.group_loc}</span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>      

                        </div>
                    </div>
                </div>
    
                <!-- 오늘의 모임 -->
                <div class="section_group section_latest_group">
                    <div class="group_title">
                        <a href="/group/list" class="font-26-500">오늘의 모임</a>
                    </div>
    
                    <div class="wrap_group">
                        <div class="wrap_group_inner">
                            
                            <c:forEach var="newGroup" items="${NewGroupList}">
                                <div class="group_box">
                                    <a href="/group/detail/?group_no=${newGroup.group_no}">
                                        <div class="group_box_inner">
                                            <c:if test="${newGroup.group_img != null}">
                                                <img src="/display?fileName=${newGroup.path}/${newGroup.temp}_${newGroup.group_img}" class="group_img">
                                            </c:if> 
                                            <c:if test="${newGroup.group_img == null}">
                                                <img src="/resources/group/img/basket.jpg" class="group_img">
                                            </c:if>
                                        </div>
                                        <div class="group_box_title">
                                            <span class="font-16-700">${newGroup.group_name}</span>
                                        </div>
                                        <div class="group_box_text">
                                            <span class="font-12-500">${newGroup.info}</span>
                                        </div>
                                        <div class="group_box_loc">
                                            <span class="font-12-400">${newGroup.group_loc}</span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach> 

                        </div>
                    </div>
                </div>
    
                <div class="section_wrap">
                    
                    <!-- 메인화면 게시판 영역 -->
                    <div class="wrap_main_board">
                        <div class="main_board_title font-26-500">커뮤니티 BEST글</div>
                        <!-- 자유게시판 BEST -->
                        <div class="main_board">
                            <a href="/board/free/list"><div class="board_title font-20-500">자유게시판</div></a>
                            <div class="board_content">
                                <table class="main_board_table font-14-500">
                                    <thead class="main_board_thead">
                                        <tr>
                                            <th>제목</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody class="main_board_tbody">
                                        <c:forEach var="freeList" items="${BestFreeBoard}">
                                            <tr class="tr-border">
                                                <td class="table-title">
                                                    <div>
                                                        <a href="board/free/view?board_no=${freeList.board_no}"><div class="title-content font-14-400">${freeList.title}</div></a>
                                                        <div class="comm-count font-12-400">${freeList.comm_cnt}</div>
                                                    </div>
                                                </td>
                                                <td class=font-14-400>${freeList.board_date}</td>
                                            </tr>   
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <!-- 운동Tip BEST 게시판 -->
                        <div class="main_board">
                            <a href="/board/tip/list"><div class="board_title font-20-500">운동Tip</div></a>
                            <div class="board_content">
                                <table class="main_board_table font-14-500">
                                    <thead class="main_board_thead">
                                        <tr>
                                            <th>제목</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>

                                    <tbody class="main_board_tbody">
                                        
                                        <c:forEach var="tipList" items="${BestTipBoard}">
                                            <tr class="tr-border">
                                                <td class="table-title">
                                                    <div>
                                                        <a href="/board/tip/view?board_no=${tipList.board_no}"><div class="title-content font-14-400">${tipList.title}</div></a>
                                                        <div class="comm-count font-12-400">${tipList.comm_cnt}</div>
                                                    </div>
                                                </td>
                                                <td class="font-14-400">${tipList.board_date}</td>
                                            </tr>
                                        </c:forEach>
                                      
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- 운동인증 BEST 게시판 -->
                        <div class="main_board">
                            <a href="/board/pic/list"><div class="board_title font-20-500">운동인증</div></a>
                            <div class="board_content">
                                <table class="main_board_table font-14-400">
                                    <thead class="main_board_thead">
                                        <tr>
                                            <th>제목</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody class="main_board_tbody">
                                            
                                        <c:forEach var="picList" items="${BestPicBoard}">
                                            <tr class="tr-border">
                                                <td class="table-title">
                                                    <div>
                                                        <a href="/board/pic/view?board_no=${picList.board_no}"><div class="title-content font-14-400">${picList.title}</div></a>
                                                        <div class="comm-count font-12-400">${picList.comm_cnt}</div>
                                                    </div>
                                                </td>
                                                <td>${picList.board_date}</td>
                                            </tr>
                                        </c:forEach>
                                                                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- 중고거래 BEST 게시판 -->
                        <div class="main_board">
                            <a href="/board/trade/list"><div class="board_title font-20-500">중고거래</div></a>
                            <div class="board_content">
                                <table class="main_board_table font-14-500">
                                    <thead class="main_board_thead">
                                        <tr>
                                            <th>제목</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody class="main_board_tbody">

                                        <c:forEach var="tradeList" items="${BestTradeBoard}">
                                            <tr class="tr-border">
                                                <td class="table-title">
                                                    <div>
                                                        <a href="board/trade/view?board_no=${tradeList.board_no}"><div class="title-content font-14-400">${tradeList.title}</div></a>
                                                        <div class="comm-count font-12-400">${tradeList.comm_cnt}</div>
                                                    </div>
                                                </td>
                                                <td class="font-14-400">${tradeList.board_date}</td>
                                            </tr>
                                        </c:forEach>
                                            
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>


   <!-- 메인화면 섹션 자바스크립트` -->
   <script src="/resources/main/js/main_section.js"></script>

   <!-- 메인화면 자바스크립트 -->
   <script src="/resources/main/js/main.js"></script>

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>