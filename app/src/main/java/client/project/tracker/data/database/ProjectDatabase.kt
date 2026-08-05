package client.project.tracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import client.project.tracker.data.entity.ProjectEntity
import client.project.tracker.data.dao.ProjectDao

@Database(
    entities = [
        ProjectEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDao

    companion object {
        const val DATABASE_NAME = "project_database"
    }
}