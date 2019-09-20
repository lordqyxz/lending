package com.p2p.lending.controller;

import java.util.List;

import com.p2p.lending.entity.Log;
import com.p2p.lending.service.LoggerService;
import com.p2p.lending.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {

	
	@Autowired
	private LoggerService service;

	@RequestMapping("/list")
	public String list() {

		System.out.println("查询日志   》》》》》》》》》》》》");

		Log log = new Log();

		List<Log> page = service.findList(BeanUtils.toMap(log));

		System.out.println(" 222222222222      " + page);

		return "/back/test";

	}
}
