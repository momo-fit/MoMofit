

$(()=> {
    const urlParams = new URL(location.href).searchParams;
    const report_no = urlParams.get('report_no');
    
    let imgPath;
    let imgTemp;
    let report_img_name;

    if(!loginNickname.includes('admin')){
        $('#comment_write').hide();
    }

    $.ajax({
        method : 'get',
        url : '/center/report//report-view',
        dataType: 'json',
        data : {'reportNum' : report_no},
        async: false,
        success : ((data)=> {
            reportView(data);
        })
    })

    $.ajax({
        method:'get',
        url : '/report/comm/comm-list',
        dataType: 'json',
        data : {'report_no' : report_no},
        async: false,
        success : ((data)=> {

            if(data != ''){
                reportCommView(data);
            }
           
        })
    })


    function imgLode() {

        $.ajax({
            method : 'get',
            url : '/center/report/report-uploadImg',
            dataType: 'json',
            data : {'reportNo' : report_no},
            async: false,
            success : ((data)=> {
				imgPath = data.path;
                imgTemp = data.temp;
                report_img_name = data.report_img_name
	
                let repotImg = $('#repotImg');
                let img = '';
                
                img += `<img src=/display?fileName=${data.path}/${data.temp}_${data.report_img_name}>`;

                repotImg.append(img);
            })
        })

    }

    function reportView(data) {

        console.log(loginNickname);

        let report_no = data.content.report_no;

        $('#modifyLink').attr('href', '/center/report/modify?report_no='+report_no);


        let reportTitle = $('.Title');
        let title = data.content.title;

        let reportNickname = $('.reportNickname');
        let nickname = data.content.nickname;

        let reportDate = $('.reportDate');
        let Date = data.content.report_date;           
        
        let repotContent = $('#repotContent');
        let content = data.content.text;

        reportTitle.append(title);
        reportNickname.append(nickname);
        reportDate.append(Date);

        if(data.content.img_check == 1){
            imgLode(report_no);
        }

        if(loginNickname != nickname && !loginUserId.includes('admin')){
            $('.edit_delete').hide();
        }

        repotContent.append(content);

    }   


    $('#remove').click(()=> {

        if(confirm("?????? ?????? ???????????????????")){

            deleteParam = {
                'imgPath' : imgPath,
                'imgTemp' : imgTemp,
                'report_no' : report_no
            }
    
            $.ajax({
                method: 'delete',
                url: '/center/report/report-delete',
                data:JSON.stringify(deleteParam),
                contentType : "application/json; charset=utf-8",
                async: false,
                success: (()=> {
                    alert("?????????????????????.");
                    location.href = "/center/report/list";
                }),
                error:(() => {
                    alert("????????? ??????????????????. ?????? ????????? ?????????");
                    location.href = "/center/report/list";
                })
            }) // ajax

        } // if

    }) // remove : click

    // =========== ?????? ==================
    // ?????? ??????
    function reportCommView(data) {

        let str = '';

        $.each(data, ((i) => {
            
            str+=
            `<div class="comment font-14-400">
                <span class="font-16-500">?????????</span>
                <input type="text" class="comment_input_text" name="" id="" value="${data[i].text}" disabled>
                <input type="text" class="report_comm_no" style="display: none;" value="${data[i].report_comm_no}"></input>
                <span>${data[i].report_comm_date}</span>
                <span></span>
                <span class="edit_delete_comm">
                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> ?????? </button>
                    <div class="space1"></div>
                    <button type="button" class="remove1 font-12-400"> ?????? </span>
                </span>
                
            </div>`
            

        }))
        
        $('.comment_out').append(str);

        // ?????? ???????????? ????????? ture
        // -> admin??? ????????? ?????? ?????? ??????
        if(!loginNickname.includes('admin')){
            $('.edit_delete_comm').hide();
        }

    }

    $('.btn-primary').click(()=> {

        let commText = $('.form-control').val();
        let commId = loginUserId;
        let commReportNo = report_no;
        

        $.ajax({
            method: 'post',
            url: '/report/comm/comm-register',
            dataType: 'json',
            async: false,
            data:{
                'report_no':commReportNo,
                'text':commText, 
                'adminId':commId, 
            },
            success:(()=> {
                location.href ='/center/report/view/?report_no='+report_no;
            })


        })

    })

    $(".comment_modify_btn").on({
        "click": function () {

            // input:text??? ??????
            let selectText = $(this).parent().parent().children("input");
            let report_comm_no = $(this).parent().parent().children(".report_comm_no");
            
            console.log(selectText.is(":disabled"));

            // ?????? text??????, ?????????????????? ?????? ???, ??????
            if(selectText.is(":disabled")){
                
                selectText.attr("disabled", false)
                .css({
                    border: "1px solid rgb(20 167 255)",
                    bowShadow: "0px 0px 5px 1px rgb(20 167 255)"
                });

                $('.comment_input_text').keydown((key) => {
                    
                    if(key.keyCode == 13){
                        modifyAjax();
                    }
                })

            } else {
                $(".comment_content").attr("disabled", true)

                .css({
                    border: "none",
                    bowShadow: "none"
                });
  
                modifyAjax();                
            }

            function modifyAjax() {
                // ??????, ???????????? ????????????
                let textVal = selectText.val()
                let report_comm_noVal = report_comm_no.val()

                modifyParam = {
                    'text':textVal, 
                    'report_comm_no':report_comm_noVal
                }

                $.ajax({
                    method: 'put',
                    url: '/report/comm/comm-modify',
                    contentType:'application/json; charset=utf-8',
                    data:JSON.stringify(modifyParam),
                    async: false,
                    success:(()=> {
                        alert("??????");
                        location.href ='/center/report/view/?report_no='+report_no;
                    })
                })
            }

 


        }
    })

    $('.remove1').on( "click", function () {
        let report_comm_noTag = $(this).parent().parent().children(".report_comm_no");
        let report_comm_no = report_comm_noTag.val();

        if(confirm("????????? ?????? ???????????????????")){
            $.ajax({
                method:'delete',
                url: '/report/comm/comm-remove',
                contentType:'application/json; charset=utf-8',
                data:JSON.stringify(report_comm_no),
                async: false,
                success: (() => {
                    alert("?????????????????????");
                    location.href ='/center/report/view/?report_no='+report_no;
                })
            }) // ajax

        } // if
        
    }) // remove1:click

})


