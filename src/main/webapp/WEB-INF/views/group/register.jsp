<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임 개설하기</title>
    
    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>

   <!-- jQuery 슬릭 라이브러리 -->
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
    
    <!-- sweetalert2  -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
        rel="stylesheet">
    
    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <!-- CSS : main 전체 페이지 설정 -->
    <link rel="stylesheet" href="/resources/common/css/main_page.css">
    <link rel="stylesheet" href="/resources/common/css/main_mediatag.css">   
    
    <!-- CSS : main_header 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_header.css">
    <!-- CSS : main_footer 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_footer.css">
    <!-- CSS : main_side 설정 -->
    <link rel="stylesheet" href="/resources/include/css/side_profile.css">

    <!-- CSS : 개별 페이지 설정 -->
    <link rel="stylesheet" href="/resources/group/css/group_open.css">

</head>
<body>

    <div class="page">
    
		<%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>
        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %>

            <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="s_box_board">
                    
                        <div class="make_group">
                            <div class="make_group_head"><br>모임개설</div>
                            <hr> 
    
                            <form class="make_group_form"
                                action="/group/register" method="post">
                                
                            <!-- 폼 왼쪽 배치 (모임 이미지&첨부) -->
                            
                            <div class="make_group_left">
                                
                                <div class="form_section">                               	
                                    <div class="form_section_content">
                                        <input type="file" id="input_file" name="uploadFile" style="height:30px;">

                                        <div id="uploadResult">
                                        
                                            
                                        </div>
                                                                                    

                                        <label class="input_file_button" for="input_file">
                                            이미지 찾기
                                        </label>
                    			    </div>                                   
                                </div>
                            </div>

                            <input type="hidden" name="member_count" value="1">
                            
                            <!-- 임시유저번호  -->
                            <input type="hidden" name="user_no" value="2">
                            <!-- 좋아요 수 -->
                            <!-- <input type="hidden" name="group_like" value="30"> -->

                            <!-- 폼 오른쪽 배치 (모임이미지 제외한 폼) -->
                            <div class="make_group_right">
    
                            <!-- 모임명 -->
                            
                            <label for="group_name">&nbsp;&nbsp;모임명&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <input type="text" class="make_group_name" autofocus="1" name="group_name" required>
                            <br><br>
                            <br>
                            <!-- 운동종목 -->
                                <div class="make_group_sport">
                                    운동 종목&nbsp;&nbsp;
                                    <select id="select_group_sport" name="sports" required>
                                        <option value="">운동을 선택하세요</option>
                                        <option value="헬스/크로스핏">헬스/크로스핏</option>
                                        <option value="요가/필라테스">요가/필라테스</option>
                                        <option value="등산">등산</option>
                                        <option value="런닝">런닝</option>
                                        <option value="싸이클">싸이클</option>
                                        <option value="축구/풋살">축구/풋살</option>
                                        <option value="농구">농구</option>
                                        <option value="야구">야구</option>
                                        <option value="테니스">테니스</option>
                                        <option value="배드민턴">배드민턴</option>
                                        <option value="기타" id="sports_add">기타</option>
                                    </select>
                                    
                                    <input type="text" id="sports_add_text">
                                </div><br><br>
    
                                <!-- 모집 성별 -->
                                <div class="make_group_gender">
                                    모집 성별&nbsp;&nbsp;
                                    <select class="select_group_gender" name="gender" required>
                                        <option value="">성별</option>
                                        <option value="남성">남성</option>
                                        <option value="여성">여성</option>
                                        <option value="상관없음">상관없음</option>
                                    </select>
                                </div><br><br>
    
                                <!-- 지역 required -->
                                <!-- <input type="hidden" name="group_loc" id="loc" > -->
                                <div class="make_group_address">
                                    &nbsp;&nbsp;&nbsp;&nbsp;지역&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <select name="addressRegion" id="addressRegion" required></select>
                                    <select name="group_loc" id="addressDo" required></select>
                                    <select name="group_loc" id="addressSiGunGu" required></select>
                                    
                                </div><br><br>
                                <!-- 최대인원 required  -->
                                <div class="make_group_num">
                                    최대 인원&nbsp;&nbsp;
                                    <input type="number" min="2" name="member_max" required>
                                </div><br><br>
    
                                <!-- 일정(선택)-->
                                <div class="make_group_schedule" >
                                    &nbsp;&nbsp;&nbsp;&nbsp;일정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="datetime-local" name="schedule" id="currentDatetime">
                                
                                    
                                    <!-- <input type="date" name="schedule">
                                    <input type="time" name="schedule"> -->
    
                                </div><br><br>
    
                                <!-- 해시태그(선택) / 첫번째칸에는 운동종목 자동입력 -->
                                <div class="make_group_hash">
                                    해시태그&nbsp;&nbsp;
                                    #<input type="text" id="input_hash1" placeholder="최대8자" name="hashtag1">
                                    #<input type="text" id="input_hash2" placeholder="최대8자" name="hashtag2">
                                    #<input type="text" id="input_hash3" placeholder="최대8자" name="hashtag3">
                            
                                </div><br><br>
    
                                <!-- 모임소개 required -->
                                <div class="make_group_intro">
                                    모임 소개&nbsp;&nbsp;<br>
                                    <textarea cols="30" rows="5" name="info" required></textarea>
                                </div>
    
    
                                <!-- 생성하기 -->
                            <!-- 1페이지로 돌아갈 페이징 값 전송 -->
                            <input type='hidden' name="currPage" value='1'>

                                <input type="submit" class="make_group_submit" value="생성">
                                <button type="button" class="make_group_cancel" >취소</button>
    
    
                                
                                </div>
                            </form>
                        </div>
					</div>
				</div>
			</section>
		</div>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    
        <!-- 모임화면 자바스크립트 -->
        <script src="/resources/group/js/group_open_location.js"></script>
        <script src="/resources/group/js/group_open_select.js"></script>
        <script src="/resources/group/js/upload.js"></script>
    
    	<script src="/resources/include/js/main_header.js"></script>
    	   	
    
        <!-- 부트스트랩 자바스크립트 -->
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>