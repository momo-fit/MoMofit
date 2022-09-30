package org.zerock.momofit.domain.group;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
// 페이징 - 검색의 기준
public class Criteria {
		
	private int currPage = 1;			// 현재 표시할 페이지 번호
	private int amount = 16;			// 한 페이지당 몇개의 데이터? - 읽어올 row 수 

	private int pagesPerPage = 5;		// 한 페이지당 보여줄 페이지 목록 길이
	
	private String type;				// 검색유형
	private String keyword;				//


	
	public String getPagingUri() {
		log.debug("getPagingUri() invoked.");
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
		
		builder.queryParam("currPage", this.currPage);
		builder.queryParam("amount", this.amount);
		builder.queryParam("pagesPerPage", this.pagesPerPage);
		builder.queryParam("type", this.type);
		builder.queryParam("keyword", this.keyword);
		
		log.info("\t+ pagingUri: " + builder.toUriString());
		
		return builder.toUriString();
	} // getPagingUri


}

