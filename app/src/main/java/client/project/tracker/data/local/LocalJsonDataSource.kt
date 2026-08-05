package client.project.tracker.data.local

import android.content.Context
import client.project.tracker.data.dto.ProjectDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalJsonDataSource(
    private val context: Context
) {

    fun getProjects(): List<ProjectDto> {

        val json = context.assets
            .open("test_data.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<ProjectDto>>() {}.type

        return Gson().fromJson(json, type)
    }
}
