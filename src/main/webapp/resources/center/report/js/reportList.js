$(() => {
    
    function listSelect(page) {
        $.ajax({
            url:'/center/report/report-list',
            method:'get',
            data: {'page':page},
            dataType: 'json',
            async: false,
            success : ((data)=> {
                console.log(data);
                view(data, page);
                
            }),
            error:(() => {
                console.log("통신에러");
            })

        })
    }


    // 기본 페이지 
    var page = 1;

    listSelect(page);


    function view(data, page) {

        let list = data.list;
        let pageMaker = data.pageMaker;

        let str = '';

        $.each(list , ((i)=> {
                str +=
                `<tr class="contnet">
                    <td><div>${list[i].report_no}</div></td>
                    <td><div><a href='/center/report/view/?report_no=${list[i].report_no}' class='aTile'>${list[i].title}</a>
                        <div class='inquery_status'>
                            <t class='font-12-400' id='sta'>${list[i].report_result}</t>
                        </div>
                    </td>
                    <td><div>${list[i].nickname}</div></td>
                    <td><div>${list[i].report_date}</div></td>
                </tr>`;

        }))
        $('.table_body').html(str);

        

        if($('.inquery_status>t').text() != 0 ){
            $('.inquery_status>t').text('처리중');
            $('.inquery_status').css("background","gray");
        } else {
            $('.inquery_status>t').text('처리완료');
            $('.inquery_status').css("background","rgb(20 167 255)");
        }

        // 페이징
        let pageination = $('#report-paging-div ul');
        let pageli = '';

        if(pageMaker.prev) {
            pageli += `<li class="mb-prev font-16-700"><</li>`;

        }

        for(let pageNum=pageMaker.startPage; pageNum <= pageMaker.endPage; pageNum++ ){
            if(pageNum == 0){
                break;  
            } 

            if(pageMaker.cri.currPage == pageNum){
                pageli +=  `<li class="mb-currPage mb-currPage-step font-16-700">${pageNum}</li>`
            } else {
                pageli += `<li class="mb-currPage-step font-16-700">${pageNum}</li>`
            }
            
        }

        if( pageMaker.next) {
            pageli += `<li class="mb-next font-16-900">></li>`;
        }

        pageination.html(pageli);

        // 페이지 이동
        $(".mb-currPage-step").on('click', function() {
            page = $(this).text(); 

            listSelect(page);
        })
        
        $(".mb-next").on('click', (()=> {
            page = pageMaker.endPage + 1;

            listSelect(page);
        }))

        $(".mb-prev").on('click', (()=> {
            page = pageMaker.startPage - 1;

            listSelect(page);
        }))
        
    }


})