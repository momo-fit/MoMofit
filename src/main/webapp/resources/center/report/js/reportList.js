$(() => {
    
    // 기본 페이지 
    var page = 1;
    var keyword;

    listSelect(page, keyword);
    
    function listSelect(page, keyword) {
        $.ajax({
            url:'/center/report/report-list',
            method:'get',
            data: {
                'page':page,
                'keyword' : keyword
            },
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

    $('#searchBtn').click(()=> {
        keyword = $('#searchVal').val();

        listSelect(page, keyword);
    })

    function view(data, page) {

        let list = data.list;
        let pageMaker = data.pageMaker;

        let str = '';

        $.each(list , ((i)=> {

                if(true){

                };

                str +=
                `<tr class="contnet">
                    <td><div>${list[i].report_no}</div></td>
                    <td><div><a href='/center/report/view/?report_no=${list[i].report_no}' class='aTile'>${list[i].title}</a>`;

                    if(list[i].report_result == 0) {
                        str +=
                        `<div class='inquery_status${list[i].report_result}'>
                            <t class='font-12-400'>처리중</t>
                        </div>`
                    } else {
                        str +=
                        `<div class='inquery_status${list[i].report_result}'>
                            <t class='font-12-400'>처리완료</t>
                        </div>`
                    }
                    
                str+=
                    `</td>
                    <td><div>${list[i].nickname}</div></td>
                    <td><div>${list[i].report_date}</div></td>
                </tr>`;
		
        }))
        
        $('.table_body').html(str);

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