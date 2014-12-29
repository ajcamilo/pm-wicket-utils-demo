package com.premiumminds.wicket.demo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.premiumminds.wicket.demo.bootstrap.BootstrapPaginatorPage;
import com.premiumminds.wicket.demo.drawer.DrawerPage;
import com.premiumminds.wicket.demo.repeaters.AjaxListSetViewPage;

@SuppressWarnings("serial")
public class TemplatePage extends WebPage {
	
	public TemplatePage() {
		add(new Menu("listSetView", AjaxListSetViewPage.class));
		add(new Menu("drawer", DrawerPage.class));
		add(new Menu("bootstrapPaginator", BootstrapPaginatorPage.class));
	}

	public class Menu extends WebMarkupContainer {

		public Menu(String id, Class<? extends TemplatePage> pageClass) {
			super(id);
			
			add(new BookmarkablePageLink<TemplatePage>("link", pageClass));
			
			if(TemplatePage.this.getClass().equals(pageClass)){
				add(AttributeModifier.append("class", "active"));
			}
		}
		
	}
}
