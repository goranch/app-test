package com.example.skytest.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.skytest.R

@Composable
fun SearchBox(onSearch: (String) -> Unit) {

    var searchText by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it; onSearch(it) },
        label = { Text(stringResource(id = R.string.search)) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(searchText) }),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.search_rounded_corner)),
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.medium_padding))
            .fillMaxWidth()
    )
}
