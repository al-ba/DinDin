package ir.lb7.dindin.framework

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ir.lb7.dindin.data.FoodDataSource
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.FoodType

class FakeFoodDataSource : FoodDataSource {

    override fun getFoods(): Observable<List<Food>> {
        return Observable.fromCallable {
            var id = 1
            listOf(
                Food(
                    id++,
                    "Hawaiian",
                    "Chicken, Mozzarella, Pineapple, Domino's sauce",
                    "200 grams, 35 cm",
                    "https://www.jessicagavin.com/wp-content/uploads/2020/07/hawaiian-pizza-16-1200.jpg",
                    35.0,
                    FoodType.PIZZA
                ),
                Food(
                    id++,
                    "Pepperoni",
                    "Mushrooms, Pepperoni, and fresh Mozzarella cheese",
                    "150 grams, 35 cm",
                    "https://static.onecms.io/wp-content/uploads/sites/19/2014/07/10/pepperoni-pizza-ck-x.jpg",
                    30.0,
                    FoodType.PIZZA
                ),
                Food(
                    id++,
                    "Meat",
                    "Olive oil, spicy Italian sausage, pepperoni, ham, bacon.",
                    "220 grams, 30 cm",
                    "https://d2z58dca51ori0.cloudfront.net/e78f0ab3-ca29-4b35-911d-c711959d4d71.png",
                    40.0,
                    FoodType.PIZZA
                ),
                Food(
                    id++,
                    "Beef",
                    "Ground Beef, bell peppers, onions and garlic.",
                    "190 grams, 25 cm",
                    "https://images-gmi-pmc.edge-generalmills.com/1e79b288-56b8-4003-a72e-ad4cec03209f.jpg",
                    25.0,
                    FoodType.PIZZA
                ),
                Food(
                    id++,
                    "Makizushi",
                    "Seaweed, vinegared rice and fish ",
                    "400 grams, 18 pieces",
                    "https://www.rotinrice.com/wp-content/uploads/2010/02/Maki-Zushi-1.jpg",
                    55.0,
                    FoodType.SUSHI
                ),
                Food(
                    id++,
                    "Temaki",
                    "Tuna, salmon, shrimp, crab meat",
                    "450 grams, 16 pieces",
                    "https://iheartumami.com/wp-content/uploads/2019/09/Temaki-Sushi-Low-Carb-Tuna-Temaki-1-500x500.jpg",
                    65.0,
                    FoodType.SUSHI
                ),
                Food(
                    id++,
                    "Coca-Cola",
                    "Carbonated water, Sugar, and Caffeine",
                    "250 ml",
                    "https://thumbor.thedailymeal.com/Ci4_whkzoJJaDBWm7flSvOAIL2w=/870x565/https://www.thedailymeal.com/sites/default/files/2019/06/13/iStock-458297599_0.jpg",
                    10.0,
                    FoodType.DRINK
                ),
                Food(
                    id++,
                    "Margarita",
                    "Tequila, orange liqueur, and lime juice",
                    "120 ml",
                    "https://cdn.diffords.com/contrib/stock-images/2016/7/08/2016a348ab245bf9d1d830786811fdbb6e89.jpg",
                    20.0,
                    FoodType.DRINK
                )
            )
        }.subscribeOn(Schedulers.io())
    }
}