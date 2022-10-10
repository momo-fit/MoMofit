<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기</title>

    <!-- 부트스트랩 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- jQuery 라이브러리 연동 방법 - 네트워크 전송방법 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.4.0/jquery-migrate.min.js"></script>



    <!-- 구글 폰트 적용 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

    <!-- CSS : font 설정 -->
    <link rel="stylesheet" href="/resources/common/css/font.css">
    <link rel="stylesheet" href="/resources/center/report/css/popup_find_nickname.css">

</head>


<body>
    <div class="find_nickname">
        <div class="wrap_find_nickname_title">
            <span class="font-28-700">닉네임 찾기</span>
        </div>

        <div class="wrap_find_nickname_search">
                <input type="text" name="" id="" class="find_nickname_search font-16-500" placeholder="닉네임">
                <button type="button" class="font-16-500">검색</button>
        </div>

        <div class="wrap_find_nickname_content">

 

            


        </div>

        <div class="wrap_find_nickname_btn">
            <button tpye="button" class="find_nickname_btn font-16-500">확인</button>
        </div>
    </div>

    <!-- 팝업 : 닉네임찾기 자바스크립트 -->
    <script src="/resources/center/report/js/popup.js"></script>
    <!-- 부트스트랩 자바스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>


<script>
    $(() => {

        $('.find_nickname_search').keypress(()=> {
            let inputText = $('.find_nickname_search').val();

            $.ajax({
                type: 'get',
                url: '/center/report//report-userfind',
                data: {'inputNic': inputText},
                dataType: 'json',
                success:((data)=> {
                    printUser(data);
                    // console.log(data.vo[1].nickname);
                })

            })

            function printUser(data) {
                let str = '';

                $.each(data.vo ,((i)=> {

                    str += 
                    `<div class="find_nickname_content">
                        <input type="radio" name="nickname" id="nickname\${i}" value="\${data.vo[i].nickname}">
                        <label class="font-16-400" for="nickname\${i}">\${data.vo[i].nickname}</label>
                    </div>`
                }))

                $('.wrap_find_nickname_content').html(str);
            }
           
        })

    })
</script>