package com.linkdev.messengerinternshiptask.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.linkdev.messengerinternshiptask.R
import com.linkdev.messengerinternshiptask.ui.utils.Constants.DAY
import com.linkdev.messengerinternshiptask.ui.utils.Constants.HOUR
import com.linkdev.messengerinternshiptask.ui.utils.Constants.MINUTE
import com.linkdev.messengerinternshiptask.ui.utils.Constants.MONTH
import com.linkdev.messengerinternshiptask.ui.utils.Constants.YEAR
import java.util.Calendar



@Composable
fun Long?.toTimeAgo(): String {
    val time = this
    val calendar = Calendar.getInstance()
    val currentTime = calendar.timeInMillis
    val diff = (currentTime - (time ?: 0L)) / 1000
    return when {
        diff < MINUTE -> stringResource(id = R.string.just_now)
        diff < 2 * MINUTE -> stringResource(id = R.string.one_minute_age)
        diff < 60 * MINUTE -> stringResource(
            id = R.string.several_minutes_ago,
            (diff / MINUTE).toString()
        )

        diff < 2 * HOUR -> stringResource(id = R.string.hour_ago)
        diff < 24 * HOUR -> stringResource(id = R.string.hour_ago, (diff / HOUR).toString())
        diff < 2 * DAY -> stringResource(id = R.string.yesterday)
        diff < 30 * DAY -> stringResource(id = R.string.several_days_ago, (diff / DAY).toString())
        diff < 2 * MONTH -> stringResource(id = R.string.month_ago)
        diff < 12 * MONTH -> stringResource(
            id = R.string.several_months_ago,
            (diff / MONTH).toString()
        )
        diff < 2 * YEAR -> stringResource(id = R.string.year_ago)
        else -> stringResource(id = R.string.several_years_ago, (diff / YEAR).toString())
    }
}
