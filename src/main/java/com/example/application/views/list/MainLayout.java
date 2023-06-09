package com.example.application.views.list;

import com.example.application.security.ServiceSecurity;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private ServiceSecurity serviceSecurity;

    public MainLayout(ServiceSecurity serviceSecurity) {
        this.serviceSecurity = serviceSecurity;

        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        H1 logo =new H1("My Web App");
        logo.addClassNames("text-l","m-m");

        Button logOut = new Button("Log Out", e -> serviceSecurity.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logOut);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createHeader() {
        RouterLink listView = new RouterLink("list", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listView
        ));
    }
}
