package ir.lb7.dindin.data

import io.reactivex.Observable
import ir.lb7.dindin.domain.Food

interface FoodDataSource {
    fun getFoods(): Observable<List<Food>>
}