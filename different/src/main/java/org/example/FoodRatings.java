package org.example;

import java.util.*;

class FoodRatings {

    public static void main(String[] args) {
        String[] foods = new String[]{"tjokfmxg","xmiuwozpmj","uqklk","mnij","iwntdyqxi","cduc","cm","mzwfjk"};
        String[] cuisines = new String[] {"waxlau","ldpiabqb","ldpiabqb","waxlau","ldpiabqb","waxlau","waxlau","waxlau"};
        int[] ratings = new int[] {9,13,7,16,10,17,16,17};

        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);
        foodRatings.changeRating("tjokfmxg",19);
        System.out.println(foodRatings.highestRated("waxlau"));
        foodRatings.changeRating("uqklk",7);
        System.out.println(foodRatings.highestRated("waxlau"));
        System.out.println(foodRatings.highestRated("waxlau"));
        foodRatings.changeRating("tjokfmxg",14);
        System.out.println(foodRatings.highestRated("waxlau"));
        System.out.println(foodRatings.highestRated("waxlau"));
        foodRatings.changeRating("tjokfmxg",4);
        System.out.println(foodRatings.highestRated("waxlau"));
        foodRatings.changeRating("mnij",18);
        System.out.println(foodRatings.highestRated("waxlau"));


    }

    Map<String, PriorityQueue<FoodItem>> cuisineToRatingMap;
    Map<String, FoodItem> menu;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        cuisineToRatingMap = new HashMap<>();
        menu = new HashMap<>();

        for (int i = 0; i < foods.length ; i++) {

            if (!cuisineToRatingMap.containsKey(cuisines[i])) {
                PriorityQueue<FoodItem> queue  = new PriorityQueue<>((o1, o2) -> o1.rating == o2.rating ? o1.name.compareTo(o2.name) : o2.rating - o1.rating);
                cuisineToRatingMap.put(cuisines[i], queue);
            }
            FoodItem foodItem = new FoodItem(foods[i], cuisines[i], ratings[i]);
            cuisineToRatingMap.get(cuisines[i]).add(foodItem);
            menu.put(foods[i], foodItem);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodItem foodItem = menu.get(food);
        Queue<FoodItem> ratingQueue = cuisineToRatingMap.get(foodItem.cuisine);
        ratingQueue.remove(foodItem);
        foodItem.rating = newRating;

        ratingQueue.add(foodItem);
    }

    public String highestRated(String cuisine) {
        return cuisineToRatingMap.get(cuisine).peek().name;
    }
}

class FoodItem {
    String name;
    String cuisine;
    int rating;

    public FoodItem(String name, String cuisine, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
