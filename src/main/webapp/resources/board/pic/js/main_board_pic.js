
$(function () {
	
	// 게시물 클릭하면 바로 첨부된 이미지 보이기 시작
	//let board_no = '<c:out value="${PicBoard.board_no}"/>';
	
	let uploadResult=$("#uploadResult");
	
	console.log(" *** 무조건 출력되어야함! \n " + String(board_no) );
	
	// getJSON(url[,data][,success]) 형식으로 씀
	// DB에서 이미지 정보 불러오기
	$.getJSON("/board/pic/imageList", {board_no : board_no}, function(arr) {  
		
		// 이미지가 없는 경우 바로 리턴
		if(arr.length === 0) {
			console.log("이미지가 없습니다.");
			return;
		}
		
		let str = "";
		let obj = arr[0];	
		
		let fileCallPath = encodeURIComponent(obj.path + "/s_" + obj.temp + "_" + obj.board_img_name);
		str += "<div id='result_card'";
		str += "data-path='" + obj.path + "' data-uuid='" + obj.temp + "' data-filename='" + obj.board_img_name + "'";
		str += ">";
		str += "<img src='/display?fileName=" + fileCallPath +"'>";
		str += "</div>";
		
		uploadResult.html(str);	
	 });
	
});

// 게시물 클릭하면 바로 첨부된 이미지 보이기 끝



// 좋아요 이미지 클릭시 숫자 올라가는 제이쿼리
$(function () {

    $(".like_btn").on({
        "click": function () {
            let likeNum = $("#like_cnt").text();
            let num = parseInt(likeNum, 10);
            num++;

            $('#like_cnt').text(num);
        }
    })

});

// 댓글창 나왔다 숨기는 자바스크립트
function clickFunc(){
    // 댓글창 선택 (btn1)
    const  recomment = document.getElementById('recomment_write');
    
    // 댓글창 숨기기 (display: none)
    if(recomment.style.display !== 'none') {
        recomment.style.display = 'none';
    }
    // 댓글창 보이기 (display: block)
    else {
        recomment.style.display = 'block';
    }
}

// 현구님 제이쿼리 연구해보기
// 제이쿼리 클릭이벤트
// $(function () {
//     $("#btn_comment").on({
//         "click" : function () {
//             if($(".card").css("display") == 'block'){
//                 $(".card").hide();
//             } else {
//                 $(".card").show();
//             }
//         },
//     })
// }); 

$(function () {

    $(".comment_modify_btn").on({
        "click": function () {

            // 부모창 선택
            let selectForm = $(this).parent().parent();
            console.log(selectForm);

            // input:text창 선택
            let selectText = $(this).parent().parent().children("input");
            console.log(selectText);

            console.log(selectText.is(":disabled"));

            // 만약 text창이, 비활성화되어 있을 때, 수정
            if(selectText.is(":disabled")){
                
                selectText.attr("disabled", false)
                .css({
                    border: "1px solid rgb(20 167 255)",
                    bowShadow: "0px 0px 5px 1px rgb(20 167 255)"
                });

            } else {
                $(".comment_content").attr("disabled", true)

                .css({
                    border: "none",
                    bowShadow: "none"
                });
                
                // 방법 1: 댓글form을 submit한다.
                // 방법 2: 비동기처리로 한다. (submit 사용 X)
                // -> 방법2, 댓글에 대한 정보를 읽어들어야한다.
                // -> 방법1과 2 모두 hidden 태그로 댓글에 대한 PK를 내포하고 있어야한다.
                selectForm.submit();
            }
        }
    })

});
