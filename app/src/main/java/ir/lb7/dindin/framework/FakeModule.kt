package ir.lb7.dindin.framework

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import ir.lb7.dindin.data.FoodDataSource
import ir.lb7.dindin.data.FoodRepository
import ir.lb7.dindin.data.OrdersDataSource
import ir.lb7.dindin.data.OrdersRepository
import ir.lb7.dindin.usecase.AddOrder
import ir.lb7.dindin.usecase.DeleteOrder
import ir.lb7.dindin.usecase.GetFoods
import ir.lb7.dindin.usecase.GetOrders
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object FakeModule {
    @Provides
    @Singleton
    fun provideFakeDataSource(): FoodDataSource = FakeFoodDataSource()

    @Provides
    fun provideFoodRepository(dataSource: FoodDataSource): FoodRepository {
        return FoodRepository(dataSource)
    }

    @Provides
    fun provideGetFoods(repository: FoodRepository): GetFoods {
        return GetFoods(repository)
    }

    @Provides
    fun provideFoodInteractors(getFoods: GetFoods): FoodInteractors {
        return FoodInteractors(getFoods)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(): OrdersDataSource = OrdersDataSourceImpl()

    @Provides
    fun provideLocalRepository(dataSource: OrdersDataSource): OrdersRepository {
        return OrdersRepository(dataSource)
    }

    @Provides
    fun provideAddOrder(repository: OrdersRepository): AddOrder {
        return AddOrder(repository)
    }

    @Provides
    fun provideGetOrders(repository: OrdersRepository): GetOrders {
        return GetOrders(repository)
    }

    @Provides
    fun provideDeleteOrder(repository: OrdersRepository): DeleteOrder {
        return DeleteOrder(repository)
    }

    @Provides
    fun provideOrderInteractors(
        addOrder: AddOrder,
        getOrders: GetOrders,
        deleteOrder: DeleteOrder
    ): OrderInteractors {
        return OrderInteractors(addOrder, getOrders, deleteOrder)
    }
}