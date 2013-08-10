package org.springrain.demo.web;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.demo.entity.User;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.DBUtils;

@Controller
@RequestMapping(value="/dbmanager")
public class DBManagerController extends BaseController {
	
	@Resource
	private DBUtils dbutils;
	
	@RequestMapping("/backup")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,User user) throws Exception{
		File file = dbutils.backupFile();
		downFile(response, file, file.getName(),true);
		return;
	}

}
