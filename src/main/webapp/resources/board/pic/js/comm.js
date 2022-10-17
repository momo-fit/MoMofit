
console.log("Reply Module......");

let commService = (function() {
    /*등록처리*/
    function add(comm, callback, error) {
        console.log("add comm.......");
		
        // 추가
        $.ajax({
            type: 'post',
            url: '/board/pic/comm/create',
            data: JSON.stringify(comm),
            contentType:"application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }//if
            }//error
        })//ajax
    }//add function
    
    
 	/*목록처리*/
 	//getList( ) 는 param 객체를 통해 필요한 파라미터를 전달받아 JSON 목록을 호출
	function getList(param, callback, error) {
		console.log("param : ", param);
		
		let board_no = param.board_no;  //글 번호
		let page = param.page || 1;
		
		//JSON 형태가 필요하므로 URL 호출 시 확장자를 '.json' 으로 요청
		$.getJSON("/board/pic/comm/pages/" + board_no + "/" + page, 

			function(data) {
				if (callback) {
					callback(data);  //callback(data); 은 아래코드의 function(list) 를 호출
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error();
				}
			});
	}
   
    
    function displayTime(timeValue) {

		var today = new Date();

		var gap = today.getTime() - timeValue;

		var dateObj = new Date(timeValue);
		var str = "";

		if (gap < (1000 * 60 * 60 * 24)) {

			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();

			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
					':', (ss > 9 ? '' : '0') + ss ].join('');

		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
			var dd = dateObj.getDate();

			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}
	}
	;

	return {
		add : add,
		getList : getList,
		displayTime : displayTime
	};
	
})();//comService 


//게시글 상세조회 화면이 실행되면, 실행되는 js
$(document).ready(function() {
	
	// let bnoValue = "${PicBoard.board_no}";  // 게시글넘버 가져오기 
	// let InputUser =  "${PicBoard.user_no}";  // 작성자
	let InputComm= $("#comm_write");  //댓글내용
	

	var commUL = $(".chat");  //댓글 목록 ul를 선택해서 그 밑에 li태그를 생성하게된다.
	showList(1);  //파라미터가 없는 경우 댓글 목록을 1페이지로 설정

	

	function showList(page) {
		commService.getList({board_no:bnoValue, page:page||1}, function(list){
			let str="";

			
			if (list == null || list.length == 0) {
				commUL.html("");
				return;
			}
			for (let i = 0, len = list.length||0; i < len; i++) {
				console.log(list[i]);

				str+="<div class='comment font-14-400'>"
				str+="	<span class='font-16-500'>"+list[i].user_no+"</span>"
				str+="	<span>"+list[i].text+"</span>"
				str+="	<span>"+commService.displayTime(list[i].comm_date)+"</span>"
				str+="	<span onclick='clickFunc()' class='link1 cursor_pointer'> 답글달기</span>"
				str+="	<span class='edit_delete'>"
				str+="  	<button type='button' class='cursor_pointer font-12-400 comment_modify_btn'> 수정 </button>"
				str+="    	<div class='space1'></div>"
				str+="    	<a href=''><span class='font-12-400'> 삭제 </span></a>"
				str+="	</span>"
				str+="</div>"
			}
			commUL.html(str);  //ul 태그에 추가
		});
	}

	/* 댓글 작성하기 버튼 클릭 시 댓글 등록 */
	$("#commSubmitBtn").on("click", function(e){
		let comm = {
				"text": InputComm.val(),
				"user_no": InputUser,
				"board_no": bnoValue
		};
		
		
		// 댓글 등록 요청
		commService.add(comm, function(result){	
			showList(1);
		});
	});
	
	
	/* -- 댓글 목록  -------------------------- */	
	let page = 1;
	commService.getList({board_no:bnoValue, page:page||1}, function(list){
		console.log("list : ", list);
	});


});

