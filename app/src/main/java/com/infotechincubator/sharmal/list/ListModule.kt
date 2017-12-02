package com.infotechincubator.sharmal.list

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.infotechincubator.sharmal.base.ActivityModule
import dagger.Module
import dagger.Provides

/**
 * Created by kyawagwin on 1/12/17.
 */
@Module
class ListModule(activity: AppCompatActivity): ActivityModule(activity) {

    @Provides
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(context)
}