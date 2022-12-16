package com.aptiv.fika.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aptiv.fika.presentation.theme.FikaTheme
import com.aptiv.fika.presentation.viewmodel.PersonViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FikaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(personViewModel: PersonViewModel = hiltViewModel()) {
    val result = personViewModel.state.collectAsState()
    val status = personViewModel.loadingState.collectAsState()

    val tabData = getTabList()
    val pagerState = rememberPagerState(pageCount = tabData.size)
    Column(modifier = Modifier.fillMaxSize()) {
        TabLayout(tabData, pagerState)
        TabContent(personViewModel, tabData, pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = { Spacer(modifier = Modifier.height(5.dp)) },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.White
            )
        },
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)
    ) {
        tabData.forEachIndexed { index, pair ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                text = { Text(text = pair.first, fontSize = 28.sp) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(viewModel: PersonViewModel, tabData: List<Pair<String, ImageVector>>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                SetHomeScreen("Sisi", viewModel)
            }
            1 -> {
                SetFikaList()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        Modifier.fillMaxSize().background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

private fun getTabList(): List<Pair<String, ImageVector>> {
    return listOf(
        "UpComing" to Icons.Default.Home,
        "List" to Icons.Default.Search
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview()
@Composable
fun PreviewTab() {
    FikaTheme {
        val tabData = getTabList()
        val pagerState = rememberPagerState(pageCount = tabData.size)
        TabLayout(tabData, pagerState)
    }
}

@Preview()
@Composable
fun PreviewContent() {
    FikaTheme {
        MainScreen()
    }
}
