Note: I set a Thread.sleep of 2000 milliseconds in the testEditRecipeTimeout() method inside the RecipeBookTest class, knowing that I set a class-level timeout of 1997 milliseconds to simulate a real-world scenario.
So, most of the time when you run the tests, the test will end successfully, but sometimes the test may fail.
I also used parallel execution to make the test execution faster.
