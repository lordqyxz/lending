package com.p2p.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;
import com.p2p.lending.entity.Dept;
import com.p2p.lending.service.DeptService;

@Controller
@RequestMapping("dept")
public class DeptController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private DeptService deptService;

	@RequestMapping("findall")
	public String findall(Model model) {
		List<Dept> deptlist = deptService.findall();
		model.addAttribute("deptlist", deptlist);
		return "WEB-INF/view/bk_deptlist";
	}

	@RequestMapping("insert")
	public String insert(Dept dept, Model model, @RequestParam(value = "dname", required = false) String dname,
			@RequestParam(value = "describe", required = false) String describe) {
		dept.setDname(dname);
		dept.setDescribes(describe);
		deptService.insert(dept);
		return "redirect:/dept/findall.do";
	}

	@RequestMapping("toadd")
	public String inserts() {
		return "WEB-INF/view/bk_deptadd";
	}

	@RequestMapping("del")
	public String del(@RequestParam(value = "did", required = false) Integer did) {

		deptService.del(did);

		return "redirect:/dept/findall.do";
	}

	@RequestMapping("toupd")
	public String toupd(@RequestParam(value = "did", required = false) Integer did, Model model) {

		Dept dept = deptService.findbyid(did);

		model.addAttribute("dept", dept);
		return "WEB-INF/view/bk_deptupd";
	}

	@RequestMapping("upd")
	public String upd(@RequestParam(value = "did", required = false) Integer did,
			@RequestParam(value = "dname", required = false) String dname,
			@RequestParam(value = "describes", required = false) String describes) {
		Dept dept = deptService.findbyid(did);
		dept.setDescribes(describes);
		dept.setDname(dname);
		deptService.upd(dept);
		return "redirect:/dept/findall.do";
	}

}
