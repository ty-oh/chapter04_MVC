package org.joonzis.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	private long bno;
	private String title, content, writer;
	private Date regdate, updatedate;
	private int replycnt;
}
