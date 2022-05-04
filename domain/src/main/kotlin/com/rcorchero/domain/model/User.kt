package com.rcorchero.domain.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {

    data class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: Geo
    ) {

        data class Geo(
            val lat: Double,
            val lng: Double
        )

        fun getFullAddress() =
            "$street, $suite. $city ($zipcode)"
    }

    data class Company(
        val name: String,
        val catchPhrase: String,
        val bs: String
    ) {
        fun getTags() = bs.split(" ")
    }

    companion object {
        fun empty() =
            User(
                id = -1,
                name = "",
                username = "",
                email = "",
                address = Address(
                    street = "",
                    suite = "",
                    city = "",
                    zipcode = "",
                    geo = Address.Geo(
                        lat = 0.0,
                        lng = 0.0
                    )
                ),
                phone = "",
                website = "",
                company = Company(
                    name = "",
                    catchPhrase = "",
                    bs = ""
                )
            )
    }
}