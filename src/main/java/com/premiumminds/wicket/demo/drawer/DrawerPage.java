package com.premiumminds.wicket.demo.drawer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.premiumminds.webapp.wicket.drawer.DrawerManager;
import com.premiumminds.wicket.demo.TemplatePage;

@SuppressWarnings("serial")
public class DrawerPage extends TemplatePage {
	private DrawerManager manager;
	
	public DrawerPage() {
		add(manager = new DrawerManager("drawerManager"));
		
		add(new AjaxLink<Void>("show"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				manager.push(new DrawerExample1(), target);
			}
			
		});
	}
}
