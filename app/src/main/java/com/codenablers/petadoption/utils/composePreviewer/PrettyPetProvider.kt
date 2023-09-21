package com.codenablers.petadoption.utils.composePreviewer

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.codenablers.petadoption.domain.datamodel.Pets

class PrettyPetProvider :
    PreviewParameterProvider<Pets.Hit> {
    override val values: Sequence<Pets.Hit>
        get() = sequenceOf(
            Pets.Hit(
                id = 2083492,
                pageURL = "https://pixabay.com/photos/cat-young-animal-kitten-gray-cat-2083492/",
                type = "photo",
                tags = "cat,young animal,kitten",
                previewURL = "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_150.jpg",
                previewWidth = 150,
                previewHeight = 91,
                webformatURL = "https://pixabay.com/get/gdd06f09ae7a000ab0671bdb9d23e38ff3977b58a1ec72f7687cd4ece7d01d24a444535561d11bd4c82ea03d7e4a6d4c1_640.jpg",
                webformatWidth = 640,
                webformatHeight = 390,
                largeImageURL = "https://pixabay.com/get/g8adf70cca4f469d63b52010741a0582d2428ea40d10b0f6534b5701fe1f659f83c80b49e187eb3d3504229775e57eaf3b0373d5bf9f423cb0cd174dc1a53d1c1_1280.jpg",
                imageWidth = 4928,
                imageHeight = 3008,
                imageSize = 4130948,
                views = 1363328,
                downloads = 811041,
                collections = 2266,
                likes = 2556,
                comments = 397,
                user_id = 1777190,
                user = "susannp4",
                userImageURL = "https://cdn.pixabay.com/user/2015/12/16/17-56-55-832_250x250.jpg"
            )
        )
}