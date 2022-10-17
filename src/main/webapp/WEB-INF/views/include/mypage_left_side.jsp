<div class="s_box_side">
    <div class="side_top">
        <div class="side_title">
            <span class="font-28-700">마이페이지</span>
        </div>
        <div class="side_profil">
            <!-- 사이드프로필-이미지 -->
            <div class="wrap_side_profil_img">
                <div class="side_profil_img_box">
                    <!-- 여기에 이미지삽입 -->
                    <c:choose>
                        <c:when test="${sessionScope.__USER__.isEmptyProfileImg()}">
                            <img src="/resources/mypage/img/profile.png">
                        </c:when>

                        <c:otherwise>
                            <img src="/display?fileName=${sessionScope.__USER__.profile_path}/${sessionScope.__USER__.profile_temp}_${sessionScope.__USER__.profile_name}">
                        </c:otherwise>
                    </c:choose>
                    
                </div>
            </div>
            <!-- 사이드프로필-닉네임변경 -->
            <div class="wrap_side_profil_nickname">
                <!-- 한줄소개 form 태그 -->
                <form action="#">
                    <label for="btn_edit_nickname"><img src="/resources/include/img/edit_btn_comment.png"><span class="font-16-500">닉네임</span></label>
                    <button type="button" id="btn_edit_nickname"></button>
                    <div>
                        <textarea name="" rows="1" disabled maxlength="10" class="side_profil_nickname font-16-500"><c:out value="${sessionScope.__USER__.nickname}"/></textarea>
                    </div>
                </form>
            </div>
            
            <!-- 사이드프로필-주소 -->
            <div class="wrap_side_profil_address">
                <c:choose>
                    <c:when test="${not empty sessionScope.__USER__.user_loc}">
                        <div class="box_check_btn">
                            <img src="/resources/include/img/checked_btn.png">
                        </div>
                        <div class="side_profil_address font-16-500"><c:out value="${sessionScope.__USER__.user_loc}"/></div>
                    </c:when>

                    <c:otherwise>
                        <div class="wrap_side_profil_certify">
                            <button class="btn_certify"><a href="/mypage/check_pw" class="font-16-500">주소 인증</a></button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            


            <!-- 사이드프로필-취미 -->
            <div class="wrap_side_profil_hobby">
                <ul>
                    <c:choose>
                        <c:when test="${not empty sessionScope.__USER__.sports1}">
                            <li class="font-14-500"><c:out value="${sessionScope.__USER__.sports1}"/></li>
                            <li class="font-14-500"><c:out value="${sessionScope.__USER__.sports2}"/></li>
                            <li class="font-14-500"><c:out value="${sessionScope.__USER__.sports3}"/></li>
                        </c:when>

                        <c:otherwise>
                            <li class="font-14-500">취미를 등록해주세요.</li>
                        </c:otherwise>
                    </c:choose>

                </ul>
            </div>
            <!-- 사이드프로필-한줄소개 -->
            <div class="wrap_side_profil_comment">
                <!-- 한줄소개 form 태그 -->
                <form action="#">
                    <label for="btn_edit_comment"><img src="/resources/include/img/edit_btn_comment.png"><span class="font-16-500">한줄소개</span></label>
                    <button type="button" id="btn_edit_comment"></button>
                    <c:choose>
                        <c:when test="${not empty sessionScope.__USER__.profile_info}">
                            <textarea name="" rows="1" disabled class="side_profil_comment font-14-400"><c:out value="${sessionScope.__USER__.profile_info}"/></textarea>
                        </c:when>

                        <c:otherwise>
                            <span class="font-14-400">한줄 소개를 등록해주세요.</span>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </div>
    
    <ul class="side_manu">
        <li class="manu_btn">
            <a href="/mypage/my_group" class="link1 font-16-500">&nbsp;나의모임
                <span class="space-2">>&nbsp;</span>
            </a>
        </li>

        <li class="manu_btn">
            <a href="/mypage/check_pw" class="link1 font-16-500">&nbsp;기본정보수정
                <span class="space-2">>&nbsp;</span>
            </a>
        </li>

        <li class="manu_btn">
            <a href="/mypage/board_list" class="link1 font-16-500">&nbsp;내 글
                <span class="space-2">>&nbsp;</span>
            </a>
        </li>

        <li class="manu_btn">
            <a href="/mypage/comment_list" class="link1 font-16-500">&nbsp;내 댓글
                <span class="space-2">>&nbsp;</span>
            </a>
        </li>

        <li class="manu_btn">
            <a href="/mypage/csboard_list" class="link1 font-16-500">
                &nbsp;문의 / 신고내역
            <span class="space-2">>&nbsp;</span></a>
        </li>
    </ul>
</div>
