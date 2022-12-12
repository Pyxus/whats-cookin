Ezekiel Halley, Katie Durkin, John Appel


Third party libraries/resources used:
- Material Icons Extended
- OkHTTP3
- Gson
- https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Dice's_coefficient


       Program Usage
# After clicking on the app icon, the Pantry screen opens
# On bottom bar there are three buttons that will take you to some of the different screens
# The buttons will you take you to the Pantry screen(initial), Recipes screen, and Tools screen, respectively
# Press the android back button to return to the previous screen

# From the Pantry screen
# Pantry screen, by default, will show you all ingredients that have been added (will be empty upon first use)
# To search through the ingredients, click on the search bar
# While typing, ingredients that don't match will be filtered out
# We were able to implement a method that allows ingredients to show up even if the user misspelled them (Dice's coefficient which is in the above list for third-party resources)
# To exit the search bar click the android back button
# Below the search bar is a Lazy Row that contains buttons which, when clicked, will only display ingredients in that selected location
# To delete one or more of your ingredients, long click on the ingredient card which will make check boxes on all of the ingredients
# To delete the checked ingredients click the bottom right button whose icon will have turned into a trash can
# If no checkboxes are selected, the delete mode will end (checkboxes and delete button will disappear)
# While not in the delete mode the button at the bottom right will function as an "add ingredient" button

# After pressing the "add ingredient" button, the next screen will open
# Clicking on an ingredient card will add them to the list of ingredients to be added
# Ingredient card will change color to signify whether or not it has been selected
# To unselect click the ingredient card again
# At the top of the screen is a search box that can be used to search for a specific ingredient (similar functionality to the search box on the Pantry screen)
# Pressing the bottom right button again will navigate to the "Add to Storage" screen

# On the "Add to Storage" screen the ingredients selected on the previous screen will be there
# For each ingredient there are 2 boxes for user input
# Clicking on the left box will open a numpad which is used to enter the number of that ingredient that are to be added
# Clicking the android back button will exit the numpad
# The box on the right will open a dropdown menu where the location that the ingredient will be stored in is entered
# After this is done for all ingredients, pressing the bottom-right button again will open an alert dialogue box
# User will be asked if they are sure they want to add these items
# If the user selects no, nothing will happen and they will remain on the current screen
# If the user selects yes, they will be added and the Pantry screen(first screen) will open and the item will appear in the list of ingredients
# The ingredient can now be searched for, deleted, and will be used in looking up recipes that have it as an ingredient

# Back on the bottom bar, pressing "Recipes" will take the user to the Recipes screen
# The recipe screen will only have recipes populating the page if the user has ingredients in their database
# If there are ingredients, a loading bar will appear to show to show status of fetching recipes
# After finishing, the screen will show all recipes, in a lazy grid, that the user has ingredients for
# For each recipe, there will be an image, the name of the recipe, and the amount of ingredients that the user has for it
# Recipes for which the user has a greater percentage of ingredients will appear at the top
# Pressing on a recipe will open up a "Recipe Details" page for that ingredient

# On the Recipe Details screen will be an image of the recipe, the ingredients needed, and the instructions
# At the bottom of the screen there may be a button which when clicked will externally open a link to a YouTube video of the recipe being made

# Back on the bottom bar, the final option is Tools which will lead to a blank page


// Notes on Tools Screen \\
 Tools Screen is an extra utility page which is a Work In Progress
 Is supposed to have a timer and a unit converter (e.g. cups to teaspoons)
 Code currently being worked on can be found under the "tools" package under "screens"