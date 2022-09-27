<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임 채팅</title>

    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>



    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <link rel="stylesheet" href="/resources/chat/css/chat.css">

	
    <!-- Socket JS 라이브러리  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>

    <!-- Stomp 라이브러리  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    <!-- myPage 유틸JS -->
    <script src="/resources/mypage/js/module/mypage_util_module.js"></script>

    <!-- 마이페이지 그룹화면 자바스크립트 -->
    <script src="/resources/mypage/js/mypage_group.js"></script>

    <!-- GroupChat Module  JS -->
    <script src="/resources/chat/js/group_chat_module.js"></script>

    <!-- 시간 포맷팅모듈 -->
    <script src="/resources/mypage/js/module/mypage_util_module.js"></script>
    
    <style>

    </style>


</head>


<body>

    <div class="outline-chat" >

        <div class="view-chat-01">
            <div class="view-chat-01-header">
                <div class="group-name font-16-700">
                    <!-- 그룹명 입력 -->
                </div>

                <div class="group-gropdown">
                    <div class="dropdown font-12-500">
                        <button class="dropbtn"> 
                            <span class="dropbtn_icon">모임정보</span>
                        </button>
                        <div class="dropdown-content group-content">
                            <div class="group-info-div">


                                <!-- 이 그룹에 대한 정보 -->


                            </div>
                        </div>
                    </div>

                    <div class="dropdown font-12-500">
                        <button class="dropbtn"> 
                            <span class="dropbtn_icon">모임멤버</span>
                        </button>
                        <div class="dropdown-content group-member-list">
                            <!-- 그룹 Members -->
                        </div>
                    </div>

                    <div class="dropdown font-12-500">
                        <button class="dropbtn"> 
                            <span class="dropbtn_icon">모임목록</span>
                        </button>
                        <div class="dropdown-content">
                            <c:forEach var="group" items="${groups}">
                                <a href="/group/chat/?group_no=${group.group_no}" class="font-12-500"><c:out value="${group.group_name}"/></a>
                            </c:forEach>
                        </div>
                    </div>
                </div>


                </div>

                <div class="view-chat-01-notice">
                <div class="chat-notice chat-top">
                    <span class="font-14-500">공지사항 입력란</span>
                    <span class="font-12-500">공지사항 시간</span>
                </div>
                <div class="notice-hidden">
                    <div class="chat-notice">
                        <span class="font-14-500">공지사항 입력란</span>
                        <span class="font-12-500">공지사항 시간</span>
                    </div>
                    <div class="chat-notice">
                        <span class="font-14-500">공지사항 입력란</span>
                        <span class="font-12-500">공지사항 시간</span>
                    </div>
                    <div class="chat-notice">
                        <span class="font-14-500">공지사항 입력란</span>
                        <span class="font-12-500">공지사항 시간</span>
                    </div>
                </div>



            </div>

            <div class="chatting-view">
                <!-- 채팅이 입력되는 공간 -->
            </div>

            <div class="chatting-submit">
                <textarea class="chat-textarea" placeholder="채팅을 입력하세요."></textarea>
                <button type="button" class="chat-textarea-btn font-16-500">전송</button>
            </div>

        </div>

        <div class="view-chat-02">

        </div>
    </div>

    <!-- Session으로부터, UESR 정보 얻기... -->

    <%@ page import = "org.zerock.momofit.domain.signUp.*" %>

    <% 
    UserDTO myDto = (UserDTO) session.getAttribute("userDTO");

    Integer user_no = myDto.getUser_no();
    String id = myDto.getId();

    String nickname = myDto.getNickname();
    String email = myDto.getEmail();
    String user_name = myDto.getUser_name();

    %>

    <script>

        var user_no = '<%= user_no %>'
        var socket = null;
        var group_no = '${param.group_no}';
        var admin_user_no = null;
        var admin_user_nickname = null;

        connectStomp();

        function connectStomp(){
        
            // endPoint : 접속URL
            var sock = new SockJS("/myGroupStomp");

            // Stomp에게 서로 연결 된 Pipe을 알려준다.
            var client = Stomp.over(sock);

            // Stomp가 연결되었는지...
            isStomp = true;

            socket = client;
            
            client.connect({}, function() {
                console.log("Connected StompTest!!!....");

                client.subscribe('/topic/' + group_no, function(event){
                    console.log("!!!!event >>>", event);

                    // 전송 온 Message(JSON 변환하여, property값 획득)
                    var chat_text = JSON.parse(event.body).message;
                    var chat_user_nickname = JSON.parse(event.body).nickname;
                    var chat_user_no = JSON.parse(event.body).user_no;
                    var currentTime = new Date();
                    var formatTime = mypageUtilService.timeFormat(currentTime);
                    
                    var str = "";

                    str += `<div class="chat-div">`;
                    
                    // 내가 쓴 채팅 : 오른쪽 정렬 출력!!
                    // 다른 사람이 쓴 채팅 : 왼쪽 정렬 출력!!
                    if(chat_user_no == user_no){
                        str += `<div class="chat-right">`;
                    } else {
                        str += `<div class="chat-left">`;
                    }

                    str +=  `<div class="chat-content-1"><img src="/resources/chat/img/profile-7794504.png"></div>
                            <div class="chat-content-2">
                                <div class="chat-info font-12-400"><span class="chat-id">\${chat_user_nickname}</span><span class="chat-time">\${formatTime}</span></div>
                                <div class="chat-text">\${chat_text}</div>
                            </div>                
                        </div></div>`

                    $('.chatting-view').append(str);

                    $(".chatting-view").scrollTop($(".chatting-view")[0].scrollHeight);
                }) // subscribe

            }) // connect

        } // connectStomp

        $(".chat-textarea-btn").on('click', function(event){

            if(isStomp){
                event.preventDefault();

                if(!isStomp && socket.readyState != 1) return;

                var msg = $(".chat-textarea").val();
                $(".chat-textarea").val("");

                socket.send('/chat/'+group_no, {}, msg);
            }

        })


        $(function () {

            showGroupInfo(group_no);

            function showGroupInfo(group_no){
                
                groupChatService.getGroupInfo(group_no, function(data){

                    var listVO = data.list;         // 멤버정보
                    console.log("참여자정보 :",listVO);
                    var group_name = data.group_name;
                    var sports = data.sports;
                    var group_loc = data.group_loc;
                    var schedule = data.schedule;
                    var format_schedule = mypageUtilService.timeFormat(schedule);

                    var info = data.info;
                    
                    // 그룹장 (전역변수로 저장)
                    admin_user_no = data.user_no;
                    // 그룹장 닉네임 (처음 index는 항상 방장!)
                    admin_user_nickname = listVO[0].nickname;

                    var group_img = data.group_img;
                    var path_img = data.path_img;
                    var temp = data.path;

                    var groupNameStr = "";

                    groupNameStr += `<span>&nbsp;#\${group_name}</span>`
                    $(".group-name").html(groupNameStr);


                    // 그룹 정보
                    var groupInfoStr = "";

                    // 그룹에 속한 사용자
                    var groupMembersStr = "";
                    
                    groupInfoStr += `<div class="group-info-img">`

                    if(group_img){

                    } else {
                        groupInfoStr += `<img src="/resources/chat/img/모임농구이미지.jpg">`
                    }
                    
                    groupInfoStr += 

                    `</div>
                    <div class="gi-text">
                        <div class="font-14-500">모임 소개</div>
                        <span class="font-12-500">\${info}</span>
                    </div>

                    <div class="gi-content">
                        <div class="font-14-500">모임장</div>
                        <span class="font-12-500">\${admin_user_nickname}</span>
                    </div>

                    <div class="gi-content">
                        <div class="font-14-500">모임 종목</div>
                        <span class="font-12-500">\${sports}</span>
                    </div>

                    <div class="gi-content">
                        <div class="font-14-500">스케쥴</div>
                        <span class="font-12-500">\${format_schedule}</span>
                    </div>

                    <div class="gi-content">
                        <div class="font-14-500">참여자 정보</div>

                        <div class="group-member">`;

                    
                    for(var i=0, len =listVO.length; i<len; i++){

                        var nickname = listVO[i].nickname;
                        var group_user_no = listVO[i].group_user_no;

                        groupInfoStr += `<span class="font-12-500">\${nickname}</span>`

                        if(group_user_no == user_no){
                            groupMembersStr += `<span class="font-12-500">\${nickname} (나)</span>`
                        } else {
                            groupMembersStr += `<span class="font-12-500">\${nickname} </span>`
                        }

                    } // for

                    groupInfoStr +=  `</div></div>`;

                    var groupInfoBox = $('.group-info-div');
                    groupInfoBox.html(groupInfoStr);

                    var groupMembersBox = $(".group-member-list");
                    groupMembersBox.html(groupMembersStr);

                }) // groupChatService.getGroupInfo
                

            } // showGroupInfo


        }) // Entry-Point


    </script>



    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>