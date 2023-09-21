@file:OptIn(ExperimentalMaterial3Api::class)

package com.codenablers.petadoption.presentation.screens.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.codenablers.petadoption.R
import com.codenablers.petadoption.components.PetCardComposable
import com.codenablers.petadoption.presentation.graph.DetailsScreen
import com.codenablers.petadoption.ui.theme.Blue_Light
import com.codenablers.petadoption.ui.theme.robotoFamily

@Composable
fun PetScreen(navController: NavController, petViewModel: PetViewModel) {
    LaunchedEffect(key1 = true) {
        petViewModel.getPrettyPets(query = "kitten", imageType = "photo", isPretty = true)
    }
    val petsUiState = petViewModel.petsUiState.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTopBar()
        when (petsUiState) {
            is PetsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PetsUiState.Success -> {
                Spacer(modifier = Modifier.height(24.dp))
                SearchPrettyPet()
                Spacer(modifier = Modifier.height(24.dp))
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(200.dp),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(petsUiState.prettyPets.hits) { prettyPet ->
                            PetCardComposable(prettyPet = prettyPet) { _ ->
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "prettyPet",
                                    value = prettyPet
                                )
                                navController.navigate(
                                    DetailsScreen.PetDetailScreen.route
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

            }

            is PetsUiState.Error -> {

            }

            is PetsUiState.OnEmpty -> {

            }
        }

    }
}

@Preview
@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_pets_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Text(
            text = stringResource(R.string.pet_home),
            style = TextStyle(fontFamily = robotoFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(shape = CircleShape)
                .border(width = 1.dp, color = Blue_Light, shape = CircleShape),
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun SearchPrettyPet() {
    var inputText by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = inputText, onValueChange = {
            inputText = it
        }, modifier = Modifier
            .border(0.dp, color = Color.Transparent)
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            )
        }, leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }, shape = CircleShape
    )
}