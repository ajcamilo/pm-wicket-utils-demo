package com.premiumminds.wicket.demo.repeaters;

import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.premiumminds.webapp.wicket.repeaters.AjaxListSetView;
import com.premiumminds.webapp.wicket.repeaters.ListSetItem;

@SuppressWarnings("serial")
public class AjaxListSetViewPanel extends Panel {
	private int counter = 1;
	
	private ListOrderedSet<String> list = new ListOrderedSet<String>();
	
	private AjaxListSetView<String> ajaxList;

	public AjaxListSetViewPanel(String id, int listSize) {
		super(id);
		
		// add dummy elements
		for(int i=1; i<listSize; i++){
			list.add("Test "+i);
		}

		// create the AjaxListSetView
		ajaxList = new AjaxListSetView<String>("ajaxList", new Model<ListOrderedSet<String>>(list), "tbody"){

			@Override
			protected void populateItem(final ListSetItem<String> item) {
				item.add(new TextField<String>("textfield", item.getModel()));
				// remove button
				item.add(new Link("remove"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						list.remove(item.getModelObject());
						target.add(ajaxList);
					}
					
				});
				// up button
				item.add(new Link("up"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						int idx = list.indexOf(item.getModelObject());
						list.remove(item.getModelObject());
						list.add(idx-1, item.getModelObject());
						target.add(ajaxList);
					}
					
				});
				// down button
				item.add(new Link("down"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						int idx = list.indexOf(item.getModelObject());
						list.remove(item.getModelObject());
						list.add(idx+1, item.getModelObject());
						target.add(ajaxList);
					}
					
				});
			}
			
		};

		add(ajaxList);
		
		// add buttons
		add(new Link("addTop") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				list.add(0, "Added "+(counter++));
				
				target.add(ajaxList);
			}
		});
		add(new Link("addBottom") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				list.add("Added "+(counter++));
				
				target.add(ajaxList);
			}
		});
		
	}

	/*
	 * Class used the distinguish ajax requests, so we can show each panel request size.
	 */
	private static abstract class Link extends AjaxLink<Void> {

		public Link(String id) {
			super(id);
		}

		@Override
		protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
			super.updateAjaxAttributes(attributes);
			attributes.setChannel(new AjaxChannel("ajaxList"));
		}
	}
}
