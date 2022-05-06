package com.sanmina.tk.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanmina.tk.orm.dao.ProjectDao;
import com.sanmina.tk.orm.model.Project;
import com.sanmina.tk.orm.model.Users;

@Controller
public class RequestReport {
	
	@Autowired
	private ProjectDao projectDao;

	@RequestMapping(value = "TicketReport")
	public String loginResolv() {
		return "Report/ReportTicket";
	}
	
	@RequestMapping(value = "getProjectsReport")
	public @ResponseBody List<Project> getProjectsReport() {
		System.out.println("REPPORT");
		return (List<Project>) projectDao.findAllProjects();
	}
	
}
