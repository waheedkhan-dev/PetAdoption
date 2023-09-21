@file:OptIn(ExperimentalMaterial3Api::class)

package com.codenablers.petadoption.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codenablers.petadoption.R
import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.ui.theme.Blue_Light
import com.codenablers.petadoption.ui.theme.Card_Background_Color
import com.codenablers.petadoption.ui.theme.Chip_Border_Color
import com.codenablers.petadoption.ui.theme.robotoFamily
import com.codenablers.petadoption.utils.composePreviewer.PrettyPetProvider


@Composable
fun PetCardComposable(
    @PreviewParameter(PrettyPetProvider::class) prettyPet: Pets.Hit,
    onPetSetSelected: (largeImageURL: String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(8.dp)
            .clickable {
                onPetSetSelected(prettyPet.largeImageURL)
            },
        shape = RoundedCornerShape(18.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(prettyPet.largeImageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.profile_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(alignment = Alignment.BottomCenter), colors = CardDefaults.cardColors(
                    containerColor = Card_Background_Color.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = prettyPet.user,
                            style = TextStyle(
                                fontFamily = robotoFamily,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                fontSize = 22.sp,
                                color = Color.Black
                            )
                        )
                        InputChip(
                            selected = false,
                            onClick = { },
                            label = {
                                Text(
                                    prettyPet.likes.toString(),
                                    style = TextStyle(
                                        fontFamily = robotoFamily,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            },
                            border = InputChipDefaults.inputChipBorder(
                                borderColor = Chip_Border_Color
                            ), trailingIcon = {
                                IconButton(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = "like"
                                    )
                                }

                            }, modifier = Modifier.padding(horizontal = 2.dp)
                        )

                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow {
                        items(prettyPet.tags.split(",")) { tag ->
                            SuggestionChip(
                                onClick = { },
                                label = {
                                    Text(
                                        tag,
                                        style = TextStyle(
                                            fontFamily = robotoFamily,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                },
                                shape = CircleShape,
                                border = SuggestionChipDefaults.suggestionChipBorder(
                                    borderColor = Chip_Border_Color
                                ), modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                }

            }
        }
        /*   Row {
               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(prettyPet.largeImageURL)
                       .crossfade(true)
                       .build(),
                   placeholder = painterResource(R.drawable.profile_image),
                   contentDescription = null,
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .width(120.dp)
                       .fillMaxHeight()
                       .clip(RoundedCornerShape(18.dp))
                       .clickable {

                       }
                       .border(
                           0.dp,
                           color = Color.Transparent,
                           RoundedCornerShape(18.dp)
                       )
               )
               Column {
                   Text(
                       text = prettyPet.user,
                       style = TextStyle(
                           fontWeight = FontWeight.Bold,
                           textAlign = TextAlign.Start,
                           fontSize = 22.sp
                       ), modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                   )
                   LazyRow(modifier = Modifier.padding(top = 12.dp)) {

                       items(prettyPet.tags.split(",")) { tag ->
                           SuggestionChip(
                               onClick = { },
                               label = { Text(tag) },
                               shape = CircleShape,
                               border = SuggestionChipDefaults.suggestionChipBorder(
                                   borderColor = Color.LightGray
                               ), modifier = Modifier.padding(horizontal = 2.dp)
                           )
                       }
                   }
               }
           }*/
    }
}

@Preview
@Composable
fun PetCardComposablePreview(
    @PreviewParameter(PrettyPetProvider::class) prettyPet: Pets.Hit,
) {
    PetCardComposable(prettyPet = prettyPet) {}
}