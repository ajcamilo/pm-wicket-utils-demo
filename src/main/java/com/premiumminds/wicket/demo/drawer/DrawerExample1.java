package com.premiumminds.wicket.demo.drawer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;

import com.premiumminds.webapp.wicket.drawer.AbstractDrawer;

@SuppressWarnings("serial")
public class DrawerExample1 extends AbstractDrawer {

	public DrawerExample1() {
		add(new AjaxLink<Void>("open") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getManager().push(new CustomDrawerExample(), target);
			}
		});
	}
}
