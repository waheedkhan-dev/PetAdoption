@file:OptIn(ExperimentalMaterial3Api::class)

package com.codenablers.petadoption.presentation.screens.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codenablers.petadoption.R
import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.presentation.graph.Graph
import com.codenablers.petadoption.ui.theme.Card_Background_Color
import com.codenablers.petadoption.ui.theme.Chip_Border_Color
import com.codenablers.petadoption.ui.theme.robotoFamily
import java.time.format.TextStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PetDetailScreen(navController: NavController, prettyPet: Pets.Hit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.detail),//stringResource(id = currentScreen.title),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            },
            navigationIcon = {

                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back icon",
                        tint = Color.White
                    )
                }

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Chip_Border_Color)
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(prettyPet.largeImageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.profile_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    })
}