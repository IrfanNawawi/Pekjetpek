package com.mockdroid.pekjetpek.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_BEST
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_RANDOM
import com.mockdroid.pekjetpek.utils.Const.Companion.SORT_WORST

object SortUtils {
    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        if (filter == SORT_BEST) {
            simpleQuery.append("ORDER BY voteAverage DESC")
        } else if (filter == SORT_WORST) {
            simpleQuery.append("ORDER BY voteAverage ASC")
        } else if (filter == SORT_RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}