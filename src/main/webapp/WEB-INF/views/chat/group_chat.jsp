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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.js" 
    integrity="sha512-2ABKLSEpFs5+UK1Ol+CgAVuqwBCHBA0Im0w4oRCflK/n8PUVbSv5IY7WrKIxMynss9EKLVOn1HZ8U/H2ckimWg==" 
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.ui.position.js" 
    integrity="sha512-vBR2rismjmjzdH54bB2Gx+xSe/17U0iHpJ1gkyucuqlTeq+Q8zwL8aJDIfhQtnWMVbEKMzF00pmFjc9IPjzR7w==" 
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <!-- jquery-contextmenu 라이브러리 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.9.2/jquery.contextMenu.css" 
    integrity="sha512-EF5k2tHv4ShZB7zESroCVlbLaZq2n8t1i8mr32tgX0cyoHc3GfxuP7IoT8w/pD+vyoq7ye//qkFEqQao7Ofrag==" 
    crossorigin="anonymous" referrerpolicy="no-referrer" />


    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <link rel="stylesheet" href="/resources/chat/css/chat.css">

    <!-- 폰트어썸 -->
	<script src="https://kit.fontawesome.com/556efa47d7.js" crossorigin="anonymous"></script>

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

                                <div class="group-info-img">`

                                </div>

                                <div class="gi-text">
                                    <div class="font-14-500">모임 소개</div>
                                    <span class="font-12-500 group-context"></span>
                                </div>
            
                                <div class="gi-content">
                                    <div class="font-14-500">모임장</div>
                                    <span class="font-12-500 group-admin"></span>
                                </div>
            
                                <div class="gi-content">
                                    <div class="font-14-500">모임 종목</div>
                                    <span class="font-12-500 group-sports"></span>
                                </div>
            
                                <div class="gi-content">
                                    <div class="font-14-500">모임지역</div>
                                    <span class="font-12-500 group-loc"></span>
                                </div>
            
                                <div class="gi-content">
                                    <div class="font-14-500">스케쥴</div>
                                    <span class="font-12-500 group-schedule"></span>
                                </div>
            
                                <div class="gi-content">
                                    <div class="font-14-500">참여자 정보</div>
            
                                    <div class="group-member">

                                        <!-- 그룹멤버 표시 -->
                                
                                    </div>
                                </div>

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
                            <span class="dropbtn_icon">나의모임</span>
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

                </div>
                
                <div class="notice-hidden">
                    <!-- 공지사항 -->
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

    <%@ page import = "org.zerock.momofit.domain.signIn.*" %>

    <% 
        LoginVO vo = (LoginVO) session.getAttribute("__USER__");

        Integer user_no = vo.getUser_no();
        String id = vo.getId();

        String nickname = vo.getNickname();
        String email = vo.getEmail();
        String user_name = vo.getUser_name();

    %>

    <script>

        var user_no = '<%= user_no %>';
        console.log("user_no : {}", user_no);
        var nickname = '<%= nickname %>';
        var socket = null;
        var group_no = '${param.group_no}';
        var admin_user_no = null;
        var admin_user_nickname = null;
        
        var user_no_set = new Set();


        $(function () {

            showGroupInfo(group_no, 0);

        }) // Entry-Point


        //---------------------------------------------------
        // 그룹 정보 얻어오기 (그룹번호, 메시지타입, 메시지텍스트)
        //---------------------------------------------------
        function showGroupInfo(group_no, msgType, msgText){
            console.log("showGroupInfo() invoked.....")

            groupChatService.getGroupInfo(group_no, function(data){

                var listVO = data.list;         // 멤버정보
                var noticeListVO = data.list_chat_notice;

                var group_name = data.group_name;
                var sports = data.sports;
                var group_loc = data.group_loc;
                var schedule = data.schedule;

                var format_schedule = mypageUtilService.normalTimeFormat(schedule);

                var info = data.info;
                
                // 그룹장 (전역변수로 저장)
                admin_user_no = data.user_no;
                console.log("그룹장 : ",admin_user_no);

                // 그룹장 닉네임 (처음 index는 항상 방장!)
                admin_user_nickname = listVO[0].nickname;
                console.log("그룹장 닉네임 : ", admin_user_nickname);

                var group_img = data.group_img;
                var path_img = data.path_img;
                var temp = data.path;

                var groupNameStr = "";

                groupNameStr += `<span>&nbsp;#\${group_name}</span>`
                $(".group-name").html(groupNameStr);


                // Group : 이미지
                if(group_img){

                } else {
                    $(".group-info-img").html(`<img src="/resources/chat/img/모임농구이미지.jpg">`);
                }

                // Group : 그룹 소개
                $(".group-context").text(info);
                // Group : 그룹 모임장
                $(".group-admin").text(admin_user_nickname);
                // Group : 그룹 모임종목
                $(".group-sports").text(sports);
                // Group : 그룹 모임지역
                $(".group-loc").text(group_loc);
                // Group : 그룹 스케쥴
                $(".group-schedule").text(format_schedule);

                // 그룹에 속한 사용자
                var groupInfoBox = $('.group-info-div');


                var groupMembersStr = ""; 
                var groupMembersBox = $(".group-member-list");

                // Group : 참여한 모임사람 출력
                $(".group-member").html('');
                for(var i=0, len =listVO.length; i<len; i++){

                    var nickname = listVO[i].nickname;
                    var group_user_no = listVO[i].group_user_no;

                    $(".group-member").append(`<span class="font-12-500">\${nickname}</span>`);

                    if(group_user_no == user_no){
                        groupMembersStr += 
                        `<div class="group-member-row">
                            <div class="font-12-500 connecting-ninkname">\${nickname}</div>
                            <input type="hidden" class="group_user_no" value="\${group_user_no}">    
                            <div class="connecting-span"></div>
                        </div>`
                    } else {
                        groupMembersStr += 
                        `<div class="group-member-row">
                            <div class="font-12-500 connecting-ninkname">\${nickname}</div>
                            <input type="hidden" class="group_user_no" value="\${group_user_no}">    
                            <div class="connecting-span"></div>
                        </div>`
                    }

                } // for

                var groupMembersBox = $(".group-member-list");
                groupMembersBox.html(groupMembersStr);


                //------------------------------------------------
                // 공지사항 리스트 획득
                //------------------------------------------------
                getChatNotice(noticeListVO);


                //------------------------------------------------
                // 유형에 따른 메시지 발송 (0 : 입장 / 1 : 메시지전송 / 2 : 퇴장 / 3 : 공지등록 )
                //------------------------------------------------
                sendNotice = {
                    message : msgText,
                    type : msgType
                }
                socket.send('/chat/'+group_no, {}, JSON.stringify(sendNotice));
                

            }) // groupChatService.getGroupInfo

            console.log("showGroupInfo End.......")

            
            
        } // showGroupInfo

        //-------------------------
        // 공지사항 삭제
        //-------------------------
        function deleteChatNotice(chat_notice_no){
            
            groupChatService.removeChatNotice(chat_notice_no, function(data) {
                console.log("삭제 성공",data);
                
                showGroupInfo(group_no, 4);

            });

        };
        
        //-------------------------
        // 공지사항 항목 얻기
        //-------------------------
        function getChatNotice(noticeListVO){
            var noticeBox = $(".chat-top");
            var noticeHiddenBox = $(".notice-hidden");

            // 공지사항 유무 확인
            if(!noticeListVO[0].chat_notice_no) {
                noticeBox.html('');
                return;
            }

            var noticeHiddenStr = "";
            var noticeStr = "";


            
            for(var i=0, len = noticeListVO.length; i<len; i++){

                var chat_notice_no = noticeListVO[i].chat_notice_no;
                var chat_notice_context = noticeListVO[i].chat_notice_context;
                var chat_notice_time = noticeListVO[i].chat_notice_time; 

                var format_notice_time = mypageUtilService.normalTimeFormat(chat_notice_time);

                if(i==0){

                    noticeStr +=
                    `<span class="font-14-500">\${chat_notice_context}</span>
                    <span class="font-12-500">\${format_notice_time}</span>`

                    if(isAuthorityGroupChat()){
                        noticeStr +=
                        `<button class="remove-notice-btn" onclick="deleteChatNotice(\${chat_notice_no})"> <i class="fa-regular fa-trash-can"></i></button>`;
                    }

                } else {
                    noticeHiddenStr +=
                    `<div class="chat-notice">
                        <span class="font-14-500">\${chat_notice_context}</span>
                        <span class="font-12-500">\${format_notice_time}</span>`


                    if(isAuthorityGroupChat()){

                        noticeHiddenStr +=
                        `<button class="remove-notice-btn" onclick="deleteChatNotice(\${chat_notice_no})"> <i class="fa-regular fa-trash-can"></i></button>`;
                    }

                    noticeHiddenStr += `</div>`;
                }
            } // for

            noticeHiddenBox.html(noticeHiddenStr);
            noticeBox.html(noticeStr)
        } // getChatNotice



        //------------------------------------------------
        // 채팅서버 연결
        //------------------------------------------------
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
                    
                    // 메시지 수신
                    receiveMessage(event);

                    // 현재 접속자 갱신
                    showConnectMember(event);
                    
                }) // subscribe

            }) // connect

        } // connectStomp

        //------------------------------------------------
        // 메시지 수신
        //------------------------------------------------
        function receiveMessage(event){
            console.log("receive Message :", event);

            // 전송 온 Message(JSON문자열을 JavaScrip객체로 변환하여, property값 획득)
            var chat_text = JSON.parse(event.body).message;
            var chat_user_nickname = JSON.parse(event.body).nickname;
            var chat_user_no = JSON.parse(event.body).user_no;
            var currentTime = new Date();
            var formatTime = mypageUtilService.timeFormat(currentTime);
            var messageType = JSON.parse(event.body).type;

            var str = "";
            str += `<div class="chat-div">`;

            switch(messageType){

                // 입장 시, 전송되는 메시지
                case 0 : 
                
                    str +=

                    `<div class="chat-notice-message">
                        <span class="font-14-700">\${chat_user_nickname} 님이 접속하셨습니다.</span>
                    </div>`

                    break;


                // 메시지 Recieve
                case 1 :
                    
                    if(chat_user_no == user_no){
                        str += `<div class="chat-right">`;
                    } else {
                        str += `<div class="chat-left">`;
                    }

                    str +=  `<div class="chat-content-1"><img src="/resources/chat/img/profile-7794504.png"></div>
                            <div class="chat-content-2">
                                <div class="chat-info font-12-400"><span class="chat-id">\${chat_user_nickname}</span><span class="chat-time">\${formatTime}</span></div>
                                <div class="chat-text context-menu-one font-14-400">\${chat_text}</div>
                            </div>                
                        </div></div>`
                    
                    break;
                
                // 퇴장 시, 메시지
                case 2 : 

                    str +=

                    `<div class="chat-notice-message">
                            <span class="font-14-700">\${chat_user_nickname} 님이 나가셨습니다.</span>
                    </div>`;



                    break;

                // 공지사항 등록 메시지
                case 3 : 
                    str +=
                    
                    `<div class="chat-notice-message">
                        <span class="font-14-700">공지사항이 등록되었습니다.</span><br>
                        <span class="font-14-700">"\${chat_text}"</span>
                    </div>`

                    break;

                default :                   
                    str = '';
                    return;
                    
            } // switch - messageType    
            
            $('.chatting-view').append(str);
            $(".chatting-view").scrollTop($(".chatting-view")[0].scrollHeight);

        } // receiveMessage


        //---------------------------------
        // contextMenu
        //---------------------------------
        $.contextMenu({
            selector: '.chat-right .context-menu-one', 

            items: {
                "notice": {name: "공지등록", icon: "fa-solid fa-arrow-up-from-bracket"},
            },

            callback: function(key, options) {
                var m = "click: " + key;
                window.console && console.log(m); 
                console.log($(this).text());
                
                if(isAuthorityGroupChat()){

                    var noticeText = $(this).text();

                    var noticeData = {
                        group_no : group_no,
                        chat_notice_context : noticeText
                    };

                    groupChatService.registerChatNotice(noticeData, function(data) {
                        console.log("Data :",data);
                        showGroupInfo(group_no, 3, noticeText);
                    });


                } else {
                    alert("권한이 없습니다.");
                } // if

            },

        }); // contextMenu


        // ------------------------------------------
        // function : 그룹채팅 권한 확인 함수
        // ------------------------------------------
        function isAuthorityGroupChat(){

            if(user_no == admin_user_no) {
                return true;
            } else {
                return false;
            }
        } // isAuthorityGroupChat


        // ------------------------------------------
        // function : 현재 접속하고 있는, 멤버를 확인 
        // ------------------------------------------
        function showConnectMember(event) {
            console.log("showConnectMember() invoked.......");

            var connectUsers = JSON.parse(event.body).connectionUsers;
            var listConnectUsers = Object.values(connectUsers);
            
            user_no_set.clear();
            for(var i = 0, len=listConnectUsers.length; i<len; i++ ){
                user_no_set.add(listConnectUsers[i].user_no);
            }

            $(".group_user_no").each(function (){
                let uno = $(this).val();
                $(this).next().html("");

                if(user_no_set.has(parseInt(uno))){

                    $(this).next().html('<div class="connecting">접속중</div>');
                }
            })
        }

        // ------------------------------------------
        // Event Handler : 채팅 전송
        // ------------------------------------------
        $(".chat-textarea-btn").on('click', function(event){

            if(isStomp){
                event.preventDefault();

                if(!isStomp && socket.readyState != 1) return;

                var msg = $(".chat-textarea").val();
                $(".chat-textarea").val("");

                send = {
                    message : msg,
                    type : 1
                }

                socket.send('/chat/'+group_no, {}, JSON.stringify(send));
                
            }

        })


    </script>


    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>