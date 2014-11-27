package com.premiumminds.wicket.demo.repeaters;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class ListViewPanel extends Panel {
	private int counter = 1;
	
	private List<String> list = new ArrayList<String>();
	
	private WebMarkupContainer container;

	public ListViewPanel(String id, int listSize) {
		super(id);
		
		// add dummy elements
		for(int i=1; i<listSize; i++){
			list.add("Test "+i);
		}

		// create the ListView
		ListView<String> listView = new ListView<String>("list", Model.ofList(list)) {
			
			@Override
			protected void populateItem(final ListItem<String> item) {
				item.add(new TextField<String>("textfield", item.getModel()));
				// remove button
				item.add(new Link("remove"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						list.remove(item.getModelObject());
						target.add(container);
					}
					
				});
				// up button
				item.add(new Link("up"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						String value = item.getModelObject();
						list.remove(item.getIndex());
						list.add(item.getIndex()-1, value);
						target.add(container);
					}
					
				});
				// down button
				item.add(new Link("down"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						String value = item.getModelObject();
						list.remove(item.getIndex());
						list.add(item.getIndex()+1, value);
						target.add(container);
					}
					
				});
			}
		};
		listView.setReuseItems(true);

		container = new WebMarkupContainer("container");
		container.add(listView);
		add(container.setOutputMarkupId(true));
		
		// add buttons
		add(new Link("addTop") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				list.add(0, "Added "+(counter++));
				target.add(container);
			}
		});
		add(new Link("addBottom") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				list.add("Added "+(counter++));
				target.add(container);
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
			attributes.setChannel(new AjaxChannel("list"));
		}
	}
	
}
