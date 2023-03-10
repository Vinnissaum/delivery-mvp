package com.vinissaum.deliverymvp;

import com.vinissaum.deliverymvp.domain.exceptions.EntityInUseException;
import com.vinissaum.deliverymvp.domain.exceptions.ResourceNotFoundException;
import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.model.Restaurant;
import com.vinissaum.deliverymvp.domain.services.KitchenService;
import com.vinissaum.deliverymvp.domain.services.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DeliveryMvpIT {

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private RestaurantService restaurantService;

	@Test
	public void shouldInsertNewKitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Chinese");

        kitchen = kitchenService.insert(kitchen);

        assertThat(kitchen).isNotNull();
        assertThat(kitchen.getId()).isNotNull();
    }

    @Test
    public void shouldThrowAnException_WhenInsertKitchenWithoutName() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Kitchen kitchen = new Kitchen();
            kitchen.setName(null);

            kitchenService.insert(kitchen);
        });
    }

    @Test
    public void shouldThrowAnException_WhenDeleteInUseKitchen() {
        assertThrows(EntityInUseException.class, () -> {
            Restaurant restaurant = new Restaurant();
            Kitchen kitchen = new Kitchen();
            kitchen.setName("test");
            kitchen = kitchenService.insert(kitchen);
            restaurant.setKitchen(kitchen);

            restaurantService.insert(restaurant);


            kitchenService.delete(kitchen.getId());
        });
    }

    @Test
    public void shouldThrowAnException_WhenDeleteANonExistingKitchen() {
        assertThrows(ResourceNotFoundException.class, () -> {
            Kitchen kitchen = new Kitchen();
            kitchen.setName("test");
            kitchen = kitchenService.insert(kitchen);
            //Deleting first time
            kitchenService.delete(kitchen.getId());
            //Should fail because does not exist anymore
            kitchenService.delete(kitchen.getId());
        });
    }

}
