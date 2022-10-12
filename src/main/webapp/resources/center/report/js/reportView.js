

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
            console.log(data);
            reportView(data);
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
})