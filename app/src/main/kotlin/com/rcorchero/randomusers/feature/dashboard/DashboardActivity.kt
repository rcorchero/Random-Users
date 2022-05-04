package com.rcorchero.randomusers.feature.dashboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rcorchero.presentation.asynchrony.StoreState
import com.rcorchero.presentation.base.ViewStore
import com.rcorchero.presentation.feature.dashboard.DashboardState
import com.rcorchero.presentation.feature.dashboard.DashboardStore
import com.rcorchero.randomusers.ui.designsystem.theme.RandomUsersTheme
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity(), ViewStore<DashboardState> {

    @Inject
    lateinit var store: DashboardStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        store.onCreate(this)
    }

    override fun StoreState<DashboardState>.render() {
        setContent {
            RandomUsersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DashboardScreen(store)
                }
            }
        }
    }
}