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



    return {
        getGroupInfo: getGroupInfo
    }

})();