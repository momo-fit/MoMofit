

// 일정의 기본값을 현재날짜로 셋팅 (slice() -> 시간을 '분'까지만 얻기 위해 사용)
// UTC 시간(9시간 느림)으로 출력되는 ISO 형식의 문자열을 현재 시간으로 변환 
$(function() {
		document.getElementById('currentDatetime').value 
			= new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000)
						.toISOString().slice(0, 16);
					
		
		// 현재날짜,시간보다 이전은 선택 못하도록 막음	
		let dateInput = document.getElementById("currentDatetime");
		dateInput.min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000)
							.toISOString().slice(0,new Date().toISOString().lastIndexOf(":"));

});



$(function() {
	$(".make_group_cancel").on('click', function () {
		
		location.href = "/group/list?currPage=1";
		
	});

});

// 운동종목 '기타' 선택시 입력칸 등장
$(function(){
    jQuery('#select_group_sport').change(function() {
        var state = jQuery('#select_group_sport option:selected').val();
        if ( state == '기타' ) {
            jQuery('#sports_add_text').show();
        } else {
            jQuery('#sports_add_text').hide();
        }
    })
});

// 일정 '변경' 선택시 입력칸 등장
// $(function(){
//     jQuery('#dateSelect').change(function() {
//         var state = jQuery('#dateSelect option:selected').val();
//         if ( state == '변경' ) {
//             jQuery('#currentDatetime').show();
//         } else {
//             jQuery('#currentDatetime').hide();
//         }
//     })
// });

// 지역 버튼 클릭시 이벤트
$(function(){
    $("#locBtn").on('click', function(e) {      
        $("#locDiv").show();                      // div 박스 나타나게
        $("#locInput").removeAttr("value");          // input 박스 안의 value 값을 삭제시킨다. (재설정 눌렀을 때 기존값 삭제시키기 위해서)
        console.log(e);
    })
});


// 운동종목 선택시 해쉬코드1에 자동입력
// ++ 기타 선택시 직접 입력한 텍스트가 들어가도록 수정해볼것
$(function() {

    $("#select_group_sport").change(function() {
    
        $("#input_hash1").val($(this).val());
    })
});


// 등록/취소 경고창
// $(function() {
//     $(".make_group_submit").on("click", function () {

// 	// if($(".make_group_name") == null) {
// 	// 	Swal.fire({
// 	// 		icon: 'success',
// 	// 		title: '모임 이름을 작성해주세요!!',
// 	// 		text: '이곳은 내용이 나타나는 곳입니다.',                     // Alert 타입
// 	// 	})
// 	// } 

// 	Swal.fire({
		
// 	   title: '모임을 생성하시겠습니까?',
// 	   text: '다시 되돌릴 수 없습니다.',
// 	   icon: 'warning',
	   
// 	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
// 	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
// 	   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
// 	   confirmButtonText: '생성', // confirm 버튼 텍스트 지정
// 	   cancelButtonText: '취소', // cancel 버튼 텍스트 지정
	   
// 	   reverseButtons: true, // 버튼 순서 거꾸로
   
// 	}).then(result => {
// 	   // 만약 Promise리턴을 받으면,
// 	   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
	   
// 	      Swal.fire('생성이 완료되었습니다.', '모임 목록으로 이동합니다.', 'success');
// 	      $(".make_group_form").submit();
	      
// 	   } else if (result.isDismissed) { // 만약 모달창에서 cancel 버튼을 눌렀다면
// 	    	// ...실행
// 	   }
	  
// 	  })
	
// 	})
	
// });

// $(function() {
//     $(".make_group_cancel").on("click", function () {
	
// 	Swal.fire({
		
// 	   title: '모임 생성을 그만하시겠습니까?',
// 	   text: '목록으로 돌아갑니다.',
// 	   icon: 'warning',
	   
// 	   showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
// 	   confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
// 	   cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
// 	   confirmButtonText: '그만하기', // confirm 버튼 텍스트 지정
// 	   cancelButtonText: '계속하기', // cancel 버튼 텍스트 지정
	   
// 	   reverseButtons: true, // 버튼 순서 거꾸로
   
// 	}).then(result => {
// 	   // 만약 Promise리턴을 받으면,
// 	   if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
	   
// 	      Swal.fire('취소되었습니다.', '모임 목록으로 이동합니다.', 'success');
// 	      location.href = "/group/list"
	      
// 	   } else if (result.isDismissed) { // 만약 모달창에서 cancel 버튼을 눌렀다면
// 	    	// ...실행
// 	   }
	  
// 	  })

// 	})
	
// });



