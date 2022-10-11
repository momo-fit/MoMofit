package org.zerock.momofit.domain.picboard;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.extern.log4j.Log4j2;



@Log4j2
@Data
public class Criteria {
	
	private int currPage = 1;			// 현재 표시할 페이지번호
	private int amount = 20;			// 한 페이지당 보여줄 레코드 건수
	private int pagesPerPage =5;		// 한 페이지당 보여줄 페이지목록의 길이	
	
	private String type;			// 검색유형
	private String keyword;				// 검색어
	
	private int pageNum;  //현재 페이지 넘버 
	
	public String[] getTypeArr() {
		return type==null? new String[] {}:type.split("");
	}
	

	
	public String getPagingUri() {
		log.debug("getPagingUri() invoked.");
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		
		builder.queryParam("currPage", this.currPage);
		builder.queryParam("amount", this.amount);
		builder.queryParam("pagesPerPage", this.pagesPerPage);
		builder.queryParam("searchType", this.type);
		builder.queryParam("keyword", this.keyword);
		
		log.info("\t+ pagingUri: " + builder.toUriString());
		
		return builder.toUriString();
	} // getPagingUri
	
	
	
	
	
} // end class