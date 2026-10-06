package com.example.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.ChatMessage
import com.example.data.Word
import com.example.data.WordListData

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainAppScreen(viewModel: MainViewModel) {
    val quizState by viewModel.quizState.collectAsStateWithLifecycle()
    val userStats by viewModel.userStats.collectAsStateWithLifecycle()
    
    var currentTab by remember { mutableStateOf("path") }

    Scaffold(
        bottomBar = {
            if (quizState is QuizState.Idle) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        selected = currentTab == "path",
                        onClick = { currentTab = "path" },
                        icon = { Icon(Icons.Default.Map, contentDescription = "Path") },
                        label = { Text("学习", fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("nav_path")
                    )
                    NavigationBarItem(
                        selected = currentTab == "words",
                        onClick = { currentTab = "words" },
                        icon = { Icon(Icons.Default.Book, contentDescription = "Words") },
                        label = { Text("词库", fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("nav_words")
                    )
                    NavigationBarItem(
                        selected = currentTab == "chat",
                        onClick = { currentTab = "chat" },
                        icon = { Icon(Icons.Default.ChatBubble, contentDescription = "AI Partner") },
                        label = { Text("伴聊", fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("nav_chat")
                    )
                    NavigationBarItem(
                        selected = currentTab == "check",
                        onClick = { currentTab = "check" },
                        icon = { Icon(Icons.Default.Spellcheck, contentDescription = "AI Check") },
                        label = { Text("评阅", fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("nav_check")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = quizState) {
                is QuizState.Idle -> {
                    when (currentTab) {
                        "path" -> PathScreen(viewModel)
                        "words" -> WordListScreen(viewModel)
                        "chat" -> ChatScreen(viewModel)
                        "check" -> GrammarScreen(viewModel)
                    }
                }
                is QuizState.Active -> {
                    QuizActiveScreen(state, viewModel)
                }
                is QuizState.Completed -> {
                    QuizCompletedScreen(state, viewModel)
                }
            }
        }
    }
}

// --- SCREEN 1: THE GAMIFIED LEVEL PATH (Duolingo style) ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PathScreen(viewModel: MainViewModel) {
    val userStats by viewModel.userStats.collectAsStateWithLifecycle()
    val allWords by viewModel.allWords.collectAsStateWithLifecycle()
    
    var selectedLevelForPreview by remember { mutableStateOf<Int?>(null) }

    val completedLevelCount = userStats?.lastCompletedLevel ?: 0
    val activeLevelIndex = completedLevelCount + 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Upper stats banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Streak",
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("Day Streak", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("${userStats?.streak ?: 1} Days", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "XP",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("Total Points", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("${userStats?.xp ?: 0} XP", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.MonetizationOn,
                        contentDescription = "Coins",
                        tint = Color(0xFFFFEB3B),
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text("Gold Coins", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Text("${userStats?.goldCoins ?: 50}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        }

        // Overall progress and today's review
        val dueCount by viewModel.dueWordCount.collectAsStateWithLifecycle()
        val totalWords = WordListData.initialWords.size
        val masteredCount = allWords.count { it.masteryState == 2 }
        val studiedCount = allWords.count { it.masteryState >= 1 }

        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "850 常用词学习路径",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "已掌握 $masteredCount / $totalWords · 学过 $studiedCount",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.testTag("mastery_progress_text")
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { if (totalWords == 0) 0f else masteredCount.toFloat() / totalWords },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF4CAF50),
                trackColor = Color.LightGray
            )
        }

        if (dueCount > 0) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("今日复习", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(
                            "有 $dueCount 个单词该复习了，趁热打铁别忘记！",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    Button(
                        onClick = { viewModel.startReview() },
                        modifier = Modifier.testTag("start_review_button")
                    ) {
                        Text("开始复习", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Path Map Scroller
        val listState = rememberLazyListState()
        LaunchedEffect(activeLevelIndex) {
            // Jump to the current level when the path opens
            listState.scrollToItem((activeLevelIndex - 1).coerceIn(0, WordListData.TOTAL_LEVELS - 1))
        }
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val levels = (1..WordListData.TOTAL_LEVELS).toList()
            itemsIndexed(levels) { index, levelNum ->
                val isUnlocked = levelNum <= activeLevelIndex
                val isCompleted = levelNum <= completedLevelCount

                // Alternating horizontal offsets for cute snake pathway
                val xOffset = when (index % 3) {
                    0 -> (-30).dp
                    1 -> 0.dp
                    else -> 30.dp
                }

                Column(
                    modifier = Modifier
                        .offset(x = xOffset)
                        .padding(vertical = 12.dp)
                        .testTag("level_node_$levelNum"),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Node Button
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isCompleted -> Color(0xFFFFD700) // Golden completed crown
                                    isUnlocked -> MaterialTheme.colorScheme.secondary
                                    else -> Color.LightGray
                                }
                            )
                            .border(
                                width = 4.dp,
                                color = when {
                                    isCompleted -> Color(0xFFFBC02D)
                                    isUnlocked -> MaterialTheme.colorScheme.primary
                                    else -> Color.DarkGray
                                },
                                shape = CircleShape
                            )
                            .clickable(enabled = isUnlocked) {
                                selectedLevelForPreview = levelNum
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompleted) {
                            Icon(
                                Icons.Default.EmojiEvents,
                                contentDescription = "Completed",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        } else {
                            Text(
                                text = "$levelNum",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Small text explaining category below
                    val categoryTag = WordListData.levelTitle(levelNum)

                    Text(
                        text = "Level $levelNum • $categoryTag",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isUnlocked) MaterialTheme.colorScheme.onBackground else Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

                // Small connector line
                if (levelNum < WordListData.TOTAL_LEVELS) {
                    val lineXOffset = when (index % 3) {
                        0 -> (-15).dp
                        1 -> 15.dp
                        else -> 0.dp
                    }
                    Box(
                        modifier = Modifier
                            .size(width = 6.dp, height = 30.dp)
                            .offset(x = lineXOffset)
                            .background(if (levelNum < activeLevelIndex) MaterialTheme.colorScheme.primary else Color.LightGray)
                    )
                }
            }
        }
    }

    // Modal Preview Dialog of the Words in the level
    selectedLevelForPreview?.let { levelNum ->
        val levelWords = allWords.filter { it.levelIndex == levelNum }
        
        Dialog(onDismissRequest = { selectedLevelForPreview = null }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Level $levelNum Word List",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "本关将学习${levelWords.size}个核心基础词汇（点喇叭听发音）：",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    HorizontalDivider()

                    // Scrollable list of level's words
                    LazyColumn(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        items(levelWords) { word ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "${word.word} ${word.ipa}",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = word.exampleSentence,
                                        fontSize = 11.sp,
                                        color = Color.DarkGray
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = word.translation,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    IconButton(onClick = { viewModel.speak(word.exampleSentence) }) {
                                        Icon(
                                            Icons.Default.VolumeUp,
                                            contentDescription = "Listen",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { selectedLevelForPreview = null },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("返回")
                        }
                        Button(
                            onClick = {
                                selectedLevelForPreview = null
                                viewModel.setLevel(levelNum)
                                viewModel.startLesson(levelNum)
                            },
                            modifier = Modifier
                                .weight(1.5f)
                                .testTag("start_lesson_button")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.PlayArrow, contentDescription = "Play")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("开始练习", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- ACTIVE LESSON SCREEN ---
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun QuizActiveScreen(state: QuizState.Active, viewModel: MainViewModel) {
    val q = state.currentQuestion
    val progress = (state.questionIndex.toFloat()) / state.totalQuestions.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Exit button and Progress Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.quitLesson() },
                modifier = Modifier.testTag("quiz_close")
            ) {
                Icon(Icons.Default.Close, contentDescription = "Exit Quiz", tint = Color.Gray)
            }
            Spacer(modifier = Modifier.width(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = Color.LightGray
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${state.questionIndex + 1}/${state.totalQuestions}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        // Broad Question Prompt
        Text(
            text = q.prompt,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 20.dp),
            textAlign = TextAlign.Center
        )

        // Main Question interaction workspace
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            when (q.type) {
                QuestionType.ENG_TO_CHI -> {
                    // English word to select Chinese
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = q.targetWord.word,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.testTag("quiz_word_display")
                        )
                        IconButton(onClick = { viewModel.speak(q.targetWord.word) }) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Option cards
                        OptionsGrid(q.options, state.selectedOptionIndex, state.answered) {
                            viewModel.selectOption(it)
                        }
                    }
                }
                QuestionType.CHI_TO_ENG -> {
                    // Chinese prompt to select English
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = q.targetWord.translation,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        OptionsGrid(q.options, state.selectedOptionIndex, state.answered) {
                            viewModel.selectOption(it)
                        }
                    }
                }
                QuestionType.LISTENING -> {
                    // Tap speaker, select spelling
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { viewModel.speak(q.targetWord.word) },
                            shape = CircleShape,
                            modifier = Modifier
                                .size(110.dp)
                                .testTag("listening_audio_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Play Audio", modifier = Modifier.size(54.dp))
                        }
                        Text(
                            text = "点击播放语音 (Tap to Speak)",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        OptionsGrid(q.options, state.selectedOptionIndex, state.answered) {
                            viewModel.selectOption(it)
                        }
                    }
                }
                QuestionType.CLOZE -> {
                    // Open gap sentence
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = state.currentQuestion.sentencePlaceholder,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        OptionsGrid(q.options, state.selectedOptionIndex, state.answered) {
                            viewModel.selectOption(it)
                        }
                    }
                }
                QuestionType.CARD_SHUFFLE -> {
                    // Reorder word cards to compose English sentence
                    val fullWords = state.currentQuestion.correctAnswer.split(" ").filter { it.isNotBlank() }
                    val randomWordsPool = remember(state.questionIndex) {
                        (fullWords + listOf("you", "good", "water", "like", "they")).shuffled()
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "目标句子: ${q.targetWord.exampleTranslation}",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        // Answer Box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(2.dp, Color.LightGray, RoundedCornerShape(12.dp))
                                .background(Color(0xFFFAFAFA))
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (state.buildAnswer.isEmpty()) {
                                Text(
                                    "点击卡片组合句子...",
                                    fontSize = 13.sp,
                                    color = Color.LightGray,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                FlowRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    state.buildAnswer.forEach { word ->
                                        Card(
                                            modifier = Modifier
                                                .clickable { viewModel.removeCardFromBuild(word) }
                                                .testTag("built_card_$word"),
                                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                                        ) {
                                            Text(
                                                text = word,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Cards drawer pool
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("可用卡片:", fontSize = 11.sp, color = Color.Gray)
                                Spacer(modifier = Modifier.height(8.dp))
                                FlowRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    randomWordsPool.forEach { word ->
                                        // Count if already fully added to build list
                                        val usedTimes = state.buildAnswer.count { it == word }
                                        val poolTimes = randomWordsPool.count { it == word }
                                        val isAvailable = usedTimes < poolTimes

                                        Card(
                                            modifier = Modifier
                                                .clickable(enabled = isAvailable && !state.answered) {
                                                    viewModel.addCardToBuild(word)
                                                }
                                                .testTag("pool_card_$word"),
                                            colors = CardDefaults.cardColors(
                                                containerColor = if (isAvailable) MaterialTheme.colorScheme.surface else Color.LightGray
                                            )
                                        ) {
                                            Text(
                                                text = word,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                                color = if (isAvailable) Color.Unspecified else Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                QuestionType.SPELLING -> {
                    // Spell the word manually with hints
                    val wordLength = q.correctAnswer.length
                    val hint = when {
                        wordLength > 3 -> "${q.correctAnswer.first()}" + "_".repeat(wordLength - 2) + "${q.correctAnswer.last()}"
                        else -> "${q.correctAnswer.first()}" + "_".repeat(wordLength - 1)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "提示: $hint ($wordLength 个字母)",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(18.dp))

                        val keyboardController = LocalSoftwareKeyboardController.current

                        OutlinedTextField(
                            value = state.currentTypedAnswer,
                            onValueChange = { viewModel.updateTypedAnswer(it) },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .testTag("spelling_input"),
                            placeholder = { Text("输入拼写...", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 16.sp) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                keyboardController?.hide()
                                viewModel.checkAnswer()
                            }),
                            singleLine = true,
                            enabled = !state.answered
                        )
                    }
                }
                QuestionType.DICTATION -> {
                    // Listen, then type what was heard
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { viewModel.speak(q.targetWord.word) },
                            shape = CircleShape,
                            modifier = Modifier
                                .size(110.dp)
                                .testTag("dictation_audio_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Play Audio", modifier = Modifier.size(54.dp))
                        }
                        Text(
                            text = "点击再听一遍，然后写下单词",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        val keyboardController = LocalSoftwareKeyboardController.current

                        OutlinedTextField(
                            value = state.currentTypedAnswer,
                            onValueChange = { viewModel.updateTypedAnswer(it) },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .testTag("spelling_input"),
                            placeholder = { Text("输入你听到的单词...", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 16.sp) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = {
                                keyboardController?.hide()
                                viewModel.checkAnswer()
                            }),
                            singleLine = true,
                            enabled = !state.answered
                        )
                    }
                }
                QuestionType.SPEAKING -> SpeakingPanel(state, viewModel)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Grade sliding bottom sheet panel
        AnimatedVisibility(
            visible = state.answered,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (state.isCorrect) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("result_panel")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (state.isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                            contentDescription = null,
                            tint = if (state.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (state.isCorrect) "Excellent! (完全正确)" else "Oops! Correct Answer (正确答案):",
                            fontWeight = FontWeight.Bold,
                            color = if (state.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                            fontSize = 16.sp
                        )
                    }
                    if (!state.isCorrect) {
                        Text(
                            text = q.correctAnswer,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(start = 40.dp, top = 4.dp),
                            color = Color(0xFFC62828)
                        )
                    }
                    Text(
                        text = "Example (例句): ${q.targetWord.exampleSentence}",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(start = 40.dp, top = 8.dp)
                    )
                    Text(
                        text = q.targetWord.exampleTranslation,
                        fontSize = 11.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 40.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Main Check/Next button
        Button(
            onClick = {
                if (!state.answered) viewModel.checkAnswer() else viewModel.nextQuestion()
            },
            enabled = state.answered || q.type != QuestionType.SPEAKING,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .testTag("quiz_action_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (state.answered) {
                    if (state.isCorrect) Color(0xFF4CAF50) else Color(0xFFEF5350)
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
        ) {
            Text(
                text = if (state.answered) "Continue (继续学习)" else "Check Answer (检查答案)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/** Read-aloud question: listen to the sentence, then say it into the system speech recognizer. */
@Composable
fun SpeakingPanel(state: QuizState.Active, viewModel: MainViewModel) {
    val q = state.currentQuestion
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val texts = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).orEmpty()
            if (texts.isNotEmpty()) viewModel.submitSpeech(texts)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = q.targetWord.exampleSentence,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .testTag("speaking_sentence")
        )
        Text(
            text = q.targetWord.exampleTranslation,
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 6.dp)
        )
        TextButton(onClick = { viewModel.speak(q.targetWord.exampleSentence) }) {
            Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("先听一遍标准发音")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                    putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
                    putExtra(RecognizerIntent.EXTRA_PROMPT, q.targetWord.exampleSentence)
                }
                try {
                    launcher.launch(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "这台设备不支持语音识别，可以先跳过这题", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = !state.answered,
            shape = CircleShape,
            modifier = Modifier
                .size(96.dp)
                .testTag("speaking_mic_button")
        ) {
            Icon(Icons.Default.Mic, contentDescription = "Speak", modifier = Modifier.size(48.dp))
        }
        Text(
            text = "点击麦克风，大声读出这句话",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
        if (state.heardText.isNotBlank()) {
            Text(
                text = "识别到：${state.heardText}",
                fontSize = 13.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        if (!state.answered) {
            TextButton(
                onClick = { viewModel.skipSpeaking() },
                modifier = Modifier.testTag("speaking_skip_button")
            ) {
                Text("现在不方便说话，跳过", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun OptionsGrid(
    options: List<String>,
    selectedIndex: Int?,
    answered: Boolean,
    onSelect: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { i, opt ->
            val isSelected = selectedIndex == i
            OutlinedCard(
                onClick = { if (!answered) onSelect(i) },
                colors = CardDefaults.outlinedCardColors(
                    containerColor = when {
                        isSelected -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.surface
                    }
                ),
                border = BorderStroke(
                    width = if (isSelected) 3.dp else 1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_option_$i")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary else Color(
                                    0xFFE0E0E0
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${'A' + i}",
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = opt,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// (Removed custom FlowRow to use native implementation)

// --- QUIZ COMPLETED SCREEN ---
@Composable
fun QuizCompletedScreen(state: QuizState.Completed, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val passed = state.isReview || state.score.toFloat() / state.maxScore.toFloat() >= 0.7f

        Icon(
            imageVector = if (passed) Icons.Default.CheckCircle else Icons.Default.Error,
            contentDescription = null,
            tint = if (passed) Color(0xFF4CAF50) else Color(0xFFEF5350),
            modifier = Modifier
                .size(100.dp)
                .testTag("completed_icon")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when {
                state.isReview -> "复习完成！"
                passed -> "Lesson Completed! (学习完满结束)"
                else -> "Try Again Later (继续加油)"
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (passed) Color(0xFF2E7D32) else Color(0xFFC62828),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your score: ${state.score} of ${state.maxScore} correct answers.",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (state.weakWords.isNotEmpty()) {
            Text(
                text = "这些词需要多看几遍：" + state.weakWords.joinToString("、") { "${it.word} ${it.translation}" },
                fontSize = 13.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .testTag("weak_words_text")
            )
        }

        if (passed) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("XP Gained", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, "XP", tint = Color(0xFFFFC107))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("+${state.xpEarned} XP", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Gold Gained", fontSize = 11.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.MonetizationOn, "Gold", tint = Color(0xFFFFEB3B))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("+${state.coinsEarned}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                    }
                }
            }
        } else {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "至少要答对70%才可以通过哦！继续保持练习来解锁更高等级吧！",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { viewModel.quitLesson() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp)
                .testTag("completed_continue_button")
        ) {
            Text("Back to Pathway (返回主地图)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

// --- SCREEN 2: VOCABULARY DICTIONARY SEARCH ---
@Composable
fun WordListScreen(viewModel: MainViewModel) {
    val allWords by viewModel.allWords.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("全部") }
    var showBookmarkedOnly by remember { mutableStateOf(false) }

    val categories = listOf("全部", "未学", "学习中", "已掌握", "易错")

    val filteredWords = allWords.filter { word ->
        val matchSearch = word.word.contains(searchQuery, ignoreCase = true) ||
                word.translation.contains(searchQuery)
        val matchCategory = when (selectedCategory) {
            "未学" -> word.masteryState == 0
            "学习中" -> word.masteryState == 1
            "已掌握" -> word.masteryState == 2
            "易错" -> word.wrongCount > 0
            else -> true
        }
        val matchBookmark = !showBookmarkedOnly || word.bookmarked
        matchSearch && matchCategory && matchBookmark
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            "850 词词库",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            "共 ${allWords.size} 个常用词：拼写、音标、释义和例句，点喇叭听发音：",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("搜索单词或中文翻译...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("vocab_search_input"),
            singleLine = true,
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Categories filters tabs
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { cat ->
                val isSelected = selectedCategory == cat
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = cat },
                    label = { Text(cat) },
                    modifier = Modifier.testTag("filter_$cat")
                )
            }
        }

        // Bookmark toggle row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("仅显示收藏单词 (Bookmarks only)", fontSize = 13.sp, color = Color.Gray)
            Switch(
                checked = showBookmarkedOnly,
                onCheckedChange = { showBookmarkedOnly = it },
                modifier = Modifier.testTag("bookmark_toggle_switch")
            )
        }

        HorizontalDivider()

        Spacer(modifier = Modifier.height(8.dp))

        if (filteredWords.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.SearchOff, "No results", tint = Color.LightGray, modifier = Modifier.size(64.dp))
                    Text("未找到相关词汇！", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredWords, key = { it.id }) { word ->
                    WordItemCard(word = word, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun WordItemCard(word: Word, viewModel: MainViewModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
            .testTag("word_card_${word.word}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title word, pronunciation & Bookmark/TTS buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = word.word,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = word.ipa,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.Monospace
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Tap speaker for offline TTS reading!
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = "Speak",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { viewModel.speak(word.word) }
                            .testTag("tts_${word.word}")
                    )
                }

                Row {
                    // Mastery tags
                    val masteryText = when (word.masteryState) {
                        2 -> "已掌握"
                        1 -> "复习中"
                        else -> "未学习"
                    }
                    val masteryColor = when (word.masteryState) {
                        2 -> Color(0xFFE8F5E9)
                        1 -> Color(0xFFFFF3E0)
                        else -> Color(0xFFF5F5F5)
                    }
                    val masteryTextColor = when (word.masteryState) {
                        2 -> Color(0xFF2E7D32)
                        1 -> Color(0xFFE65100)
                        else -> Color.DarkGray
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(masteryColor)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(masteryText, fontSize = 10.sp, color = masteryTextColor, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { viewModel.toggleBookmark(word) },
                        modifier = Modifier
                            .size(24.dp)
                            .testTag("bookmark_${word.word}")
                    ) {
                        Icon(
                            imageVector = if (word.bookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (word.bookmarked) Color(0xFFFF9800) else Color.LightGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Chinese Translation and POS type
            Row {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(word.category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = word.translation,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Example sentence boxes
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = "Example sentence:",
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Medium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = word.exampleSentence,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { viewModel.speak(word.exampleSentence) },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(Icons.Default.VolumeUp, "Speak sentence", tint = Color.Gray, modifier = Modifier.size(16.dp))
                    }
                }
                Text(
                    text = word.exampleTranslation,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// --- SCREEN 3: COMPANION COACH AI CHAT ROOM (Lily) ---
@Composable
fun ChatScreen(viewModel: MainViewModel) {
    val messages by viewModel.chatMessages.collectAsStateWithLifecycle()
    val isAiLoading by viewModel.isAiLoading.collectAsStateWithLifecycle()

    var typedInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Autoscoll whenever new message drops
    LaunchedEffect(messages.size, isAiLoading) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Chat room tutor header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Lily", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Lily • Basic AI Tutor", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text("在线练习口语沟通！限基础本表850词", fontSize = 10.sp, color = Color.Gray)
                }

                Button(
                    onClick = { viewModel.clearChat() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer, contentColor = MaterialTheme.colorScheme.onErrorContainer),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = Modifier.height(32.dp).testTag("clear_chat_button")
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Clear Chat", modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("重置", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Messages Box
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (messages.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Chat, "Empty Chat", tint = Color.LightGray, modifier = Modifier.size(64.dp))
                        Text("莉莉老师正在等你呢！", color = Color.Gray, fontSize = 14.sp)
                        Text("点击下方重置按钮，或者发一条消息开始聊天！", color = Color.LightGray, fontSize = 11.sp)
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    messages.forEach { msg ->
                        ChatDialogBubble(msg, viewModel)
                    }

                    if (isAiLoading) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Lily is typing...", fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, fontSize = 11.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        // Suggestion Chips Quick Typing Panel
        // Everyday situations to role-play with the tutor
        val scenarios = listOf(
            "自我介绍" to "introducing myself to a new friend",
            "点餐" to "ordering food in a restaurant",
            "问路" to "asking the way in a city",
            "购物" to "buying clothes in a shop",
            "看病" to "seeing a doctor",
            "打电话" to "making a phone call to a friend",
            "订酒店" to "checking in at a hotel"
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(scenarios) { (label, situation) ->
                AssistChip(
                    onClick = { viewModel.startScenario(situation) },
                    label = { Text("情景：$label", fontSize = 11.sp) },
                    modifier = Modifier.testTag("scenario_chip_$label")
                )
            }
        }

        if (messages.isNotEmpty()) {
            val suggestions = listOf("How are you?", "Let's talk about food.", "Tell me about a good friend.")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(suggestions) { s ->
                    SuggestionChip(
                        onClick = { viewModel.sendChatMessage(s) },
                        label = { Text(s, fontSize = 11.sp) },
                        modifier = Modifier.testTag("suggestion_chip_$s")
                    )
                }
            }
        }

        // Input field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = typedInput,
                onValueChange = { typedInput = it },
                placeholder = { Text("使用英语发一条消息吧...") },
                modifier = Modifier
                    .weight(1f)
                    .testTag("chat_input_text_field"),
                shape = RoundedCornerShape(24.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (typedInput.isNotBlank()) {
                        viewModel.sendChatMessage(typedInput)
                        typedInput = ""
                    }
                }),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (viewModel.speechAvailable) {
                // Speak instead of typing: the recognized sentence is put in the input box for review
                val micContext = LocalContext.current
                val micLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val heard = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()
                        if (!heard.isNullOrBlank()) typedInput = heard
                    }
                }
                IconButton(
                    onClick = {
                        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                        }
                        try {
                            micLauncher.launch(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(micContext, "这台设备不支持语音识别", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.testTag("chat_mic_button")
                ) {
                    Icon(Icons.Default.Mic, contentDescription = "Speak", tint = MaterialTheme.colorScheme.primary)
                }
            }
            IconButton(
                onClick = {
                    if (typedInput.isNotBlank()) {
                        viewModel.sendChatMessage(typedInput)
                        typedInput = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .testTag("chat_send_button"),
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun ChatDialogBubble(msg: ChatMessage, viewModel: MainViewModel) {
    val isUser = msg.sender == "user"
    var showTranslation by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.85f),
            horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        )
                    )
                    .background(
                        if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                    )
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = msg.content,
                        fontSize = 14.sp,
                        color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (!isUser && msg.translation != null && showTranslation) {
                        Spacer(modifier = Modifier.height(6.dp))
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = msg.translation,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Translation and voice playback helper tools
            if (!isUser) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                ) {
                    if (msg.translation != null) {
                        Text(
                            text = if (showTranslation) "隐藏翻译" else "显示翻译",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clickable { showTranslation = !showTranslation }
                                .testTag("toggle_trans_${msg.id}")
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Text(
                        text = "朗读 (TTS)",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .clickable { viewModel.speak(msg.content) }
                            .testTag("tts_bubble_${msg.id}")
                    )
                }
            }
        }
    }
}

// --- SCREEN 4: AI GRAMMAR COMPOSITION CHECKER ---
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GrammarScreen(viewModel: MainViewModel) {
    val userStats by viewModel.userStats.collectAsStateWithLifecycle()
    val allWords by viewModel.allWords.collectAsStateWithLifecycle()
    val checkResult by viewModel.grammarCheckResult.collectAsStateWithLifecycle()
    val isChecking by viewModel.isCheckingGrammar.collectAsStateWithLifecycle()

    var textInput by remember { mutableStateOf("") }

    val levelWords = remember(userStats?.lastCompletedLevel) {
        val completed = userStats?.lastCompletedLevel ?: 0
        allWords.filter { it.levelIndex == completed + 1 }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "AI Grammar Coach (AI 语法教练)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            "写一两句英语，让AI语法教练分析、纠错并提供地道翻译：",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Text composition input
        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            placeholder = { Text("例如：I want do my work because and make my family happy.") },
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .testTag("grammar_textarea"),
            maxLines = 4
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Actions row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = {
                    textInput = ""
                    viewModel.clearGrammarResult()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("清空")
            }
            Button(
                onClick = { viewModel.checkGrammar(textInput) },
                modifier = Modifier
                    .weight(1.8f)
                    .testTag("grammar_submit_button"),
                enabled = !isChecking && textInput.isNotBlank()
            ) {
                if (isChecking) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, "AI Check")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("AI 纠错分析", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Suggestion items keywords row
        if (levelWords.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        "💡 本关可用词汇（挑战写作！）：",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp), 
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        levelWords.forEach { word ->
                            Text(
                                text = word.word,
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(6.dp))
                                    .clickable {
                                        val leadingSpace = if (textInput.isNotEmpty() && !textInput.endsWith(" ")) " " else ""
                                        textInput += "$leadingSpace${word.word}"
                                    }
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Display results
        checkResult?.let { result ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(16.dp))
                    .testTag("grammar_result_card")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AutoAwesome, "Analyzed", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("AI Coach Review (分析报告)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = result,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
