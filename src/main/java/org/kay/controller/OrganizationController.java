package org.kay.controller;

import org.kay.entity.Organization;
import org.kay.framework.model.easyui.DataWrap;
import org.kay.framework.persistence.util.NativeDBUtil;
import org.kay.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("organizationController")
public class OrganizationController extends BaseController {
	
	private DataWrap<Organization> dataWrap = new DataWrap<Organization>();
	
	@Autowired
	private OrganizationService organizationService;

	@Override
	@RequestMapping("/")
	public String init() throws Exception {
		dataWrap.getPageInfo().setRowOfPage(10);
		return "manager/organization";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add() throws Exception {
		Organization org = new Organization();
		Long seqNo = NativeDBUtil.getInstance().genSequenceNo("SYS_SEQ_COMMON_ID");
		org.setPartyId(seqNo);
		return org;
	}

	@RequestMapping("/save")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object save(Model model, @RequestParam(value = "partyId", required = false) String partyId) throws Exception {
		this.organizationService.save(dataWrap, partyId);
		this.responseData.setMessage("保存成功！");
		return responseData;
	}

	@RequestMapping("/retrieveDataGrid")
	@ResponseBody
	public Object retrieveDataGrid(Model model, @RequestParam(value = "partyId", required = false) String partyId)
			throws Exception {
		this.organizationService.retrieveDataGrid(this.dataWrap, partyId);
		return dataWrap.createDataGrid();
	}

	@RequestMapping("/retrieveTree")
	@ResponseBody
	public Object retrieveTree(@RequestParam(value = "id", required = false) String id) throws Exception {
		return this.organizationService.retrieveTree(id);
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public DataWrap<Organization> getDataWrap() {
		return dataWrap;
	}

	public void setDataWrap(DataWrap<Organization> dataWrap) {
		this.dataWrap = dataWrap;
	}

}
