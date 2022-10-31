
console.log("comm Module......");


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

		var yy = dateObj.getFullYear();
		var mm = dateObj.getMonth() + 1; // getMonth() is zero-based
		var dd = dateObj.getDate();

		return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
				(dd > 9 ? '' : '0') + dd ].join('');

	}
	
	function update(comm, callback, error) {

		// console.log("comm_no: " + comm.comm_no);

		$.ajax({
			type : 'put',
			url : '/board/pic/comm/' + comm.comm_no,
			data : JSON.stringify(comm),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}//if
			}//error
		});//ajax
	}//function update
	
	
	function remove(comm_no, callback, error) {
		$.ajax({
			type: 'delete',
			url: '/board/pic/comm/' + comm_no,
			success: function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}//if
			}//error
		});//remove ajax
	}//function remove

	return {
		add:add,
		getList : getList,
		displayTime : displayTime,
		update:update,
		remove:remove
	};
	
})();//comService 


//게시글 상세조회 화면이 실행되면, 실행되는 js
$(document).ready(function() {
	
	// let bnoValue = "${PicBoard.board_no}";  // 게시글넘버 가져오기 
	// let InputUser =  "${PicBoard.user_no}";  // 작성자
	let InputComm= $("#comm_write");  //댓글내용
	

	var commUL = $(".chat");  //댓글 목록 ul를 선택해서 그 밑에 li태그를 생성하게된다.
	showList(1);  //파라미터가 없는 경우 댓글 목록을 1페이지로 설정

	


	// 수정용 화면
	function showList(page, commNo) {
		commService.getList({board_no:boardNo, page:page||1, commNo:commNo}, function(list){
			let str="";

			if (list == null || list.length == 0) {
				commUL.html("");
				return;
			}//if
			for (let i = 0, len = list.length||0; i < len; i++) {
				console.log("i : ", i);
				// 매개변수로 들어온 commNo(댓글번호)와 일치하면 textarea(내용)만 수정할 수 있게 뜨는 화면 
				if (commNo == list[i].comm_no){
					console.log("if list[i].comm_no : ", list[i].comm_no);
					str+="<div class='comment font-14-400'>"
					str+="	<span class='font-16-500'>"+list[i].nickname+"</span>"
					str+="	<input id='modified_comm_text' value='"+list[i].text+"'/>"
					str+="	<span>"+commService.displayTime(list[i].comm_date)+"</span>"
					str+="	<span onclick='clickFunc()' class='link1 cursor_pointer'> 답글달기</span>"
					str+="	<span class='edit_delete'>"
					str+="		<input type='hidden' id='comm_number' value='"+list[i].comm_no+"'/>"
					str+="  	<button type='button' name='comment_modifyDone_btn' class='cursor_pointer font-12-400 comment_btn'> 작성 </button>"
					str+="    	<div class='space1'></div>"
					str+="    	<button type='button' name='comment_delete_btn' class='cursor_pointer font-12-400 comment_btn'> 삭제 </button>"
					str+="	</span>"
					str+="</div>"			
				} else {
					console.log("else list[i].comm_no : ", list[i].comm_no);
					str+="<div class='comment font-14-400'>"
					str+="	<span class='font-16-500'>"+list[i].nickname+"</span>"
					str+="	<span>"+list[i].text+"</span>"
					str+="	<span>"+commService.displayTime(list[i].comm_date)+"</span>"
					str+="	<span onclick='clickFunc()' class='link1 cursor_pointer'> 답글달기</span>"
					str+="	<span class='edit_delete'>"
					str+="  	<button type='button' name='comment_modify_btn' value='"+list[i].comm_no+"' class='cursor_pointer font-12-400 comment_btn'> 수정 </button>"
					str+="    	<div class='space1'></div>"
					str+="    	<button type='button' name='comment_delete_btn' value='"+list[i].comm_no+"' class='cursor_pointer font-12-400 comment_btn'> 삭제 </button>"
					str+="	</span>"
					str+="</div>"
					
					let commNo = $("button[name=comment_modify_btn]").val();
					console.log("else commNo : ", commNo);
				}

	
			}//for
			commUL.html(str);  //ul 태그에 추가
			
							
					//댓글 수정 (수정할 수 있도록 창을 띄움)
					
						$('button[name="comment_modify_btn"]').on("click",function(e){
						console.log("수정 버튼 클릭!");
						let commNo = $(this).val();
						console.log("commNo : ", commNo);
						showList(1, commNo);  //﻿commNo와 일치하는 댓글라인만 내용 바꿀 수 있게 목록 갱신
				
					}); //댓글 수정 끝 
					
					// 댓글 수정 (수정 완료 - 디비 내용 변경)
					
						$('button[name="comment_modifyDone_btn"]').on("click",function(e){
						console.log("수정 버튼 클릭!");
						let commNo=$("#comm_number");
						let modified_comm_text = $("#modified_comm_text");
						let comm = {
							"comm_no": commNo.val(), 
							"text": modified_comm_text.val()
						};
				
						commService.update(comm, function(result) {
							alert(result);
							showList(1, 0);  //﻿댓글 목록을 갱신
						});
					});
					
					//댓글 삭제
					//commUL.on("click", 'button[name="comment_delete_btn"]', function(e){
						$('button[name="comment_delete_btn"]').on("click",function(e){
						console.log("삭제 버튼 클릭!");
						let commNo = $(this).val();
						commService.remove(commNo, function(result) {
							alert(result);
							showList(1, 0);
						});
					});//댓글 삭제 코드 
			
			
				});//getList
			}//showModifiedList
			
			/* 댓글 작성하기 버튼 클릭 시 댓글 등록 */
			$("#commSubmitBtn").on("click", function(e){
				let comm = {
						"text": InputComm.val(),
						"board_no": boardNo,
						"parent_no": 0,
						"user_no": commId
				};
				
				// 댓글 등록 요청
				commService.add(comm, function(result){	
					$(".form-control").val('');
					showList(1, 0);
				}); // 댓글 등록 요청 끝
			});//댓글 작성하기 버튼 클릭 시 댓글 등록 끝
			
		
			
		
				
		
		
		
			/* -- 댓글 목록  -------------------------- 
			let page = 1;
			commService.getList({board_no:boardNo, page:page||1}, function(list){
				console.log("list : ", list);
			});
			*/	
		
		});

