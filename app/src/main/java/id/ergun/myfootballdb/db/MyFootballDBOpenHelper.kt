package id.ergun.myfootballdb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyFootballDBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        private var instance: MyFootballDBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyFootballDBOpenHelper {
            if (instance == null) {
                instance = MyFootballDBOpenHelper(ctx.applicationContext)
            }
            return instance as MyFootballDBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchFavorite.TABLE_MATCH_FAVORITE, true,
                MatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchFavorite.ID_EVENT to INTEGER + UNIQUE,
                MatchFavorite.DATE_EVENT to TEXT,
                MatchFavorite.ID_HOME_TEAM to INTEGER,
                MatchFavorite.HOME_TEAM to TEXT,
                MatchFavorite.HOME_SCORE to INTEGER,
                MatchFavorite.HOME_FORMATION to TEXT,
                MatchFavorite.HOME_GOAL_DETAILS to TEXT,
                MatchFavorite.HOME_SHOTS to INTEGER,
                MatchFavorite.HOME_LINEUP_GOALKEEPER to TEXT,
                MatchFavorite.HOME_LINEUP_DEFENSE to TEXT,
                MatchFavorite.HOME_LINEUP_MIDFIELDER to TEXT,
                MatchFavorite.HOME_LINEUP_FORWARD to TEXT,
                MatchFavorite.HOME_LINEUP_SUBSTITUTES to TEXT,
                MatchFavorite.HOME_BADGE to TEXT,
                MatchFavorite.ID_AWAY_TEAM to INTEGER,
                MatchFavorite.AWAY_TEAM to TEXT,
                MatchFavorite.AWAY_SCORE to INTEGER,
                MatchFavorite.AWAY_FORMATION to TEXT,
                MatchFavorite.AWAY_GOAL_DETAILS to TEXT,
                MatchFavorite.AWAY_SHOTS to INTEGER,
                MatchFavorite.AWAY_LINEUP_GOALKEEPER to TEXT,
                MatchFavorite.AWAY_LINEUP_DEFENSE to TEXT,
                MatchFavorite.AWAY_LINEUP_MIDFIELDER to TEXT,
                MatchFavorite.AWAY_LINEUP_FORWARD to TEXT,
                MatchFavorite.AWAY_LINEUP_SUBSTITUTES to TEXT,
                MatchFavorite.AWAY_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchFavorite.TABLE_MATCH_FAVORITE, true)
    }
}

val Context.database: MyFootballDBOpenHelper
    get() = MyFootballDBOpenHelper.getInstance(applicationContext)