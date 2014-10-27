package org.kay.service;

import org.kay.entity.Organization;
import org.kay.framework.model.easyui.DataWrap;

public interface OrganizationService {

	void save(DataWrap<Organization> dataWrap, String partyId);

	Object retrieveTree(String id);

	void retrieveDataGrid(DataWrap<Organization> dataWrap, String partyId);

}
