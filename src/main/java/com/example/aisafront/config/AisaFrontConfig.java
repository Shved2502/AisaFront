package com.example.aisafront.config;

import com.aisa.coffee.client.api.RecipeControllerApi;
import com.aisa.coffee.client.api.WorkControllerApi;
import com.aisa.coffee.client.invoker.ApiClient;
import com.aisa.coffee.client.model.CoffeeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AisaFrontConfig {

    @Bean
    public CoffeeDTO coffeeDTO(){
        return new CoffeeDTO();
    }

    @Bean
    public RecipeControllerApi recipeControllerApi() {
        return new RecipeControllerApi(apiClient());
    }

    @Bean
    public WorkControllerApi workControllerApi() {
        return new WorkControllerApi(apiClient());
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }
}
