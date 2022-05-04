package com.rcorchero.presentation.common

import com.rcorchero.domain.model.User
import com.rcorchero.presentation.asynchrony.AsynchronyManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

object FakeData {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun mockAsynchronyManager() =
        AsynchronyManagerImpl(
            coroutineContext = UnconfinedTestDispatcher(),
            io = UnconfinedTestDispatcher()
        )

    fun mockUserList() = listOf(
        User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = User.Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = User.Address.Geo(
                    lat = -37.3159,
                    lng = 81.1496
                ),
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = User.Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        ),
        User(
            id = 2,
            name = "Ervin Howell",
            username = "Antonette",
            email = "Shanna@melissa.tv",
            address = User.Address(
                street = "Victor Plains",
                suite = "Suite 879",
                city = "Wisokyburgh",
                zipcode = "90566-7771",
                geo = User.Address.Geo(
                    lat = -43.9509,
                    lng = -34.4618
                )
            ),
            phone = "010-692-6593 x09125",
            website = "anastasia.net",
            company = User.Company(
                name = "Deckow-Crist",
                catchPhrase = "Proactive didactic contingency",
                bs = "synergize scalable supply-chains"
            )
        ),
        User(
            id = 3,
            name = "Clementine Bauch",
            username = "Samantha",
            email = "Nathan@yesenia.net",
            address = User.Address(
                street = "Douglas Extension",
                suite = "Suite 847",
                city = "McKenziehaven",
                zipcode = "59590-4157",
                geo = User.Address.Geo(
                    lat = -68.6102,
                    lng = -47.0653
                )
            ),
            phone = "1-463-123-4447",
            website = "ramiro.info",
            company = User.Company(
                name = "Romaguera-Jacobson",
                catchPhrase = "Face to face bifurcated interface",
                bs = "e-enable strategic applications"
            )
        )
    )
}