package ir.lb7.dindin.framework

import ir.lb7.dindin.usecase.AddOrder
import ir.lb7.dindin.usecase.DeleteOrder
import ir.lb7.dindin.usecase.GetOrders

class OrderInteractors(
    val addOrder: AddOrder,
    val getOrders: GetOrders,
    val deleteOrders: DeleteOrder
)