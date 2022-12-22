package org.joonzis.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType; // �̹��� ������ ���� �̹��� ������ �ƴѰ�츦 �����Ͽ� ������� ���� �� ���.... ���������� �������� ���� å�� ����������
	private long bno;
	
	// �Խñ� ��� �� �ѹ��� BoardAttachVO�� ó���ϱ� ���� List �߰�
}
