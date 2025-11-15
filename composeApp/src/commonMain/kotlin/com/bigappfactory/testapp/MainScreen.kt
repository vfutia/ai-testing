package com.bigappfactory.testapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bigappfactory.testapp.presentation.composable.widgets.AccountBadge
import com.bigappfactory.testapp.presentation.composable.widgets.BokehBackground
import com.bigappfactory.testapp.presentation.composable.widgets.Container
import com.bigappfactory.testapp.presentation.composable.widgets.FilterBar
import com.bigappfactory.testapp.presentation.composable.widgets.RoundIconButton
import com.bigappfactory.testapp.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import testapp.composeapp.generated.resources.Res
import testapp.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val selectedFilters = remember { mutableStateListOf<String>() }

    Scaffold(
//            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    AccountBadge()
                },
                actions = {
                    RoundIconButton(
                        onClick = {},
                        modifier = Modifier.padding(end = AppTheme.dimensions.paddingLarge),
                        icon = Icons.Default.Settings
                    )
                },
                colors = TopAppBarDefaults
                    .topAppBarColors()
                    .copy(
                        containerColor = Color.Transparent,
                        titleContentColor = Color.White
                    )
            )
        }
    ) { innerPadding ->

        BokehBackground(modifier = Modifier.fillMaxSize())

        var showContent by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .animateContentSize()
            ) {
                FilterBar(
                    title = "Category",
                    filters = listOf("Test", "Another One", "Option", "Things"),
                    selectedFilters = selectedFilters,
                    onFilterClick = { filter ->
                        if (selectedFilters.contains(filter)) {
                            selectedFilters.remove(filter)
                        } else {
                            selectedFilters.add(filter)
                        }
                    }
                )

                Container (
                    modifier = Modifier
                        .padding(vertical = AppTheme.dimensions.padding)
                        .padding(horizontal = AppTheme.dimensions.paddingLarge),
                    isClosable = true
                ) {
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                }

                Container (
                    modifier = Modifier
                        .padding(vertical = AppTheme.dimensions.padding)
                        .padding(horizontal = AppTheme.dimensions.paddingLarge),
                    isExpandable = true
                ) {
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                }

                Container (
                    modifier = Modifier
                        .padding(vertical = AppTheme.dimensions.padding)
                        .padding(horizontal = AppTheme.dimensions.paddingLarge)
                ) {
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                }

                Container (
                    modifier = Modifier
                        .padding(vertical = AppTheme.dimensions.padding)
                        .padding(horizontal = AppTheme.dimensions.paddingLarge)
                ) {
                    Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                }


                Column(
                    modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingLarge)
                ) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = AppTheme.colors.primary
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { showContent = !showContent }
                    ) {
                        Text("Click me!")
                    }
                    AnimatedVisibility(showContent) {
                        val greeting = remember { Greeting().greet() }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Image(painterResource(Res.drawable.compose_multiplatform), null)
                            Text("Compose: $greeting")
                        }
                    }
                }
            }
        }
    }
}