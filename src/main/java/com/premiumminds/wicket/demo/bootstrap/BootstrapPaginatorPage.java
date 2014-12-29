package com.premiumminds.wicket.demo.bootstrap;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.premiumminds.webapp.wicket.bootstrap.BootstrapPaginator;
import com.premiumminds.wicket.demo.TemplatePage;

@SuppressWarnings("serial")
public class BootstrapPaginatorPage extends TemplatePage {
	private BootstrapPaginator paginator;
	
	private Form<Void> form;
	
	public BootstrapPaginatorPage() {
		paginator = new BootstrapPaginator("paginator", Model.of(1000), 20) {
			
			@Override
			public void onPageChange(AjaxRequestTarget target, IModel<Integer> page) {
				target.add(form);
			}
		};
		
		add(paginator);
		addForm();
	}
	
	private void addForm(){
		form = new Form<Void>("form");
		
		form.add(new TextField<Integer>("currentPage", new PropertyModel<Integer>(paginator, "modelObject"), Integer.class));
		form.add(new TextField<Integer>("resultsPerPage", new PropertyModel<Integer>(paginator, "numberResultsPerPage"), Integer.class));
		form.add(new TextField<Integer>("pagesToShow", new PropertyModel<Integer>(paginator, "pagesToShow"), Integer.class));
		form.add(new TextField<Integer>("totalResults", new PropertyModel<Integer>(paginator, "totalResults.object"), Integer.class));
		form.add(new CheckBox("hiddenFirstButton", new PropertyModel<Boolean>(paginator, "hiddenFirstButton")));
		form.add(new CheckBox("hiddenLastButton", new PropertyModel<Boolean>(paginator, "hiddenLastButton")));
		form.add(new CheckBox("hiddenNextButton", new PropertyModel<Boolean>(paginator, "hiddenNextButton")));
		form.add(new CheckBox("hiddenPreviousButton", new PropertyModel<Boolean>(paginator, "hiddenPreviousButton")));
		form.add(new CheckBox("showFirstButton", new PropertyModel<Boolean>(paginator, "showFirstButton")));
		form.add(new CheckBox("showLastButton", new PropertyModel<Boolean>(paginator, "showLastButton")));
		form.add(new CheckBox("showNextButton", new PropertyModel<Boolean>(paginator, "showNextButton")));
		form.add(new CheckBox("showPreviousButton", new PropertyModel<Boolean>(paginator, "showPreviousButton")));
		form.add(new AjaxSubmitLink("submit") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(paginator);
			}
		});
		add(form);
	}
}
