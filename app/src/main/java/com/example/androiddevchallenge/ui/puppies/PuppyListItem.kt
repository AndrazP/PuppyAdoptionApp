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
package com.example.androiddevchallenge.ui.puppies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.NetworkImage

@Composable
fun PuppyListItem(
    puppy: Puppy,
    onClick: () -> Unit
) {
    val typography = MaterialTheme.typography
    Column(
        Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        NetworkImage(
            url = puppy.thumbUrl,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(8.dp)),
        )

        Spacer(Modifier.height(8.dp))

        Row(Modifier.padding(bottom = 8.dp)) {
            Column(Modifier.weight(1f)) {
                Text(
                    puppy.name,
                    style = typography.h5,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    puppy.breed,
                    style = typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            PuppyGenderIcon(puppy)
        }
    }
}

@Composable
fun PuppyGenderIcon(puppy: Puppy) {
    when (puppy.sex) {
        Sex.MALE -> {
            Icon(
                Icons.Rounded.Male,
                contentDescription = "Localized description",
                tint = Color.Blue,
                modifier = Modifier.size(36.dp)
            )
        }
        else -> {
            Icon(
                Icons.Rounded.Female,
                contentDescription = "Localized description",
                tint = Color.Red,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Preview(name = "Puppy list item")
@Composable
private fun PuppyListItemPreviewLight() {
    PuppyListItemPreview(false)
}

@Preview(name = "Puppy list item – Dark")
@Composable
private fun PuppyListItemPreviewDark() {
    PuppyListItemPreview(true)
}

@Composable
private fun PuppyListItemPreview(darkTheme: Boolean) {
    MyTheme(darkTheme) {
        PuppyListItem(
            puppy = puppies.first(),
            onClick = {}
        )
    }
}
