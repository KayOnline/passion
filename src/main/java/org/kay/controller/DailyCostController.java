package org.kay.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "dailyCostController")
public class DailyCostController {
	
	@RequestMapping(value = "/getpdfReport", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView,
			@RequestParam(value = "format", required = false) String format) throws Exception {

		Map<String, String> model = new HashMap<String, String>();

		if ("csv".equals(format)) {
			model.put("format", "csv");
		} else if ("pdf".equals(format)) {
			model.put("format", "pdf");
		} else if ("xls".equals(format)) {
			model.put("format", "xls");
		} else {
			model.put("format", "html");
		}

		return new ModelAndView("multiFormatView", model);
	}

}
