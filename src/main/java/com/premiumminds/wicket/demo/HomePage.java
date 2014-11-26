package com.premiumminds.wicket.demo;


import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.premiumminds.wicket.demo.repeaters.AjaxListSetViewPanel;
import com.premiumminds.wicket.demo.repeaters.ListViewPanel;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);
		
		add(new AjaxListSetViewPanel("ajaxListView", 5));
		add(new ListViewPanel("listView", 5));
    }
}
