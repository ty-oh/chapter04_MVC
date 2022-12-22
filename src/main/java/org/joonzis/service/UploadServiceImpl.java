package org.joonzis.service;

import org.joonzis.mapper.BoardAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UploadServiceImpl implements UploadService{
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper mapper;
	
	
	
}
