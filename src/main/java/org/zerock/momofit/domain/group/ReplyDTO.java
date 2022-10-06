package org.zerock.momofit.domain.group;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
    
    private Integer group_comm_no;   // PK
    private Integer group_no;        // groups FK
    private String text;   
    private Date group_comm_date;
    private Date edit;
    private Integer parent_no;
    private Integer user_no;        // users FK
    
} //end
