import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;


    @Test
    public void setBunsTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun,burger.bun);
    }

    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertFalse(burger.ingredients.isEmpty());
        assertThat(burger.ingredients, contains(ingredient));
    }

    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.isEmpty());
        assertThat(burger.ingredients, not(contains(ingredient)));

    }

    @Test
    public void moveIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0,2);
        assertEquals(2,burger.ingredients.indexOf(ingredient));
    }

    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Mockito.when(burger.bun.getPrice()).thenReturn(100.0F);
        Mockito.when(ingredient.getPrice()).thenReturn(100.0F);
        Mockito.when(ingredient1.getPrice()).thenReturn(150.0F);
        Mockito.when(ingredient2.getPrice()).thenReturn(250.0F);
        assertEquals(700,burger.getPrice(),0.0);
    }

    @Test
    public void getReceiptTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Mockito.when(burger.bun.getPrice()).thenReturn(100.0F);
        Mockito.when(burger.bun.getName()).thenReturn("WHEAT BUN");
        Mockito.when(ingredient.getPrice()).thenReturn(100.0F);
        Mockito.when(ingredient.getName()).thenReturn("chicken cutlet");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient1.getPrice()).thenReturn(150.0F);
        Mockito.when(ingredient1.getName()).thenReturn("ketchup");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getPrice()).thenReturn(250.0F);
        Mockito.when(ingredient2.getName()).thenReturn("pickled cucumber");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        String expectedReceipt = "(==== WHEAT BUN ====)\r\n" + "= filling chicken cutlet =\r\n" + "= sauce ketchup =\r\n" + "= filling pickled cucumber =\r\n" + "(==== WHEAT BUN ====)\r\n" + "\r\n" + "Price: 700,000000" + "\r\n" ;

        assertEquals(expectedReceipt,burger.getReceipt());
    }
}
