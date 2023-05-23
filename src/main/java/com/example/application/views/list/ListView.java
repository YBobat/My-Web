package com.example.application.views.list;

import com.example.application.data.entity.Contact;
import com.example.application.data.service.ServiceClass;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.Collections;


@PageTitle("Contacts")
@Route(value = "")
public class ListView extends VerticalLayout {
    private ServiceClass service;
    Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filterText = new TextField();
    ContactForms form;

    public ListView(ServiceClass service) {
        this.service = service;
        addClassName("listveiw");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );
        updateList();

    }

    private void updateList() {
        grid.setItems(service.findAllContacts((filterText.getValue())));
    }

    private Component getContent(){
        HorizontalLayout  content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form =new ContactForms(service.findAllCompanies(), service.findAllStatuseds());
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {
        grid.addClassName("contactgrid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));


    }

}
