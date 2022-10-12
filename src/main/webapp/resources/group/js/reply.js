console.log("Reply Module...");

// 외부에서는 replyService.add(객체,콜백)를 전달하는 형태로 호출 / Ajax호출은 감춰져있음 -> 코드 깔끔하게 작성 가능
var replyService = (function() {
	
	/* 댓글 작성*/
	function add(reply, callback, error) {      // 파라미터로 callback과 error를 함수로 받음
                                                // - Ajax 호출이 성공하고, callback 값으로 적절한 함수가 존재한다면 
		console.log("add reply....");           //   해당 함수를 호출해서 결과를 반영    
	
	    $.ajax({
	        type : 'post',
	        url : '/group/replies/new',
	        data : JSON.stringify(reply),
	        contentType : "application/json; charset=utf-8",
	        success : function(result, status, xhr) {
	            if (callback) {
	                callback(result);
	            }
	        },
	        error : function(xhr, status, er) {
	            if (error) {
	                error(er);
	            }
	        }
	    })
    }
    
    /* 댓글 목록 */
    function getList(param, callback, error) {
 
	    var gno = param.group_no;
	    var page = param.page || 1;
	 
	    $.getJSON("/group/replies/pages/" + gno + "/" + page ,
	        function(data) {
	          if (callback) {
	            callback(data);
	          }
	        }).fail(function(xhr, status, err) {
	      if (error) {
	        error();
	      }
	    });
	  }
	  
	/* 댓글 삭제*/
	function remove(group_comm_no, callback, error) {
	    $.ajax({
	      type : 'delete',
	      url : '/group/replies/' + group_comm_no,
	      success : function(deleteResult, status, xhr) {
	        if (callback) {
	          callback(deleteResult);
	        }
	      },
	      error : function(xhr, status, er) {
	        if (error) {
	          error(er);
	        }
	      }
	    });
	  }

	/* 댓글 수정*/
	function update(text, callback, error) {
		
	     $.ajax({
	      type : 'put',
	      url : '/group/replies/' + text.group_comm_no,
	      data : JSON.stringify(text),
	      contentType : "application/json; charset=utf-8",
	      success : function(result, status, xhr) {
	        if (callback) {
	          callback(result);
	        }
	      },
	      error : function(xhr, status, er) {
	        if (error) {
	          error(er);
	        }
	      }
	    });
	  }
	  
	/* 특정 댓글 조회*/
	function get(group_comm_no, callback, error) {
 
	    $.get("/group/replies/" + group_comm_no, function(result) {
	 
	      if (callback) {
	        callback(result);
	      }
	 
	    }).fail(function(xhr, status, err) {
	      if (error) {
	        error();
	      }
	    });
	  }

      /* 시간 처리 - 해당일 : 시/분/초 
                     24시간 후 : 년/월/일  */
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
		get : get,
		getList : getList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};

})();