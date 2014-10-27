package org.kay.service;

import java.util.List;
import org.kay.entity.Person;
import org.kay.framework.model.easyui.DataWrap;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
	public List<Person> retrieve(String partyId);
	@Transactional
	public void save(DataWrap<Person> dataWrap, String partyId);
}
