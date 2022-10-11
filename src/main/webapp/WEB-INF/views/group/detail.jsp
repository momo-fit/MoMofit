<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임보기</title>
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
    <link href="https://fonts.googleapis.com/css2?family=Gugi&family=Hi+Melody&family=Sunflower:wght@500&display=swap" rel="stylesheet">

    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/7d82554876.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/556efa47d7.js" crossorigin="anonymous"></script>

    <!-- 전 페이지 공통 설정 -->
    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <!-- CSS : main 전체 페이지 설정 -->
    <link rel="stylesheet" href="/resources/common/css/main_page.css">
    <!-- CSS : main 미디어 태그 -->
    <link rel="stylesheet" href="/resources/common/css/main_mediatag.css">
    
    <!-- CSS : main_header 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_header.css">
    <!-- CSS : main footer 설정 -->
    <link rel="stylesheet" href="/resources/include/css/main_footer.css">
    <!-- CSS : main_side 설정 -->
    <link rel="stylesheet" href="/resources/include/css/side_profile.css">

    <!-- CSS : main_board_group -->
    <link rel="stylesheet" href="/resources/group/css/main_board_group.css">

    <!-- 메인페이지 필수 CSS -->
    <link rel="stylesheet" href="/resources/main/css/main_section_card.css">
    <link rel="stylesheet" href="/resources/main/css/main_section_board.css">
    
</head>

