package org.zerock.momofit.domain.group;

import java.util.Date;

import lombok.Data;

@Data
public class GroupMemberDTO {
    
    private Integer group_member_no;   // PK
    private Integer group_no;          // groups FK
    private Integer user_no;           // users FK
    private Date join_date;
    
} //end
