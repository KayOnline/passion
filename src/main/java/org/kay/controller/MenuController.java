/******************************************************************************
 *	Copyright © 2014 Apple Inc. All rights reserved.
 *****************************************************************************/
package org.kay.controller;

import org.kay.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "menuController")
public class MenuController {

	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public Object query(@RequestParam(value = "id", required = false) String id) throws Exception {
		return this.menuService.query(id);
	}

	@RequestMapping(value = "/addNode")
	@ResponseBody
	public Object addNode() throws Exception {
		return this.menuService.addNode();
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(@RequestParam(value="inserted", required = false) String inserted,
					   @RequestParam(value="updated", required = false) String updated,
					   @RequestParam(value="deleted", required = false) String deleted) throws Exception {
		return this.menuService.save(inserted, updated, deleted);
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam(value="id", required=false) String id) throws Exception {
		return this.menuService.delete(id);
	}

}
