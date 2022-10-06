console.log("group_chat_module....");



var groupChatService = (function () {



    //--------------------------
    //1. 모임 정보 불러오기 ( + 멤버정보)
    //--------------------------
    function getGroupInfo(group_no, callback, error){

        $.ajax({
            url: "/chat/rooms/" + group_no,
            type: "GET",
            dataType: "json",
	

            success:function(data){
                if(callback){
                    callback(data);
                }
            },

            error: function(xhr, status,error){
                if(error){
                    error();
                }
            }

        })

    } // getGroupInfo
    
    
    function removeChatNotice(chat_notice_no, callback, error){
        
        $.ajax({
            url: "/chat/notices/" + chat_notice_no,
            type : "DELETE",

            
            success : function(data){
                if(callback){
                    callback(data);
                }
            }, // success

            error : function(xhr, status, error){
                if(error){
                    error();
                }
            } // error

        })

	} // removeChatNotice


    function registerChatNotice(notice, callback, error){

        $.ajax({
            url : "/chat/insert",
            type : "POST",
            data : JSON.stringify(notice),
            contentType : "application/json; charset=utf-8",

            success : function(data){
                if(callback){
                    callback(data);
                }
            }, // success
            
            error : function(xhr, status, error){
                if(error){
                    error();
                }
            }, // error

        })


    } // registerChatNotice



    return {
        getGroupInfo: getGroupInfo,
        removeChatNotice : removeChatNotice,
        registerChatNotice : registerChatNotice
    }

})();