$(()=> {
    const urlParams = new URL(location.href).searchParams;
    const report_no = urlParams.get('report_no');

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

                let repotImg = $('#repotImg');
                let img = '';
                
                img += `<img src=/display?fileName=${data.path}/${data.temp}>`;

                repotImg.append(img);
            })
        })

    }

    function reportView(data) {
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

        repotContent.append(content);

    }   

})