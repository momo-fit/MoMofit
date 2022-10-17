$(function () {
            
    // 팝업창이 열렸을 때, 부모창에 입력되었 던 닉네임을 가져온다.
    let parentNickname = opener.$(".username").val();
    console.log(parentNickname);
    $(".find_nickname_search").val(parentNickname);

    // 팝업창 확인버튼이 눌렸을 때, 부모창으로 닉네임값 전달
    $(".find_nickname_btn").on("click", function (){
        
        let e = $(".find_nickname_content input[name='nickname']:checked").val();
        // console.log(e);
        
        var word3 = e.substring(e.lastIndexOf('|') + 1);

        $(".username", opener.document).val(word3);
        
        window.close();

    })
})

$(() => {
        
    $('.find_nickname_search').on('keypress', ()=> {
        let inputText = $('.find_nickname_search').val();

        
        $.ajax({
            type: 'get',
            url: '/center/report//report-userfind',
            data: {'inputNic': inputText},
            dataType: 'json',
            success:((data)=> {
                printUser(data);

            })

        })

        function printUser(data) {
            let str = '';

            $.each(data.vo ,((i)=> {

                str += 
                `<div class="find_nickname_content">
                    <input type="radio" name="nickname" id="nickname${i}" value="${data.vo[i].nickname} | ${data.vo[i].user_no}">
                    <label class="font-16-400" for="nickname${i}">${data.vo[i].nickname} | ${data.vo[i].user_no}</label>
                </div>`
            }))

            $('.wrap_find_nickname_content').html(str);
        }
       
    })

})
