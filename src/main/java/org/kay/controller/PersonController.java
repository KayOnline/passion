package org.kay.controller;

import java.util.List;
import org.kay.entity.Person;
import org.kay.framework.model.easyui.DataWrap;
import org.kay.framework.model.easyui.TreeNode;
import org.kay.framework.model.service.ITreeRetriever;
import org.kay.framework.persistence.util.NativeDBUtil;
import org.kay.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("personController")
public class PersonController extends BaseController {

	private DataWrap<Person> dataWrap = new DataWrap<Person>();

	@Autowired
	private PersonService personService;
	
	@Autowired
	private ITreeRetriever<TreeNode> organizationTreeService;
	
	@RequestMapping("/")
	public String init() throws Exception {
		dataWrap.getPageInfo().setRowOfPage(10);
		return "manager/person";
	}

	@RequestMapping("retrieveDataGrid")
	@ResponseBody
	public Object retrieveDataGrid(@RequestParam(value = "partyId", required = false) String partyId) {
		if (!StringUtils.isEmpty(partyId)) {
			List<Person> persons = this.personService.retrieve(partyId);
			this.dataWrap.setDataList(persons);
		}
		return dataWrap.createDataGrid();
	}

	
	@RequestMapping("/retrieveTree")
	@ResponseBody
	public Object retrieveTree(@RequestParam(value = "id", required = false) String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			return this.organizationTreeService.createRootNode();
		} else {
			return this.organizationTreeService.retrieveChildren(id);
		}
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Object save(Model model, @RequestParam(value = "partyId", required = false) String partyId) throws Exception {
		this.personService.save(dataWrap, partyId);
		this.responseData.setMessage("保存成功！");
		return responseData;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add() throws Exception {
		Person person = new Person();
		Long seqNo = NativeDBUtil.getInstance().genSequenceNo("SYS_SEQ_COMMON_ID");
		person.setPartyId(seqNo);
		return person;
	}

	public DataWrap<Person> getDataWrap() {
		return dataWrap;
	}

	public void setDataWrap(DataWrap<Person> dataWrap) {
		this.dataWrap = dataWrap;
	}

	public ITreeRetriever<TreeNode> getOrganizationTreeService() {
		return organizationTreeService;
	}

	public void setOrganizationTreeService(ITreeRetriever<TreeNode> organizationTreeService) {
		this.organizationTreeService = organizationTreeService;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
