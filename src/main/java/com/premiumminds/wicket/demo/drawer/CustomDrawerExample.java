package com.premiumminds.wicket.demo.drawer;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.model.PropertyModel;

import com.premiumminds.webapp.wicket.drawer.AbstractDrawer;

@SuppressWarnings("serial")
public class CustomDrawerExample extends AbstractDrawer {

	public CustomDrawerExample() {
		add(new AjaxCheckBox("close", new PropertyModel<Boolean>(this, "allowClose")) {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			}
		});
		
		add(new AjaxLink<Void>("open") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				getManager().push(new CustomDrawerExample(), target);
			}
		});
	}
}
