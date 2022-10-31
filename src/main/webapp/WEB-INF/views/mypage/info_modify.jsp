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
    <!-- CSS : 마이페이지 개인정보수정 CSS -->
    <link rel="stylesheet" href="/resources/mypage/css/mypage_info_modify.css">

    <!-- RSA 암호화 라이브러리 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jsencrypt/3.2.1/jsencrypt.min.js" integrity="sha512-hI8jEOQLtyzkIiWVygLAcKPradIhgXQUl8I3lk2FUmZ8sZNbSSdHHrWo5mrmsW1Aex+oFZ+UUK7EJTVwyjiFLA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

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
                        <div class="name1 font-24-700"><span class="font-24-700">개인정보수정</span></div>
                        <div class="mypage-border"></div><br>
    
                        <div class="s_box_content2">
                            <div class="name2 font-22-700">기본정보</div>
                            <%@ page import = "org.zerock.momofit.domain.signIn.*" %>
                            <%@ page import = "java.util.Date" %>
                            <%
                                LoginVO loginUserVO = (LoginVO) session.getAttribute("__USER__");

  
                                Date userDate = loginUserVO.getBirth();

                                String userGender = loginUserVO.getGender();

                                String userEmail= loginUserVO.getEmail();
                                String[] emailSplit = userEmail.split(",");

                                String emailID = emailSplit[0];
                                String emailDomain = emailSplit[1];
                                
                                String userLocation = loginUserVO.getUser_loc();

                                String user_profile_name = loginUserVO.getProfile_name();
                                String user_profile_temp = loginUserVO.getProfile_temp();
                                String user_profile_path = loginUserVO.getProfile_path();

                                boolean isEmptyProfileImg = loginUserVO.isEmptyProfileImg();
                    
                            %>
                            <c:set var="userGender" value="<%= userGender %>"/>
                            <c:set var="emailID" value="<%= emailID %>"/>
                            <c:set var="emailDomain" value="<%= emailDomain %>"/>
                            <c:set var="userDate" value="<%= userDate %>"/>
                            <c:set var="userLocation" value="<%= userLocation %>"/>

                            <c:set var="profile_name" value="<%= user_profile_name %>"/>
                            <c:set var="profile_temp" value="<%= user_profile_temp %>"/>
                            <c:set var="profile_path" value="<%= user_profile_path %>"/>
    
                            <form action="/mypage/info_modify" method="post" enctype="multipart/form-data">
                                <table class="table table-boardered">
                                    <tr name3 class="name3 font-14-400">
                                        <th>아이디</th>
                                        <td><input type="text" class="form-control" name="id" id="id" placeholder="id" readonly></td>
                                        </td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>닉네임</th>
                                        <td><input type="text" class="form-control" name="nickname" id="nick_name"placeholder="id" value="<c:out value='${sessionScope.__USER__.nickname}'/>"></td>
                                        <td><input type="button" value="중복확인" class="btn btn-primary" id="nickBtn" /></td>
                                    </tr>

                                    <tr>
                                        <th></th>
                                        <td><span id="nickNameResult" class="font-12-400"></span></td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>비밀번호</th>
                                        <td><input type="password" class="form-control pass1" name="pass1" placeholder="비밀번호">
                                            <input type="hidden" name="pass" id="encrypted-pass">
                                        </td>
                                    </tr>


    
                                    <tr name3 class="name3 font-14-400">
                                        <th>비밀번호 확인</th>
                                        <td><input type="password" class="form-control pass2" name="pass2" placeholder="비밀번호 확인">
                                        </td>
                                    </tr>

                                    <tr class="pwCheck">
                                        <th></th>
                                        <td><span id="pwCheck" class="font-12-400"></span></td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>이름</th>
                                        <td><input type="text" class="form-control" name="user_name" value="<c:out value='${sessionScope.__USER__.user_name}'/>"></td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>전화번호</th>
                                        <td><input type="tel" class="form-control" name="tel" placeholder="-없이 숫자만 입력" maxlength="11" oninput="autoHypenPhone(this)" value="<c:out value='${sessionScope.__USER__.tel}'/>">
                                        </td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>성별</th>
                                        <td>
                                            <select name="gender" class="form-control">
                                                <option value="">성별</option>
                                                <option value="m" <c:if test="${userGender == 'm'}">selected</c:if>>남자</option>
                                                <option value="f" <c:if test="${userGender == 'f'}">selected</c:if>>여자</option>
                                            </select>
                                        </td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>생년월일</th>
                                        <td>
                                            <input type="date" name="birth" class="form-control" value="<c:out value='${userDate}' />">
                                        </td>
                                    </tr>
                                    
                                    <tr name3 class="name3 font-14-400">
                                        <th>이메일</th>
                                        <td>
                                            <input class="form-control-sm emailbox" name="email1" type="text" value="<c:out value='${emailID}'/>" readonly> @ <input
                                                class="form-control-sm emailbox" name="email2" type="text" value="<c:out value='${emailDomain}'/>" readonly>
                                        </td>
                                    </tr>
    
                                    <tr>
                                        <td colspan="3">
                                            <hr>
                                            <div class="name2 font-22-700" style="text-align: center;">추가정보</div>
                                        </td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>지역</th>
                                        <td><input type="search" class="form-control" id="loc" name="user_loc" placeholder="지역" value="<c:out value='${userLocation}'/>"></td>
                                        <td><button type="button" class="btn btn-primary btn-primary3">지역검색</button></td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>관심운동</th>
                                        <td>
                                            <input type="checkbox" name="hobby" value="헬스/크로스핏" id="label1" onclick="CountChecked(this)"><label for="label1">헬스/크로스핏&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="등산" id="label2" onclick="CountChecked(this)"><label for="label2">등산&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="런닝" id="label3" onclick="CountChecked(this)"><label for="label3">런닝&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="싸이클" id="label4" onclick="CountChecked(this)"><label for="label4">싸이클&nbsp;</label> <br>
                                            <input type="checkbox" name="hobby" value="축구/풋살" id="label5" onclick="CountChecked(this)"><label for="label5">축구/풋살&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="농구" id="label6" onclick="CountChecked(this)"><label for="label6">농구&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="야구" id="label7" onclick="CountChecked(this)"><label for="label7">야구&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="테니스" id="label8" onclick="CountChecked(this)"><label for="label8">테니스&nbsp;</label>
                                            <input type="checkbox" name="hobby" value="배드민턴" id="label9" onclick="CountChecked(this)"><label for="label9">배드민턴 &nbsp;</label>
                                        </td>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <div class="profile_wrap">
                                            <th>프로필 사진</th>
                                            <td>
                                                <input type="hidden" name="profile_temp" class="hidden-user-img" value="<c:out value='${profile_temp}'/>">
                                                <input type="hidden" name="profile_path" class="hidden-user-img" value="<c:out value='${profile_path}'/>">
                                                <input type="hidden" name="profile_name" class="hidden-user-img" value="<c:out value='${profile_name}'/>">
                                                <c:choose>
                                                    <c:when test="${sessionScope.__USER__.isEmptyProfileImg()}">
                                                        <div class="profile-img-box">
                                                            <img src="/resources/mypage/img/profile.png" class="profile">
                                                        </div>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <div class="profile-img-box">
                                                            <img src="/display?fileName=${profile_path}/${profile_temp}_${profile_name}" class="profile">
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                                <br>
                                                <label class="btn btn-sm" for="file" id="fileUp">추가</label>
                                                <input type="file" id="file" name="file" style="display: none;" accept="image/*" >  
                                                <button type="button" class="btn btn-secondary btn-sm" id="currImg">기본</button>   
                                            </td>
                                        </div>
                                    </tr>
    
                                    <tr name3 class="name3 font-14-400">
                                        <th>한줄 소개</th>
                                        <td><input type="text" class="form-control" name="profile_info" placeholder="(선택)" value="<c:out value='${__USER__.profile_info}'/>">
                                        </td>
                                    </tr>
    
                                </table>
    
                                <div>
                                    <button type="button" class="sign_up">수정하기</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <!-- 하단 Footer -->
 		<%@include file="../include/footer.jsp" %>
    </div>


    <script>
        var myUserNickname = "<c:out value='${sessionScope.__USER__.nickname}'/>";
        var myUserSports1 = "<c:out value='${sessionScope.__USER__.sports1}'/>";
        var myUserSports2 = "<c:out value='${sessionScope.__USER__.sports2}'/>";
        var myUserSports3 = "<c:out value='${sessionScope.__USER__.sports3}'/>";

        var serverPublicKey = "<c:out value='${__PUBLIC_KEY__}'/>";

        // 이미지 - 기본버튼클릭
        $("#currImg").on('click', function (){
            // hidden태그 - 기존 이미지 정보 삭제
            $(".hidden-user-img").val('');

            // 이미지 박스 이미지 변경
            $(".profile").attr('src','/resources/mypage/img/profile.png');
        })

         // 게시글 이미지 바로 수정
        $("#file").on('change', function(){
            readURL(this);
            $(".hidden-user-img").val('');
        });


        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                $(".profile").attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }






    </script>
    
    <!-- 팝업 open -->
    <script src="/resources/signUp/js/mapPopup.js"></script>

    <!-- 마이페이지 개인정보수정 자바스크립트 -->
    <script src="/resources/mypage/js/mypage_info_modify.js"></script>

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