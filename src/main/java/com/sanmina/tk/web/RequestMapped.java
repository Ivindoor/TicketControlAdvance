package com.sanmina.tk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanmina.tk.orm.dao.MappedPartNumberDao;
import com.sanmina.tk.orm.model.MappedPartNumber;

@Controller
public class RequestMapped {

	@Autowired
	private MappedPartNumberDao mappedDao;

	@RequestMapping(value = "MappedPartNumber")
	public ModelAndView Mapped(ModelMap model) {
		try {
			List<MappedPartNumber> mapped = mappedDao.findAllMapped();
			model.addAttribute("mapped", mapped);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Mapped/MappedPartNumber", model);
	}

	@RequestMapping(value = "showAllMapped")
	public @ResponseBody ModelAndView ShowAllMapped(ModelMap model) {
		try {
			List<MappedPartNumber> mapped = mappedDao.findAllMapped();
			model.addAttribute("mapped", mapped);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("Mapped/TableMapped", model);
	}

	@RequestMapping(value = "saveNewMapped", method = RequestMethod.POST)
	public @ResponseBody String SaveNewMapped(String partSanmina, String partCustomer, String customer) {
		String vMsg = "";
		try {
			if (mappedDao.findMappedByPartSanmina(partSanmina.trim().toUpperCase()) == null) {
				MappedPartNumber mapped = new MappedPartNumber();
				mapped.setActive(true);
				mapped.setCustomer(customer.trim().toUpperCase());
				mapped.setPartNumberCustomer(partCustomer.trim().toUpperCase());
				mapped.setPartNumberSanmina(partSanmina.trim().toUpperCase());
				mappedDao.saveMapped(mapped);
				vMsg = "Ok";
			} else {
				vMsg = "This mapped already exist";
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "updateMapped", method = RequestMethod.POST)
	public @ResponseBody String UpdateMapped(String partSanmina, String partCustomer, String customer, String findPartSanmina) {
		String vMsg = "";
		try {
			MappedPartNumber mapped = mappedDao.findMappedByPartSanmina(findPartSanmina.trim().toUpperCase());
			mapped.setCustomer(customer.trim().toUpperCase());
			mapped.setPartNumberCustomer(partCustomer.trim().toUpperCase());
			mapped.setPartNumberSanmina(partSanmina.trim().toUpperCase());
			mappedDao.updateMapped(mapped);
			vMsg = "Ok";
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "changeActive", method = RequestMethod.POST)
	public @ResponseBody String ChangeActive(String partSanmina) {
		String vMsg = "";
		try {
			MappedPartNumber mapped = mappedDao.findMappedByPartSanmina(partSanmina.trim().toUpperCase());
			if (mapped != null) {
				if (mapped.getActive() == true) {
					mapped.setActive(false);
				} else {
					mapped.setActive(true);
				}
				mappedDao.updateMapped(mapped);
				vMsg = "Ok";
			} else {
				vMsg = "This mapped hasn't a part number sanmina";
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}
}
