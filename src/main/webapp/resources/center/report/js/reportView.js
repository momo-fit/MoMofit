

$(()=> {
    const urlParams = new URL(location.href).searchParams;
    const report_no = urlParams.get('report_no');
    
    let imgPath;
    let imgTemp;
    let report_img_name;

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

        if(loginNickname != nickname){
            $('.edit_delete').hide();
        }

        

        repotContent.append(content);

    }   


    $('#remove').click(()=> {

        if(confirm("글을 삭제 하시겠습니까?")){

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
                    alert("삭제되었습니다.");
                    location.href = "/center/report/list";
                }),
                error:(() => {
                    alert("삭제를 실패했습니다. 다시 시도해 주세요");
                    location.href = "/center/report/list";
                })
            }) // ajax

        } // if

    }) // remove : click

    // =========== 댓글 ==================
    // 댓글 표시
    function reportCommView(data) {

        let str = '';

        $.each(data, ((i) => {

            str+=
            `<div class="comment font-14-400">
                <span class="font-16-500">운영자</span>
                <input type="text" class="comment_input_text" name="" id="" value="${data[i].text}" disabled>
                <span>${data[i].report_comm_date}</span>
                <span></span>
                <span class="edit_delete_comm">
                    <button type="button" class="cursor_pointer font-12-400 comment_modify_btn"> 수정 </button>
                    <div class="space1"></div>
                    <a href=""><span class="remove1 font-12-400"> 삭제 </span></a>
                </span>
            </div>`
            

        }))
        
        $('.comment_out').append(str);

        // 해당 문지열이 있으면 ture
        // -> admin이 아닐시 수정 삭제 숨김
        if(!loginNickname.includes('admin')){
            $('.edit_delete_comm').hide();
        }

    }

    $('.btn-primary').click(()=> {

        let commText = $('.form-control').val();
        let commId = loginUserId;
        let commReportNo = report_no;
        
        console.log(commText);




    })


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
                
                
                selectForm.submit();
            }
        }
    })
})


