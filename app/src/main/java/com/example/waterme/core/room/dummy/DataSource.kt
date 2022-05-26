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
package com.example.waterme.core.room.dummy

import com.example.waterme.core.room.model.PlantDB

object DataSource {
    val plants = listOf(
        PlantDB(
            id = 0,
            name = "Lithop",
            schedule = "Monthly",
            type = "Succulent",
            description = "Stone mimicking succulent"
        ),
        PlantDB(
            id = 1,
            name = "Carrot",
            schedule = "Daily",
            type = "Root",
            description = "Hardy root vegetable"
        ),
        PlantDB(
            id = 2,
            name = "Peony",
            schedule = "Weekly",
            type = "Flower",
            description = "Spring blooming flower"
        ),
        PlantDB(
            id = 3,
            name = "Pothos",
            schedule = "Weekly",
            type = "Houseplant",
            description = "Indoor vine"
        ),
        PlantDB(
            id = 4,
            name = "Fiddle Leaf Fig",
            schedule = "Weekly",
            type = "Broadleaf evergreen",
            description = "Ornamental fig"
        ),
        PlantDB(
            id = 5,
            name = "Strawberry",
            schedule = "Daily",
            type = "Fruit",
            description = "Delicious 'multiple fruit'"
        )
    )
}
