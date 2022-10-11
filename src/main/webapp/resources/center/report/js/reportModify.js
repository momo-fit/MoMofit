$(() => {
    const urlParams = new URL(location.href).searchParams;
    const report_no = urlParams.get('report_no');

    $.ajax({
        url:'/center/report/report-modifyView',
        method:'get',
        data: {'report_no':report_no},
        dataType: 'json',
        success: ((data) => {
            updateView(data);
        })

    })

    function updateView(data){

        let reportType = $('.type');
        let type = data.report_type;

        let reportTitle = $('.writing_title');
        let title = data.title;

        let reportFile = $('.file');
        let file = data.report_img_name;

        let reportTarget = $('.target');
        let target = data.report_user;           
        
        let repotContent = $('.report_textarea_content');
        let content = data.text;

        reportType.val(type);
        reportTitle.val(title);
        reportFile.val(file);
        reportTarget.val(target);
        repotContent.append(content);

        

        if(data.img_check == 1){
            console.log("123");
            $('#img').show();

            let repotImg = $('#img');
            let img = '';
            
            img += `<img id="uplodeImg" src=/display?fileName=${data.path}/${data.temp}>`;

            repotImg.append(img);
        }


    }

    $('.report_submit').click(()=> {


        let title = $('.writing_title').val();
        let text = $('.report_textarea_content').val();
        console.log(title);
        console.log(text);


        let param = {
            'title':title,
            'text':text,
            'report_no':report_no 
        };

        console.log(param);

        $.ajax({
            url:'/center/report/report-modifyPut',
            method:'put',	
            data:JSON.stringify(param),
            contentType : "application/json; charset=utf-8",
            async: false,
            success:((data)=> {
                console.log(data);
                location.href = "/center/report/list"
            }),
            error:((request,status,error) => {
                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            })
        })

    })

})