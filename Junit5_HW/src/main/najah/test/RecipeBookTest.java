package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import main.najah.code.Calculator;
import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;

@Execution(ExecutionMode.CONCURRENT)
@Timeout(value = 1997, unit = TimeUnit.MILLISECONDS)
class RecipeBookTest {
	
	private RecipeBook recipeBook;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipe5; 

    @BeforeAll
	static void setUpBeforeClass() throws Exception {
    	System.out.println("Start Runing Calculator Tests");
	}
    
	@BeforeEach
	void setUp() throws Exception {
		recipeBook = new RecipeBook();

        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setPrice("150");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("2");
        recipe1.setAmtChocolate("0");

        recipe2 = new Recipe();
        recipe2.setName("Latte");
        recipe2.setPrice("225");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("3");
        recipe2.setAmtSugar("2");
        recipe2.setAmtChocolate("0");

        recipe3 = new Recipe();
        recipe3.setName("Mocha");
        recipe3.setPrice("350");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("1");
        recipe3.setAmtSugar("2");
        recipe3.setAmtChocolate("2");

        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setPrice("200");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("3");
        recipe4.setAmtSugar("2");
        recipe4.setAmtChocolate("4");

        recipe5 = new Recipe();
        recipe5.setName("Extra Coffee");
        recipe5.setPrice("175");
        recipe5.setAmtCoffee("4");
        recipe5.setAmtMilk("1");
        recipe5.setAmtSugar("2");
        recipe5.setAmtChocolate("0");
        
		System.out.println("Setup Completed");
	}
	
    @Test
    @DisplayName("Test RecipeBook Class GetRecipes Method returns an array of the correct size initially")
    void testGetRecipes_InitialSize() {
        assertEquals(4, recipeBook.getRecipes().length);
    }
    
    @Test
    @DisplayName("Test RecipeBook Class AddRecipe Method successfully adds a new recipe")
    void testAddRecipe() {
        assertTrue(recipeBook.addRecipe(recipe1));
        assertEquals(recipe1, recipeBook.getRecipes()[0]);
    }
    
    @Test
    @DisplayName("Test RecipeBook Class AddRecipe Method fails when the recipe book is full")
    void testAddRecipeInFullBook() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
        recipeBook.addRecipe(recipe4);
        assertFalse(recipeBook.addRecipe(recipe5));
    }
    
    @Test
    @DisplayName("Test RecipeBook Class AddRecipe Method rwturn false when trying to adds a already exists recipe when the recipe book is not full")
    void testAddExistRecipeInRecipeBook() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);

        assertFalse(recipeBook.addRecipe(recipe1));
    }
    
    @Test
    @DisplayName("Test RecipeBook Class AddRecipe Method rwturn false when trying to adds a already exists recipe when the recipe book is full")
    void testAddExistRecipeInFullRecipeBook() {
        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);
        recipeBook.addRecipe(recipe3);
        recipeBook.addRecipe(recipe4);
        assertFalse(recipeBook.addRecipe(recipe1));
    }
    
    @Test
    @DisplayName("Test RecipeBook Class DeleteRecipe Method successfully deletes an existing recipe")
    void testDeleteExistingRecipe() {
        recipeBook.addRecipe(recipe1);
        String deletedRecipeName = recipeBook.deleteRecipe(0);
        assertEquals("Coffee", deletedRecipeName);
        assertEquals("",recipeBook.getRecipes()[0].getName());
    }
    
    @Test
    @DisplayName("Test RecipeBook Class DeleteRecipe Method returns null if the recipe does not exist at the index")
    void testDeleteNotExistingRecipe() {
        assertNull(recipeBook.deleteRecipe(0));
    }

    @Test
    @Disabled("Fix the implementation to brevent seting the new recipe name to '' (empty string) -line 77-")
    @DisplayName("Test RecipeBook Class EditRecipe Method successfully edits an existing recipe")
    void testEditRecipe() throws RecipeException {
        recipeBook.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        
        newRecipe.setName("Strong Coffee");
        newRecipe.setPrice("200");
        newRecipe.setAmtCoffee("5");
        newRecipe.setAmtMilk("0");
        newRecipe.setAmtSugar("1");
        newRecipe.setAmtChocolate("0");
        
        String editedRecipeName = recipeBook.editRecipe(0, newRecipe);
        
        assertEquals("Coffee", editedRecipeName);
        assertEquals("Strong Coffee", recipeBook.getRecipes()[0].getName());
        assertEquals(200, recipeBook.getRecipes()[0].getPrice());
        assertEquals(5, recipeBook.getRecipes()[0].getAmtCoffee());
    }
    
    @Test
    @DisplayName("Test RecipeBook Class EditRecipe Method return null if the recipe to get edited is not in the recipe book")
    void testEditRecipeNotInBook() throws RecipeException {
        recipeBook.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        
        newRecipe.setName("Strong Coffee");
        newRecipe.setPrice("200");
        newRecipe.setAmtCoffee("5");
        newRecipe.setAmtMilk("0");
        newRecipe.setAmtSugar("1");
        newRecipe.setAmtChocolate("0");
        
        String editedRecipeName = recipeBook.editRecipe(1, newRecipe);
        
        assertNull(editedRecipeName);
    }
    
    
    @Test
    @DisplayName("Timeout Test RecipeBook Class for GetRecipes Method")
    void testGetRecipesTimeout() throws InterruptedException {
    	Thread.sleep(1000);
        recipeBook.getRecipes();
    }

    @Test
    @DisplayName("Timeout Test RecipeBook Class for AddRecipe ")
    void testAddRecipeTimeout() throws InterruptedException {
    	Thread.sleep(1000);
        recipeBook.addRecipe(recipe1);
    }

    @Test
    @DisplayName("Timeout Test RecipeBook Class for DeleteRecipe")
    void testDeleteRecipeTimeout() throws InterruptedException {
    	Thread.sleep(1000);
        recipeBook.addRecipe(recipe1);
        recipeBook.deleteRecipe(0);
    }

    @Test
    @DisplayName("Timeout Test RecipeBook Class for EditRecipe")//i add this values to make it work or not by chance to simulate a real work scenario which is unpredictable  
    void testEditRecipeTimeout() throws RecipeException, InterruptedException {
    	Thread.sleep(2000);
        recipeBook.addRecipe(recipe1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Strong Coffee");
        newRecipe.setPrice("200");
        recipeBook.editRecipe(0, newRecipe);
    }
    
   @AfterEach
    void tearDown() throws Exception {
        System.out.println("Test Completed");
    }
   
   @AfterAll
   static void tearDownAfterClass() throws Exception {
       System.out.println("All Calculator class methods Tests Completed");
   }

}
