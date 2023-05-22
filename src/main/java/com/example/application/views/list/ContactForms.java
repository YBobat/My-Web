package com.example.application.views.list;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Status;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class ContactForms extends FormLayout {
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    EmailField email = new EmailField("Email");
    ComboBox<Status> status = new ComboBox<>("status");
    ComboBox<Company> company = new ComboBox<>("Company");
}
