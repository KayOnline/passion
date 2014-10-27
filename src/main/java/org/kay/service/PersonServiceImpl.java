package org.kay.service;

import java.util.List;

import org.kay.entity.PartyRelationship;
import org.kay.entity.Person;
import org.kay.framework.model.easyui.DataWrap;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.util.NativeDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private BaseDao baseDao;
	
	public List<Person> retrieve(String partyId) {
		String queryJpql = " SELECT s FROM Person s "
				         + "  WHERE EXISTS (SELECT 1 "
				         + "   FROM PartyRelationship t"
				         + "  WHERE t.relationshipType = '002'"
				         + "    AND t.partyIdTo = s.partyId "
				         + "    AND t.partyIdFrom = :partyIdFrom)";
		QueryParamList queryParamList = new QueryParamList();
		queryParamList.addParam("partyIdFrom", Long.valueOf(partyId));
		List<Person> persons = this.baseDao.find(queryJpql, queryParamList);
		return persons;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(DataWrap<Person> dataWrap, String partyId) {
		// deleted
		for (Person person : dataWrap.getDeletedList()) {
			this.baseDao.remove(Person.class, person.getPartyId());
		}

		// updated
		for (Person person : dataWrap.getUpdatedList()) {
			this.baseDao.merge(person);
		}

		// inserted
		for (Person person : dataWrap.getInsertedList()) {
			this.baseDao.persist(person);
			long relationshipId = NativeDBUtil.getInstance().genSequenceNo("SYS_SEQ_COMMON_ID");
			PartyRelationship pr = new PartyRelationship();
			pr.setRelationshipId(relationshipId);
			pr.setPartyIdFrom(Long.valueOf(partyId));
			pr.setPartyIdTo(person.getPartyId());
			pr.setRelationshipType("002");
			this.baseDao.persist(pr);
		}

	}
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
