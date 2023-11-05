package com.kieronquinn.app.smartspacer.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kieronquinn.app.smartspacer.repositories.AtAGlanceRepository
import com.kieronquinn.app.smartspacer.sdk.utils.applySecurity
import com.kieronquinn.app.smartspacer.utils.extensions.verifySecurity
import org.koin.android.ext.android.inject

class AtAGlanceAboutActivity: AppCompatActivity() {

    private val atAGlanceRepository by inject<AtAGlanceRepository>()

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AtAGlanceAboutActivity::class.java).apply {
                applySecurity(context)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.verifySecurity()
        val state = atAGlanceRepository.getState()
        if(state?.clickPendingIntent != null && state.optionsIntent != null){
            startIntentSender(
                state.clickPendingIntent.intentSender,
                state.optionsIntent,
                0,
                0,
                0,
                null
            )
        }
        finishAndRemoveTask()
    }

}