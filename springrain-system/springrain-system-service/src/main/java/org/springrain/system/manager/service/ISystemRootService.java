package org.springrain.system.manager.service;

import java.util.List;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.system.manager.dto.FileDto;

@RpcServiceAnnotation
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
