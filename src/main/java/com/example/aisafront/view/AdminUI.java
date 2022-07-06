package com.example.aisafront.view;

import com.aisa.coffee.client.api.RecipeControllerApi;
import com.aisa.coffee.client.model.CoffeeDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("api/v2/admin")
public class AdminUI extends VerticalLayout {

    public AdminUI(RecipeControllerApi recipeControllerApi) {

        TextField name = new TextField();
        IntegerField coffeeConsumption = new IntegerField();
        IntegerField milkConsumption = new IntegerField();
        IntegerField waterConsumption = new IntegerField();
        IntegerField purityConsumption = new IntegerField();
        IntegerField timeConsumption = new IntegerField();

        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(name,"Coffee name");
        formLayout.addFormItem(coffeeConsumption,"Coffee consumption");
        formLayout.addFormItem(milkConsumption,"Milk consumption");
        formLayout.addFormItem(waterConsumption,"Water consumption");
        formLayout.addFormItem(purityConsumption,"Purity consumption");
        formLayout.addFormItem(timeConsumption,"Time consumption");

        Button submit = new Button("Submit");
        submit.addClickListener(click -> {
            submit.setEnabled(false);

            CoffeeDTO coffeeDTO = new CoffeeDTO();
            coffeeDTO.setId(Long.parseLong(String.valueOf(recipeControllerApi.getRecipes().size() + 1)));
            coffeeDTO.setName(name.getValue());
            coffeeDTO.setCoffeeConsumption(coffeeConsumption.getValue());
            coffeeDTO.setMilkConsumption(milkConsumption.getValue());
            coffeeDTO.setWaterConsumption(waterConsumption.getValue());
            coffeeDTO.setTimeConsumption(timeConsumption.getValue());
            coffeeDTO.setPurityConsumption(purityConsumption.getValue());

            recipeControllerApi.addRecipe(coffeeDTO);

            Notification notification = Notification.show("Saved");
            notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addDetachListener(detachEvent -> submit.setEnabled(true));
        });

        add(formLayout, submit);
    }
}
