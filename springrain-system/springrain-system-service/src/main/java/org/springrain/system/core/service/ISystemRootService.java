package org.springrain.system.core.service;

import java.util.List;

import org.springrain.system.core.dto.FileDto;

public interface ISystemRootService extends IBaseSpringrainService {

	/**
	 * 列出文件
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */

	List<FileDto> findFileDtosByPath(String path, String rootPath) throws Exception;

}
