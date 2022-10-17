$(document).ready(function() {
    var result = '<c:out value="${result}"/>';

});


$(function() {
	
	$(".search_btn").on("click", function(e){
        var searchForm = $("#searchForm");
    
        
        let type = $(".search_area select").val();
        
        let keyword = $(".search_area input[name='keyword']").val();
        
        // if(!type){
        //     alert("검색 종류를 선택하세요.");
        //     return false;
        // }
        
        // if(!keyword){
        //     alert("키워드를 입력하세요.");
        //     return false;
        // }        
        
        searchForm.find("input[name='type']").val(type);
        searchForm.find("input[name='keyword']").val(keyword);
        searchForm.find("input[name='pageNum']").val(1);
        searchForm.submit();
    });
});


