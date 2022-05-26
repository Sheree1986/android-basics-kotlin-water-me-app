/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.waterme.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.waterme.core.repository.Repository
import com.example.waterme.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class HomeViewModel(private val repository: Repository, private val application: Application) :
    ViewModel() {

    fun getAllPlants() = repository.getAllPlants().asLiveData()

    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {
        val data = Data.Builder().putString(WaterReminderWorker.nameKey, plantName).build()

        val request =
            OneTimeWorkRequest.Builder(WaterReminderWorker::class.java)
                .setInputData(data)
                .setInitialDelay(duration, unit)
                .build()

        WorkManager.getInstance(application).apply {
            beginUniqueWork(plantName, ExistingWorkPolicy.REPLACE, request).enqueue()
        }
    }
}
