package com.example.aisafront.view;

import com.aisa.coffee.client.api.RecipeControllerApi;
import com.aisa.coffee.client.api.WorkControllerApi;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("api/v2")
public class UserUI extends VerticalLayout {

    VerticalLayout layout;
    Button statusButton;
    Button coffeeButton;
    Button containerButton;
    ComboBox<String> coffeeBox = new ComboBox<>("Coffee");
    ComboBox<String> containerBox = new ComboBox<>("Container");

    public UserUI(RecipeControllerApi recipeControllerApi, WorkControllerApi workControllerApi) {
        layout = new VerticalLayout();

        statusButton = new Button("Get status");
        statusButton.addClickListener(click -> {
            String status = workControllerApi.getStatus();

            if (status != null) {
                statusButton.setEnabled(false);
                Notification notification = Notification.show(status);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
            }
        });

        coffeeButton = new Button("Make coffee");
        coffeeButton.addClickListener(click -> {
            statusButton.setEnabled(false);
            workControllerApi.makeCoffee(coffeeBox.getValue());
            Notification notification = Notification.show(workControllerApi.getStatus());
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
        });

        containerButton = new Button("Update container");
        containerButton.addClickListener(click -> {
            statusButton.setEnabled(false);
            workControllerApi.updateContainer(containerBox.getValue());
            Notification notification = Notification.show(workControllerApi.getStatus());
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> statusButton.setEnabled(true));
        });

        coffeeBox.setItems(recipeControllerApi.getRecipes());

        containerBox.setItems("Coffee", "Milk", "Water");

        HorizontalLayout statusLayout = new HorizontalLayout(statusButton);
        HorizontalLayout coffeeLayout = new HorizontalLayout(coffeeBox, coffeeButton);
        HorizontalLayout containerLayout = new HorizontalLayout(containerBox, containerButton);

        add(
                statusLayout,
                coffeeLayout,
                containerLayout
        );
    }
}
