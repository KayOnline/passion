package org.kay.service;

import java.util.ArrayList;
import java.util.List;
import org.kay.entity.Organization;
import org.kay.entity.Party;
import org.kay.entity.PartyRelationship;
import org.kay.framework.model.easyui.DataWrap;
import org.kay.framework.model.easyui.TreeNode;
import org.kay.framework.model.service.ITreeRetriever;
import org.kay.framework.persistence.dao.BaseDao;
import org.kay.framework.persistence.model.QueryParamList;
import org.kay.framework.persistence.util.NativeDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService, ITreeRetriever<TreeNode> {

	@Autowired
	private BaseDao baseDao;
	
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	private void delAll(Long partyId) {
		String queryJpql = " select s from PartyRelationship s " 
						 + "  where s.partyIdFrom = :partyIdFrom "
				         + "    and s.relationshipType = '001' ";
		QueryParamList queryParamList =  new QueryParamList();
		queryParamList.addParam("partyIdFrom", partyId);
		List<Object> list = this.baseDao.find(queryJpql, queryParamList);
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				PartyRelationship pr = (PartyRelationship) obj;
				Long sonPartyId = pr.getPartyIdTo();
				this.delAll(sonPartyId);
			}
		}
		String deleteJpql = " delete from PartyRelationship s " 
				 		  + "  where s.partyIdFrom = :partyIdFrom "
				 		  + "    and s.relationshipType = '001' ";
		this.baseDao.executeUpdate(deleteJpql, queryParamList);
		this.baseDao.remove(Organization.class, partyId);
		this.baseDao.remove(Party.class, partyId);
	}
	
	@Override
	@Transactional
	public void save(DataWrap<Organization> dataWrap, String partyId) {
		// deleted
		for (Organization org : dataWrap.getDeletedList()) {
			delAll(org.getPartyId());
		}

		// updated
		for (Organization org : dataWrap.getUpdatedList()) {
			this.baseDao.merge(org);
		}

		// inserted
		for (Organization org : dataWrap.getInsertedList()) {
			
			Party party = new Party(org.getPartyId(), "001");
			baseDao.merge(party);
			
			Organization pOrg = this.baseDao.find(Organization.class, Long.valueOf(partyId));
			int groupLevel = Integer.valueOf(pOrg.getGroupLevel()) + 1;
			org.setGroupLevel(groupLevel + "");
			baseDao.merge(org);
			
			PartyRelationship pr = new PartyRelationship();
			Long relationshipId = NativeDBUtil.getInstance().genSequenceNo("SYS_SEQ_COMMON_ID");
			pr.setRelationshipId(relationshipId);
			pr.setPartyIdFrom(Long.valueOf(partyId));
			pr.setPartyIdTo(org.getPartyId());
			pr.setRelationshipType("001");
			baseDao.merge(pr);
		}
	}

	@Override
	public Object retrieveTree(String id) {
		return StringUtils.isEmpty(id) ? this.createRootNode() : this.retrieveChildren(id);
	}

	@Override
	public List<TreeNode> createRootNode() {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode();
		root.setId("0");
		root.setText("组织机构");
		root.setState("closed");
		nodes.add(root);
		return nodes;
	}

	@Override
	public List<TreeNode> retrieveChildren(String code) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		String jpql = " SELECT s FROM Organization s WHERE EXISTS (SELECT 1 FROM PartyRelationship t "
				    + " WHERE t.partyIdFrom = :partyIdFrom and t.relationshipType = '001' " 
				    + " AND t.partyIdTo = s.partyId)";
		QueryParamList paramList = new QueryParamList();
		paramList.addParam("partyIdFrom", Long.valueOf(code));
		for (Object obj : this.baseDao.find(jpql, paramList)) {
			if (obj instanceof Organization) {
				Organization org = (Organization) obj;
				TreeNode node = new TreeNode();
				node.setId(org.getPartyId().toString());
				node.setText(org.getGroupName());
				node.setState(this.hasChild(org.getPartyId().toString()) == true ? "closed" : "open");
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public boolean hasChild(String code) {
		String jpql = "select s from PartyRelationship s where s.partyIdFrom = :partyIdFrom and s.relationshipType = '001'";
		QueryParamList paramList = new QueryParamList();
		paramList.addParam("partyIdFrom", Long.valueOf(code));
		List<Object> list = this.baseDao.find(jpql, paramList);
		return list != null && list.size() > 0;
	}

	@Override
	public void retrieveDataGrid(DataWrap<Organization> dataWrap, String partyId) {
		if (!StringUtils.isEmpty(partyId)) {
			String queryJpql = " SELECT s FROM Organization s WHERE s.partyId <> '0' "
							 + "    AND s.partyId = :partyId OR EXISTS (SELECT 1	"
							 + "   FROM PartyRelationship t WHERE t.partyIdFrom = :partyId"
							 + "    AND t.partyIdTo = s.partyId)"
							 + "  ORDER BY s.partyId";
	
			QueryParamList params = new QueryParamList();
			params.addParam("partyId", Long.valueOf(partyId));
			List<Organization> dataList = this.baseDao.find(queryJpql, params, dataWrap.getPageInfo(), null);
			dataWrap.setDataList(dataList);
		}
	}

}