<body>
    <div class="page">

        <!-- header -->
        <%@ include file = "/WEB-INF/views/include/header.jsp" %>  
        
        <%@ include file = "/WEB-INF/views/include/sideLogin.jsp" %>  


        <!-- 메인 컨텐츠부 -->
        <section>

            <div class="section_wrap">
                <div class="view">

                    <div class="font-16-500">
                        <div class="board_name">
                            
                            &#128692;모임보기
                            <form action="/group/remove" method="post">

                                <input type='hidden' name='group_no' value='<c:out value="${group.group_no}"/>' readonly="readonly">
                                <input type='hidden' name="currPage" value='<c:out value="${cri.currPage}" />'>
                                

                                <input type="submit" value="삭제" id="del" 
                                        onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) return false;">

                                <label for="del">
                                    삭제  <img src="/resources/group/img/remove-button.png" width="30px">
                                </label>        
                            </form>
                            
                        </div>
                    </div>
  

                    <div class="title font-28-400">
                        ${group.group_name}
                    </div>
                
                    <div class="info"> 
                        <div class="info2">
                            <span class="font-12-400"> 작성자 <b class="font-12-400"><c:out value="${group.user_no}"/></b> </span>
                            <!-- <div class="space1"></div>
                            <span class="font-12-400"> 조회수 <b class="font-12-400">1</b> </span> -->
                            <div class="space1"></div>
                            <span class="font-12-400">작성일- <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${group.group_date}" /></span>

                            <span class="font-12-400" id="edit">
                                &nbsp; 수정일- 
                            	<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${group.edit}" />
                            </span>


                            <div class="space1"></div>

                        </div>

                        <div class="edit_delete">
                            <a href="/group/modify?group_no=${group.group_no}&currPage=${cri.currPage}"><span class="font-14-500"> 수정 </span></a>
                            <div class="space1"></div>
                            


                            <!-- <a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;" 
                            href="/group/remove" ><span class="font-14-500"> 삭제 </span></a> -->

                            <div class="space1"></div>
                            <a href="/group/memberList"><span class="font-14-500"> 모임장 위임 </span></a>

                            

                        </div>
                    </div>


                    <!-- 본문 -->
                    <div class="content_wrap">
                        <div class="content_wrap_1">
                            <div class="content_wrap_2">

                                <!-- 본문 이미지 -->
                                <div class="content_img">

                                    <c:if test="${group.group_img != null}">
                                        <img class="group_box_img" src="/display?fileName=${group.path}/${group.temp}_${group.group_img}">
                                    </c:if>

                                    <c:if test="${group.group_img == null}">
                                        <img class="group_box_img" src="/resources/group/img/basket.jpg">
                                    </c:if>

                                </div>

                                <!-- 소개글 -->
                                <div class="content_intro font-14-400">
                                    <h6 id="text_info"><span class="bottom-line"> 모임을 소개합니다.</span></h6>
                                    ${group.info}
                                    <br>                                    
                                </div>

                            </div>
                            

                            <!-- 모임장 파트 -->
                            <div class="content_group_leader font-14-400">

                                <div class="font-20-700"> 모임장 </div>
                                <div class="side_profile_1_person">
                                    
                                    <img src="/resources/include/img/side_sample.jpg">
                                </div>
                    
                                <div class="side_profile_1_text">
                                    <span class="side_profile_nickname">작성자</span>
                                    <img src="/resources/group/img/female-2404482.png" width="20px" alt="">
                                    <!-- <img src="./img/male-272547.png" width="20px"alt=""> -->
                                    <!-- <span class="side_profile_gender">여</span> -->
                                </div><br>
                    
                                <div class="side_profile_1_loc">
                                    서울시 강남구 <br>
                                    <span class="side_profile_loc_check">지역인증완료
                                        <img src="/resources/group/img/checkbox.png" width="15px">
                                    </span>
                                </div><br>
                    
                                <div class="side_profile_1_sports">
                                    <ul>
                                        <li>헬&nbsp;스</li>
                                        <li>등&nbsp;산</li>
                                        <li>농&nbsp;구</li>
                                    </ul>
                                </div>

                                <div class="like_div">
                                    <img src="/resources/group/img/like.png" height="30" class="like_btn">
                                    <span id="like_cnt" class="font-14-700">0</span>
                                </div>

                                <a href="/group/chat"><button type="button" class="joinBtn">참가하기</button></a>                    
                            </div>
                        </div>


                        <!-- 신고하기 -->
                        <div class="report">
                            <a href="" 
                            onclick="window.open(this.href, '_blank', 'width=800, height=600'); return false;"
                            class="link1 float_right font-14-700">신고</a><br>                                                    
                        </div>

                        <!-- 댓글 START   -->

                        <!-- 댓글 JS -->
                        <script type="text/javascript" src="/resources/group/js/reply.js"></script>


						<!-- 댓글 TEST  -->
                        <script>
                                console.log("JS TEST");

                                var gnoValue = '<c:out value="${group.group_no}"/>';

                                // 1. 등록 TEST 
                                //for replyService add test 
                                // replyService.add(
                                //     {text:"JS Test", user_no:"1", group_no:gnoValue},
                                //     function(result) {
                                //         alert("RESULT: " + result);
                                //     }
                                // );
                                
                                // 2. 목록 TEST 
                                replyService.getList({group_no:gnoValue, page:1}, function(list){
                                    
                                    for(var i = 0,  len = list.length||0; i < len; i++ ){
                                    console.log(list[i]);
                                    }
                                });
                                
                                // 3. 삭제 TEST  
                                // '60'번 댓글 삭제
                                // replyService.remove(60, function(count) {
                                // 	console.log(count);
                                // 	 
                                // if (count === "success") { 
                                // 		alert("REMOVED"); } 
                                // }, 
                                
                                // function(err) { 
                                // 	alert('ERROR...'); 
                                // });
                                
                                // 4. 수정 TEST 
                            	//22번 댓글 수정 
                                // replyService.update({
                                // group_comm_no : 22,
                                // group_no : gnoValue,
                                // text : "Modified Reply"
                                // }, function(result) {
                                //  
                                // alert("수정 완료!!!");
                                //  
                                // });  
                            
                            	
                                //  5. 특정 댓글 조회 TEST
                                // replyService.get(22, function(data){
                                //     console.log(data);
                                // });

                        </script>                        

                        
                        <!-- 댓글창 -->
                        <div class="comment_wrap">

                            <div class='row'>

                                <div class="col-lg-12">
                              
                                  <!-- /.panel -->
                                  <div class="panel panel-default">

                                    <div class="panel-heading font-16-500">
                                      <i class="fa fa-comments fa-fw"></i> 댓글
                                      <button id='addReplyBtn'>댓글 작성</button>
                                    </div>      
                                    <br>
                                    
                                    <!-- /.panel-heading -->
                                    <div class="panel-body">        
                                    
                                      <ul class="chat">
                                        <!-- <li class="left clearfix" data-group_comm_no="12">
                                            <div>
                                                <div class="header">
                                                    <strong class="primary-font">춘식이</strong>
                                                    <small class="pull-right text-muted">2022-10-07 13:13 </small>
                                                </div>
                                                <p>초보도 가능한가요?</p>
                                            </div>
                                        </li> -->
                                        <!-- end reply -->
                                      </ul>
                                      <!-- ./ end ul -->
                                    </div>
                                    <!-- /.panel .chat-panel -->
                              
                                  <div class="panel-footer"></div>
                              
                              
                                      </div>
                                </div>
                                <!-- ./ end row -->
                              </div>


                                <!-- Modal -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
                                aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                        aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
                                    </div>
                                    
                                    <div class="modal-body">
                                    <div class="form-group">
                                        <label>Reply</label> 
                                        <input class="form-control" name='text' value='New Reply!!!!'>
                                    </div>      
                                    <div class="form-group">
                                        <label>Replyer</label> 
                                        <input class="form-control" name='writer' value='replyer'>
                                    </div>
                                    <div class="form-group">
                                        <label>Reply Date</label> 
                                        <input class="form-control" name='group_comm_date' value='2022-10-07 13:13'>
                                    </div>

                                    </div>
                                <div class="modal-footer">
                                <button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
                                <button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
                                <button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
                                <button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
                                </div>          </div>
                                <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                                </div>
                                <!-- /.modal -->


                            <!-- 댓글1 -->
                            <!-- <form action="" class="comment font-14-400">
                                <span class="font-16-500">닉네임1</span>
                                <input type="text" class="comment_input_text" name="" id="" value="댓글내용입니다1" disabled>
                                <span>02:28</span>
                                <span onclick="clickFunc()" class="link1 cursor_pointer"> 답글달기</span>
                                <span class="edit_delete">
                                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                                    <div class="space1"></div>
                                    <a href=""><span class="font-12-400"> 삭제 </span></a>
                                </span>
                            </form> -->

                            <!-- 대댓글 작성창 -->  
                            <!-- <form action="">
                                <div id="recomment_write" class="card">
                                    <div class="card-body">
                                        <textarea class="form-control" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                        onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'"></textarea>
                                        <button type="submit" class="btn btn-primary btn_color_blue float_right">작성</button>
                                    </div>
                                </div>  
                            </form>                           -->
                                

    
                            <br>
                        </div>

                        <!-- 댓글 작성창 -->
                        <!-- <form action="">
                            <div id="comment_write" class="card">
                                <div class="card-body">
                                    <textarea class="form-control" row="1" style="width: 90%;" placeholder="여기에 댓글을 입력하세요"
                                    onfocus="this.placeholder=''" onblur="this.placeholder='여기에 댓글을 입력하세요'"></textarea>
                                    <button type="submit" class="btn btn-primary btn_color_blue float_right">작성</button>
                                </div>
                            </div>                             
                        </form>
                            
                        <br> -->
                        
                    </div>

                    <script>
                    $(document).ready(function () {
                        
                        var gnoValue = '<c:out value="${group.group_no}"/>';
                        var replyUL = $(".chat");   /* 해당 ul태그를 선택해 그 아래에 li태그를 생성하게 됨 */

                        /* 브라우저에서 DOM 처리가 끝나면 자동으로 showList()가 호출되면서 <ul> 태그 내의 내용으로 처리된다. */
                        showList(1);                /* 페이지 번호를 파라미터로 받음 - 없는 경우 자동으로 1페이지 */
                        function showList(page){
                        
                        replyService.getList({group_no:gnoValue, page: page|| 1 }, function(list) {
                            
                            var str="";
                            if(list == null || list.length == 0){
                            
                                replyUL.html("");
                            
                                return;
                        }
                        for (var i = 0, len = list.length || 0; i < len; i++) {
                        str +="<li class='left clearfix' data-group_comm_no='"+list[i].group_comm_no+"'>";
                        str +="  <div><div class='header'><strong class='primary-font'>"+list[i].writer+"</strong>"; 
                        str +="    <small class='pull-right text-muted'>"+replyService.displayTime(list[i].group_comm_date)+"</small></div>";
                        str +="    <p>"+list[i].text+"</p></div></li>";
                        }
                     
                        replyUL.append(str);
                            
                        
                        });//end function
                        
                    }//end showList
                    
                    // 새로운 댓글 추가 버튼 이벤트 처리
                    var modal = $(".modal");

                    var modalInputReply = modal.find("input[name='text']");
                    var modalInputReplyer = modal.find("input[name='writer']");
                    var modalInputReplyDate = modal.find("input[name='group_comm_date']");
                    
                    var modalModBtn = $("#modalModBtn");
                    var modalRemoveBtn = $("#modalRemoveBtn");
                    var modalRegisterBtn = $("#modalRegisterBtn");
                    
                    $("#modalCloseBtn").on("click", function(e){
                        
                        modal.modal('hide');
                    });
                    
                    $("#addReplyBtn").on("click", function(e){
                    
                    modal.find("input").val("");
                    modalInputReplyDate.closest("div").hide();
                    modal.find("button[id !='modalCloseBtn']").hide();
                    
                    modalRegisterBtn.show();
                    
                    $(".modal").modal("show");
                    
                    });

                    // 댓글 등록 및 목록 갱신
                    modalRegisterBtn.on("click",function(e){
    
                        var reply = {
                                text: modalInputReply.val(),
                                writer:modalInputReplyer.val(),
                                group_no:gnoValue
                            };

                        replyService.add(reply, function(result){
                            
                            alert(result);
                            
                            modal.find("input").val("");
                            modal.modal("hide");
                            
                            // 댓글 목록 갱신 -> 화면에 새로 등록된 댓글 보이게 함
                            showList(1);
                        });
                    });
                        
                    // 특정 댓글 클릭 이벤트 처리
                    // 댓글은 Ajax로 가져온 결과를 DOM에 추가하는 형태이므로 이벤트 위임 방식을 이용해서 처리 ( on()사용 )
                    $(".chat").on("click", "li", function(e){
                    
                        var group_comm_no = $(this).data("group_comm_no");

                        console.log(group_comm_no);
                        
                        replyService.get(group_comm_no, function(reply){
                        
                            modalInputReply.val(reply.text);
                            modalInputReplyer.val(reply.writer);
                            modalInputReplyDate.val(replyService.displayTime( reply.group_comm_date))
                            .attr("readonly","readonly");
                            modal.data("group_comm_no", reply.group_comm_no);
                            
                            modal.find("button[id !='modalCloseBtn']").hide();
                            modalModBtn.show();
                            modalRemoveBtn.show();
                            
                            $(".modal").modal("show");
                                
                        });
                    });

                    modalModBtn.on("click", function(e){

                        var reply = {group_comm_no:modal.data("group_comm_no"), text: modalInputReply.val()};
                        
                        replyService.update(reply, function(result){
                                
                            alert(result);
                            modal.modal("hide");
                            showList(1);
                            
                        });
                        
                    });

                    modalRemoveBtn.on("click", function (e){

                        var group_comm_no = modal.data("group_comm_no");
                        
                        replyService.remove(group_comm_no, function(result){
                                
                            alert(result);
                            modal.modal("hide");
                            showList(1);
                            
                        });
                        
                    });


                        
                })




                </script>
                    
                    <!-- 이전글 목록 다음글 -->
                    <div class="btn_row">
                        <a href=""><button type="button" class="btn btn-primary btn-sm btn_color_blue">이전글</button></a>

                        <!-- 페이지번호를 가지고 목록으로 넘어가야함 -->
                        <a href="/group/list?currPage=${cri.currPage}">
                            <button type="button" class="btn btn-secondary btn-sm" id="listBtn">목록</button>
                        </a>

                        <a href=""><button type="button" class="btn btn-primary btn-sm btn_color_blue" >다음글</button></a>
                    </div>
            
                </div>
            </div>

        </section>

        <!-- 하단 Footer -->
        <%@ include file = "/WEB-INF/views/include/footer.jsp" %>
    </div>

    <!-- 모임보기 자바스크립트 -->
    <script src="/resources/group/js/main_board_group.js"></script>

    <!-- 메인화면 섹션 자바스크립트` -->
    <script src="/resources/include/js/main_section.js"></script>

    <!-- 메인화면 자바스크립트 -->
    <script src="/resources/include/js/main_header.js"></script>
    

    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>