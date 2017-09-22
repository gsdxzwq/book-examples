package com.zhaowq.javatest.effectivejava;

/**
 * builder模式
 */
public class NutritionFacts {
    private final int servingSize;// 这里只是列举比较少的参数
    private final int servings;
    private final int calaries;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // required parameters
        private final int servingSize;
        private final int servings;

        // optional parameters--initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder caloies(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }


    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calaries = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocacloa =
                new NutritionFacts.Builder(240, 8).caloies(100).sodium(35).carbohydrate(22).build();
        System.out.println("---");
    }
}
