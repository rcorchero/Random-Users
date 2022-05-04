package com.rcorchero.randomusers.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.fintonic.presentation.common.args.WithArgs
import com.rcorchero.presentation.asynchrony.StoreState
import com.rcorchero.presentation.base.ViewStore
import com.rcorchero.presentation.feature.detail.DetailArgs
import com.rcorchero.presentation.feature.detail.DetailState
import com.rcorchero.presentation.feature.detail.DetailStore
import com.rcorchero.randomusers.ui.designsystem.theme.RandomUsersTheme
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity(), ViewStore<DetailState>,
    WithArgs<DetailArgs> {

    override val getArgs by getArgs {
        DetailArgs(userId = intent.getIntExtra(USER_ID, -1))
    }

    @Inject
    lateinit var store: DetailStore

    companion object {
        private const val USER_ID = "USER_ID"

        operator fun invoke(
            context: Context,
            userId: Int,
        ) = Intent(context, DetailActivity::class.java).apply {
            putExtra(USER_ID, userId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        store.onCreate(this)
    }

    override fun onBackPressed() {
        store.goBack()
    }

    override fun StoreState<DetailState>.render() {
        setContent {
            RandomUsersTheme {
                DetailScreen(store)
            }
        }
    }
}