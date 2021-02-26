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
package com.example.androiddevchallenge.ui.puppy

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.Male
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PuppyRepo
import com.example.androiddevchallenge.data.puppies
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.model.Sex
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.util.NetworkImage
import com.example.androiddevchallenge.util.quantityStringResource
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun PuppyDetails(
    puppyId: Long,
    upPress: () -> Unit
) {
    // Simplified for the sample
    val puppy = remember(puppyId) { PuppyRepo.getPuppy(puppyId) }
    // TODO: Show error if course not found.
    PuppyDetails(puppy, upPress)
}

@Composable
fun PuppyDetails(
    puppy: Puppy,
    upPress: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            item { PuppyDetailsHeader(puppy, upPress) }
            item { PuppyDetailsDescription(puppy) }
            item { PuppyDetailsFooter(puppy, upPress) }
        }
    }
}

@Composable
fun PuppyDetailsHeader(
    puppy: Puppy,
    upPress: () -> Unit
) {
    Box {
        NetworkImage(
            url = puppy.thumbUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back"/*stringResource(R.string.label_back)*/
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PuppyDetailsDescription(puppy: Puppy) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Text(
                puppy.name,
                style = typography.h5,
                modifier = Modifier
                    .weight(1f)
            )

            PuppyGenderIcon(puppy)
        }

        Text(
            puppy.breed,
            style = typography.body1,
            color = Color.Gray,
        )

        Text(
            quantityStringResource(R.plurals.age_months, puppy.age, puppy.age),
            style = typography.body1,
            color = Color.Gray,
        )
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

@Composable
fun PuppyDetailsFooter(puppy: Puppy, upPress: () -> Unit) {

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        val openDialog = remember { mutableStateOf(false) }

        ShareButton(puppy, modifier = Modifier.padding(end = 16.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = {
                openDialog.value = true
            },
        ) {
            Text(stringResource(R.string.button_adoption))
        }

        if (openDialog.value) {
            ShowAdoptedAPuppyDialog(puppy, upPress)
        }
    }
}

@Composable
fun ShowAdoptedAPuppyDialog(puppy: Puppy, upPress: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.adopted_puppy_dialog_title)) },
        text = { Text(text = stringResource(R.string.adopted_puppy_dialog_text, puppy.name)) },
        confirmButton = {
            TextButton(onClick = upPress) {
                Text(stringResource(android.R.string.ok))
            }
        }
    )
}

@Composable
fun ShareButton(puppy: Puppy, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Icon(
        Icons.Rounded.Share,
        contentDescription = "Localized description",
        tint = Color.Gray,
        modifier = modifier
            .background(color = Color.LightGray, shape = CircleShape)
            .clickable {
                shareChallengeLink(context)
            }
            .padding(8.dp)
    )
}

private fun shareChallengeLink(context: Context) {
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(
        "label",
        "https://developer.android.com/dev-challenge#the-latest-challenge"
    )
    clipboard.setPrimaryClip(clip)

    Toast
        .makeText(context, "Link copied.", Toast.LENGTH_LONG)
        .show()
}

@Preview(name = "Course Details")
@Composable
private fun PuppyDetailsPreview() {
    val puppyId = puppies.first().id
    PuppyDetails(
        puppyId = puppyId,
        upPress = { }
    )
}
