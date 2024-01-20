package br.com.rblemer.pipocaflix.ui.screen.search

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import br.com.rblemer.pipocaflix.R
import br.com.rblemer.pipocaflix.TestTags.SEARCH_TEXT_FIELD

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieSearchBar(
    value: String,
    onValueChanged: (String) -> Unit,
    onSearchRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier.testTag(SEARCH_TEXT_FIELD),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(R.string.title_search))
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchRequested()
                keyboardController?.hide()
            }
        )
    )
}