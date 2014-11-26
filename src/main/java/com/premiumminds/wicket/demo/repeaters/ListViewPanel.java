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
		
		for(int i=1; i<listSize; i++){
			list.add("Test "+i);
		}

		ListView<String> listView = new ListView<String>("list", Model.ofList(list)) {
			
			@Override
			protected void populateItem(final ListItem<String> item) {
				item.add(new TextField<String>("textfield", item.getModel()));
				item.add(new Link("remove"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						list.remove(item.getModelObject());
						target.add(container);
					}
					
				});
				item.add(new Link("up"){

					@Override
					public void onClick(AjaxRequestTarget target) {
						String value = item.getModelObject();
						list.remove(item.getIndex());
						list.add(item.getIndex()-1, value);
						target.add(container);
					}
					
				});
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
		container = new WebMarkupContainer("container");
		container.add(listView);
		
		listView.setReuseItems(true);
		
		add(container.setOutputMarkupId(true));
		
		// buttons
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
