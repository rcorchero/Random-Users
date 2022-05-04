package com.rcorchero.data.entities.remote

import com.rcorchero.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "address") val address: AddressDto,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String,
    @Json(name = "company") val company: CompanyDto
) {
    @JsonClass(generateAdapter = true)
    data class AddressDto(
        @Json(name = "street") val street: String,
        @Json(name = "suite") val suite: String,
        @Json(name = "city") val city: String,
        @Json(name = "zipcode") val zipcode: String,
        @Json(name = "geo") val geo: GeoDto
    ) {
        @JsonClass(generateAdapter = true)
        data class GeoDto(
            @Json(name = "lat") val lat: String,
            @Json(name = "lng") val lng: String
        ) {
            fun toDomain(): User.Address.Geo = User.Address.Geo(
                lat = lat.toDouble(),
                lng = lng.toDouble()
            )
        }

        fun toDomain(): User.Address = User.Address(
            street = street,
            suite = suite,
            city = city,
            zipcode = zipcode,
            geo = geo.toDomain()
        )
    }

    @JsonClass(generateAdapter = true)
    data class CompanyDto(
        @Json(name = "name") val name: String,
        @Json(name = "catchPhrase") val catchPhrase: String,
        @Json(name = "bs") val bs: String
    ) {
        fun toDomain(): User.Company = User.Company(
            name = name,
            catchPhrase = catchPhrase,
            bs = bs
        )
    }

    fun toDomain(): User = User(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address.toDomain(),
        phone = phone,
        website = website,
        company = company.toDomain()
    )
}

fun List<UserDto>.toDomain(): List<User> = map { it.toDomain() }