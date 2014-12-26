package com.premiumminds.wicket.demo.repeaters;


import com.premiumminds.wicket.demo.TemplatePage;

public class AjaxListSetViewPage extends TemplatePage {
	private static final long serialVersionUID = 1L;
	
	public AjaxListSetViewPage() {
		
		add(new AjaxListSetViewPanel("ajaxListView", 5));
		add(new ListViewPanel("listView", 5));
    }
}
