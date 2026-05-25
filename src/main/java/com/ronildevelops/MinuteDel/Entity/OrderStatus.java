package com.ronildevelops.MinuteDel.Entity;

public enum OrderStatus {



        PENDING,      // Order placed by customer; awaiting payment verification/system confirmation
        CONFIRMED,    // Order confirmed; dark store has received it and is preparing to pack
        PACKED,       // Items packed into bags by store staff; waiting for rider pickup
        PICKED_UP,    // Rider has picked up the bags and is currently en route to the customer
        DELIVERED,    // Order successfully delivered to the customer's doorstep (Terminal State)
        CANCELLED    // Order aborted due to payment failure, stock issues, or system rejection (Terminal State)

}
