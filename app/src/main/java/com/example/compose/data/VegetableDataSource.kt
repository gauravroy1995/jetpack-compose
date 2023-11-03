package com.example.compose.data


data class Vegetable(
    val id: Int,
    val name: String,
    val benefits: String,
    val price: Double,
)

data class Entree(
    val id: Int,
    val name: String,
    val benefits: String,
    val price: Double,
)

data class Accompany(
    val id: Int,
    val name: String,
    val benefits: String,
    val price: Double,
)


object DataSource {
    val vegetables = listOf<Vegetable>(
        Vegetable(
            id = 1,
            name = "Tomato",
            benefits = "Tomatoes are the major dietary source of the antioxidant lycopene, which has been linked to many health benefits, including reduced risk of heart disease and cancer. They are also a great source of vitamin C, potassium, folate, and vitamin K.",
            price = 1.99
        ),
        Vegetable(
            id = 2,
            name = "Potato",
            benefits = "Potatoes are a good source of several vitamins and minerals, including potassium, folate, and vitamins C and B6. They are also an excellent source of resistant starch, which may help reduce insulin resistance.",
            price = 2.99
        ),
        Vegetable(
            id = 3,
            name = "Onion",
            benefits = "Onions are low in calories yet high in nutrients, including vitamin C, B vitamins and potassium. They are also rich in antioxidants, such as quercetin, and compounds that may help reduce inflammation, such as sulfur compounds and saponins.",
            price = 3.99
        ),
        Vegetable(
            id = 4,
            name = "Carrot",
            benefits = "Carrots are a particularly good source of beta carotene, fiber, vitamin K1, potassium, and antioxidants. They also have a number of health benefits. They're a weight-loss-friendly food and have been linked to lower cholesterol levels and improved eye health.",
            price = 4.99
        ),
        Vegetable(
            id = 5,
            name = "Cabbage",
            benefits = "Cabbage is a good source of vitamin K, vitamin C and fiber. It also contains decent amounts of vitamin B6, folate, manganese, and various other nutrients.",
            price = 5.99
        ),
        Vegetable(
            id = 6,
            name = "Cauliflower",
            benefits = "Cauliflower is an extremely healthy vegetable that's a significant source of nutrients. It also contains unique plant compounds that may reduce the risk of several diseases, including heart disease and cancer.",
            price = 6.99
        ),
        Vegetable(
            id = 7,
            name = "Broccoli",
            benefits = "Broccoli is a good source of fiber and protein, and contains iron, potassium, calcium, selenium and magnesium as well as the vitamins A, C, E, K and a good array of B vitamins including folic acid.",
            price = 7.99
        ),
        Vegetable(
            id = 8,
            name = "Spinach",
            benefits = "Spinach is an excellent source of many vitamins and minerals, including (3): Vitamin A. Spinach is high in carotenoids, which your body can turn into vitamin A.",
            price = 8.99
        ),
    )
    val entrees = listOf<Entree>(
        Entree(
            id = 1,
            name = "Cake",
            benefits = "Cake is good resource of energy",
            price = 1.99
        ),
        Entree(
            id = 2,
            name = "Bread",
            benefits = "Bread is good resource of energy and it has fibre content",
            price = 2.99
        ),
        Entree(
            id = 3,
            name = "Biscuit",
            benefits = "Biscuit is good resource of energy and it has fibre content",
            price = 3.99
        ),
        Entree(
            id = 4,
            name = "Pizza",
            benefits = "Pizza is good resource of energy and it has fibre content",
            price = 4.99
        ),
    )
    val accompanies = listOf<Accompany>(
        Accompany(
            id = 1,
            name = "Coke",
            benefits = "Coke is good resource of energy",
            price = 1.99
        ),
        Accompany(
            id = 2,
            name = "Pepsi",
            benefits = "Pepsi is good resource of energy and it has fibre content",
            price = 2.99
        ),
    )
}


