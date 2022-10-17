
<!-- CSS : main_side 설정 -->
<link rel="stylesheet" href="/resources/include/css/side_profile.css">
<link rel="stylesheet" href="/resources/include/css/side_profile_unlogin.css">
	
<!-- 구글 폰트 적용 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Gugi&family=Hi+Melody&family=Sunflower:wght@500&display=swap" rel="stylesheet">
<!-- fontawesome -->
<script src="https://kit.fontawesome.com/7d82554876.js" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/556efa47d7.js" crossorigin="anonymous"></script>
<!-- CSS : font 설정 -->
<link rel="stylesheet" href="/resources/common/css/font.css">

<!-- ======================================================================================== -->

<!-- 사이드바 버튼 -->
<input type="checkbox" id="side_profile_icon">
    <label for="side_profile_icon">
        <img src="/resources/include/img/user.png" width="50px">
    </label>

<!-- ======================================================================================== -->
<!-- *** 로그인 OK 상태 ***-->
<!-- ======================================================================================== -->
<c:if test="${sessionScope.__USER__ != null}">
    <div class="side_profile_1">

    <!-- 프로필사진 -->
    <div class="side_profile_1_person">

        <c:choose>
            <c:when test="${sessionScope.__USER__.isEmptyProfileImg()}">
                <img src="/resources/mypage/img/profile.png">
            </c:when>

            <c:otherwise>
                <img src="/display?fileName=${sessionScope.__USER__.profile_path}/${sessionScope.__USER__.profile_temp}_${sessionScope.__USER__.profile_name}">
            </c:otherwise>
        </c:choose>
        
    </div>

    <div class="side_profile_1_text">
        <!-- 닉네임 -->
        <span class="side_profile_nickname font-20-500">
            <c:out value="${sessionScope.__USER__.nickname}"/>
        </span>

        <!-- 성별 이미지로 표시 -->
        <c:if test="${sessionScope.__USER__.gender == 'f'}" >
            <img src="/resources/include/img/female-2404482.png" width="20px" alt="">
        </c:if>
        <c:if test="${sessionScope.__USER__.gender == 'm'}" >
            <img src="/resources/include/img/male-272547.png" width="20px"alt="">
        </c:if>
    
    </div><br>

    <!-- 지역 -->
    <div class="side_profile_1_loc font-18-500">
        <c:out value="${sessionScope.__USER__.user_loc}" /> 
        <br>
        <c:if test="${sessionScope.__USER__.user_loc != null}">
	        <span class="side_profile_loc_check">지역인증완료
	            <img src="/resources/include/img/check_grin.png" width="15px">
	        </span>
        </c:if>
    </div><br>

    <!-- 운동(최대3개) -->
    <div class="side_profile_1_sports">
        <ul class="font-16-400">
            <li><c:out value="${sessionScope.__USER__.sports1}"/></li>
            <li><c:out value="${sessionScope.__USER__.sports2}"/></li>
            <li><c:out value="${sessionScope.__USER__.sports3}"/></li>
        </ul>
    </div>

    <!-- 한줄 소개 -->
    <div class="side_profile_1_intro">
        <p class="font-16-700"><c:out value="${sessionScope.__USER__.profile_info}"/></p>
    </div>

    <!-- 이동 버튼 -->
    <button type="button" class="side_profile_1_mess font-16-500" onclick="location.href='/mypage/my_group'">나의 모임</button>
    <button type="button" class="side_profile_1_edit font-16-500" onclick="location.href='/mypage/check_pw'">정보 수정</button>

    </div>
</c:if>

<!-- ======================================================================================== -->
<!-- ***로그인 X 상태*** -->
<!-- ======================================================================================== -->
<c:if test="${sessionScope.__USER__ == null}">
    <div class="side_profile_2">
    <!-- 로그인하세요 & 기본이미지 -->
    <div class="side_profile_2_info">
        <img src="/resources/include/img/free-icon-warning-6897039.png" width=30px alt="">
        <p>로그인하시면 더 다양한 기능을 이용하실 수 있습니다.</p>
        <img src="/resources/include/img/profile-7794504.png" width=170px alt="">
    </div>

        <div class="login_box">
            
            <!-- <input type="text" name="login_id" id="login_id" autofocus="1" placeholder="아이디를 입력하세요."><br>
            <input type="password" name="login_pw" id="login_pw" placeholder="비밀번호를 입력하세요."><br>
            
            <div class="checkbox_autologin">

                <input type="checkbox" class="checkbox_autologin" value="1"> 자동로그인
            
            </div> -->

            <!-- 로그인 버튼 -->
            <button type="button" class="button_login" onclick="location.href='/signin'" name="button_login">
                로그인
            </button><br>

            <!-- 회원가입 버튼 -->
            <button type="button" class="button_join" onclick="location.href='/signup'" name="button_join">
                회원가입
            </button><br>
            <!-- 아이디/비번찾기 -->
            <div class="login_link">
                <a href="/find/pw" class="pw_link" >비밀번호 찾기</a>
                <span class="link_divider"> | </span>
                <a href="/find/id" class="id_link" >아이디 찾기</a>
            </div>  

        </div>
    </div>
</c:if>
