# What's Cookin - Recipe Suggestion App

<p align="center">
    <img src="demo.gif" alt="Interpreter demo">
</p>

This project was created for my Mobile Application Development class and was submitted Dec. 2022

# Group Members

Ezekiel Halley, Katie Durkin, John Appel

# Third party libraries used:

- Material Icons Extended
- OkHTTP3
- Gson

# Resources Used

- [Dice's Coefficient Algorithm](https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Dice's_coefficient): Used for search bar functionality

# Program Usage

- After launching the app you'll be greeted with the pantry screen. The bottom bar features 2
  navigation options: Pantry (The initial screen), and Recipes.

## Pantry screen

- In the pantry screen you can view all ingredients that have been added to your pantry; this will
  be empty on first use.
- To add ingredients select the plus button which will navigate you to a new screen. See "Ingredient
  Selection Screen" section.
- To search through ingredients enter the ingredient name in the search bar located at the top. This
  search is based on similarity so the name does not need to be exact.
- Below the search bar you'll find the storage location filters. Simply select a filter and the
  pantry will be updated to showcase only ingredients stored in the associated location.
- To delete a pantry item hold one of the items and a delete process will be initiated. From there
  simply select all ingredients you wish to remove.
- When performing a delete the plus button will transform into a trash icon. Selecting this button
  will remove all selected ingredients.
- To exit delete mode simply uncheck all selected pantry items.

## Ingredient Selection Screen

- After pressing the plus button located in the pantry screen you will be taken to the ingredient
  selection screen.
- Ingredients can be selected from the list by tapping them. Multiple ingredients can be selected.
- The search bar allows you to search through all ingredients. The search bar is based on similarity
  so the name does not need to be exact.
- When at least 1 ingredient is selected a plus button. Selected this will navigate you to the "Add
  To Storage Screen"

## Add To Storage Screen

- In this screen each selected ingredient from the "Ingredient Selection Screen" will be displayed
- Here you can set the ingredient count and storage location for each.
- When clicking the plu button a confirmation will appear. If yes is selected you will be navigated
  back to the pantry screen with your ingredients added.

## Recipe Screen

- If ingredients exist in the pantry the recipe screen will generate meals that can be made with
  those ingredients.
- This generation takes a while so a loading bar will be displayed while it runs in the background.
- Once complete the screen will be populated with meals ordered from highest ingredient ratio to
  smallest
- Selecting a meal will navigate you to the "Recipe Details Screen" which houses information on that
  meal

## Recipe Details Screen

- In this screen you can view the ingredients and instructions on how to make the meal
- If the API features a youtube tutorial then a "Youtube Tutorial" button can be found at the
  bottom. Clicking this will open the youtube video in the youtube app.
-

# Notes on Tools Screen

Tools screen was intended to be a utility page featuring a timer and unit converter but it was
scrapped due to last moment hiccups.
