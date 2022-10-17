$(".search_btn").on("click", function(e){
    e.preventDefault();
    let PicBoard_SearchForm = $(".PicBoard_SearchForm");
    
    let val = $("input[name='keyword']").val();
    PicBoard_SearchForm.find("input[name='keyword']").val(val);
    PicBoard_SearchForm.find("input[name='pageNum']").val(1);
    PicBoard_SearchForm.submit();
});