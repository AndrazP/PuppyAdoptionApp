/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex

/**
 * A fake repo
 */
object PuppyRepo {
    fun getPuppy(puppyId: Long): Puppy = puppies.find { it.id == puppyId }!!
}

private const val imageParams = "?auto=format&w=1080&q=80"
val puppies = listOf(
    Puppy(
        1,
        "Darlene",
        "https://images.unsplash.com/photo-1513639725746-c5d3e861f32a$imageParams",
        "French Buldog",
        Sex.FEMALE,
        3
    ),
    Puppy(
        2,
        "Katherin",
        "https://images.unsplash.com/photo-1593134257782-e89567b7718a$imageParams",
        "Jack Russell Terrier",
        Sex.FEMALE,
        2
    ),
    Puppy(
        3,
        "Max",
        "https://images.unsplash.com/photo-1579112902044-211d42c6a4bb$imageParams",
        "Border Collie",
        Sex.MALE,
        6
    ),
    Puppy(
        4,
        "Bonnie",
        "https://images.unsplash.com/photo-1546985603-fb0ce8faada7",
        "Poodle",
        Sex.FEMALE,
        5
    ),
    Puppy(
        5,
        "Oakley",
        "https://images.unsplash.com/photo-1507146426996-ef05306b995a",
        "Golden Retriever",
        Sex.MALE,
        3
    ),
    Puppy(
        6,
        "Rambo",
        "https://images.unsplash.com/photo-1517982990603-1d52f060fe6a",
        "Pit Bull",
        Sex.MALE,
        7
    ),
    Puppy(
        7,
        "Chum",
        "https://images.unsplash.com/photo-1610385874395-e75e493098b7$imageParams",
        "Shih Tzu",
        Sex.MALE,
        1
    ),
    Puppy(
        8,
        "Bubbles",
        "https://images.unsplash.com/photo-1508532566027-b2032cd8a715$imageParams",
        "Maltese",
        Sex.MALE,
        2
    ),
    Puppy(
        9,
        "Riley",
        "https://images.unsplash.com/photo-1577447161356-a9e4dcca6964",
        "King Charles Spaniel",
        Sex.FEMALE,
        4
    ),
    Puppy(
        10,
        "Martha",
        "https://images.unsplash.com/photo-1591946653358-5a2cf4c2a1bc$imageParams",
        "Cavalier King Charles Spaniel",
        Sex.FEMALE,
        5
    ),
)
