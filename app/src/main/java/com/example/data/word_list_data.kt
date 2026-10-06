package com.example.data

object WordListData {
    private val coreWords = listOf(
        // Level 1: Operations (Verbs - Action)
        Word(
            id = 1,
            word = "come",
            category = "Operations",
            translation = "来",
            ipa = "/kʌm/",
            exampleSentence = "Please come here.",
            exampleTranslation = "请到这里来。",
            levelIndex = 1
        ),
        Word(
            id = 2,
            word = "get",
            category = "Operations",
            translation = "得到 / 获得",
            ipa = "/ɡet/",
            exampleSentence = "Can I get some water?",
            exampleTranslation = "我可以弄点水吗？",
            levelIndex = 1
        ),
        Word(
            id = 3,
            word = "give",
            category = "Operations",
            translation = "给",
            ipa = "/ɡɪv/",
            exampleSentence = "Give me the book.",
            exampleTranslation = "把书给我。",
            levelIndex = 1
        ),
        Word(
            id = 4,
            word = "go",
            category = "Operations",
            translation = "去 / 走",
            ipa = "/ɡəʊ/",
            exampleSentence = "We can go to the house.",
            exampleTranslation = "我们可以去那个房子。",
            levelIndex = 1
        ),
        Word(
            id = 5,
            word = "keep",
            category = "Operations",
            translation = "保持",
            ipa = "/kiːp/",
            exampleSentence = "Keep your room clean.",
            exampleTranslation = "保持你的房间干净。",
            levelIndex = 1
        ),
        Word(
            id = 6,
            word = "let",
            category = "Operations",
            translation = "让",
            ipa = "/let/",
            exampleSentence = "Let me see.",
            exampleTranslation = "让我看看。",
            levelIndex = 1
        ),
        Word(
            id = 7,
            word = "make",
            category = "Operations",
            translation = "制造 / 使",
            ipa = "/meɪk/",
            exampleSentence = "He can make bread.",
            exampleTranslation = "他会做面包。",
            levelIndex = 1
        ),
        Word(
            id = 8,
            word = "put",
            category = "Operations",
            translation = "放",
            ipa = "/pʊt/",
            exampleSentence = "Put the apple on the table.",
            exampleTranslation = "把苹果放在桌上。",
            levelIndex = 1
        ),
        Word(
            id = 9,
            word = "seem",
            category = "Operations",
            translation = "似乎 / 好像",
            ipa = "/siːm/",
            exampleSentence = "They seem happy today.",
            exampleTranslation = "他们今天好像很高兴。",
            levelIndex = 1
        ),
        Word(
            id = 10,
            word = "take",
            category = "Operations",
            translation = "拿走 / 花费",
            ipa = "/teɪk/",
            exampleSentence = "Take this hand.",
            exampleTranslation = "牵着这只手。",
            levelIndex = 1
        ),

        // Level 2: Operations (Helper Verbs & Particles)
        Word(
            id = 11,
            word = "be",
            category = "Operations",
            translation = "是 / 存在",
            ipa = "/biː/",
            exampleSentence = "I want to be your friend.",
            exampleTranslation = "我想做你的朋友。",
            levelIndex = 2
        ),
        Word(
            id = 12,
            word = "do",
            category = "Operations",
            translation = "做 / 尽力",
            ipa = "/duː/",
            exampleSentence = "Do your work.",
            exampleTranslation = "做你的工作。",
            levelIndex = 2
        ),
        Word(
            id = 13,
            word = "have",
            category = "Operations",
            translation = "有",
            ipa = "/hæv/",
            exampleSentence = "I have a small dog.",
            exampleTranslation = "我有一只小狗。",
            levelIndex = 2
        ),
        Word(
            id = 14,
            word = "say",
            category = "Operations",
            translation = "说",
            ipa = "/seɪ/",
            exampleSentence = "What did you say?",
            exampleTranslation = "你说了什么？",
            levelIndex = 2
        ),
        Word(
            id = 15,
            word = "see",
            category = "Operations",
            translation = "看见 / 明白",
            ipa = "/siː/",
            exampleSentence = "I see a bird in the sky.",
            exampleTranslation = "我看见天空有一只鸟。",
            levelIndex = 2
        ),
        Word(
            id = 16,
            word = "send",
            category = "Operations",
            translation = "派送 / 发送",
            ipa = "/send/",
            exampleSentence = "Send me a letter.",
            exampleTranslation = "给我寄封信。",
            levelIndex = 2
        ),
        Word(
            id = 17,
            word = "may",
            category = "Operations",
            translation = "也许 / 可以；May = 五月",
            ipa = "/meɪ/",
            exampleSentence = "You may go now.",
            exampleTranslation = "你现在可以走了。",
            levelIndex = 2
        ),
        Word(
            id = 18,
            word = "will",
            category = "Operations",
            translation = "将要",
            ipa = "/wɪl/",
            exampleSentence = "It will rain tomorrow.",
            exampleTranslation = "明天会下雨。",
            levelIndex = 2
        ),
        Word(
            id = 19,
            word = "about",
            category = "Operations",
            translation = "关于 / 大约",
            ipa = "/əˈbaʊt/",
            exampleSentence = "Tell me about yourself.",
            exampleTranslation = "告诉我关于你自己的事。",
            levelIndex = 2
        ),
        Word(
            id = 20,
            word = "across",
            category = "Operations",
            translation = "穿过",
            ipa = "/əˈkrɒs/",
            exampleSentence = "Go across the street.",
            exampleTranslation = "穿过这条街道。",
            levelIndex = 2
        ),

        // Level 3: Prepositions of Direction & Position
        Word(
            id = 21,
            word = "after",
            category = "Operations",
            translation = "在...之后",
            ipa = "/ˈɑːftə/",
            exampleSentence = "See you after school.",
            exampleTranslation = "放学后见。",
            levelIndex = 3
        ),
        Word(
            id = 22,
            word = "against",
            category = "Operations",
            translation = "反对 / 靠着",
            ipa = "/əˈɡenst/",
            exampleSentence = "She lean against the wall.",
            exampleTranslation = "她靠在墙上。",
            levelIndex = 3
        ),
        Word(
            id = 23,
            word = "among",
            category = "Operations",
            translation = "在...之中",
            ipa = "/əˈmʌŋ/",
            exampleSentence = "He is among friends.",
            exampleTranslation = "他在朋友们中间。",
            levelIndex = 3
        ),
        Word(
            id = 24,
            word = "at",
            category = "Operations",
            translation = "在 (某处)",
            ipa = "/æt/",
            exampleSentence = "I am at the door.",
            exampleTranslation = "我在门口。",
            levelIndex = 3
        ),
        Word(
            id = 25,
            word = "before",
            category = "Operations",
            translation = "在...之前",
            ipa = "/bɪˈfɔː/",
            exampleSentence = "Wash your hands before eating.",
            exampleTranslation = "吃饭前洗手。",
            levelIndex = 3
        ),
        Word(
            id = 26,
            word = "between",
            category = "Operations",
            translation = "在 (两者) 之间",
            ipa = "/bɪˈtwiːn/",
            exampleSentence = "Stand between father and mother.",
            exampleTranslation = "站在爸爸和妈妈中间。",
            levelIndex = 3
        ),
        Word(
            id = 27,
            word = "by",
            category = "Operations",
            translation = "通过 / 在旁",
            ipa = "/baɪ/",
            exampleSentence = "We learn by doing.",
            exampleTranslation = "我们通过实践来学习。",
            levelIndex = 3
        ),
        Word(
            id = 28,
            word = "down",
            category = "Operations",
            translation = "向下",
            ipa = "/daʊn/",
            exampleSentence = "Sit down please.",
            exampleTranslation = "请坐下。",
            levelIndex = 3
        ),
        Word(
            id = 29,
            word = "from",
            category = "Operations",
            translation = "来自",
            ipa = "/frɒm/",
            exampleSentence = "Where are you from?",
            exampleTranslation = "你来自哪里？",
            levelIndex = 3
        ),
        Word(
            id = 30,
            word = "in",
            category = "Operations",
            translation = "在...里面",
            ipa = "/ɪn/",
            exampleSentence = "The water is in the cup.",
            exampleTranslation = "水在杯子里。",
            levelIndex = 3
        ),

        // Level 4: Prepositions & Conjunctions
        Word(
            id = 31,
            word = "off",
            category = "Operations",
            translation = "离开 / 关掉",
            ipa = "/ɒf/",
            exampleSentence = "Take off your coat.",
            exampleTranslation = "脱下你的外套。",
            levelIndex = 4
        ),
        Word(
            id = 32,
            word = "on",
            category = "Operations",
            translation = "在...上面 / 打开",
            ipa = "/ɒn/",
            exampleSentence = "The pen is on the paper.",
            exampleTranslation = "钢笔在纸上面。",
            levelIndex = 4
        ),
        Word(
            id = 33,
            word = "over",
            category = "Operations",
            translation = "在...上方 / 越过",
            ipa = "/ˈəʊvə/",
            exampleSentence = "The bird flies over the house.",
            exampleTranslation = "鸟儿从房子上方飞过。",
            levelIndex = 4
        ),
        Word(
            id = 34,
            word = "through",
            category = "Operations",
            translation = "穿过 / 经历",
            ipa = "/θruː/",
            exampleSentence = "Walk through the park.",
            exampleTranslation = "步行穿过公园。",
            levelIndex = 4
        ),
        Word(
            id = 35,
            word = "to",
            category = "Operations",
            translation = "到 / 向 / 向往",
            ipa = "/tuː/",
            exampleSentence = "We go to school.",
            exampleTranslation = "我们去学校。",
            levelIndex = 4
        ),
        Word(
            id = 36,
            word = "under",
            category = "Operations",
            translation = "在...下方",
            ipa = "/ˈʌndə/",
            exampleSentence = "My shoes are under the bed.",
            exampleTranslation = "我的鞋在床底下。",
            levelIndex = 4
        ),
        Word(
            id = 37,
            word = "up",
            category = "Operations",
            translation = "向上",
            ipa = "/ʌp/",
            exampleSentence = "Look up at the stars.",
            exampleTranslation = "抬头看星星。",
            levelIndex = 4
        ),
        Word(
            id = 38,
            word = "with",
            category = "Operations",
            translation = "和...一起 / 带有",
            ipa = "/wɪð/",
            exampleSentence = "Come with me.",
            exampleTranslation = "跟我来。",
            levelIndex = 4
        ),
        Word(
            id = 39,
            word = "and",
            category = "Operations",
            translation = "和 / 并且",
            ipa = "/ænd/",
            exampleSentence = "You and I are friends.",
            exampleTranslation = "你和我是朋友。",
            levelIndex = 4
        ),
        Word(
            id = 40,
            word = "but",
            category = "Operations",
            translation = "但是",
            ipa = "/bʌt/",
            exampleSentence = "He seems poor but happy.",
            exampleTranslation = "他看起来很穷，但过得很快乐。",
            levelIndex = 4
        ),

        // Level 5: Basic Connections & Interrogatives
        Word(
            id = 41,
            word = "or",
            category = "Operations",
            translation = "或者",
            ipa = "/ɔː/",
            exampleSentence = "Do you want milk or water?",
            exampleTranslation = "你要牛奶还是水？",
            levelIndex = 5
        ),
        Word(
            id = 42,
            word = "if",
            category = "Operations",
            translation = "如果",
            ipa = "/ɪf/",
            exampleSentence = "If it rains, we can stay inside.",
            exampleTranslation = "如果下雨，我们可以呆在室内。",
            levelIndex = 5
        ),
        Word(
            id = 43,
            word = "because",
            category = "Operations",
            translation = "因为",
            ipa = "/bɪˈkɒz/",
            exampleSentence = "I love you because you are good.",
            exampleTranslation = "我爱你因为你很善良。",
            levelIndex = 5
        ),
        Word(
            id = 44,
            word = "while",
            category = "Operations",
            translation = "当...的时候",
            ipa = "/waɪl/",
            exampleSentence = "Be quiet while she is reading.",
            exampleTranslation = "她看书的时候保持安静。",
            levelIndex = 5
        ),
        Word(
            id = 45,
            word = "though",
            category = "Operations",
            translation = "虽然 / 尽管",
            ipa = "/ðəʊ/",
            exampleSentence = "He went out though it was cold.",
            exampleTranslation = "尽管天气冷，他还是出去了。",
            levelIndex = 5
        ),
        Word(
            id = 46,
            word = "how",
            category = "Operations",
            translation = "如何",
            ipa = "/haʊ/",
            exampleSentence = "How did you make this?",
            exampleTranslation = "你是怎么做这件东西的？",
            levelIndex = 5
        ),
        Word(
            id = 47,
            word = "when",
            category = "Operations",
            translation = "何时",
            ipa = "/wen/",
            exampleSentence = "When will you come back?",
            exampleTranslation = "你什么时候回来？",
            levelIndex = 5
        ),
        Word(
            id = 48,
            word = "where",
            category = "Operations",
            translation = "何处",
            ipa = "/weə/",
            exampleSentence = "Where is my book?",
            exampleTranslation = "我的书在哪里？",
            levelIndex = 5
        ),
        Word(
            id = 49,
            word = "who",
            category = "Operations",
            translation = "谁",
            ipa = "/huː/",
            exampleSentence = "Who is at the door?",
            exampleTranslation = "谁在门口？",
            levelIndex = 5
        ),
        Word(
            id = 50,
            word = "why",
            category = "Operations",
            translation = "为什么",
            ipa = "/waɪ/",
            exampleSentence = "Why are you sad?",
            exampleTranslation = "你为什么伤心？",
            levelIndex = 5
        ),

        // Level 6: Family & Home (Nouns)
        Word(
            id = 51,
            word = "father",
            category = "General Nouns",
            translation = "父亲",
            ipa = "/ˈfɑːðə/",
            exampleSentence = "My father works hard.",
            exampleTranslation = "我父亲工作很勤奋。",
            levelIndex = 6
        ),
        Word(
            id = 52,
            word = "mother",
            category = "General Nouns",
            translation = "母亲",
            ipa = "/ˈmʌðə/",
            exampleSentence = "His mother makes sweet cake.",
            exampleTranslation = "他母亲做出甜美的蛋糕。",
            levelIndex = 6
        ),
        Word(
            id = 53,
            word = "brother",
            category = "General Nouns",
            translation = "兄弟 / 哥哥",
            ipa = "/ˈbrʌðə/",
            exampleSentence = "My brother has a big hat.",
            exampleTranslation = "我哥哥有一顶大帽子。",
            levelIndex = 6
        ),
        Word(
            id = 54,
            word = "sister",
            category = "General Nouns",
            translation = "姐妹 / 妹妹",
            ipa = "/ˈsɪstə/",
            exampleSentence = "We have one little sister.",
            exampleTranslation = "我们有一个小妹妹。",
            levelIndex = 6
        ),
        Word(
            id = 55,
            word = "baby",
            category = "Picturable Nouns",
            translation = "婴儿",
            ipa = "/ˈbeɪbi/",
            exampleSentence = "The baby is sleeping.",
            exampleTranslation = "婴儿正在睡觉。",
            levelIndex = 6
        ),
        Word(
            id = 56,
            word = "family",
            category = "General Nouns",
            translation = "家庭",
            ipa = "/ˈfæməli/",
            exampleSentence = "I love my family.",
            exampleTranslation = "我爱我的家庭。",
            levelIndex = 6
        ),
        Word(
            id = 57,
            word = "house",
            category = "Picturable Nouns",
            translation = "房子",
            ipa = "/haʊs/",
            exampleSentence = "They built a beautiful house.",
            exampleTranslation = "他们盖了一栋漂亮的房子。",
            levelIndex = 6
        ),
        Word(
            id = 58,
            word = "room",
            category = "General Nouns",
            translation = "房间 / 空间",
            ipa = "/ruːm/",
            exampleSentence = "There is a table in the room.",
            exampleTranslation = "房间里有一张桌子。",
            levelIndex = 6
        ),
        Word(
            id = 59,
            word = "door",
            category = "Picturable Nouns",
            translation = "门",
            ipa = "/dɔː/",
            exampleSentence = "Open the door.",
            exampleTranslation = "开一下门。",
            levelIndex = 6
        ),
        Word(
            id = 60,
            word = "window",
            category = "Picturable Nouns",
            translation = "窗户",
            ipa = "/ˈwɪndəʊ/",
            exampleSentence = "Keep the window open.",
            exampleTranslation = "保持窗户开着。",
            levelIndex = 6
        ),

        // Level 7: Human Body (Nouns)
        Word(
            id = 61,
            word = "eye",
            category = "Picturable Nouns",
            translation = "眼睛",
            ipa = "/aɪ/",
            exampleSentence = "She has a blue eye.",
            exampleTranslation = "她有一只蓝色的眼睛。",
            levelIndex = 7
        ),
        Word(
            id = 62,
            word = "ear",
            category = "Picturable Nouns",
            translation = "耳朵",
            ipa = "/ɪə/",
            exampleSentence = "Keep your ear warm in snow.",
            exampleTranslation = "在雪天里让耳朵保持暖和。",
            levelIndex = 7
        ),
        Word(
            id = 63,
            word = "mouth",
            category = "Picturable Nouns",
            translation = "嘴巴",
            ipa = "/maʊθ/",
            exampleSentence = "Words come from your mouth.",
            exampleTranslation = "话语从你的嘴里说出来。",
            levelIndex = 7
        ),
        Word(
            id = 64,
            word = "nose",
            category = "Picturable Nouns",
            translation = "鼻子",
            ipa = "/nəʊz/",
            exampleSentence = "The smell comes to my nose.",
            exampleTranslation = "气味传到了我的鼻子里。",
            levelIndex = 7
        ),
        Word(
            id = 65,
            word = "face",
            category = "General Nouns",
            translation = "脸 / 面向",
            ipa = "/feɪs/",
            exampleSentence = "Keep a smile on your face.",
            exampleTranslation = "脸上保持微笑。",
            levelIndex = 7
        ),
        Word(
            id = 66,
            word = "hand",
            category = "Picturable Nouns",
            translation = "手",
            ipa = "/hænd/",
            exampleSentence = "Wash your hand with water.",
            exampleTranslation = "用水洗手。",
            levelIndex = 7
        ),
        Word(
            id = 67,
            word = "foot",
            category = "Picturable Nouns",
            translation = "脚",
            ipa = "/fʊt/",
            exampleSentence = "He went on foot.",
            exampleTranslation = "他是步行去的。",
            levelIndex = 7
        ),
        Word(
            id = 68,
            word = "head",
            category = "Picturable Nouns",
            translation = "头部 / 首长",
            ipa = "/hed/",
            exampleSentence = "He shakes his head.",
            exampleTranslation = "他摇了摇头。",
            levelIndex = 7
        ),
        Word(
            id = 69,
            word = "hair",
            category = "Picturable Nouns",
            translation = "头发",
            ipa = "/heə/",
            exampleSentence = "Her hair is black and long.",
            exampleTranslation = "她的头发黑而长。",
            levelIndex = 7
        ),
        Word(
            id = 70,
            word = "body",
            category = "General Nouns",
            translation = "身体",
            ipa = "/ˈbɒdi/",
            exampleSentence = "Keep your body healthy.",
            exampleTranslation = "保持身体健康。",
            levelIndex = 7
        ),

        // Level 8: Food & Drink (Nouns)
        Word(
            id = 71,
            word = "food",
            category = "General Nouns",
            translation = "食物",
            ipa = "/fuːd/",
            exampleSentence = "The local food is very good.",
            exampleTranslation = "当地的食物非常好吃。",
            levelIndex = 8
        ),
        Word(
            id = 72,
            word = "drink",
            category = "General Nouns",
            translation = "饮料 / 喝",
            ipa = "/drɪŋk/",
            exampleSentence = "Give me something to drink.",
            exampleTranslation = "给我一点喝的东西。",
            levelIndex = 8
        ),
        Word(
            id = 73,
            word = "water",
            category = "General Nouns",
            translation = "水",
            ipa = "/ˈwɔːtə/",
            exampleSentence = "Drink clean water.",
            exampleTranslation = "喝干净的水。",
            levelIndex = 8
        ),
        Word(
            id = 74,
            word = "milk",
            category = "General Nouns",
            translation = "牛奶",
            ipa = "/mɪlk/",
            exampleSentence = "The baby has some warm milk.",
            exampleTranslation = "婴儿喝了一些温暖的牛奶。",
            levelIndex = 8
        ),
        Word(
            id = 75,
            word = "bread",
            category = "General Nouns",
            translation = "面包",
            ipa = "/bred/",
            exampleSentence = "We eat bread in the morning.",
            exampleTranslation = "我们早上吃面包。",
            levelIndex = 8
        ),
        Word(
            id = 76,
            word = "apple",
            category = "Picturable Nouns",
            translation = "苹果",
            ipa = "/ˈæpl/",
            exampleSentence = "I have a red apple.",
            exampleTranslation = "我有一个红苹果。",
            levelIndex = 8
        ),
        Word(
            id = 77,
            word = "fruit",
            category = "General Nouns",
            translation = "水果",
            ipa = "/fruːt/",
            exampleSentence = "Eat some fresh fruit.",
            exampleTranslation = "吃点新鲜的水果。",
            levelIndex = 8
        ),
        Word(
            id = 78,
            word = "meat",
            category = "General Nouns",
            translation = "肉类",
            ipa = "/miːt/",
            exampleSentence = "We have bread and meat.",
            exampleTranslation = "我们有面包和肉。",
            levelIndex = 8
        ),
        Word(
            id = 79,
            word = "egg",
            category = "Picturable Nouns",
            translation = "鸡蛋",
            ipa = "/eɡ/",
            exampleSentence = "An egg is good for health.",
            exampleTranslation = "鸡蛋对健康有益。",
            levelIndex = 8
        ),
        Word(
            id = 80,
            word = "soup",
            category = "General Nouns",
            translation = "汤",
            ipa = "/suːp/",
            exampleSentence = "The potato soup is hot.",
            exampleTranslation = "马铃薯汤是温热的。",
            levelIndex = 8
        ),

        // Level 9: Common Adjectives (Describing Size & State)
        Word(
            id = 81,
            word = "good",
            category = "Adjectives",
            translation = "好的 / 善良的",
            ipa = "/ɡʊd/",
            exampleSentence = "He is a good student.",
            exampleTranslation = "他是一个好学生。",
            levelIndex = 9
        ),
        Word(
            id = 82,
            word = "bad",
            category = "Adjectives",
            translation = "坏的 / 恶劣的",
            ipa = "/bæd/",
            exampleSentence = "Do not eat bad food.",
            exampleTranslation = "不要吃坏掉的食物。",
            levelIndex = 9
        ),
        Word(
            id = 83,
            word = "new",
            category = "Adjectives",
            translation = "新的",
            ipa = "/njuː/",
            exampleSentence = "They have a new car.",
            exampleTranslation = "他们买了一辆新车。",
            levelIndex = 9
        ),
        Word(
            id = 84,
            word = "old",
            category = "Adjectives",
            translation = "老的 / 旧的",
            ipa = "/əʊld/",
            exampleSentence = "I read an old book.",
            exampleTranslation = "我读过一封旧书。",
            levelIndex = 9
        ),
        Word(
            id = 85,
            word = "big",
            category = "Adjectives",
            translation = "大的",
            ipa = "/bɪɡ/",
            exampleSentence = "Look at that big house.",
            exampleTranslation = "看着那栋大房子。",
            levelIndex = 9
        ),
        Word(
            id = 86,
            word = "small",
            category = "Adjectives",
            translation = "小的",
            ipa = "/smɔːl/",
            exampleSentence = "A small boy is running.",
            exampleTranslation = "一个小男孩在跑。",
            levelIndex = 9
        ),
        Word(
            id = 87,
            word = "long",
            category = "Adjectives",
            translation = "长的",
            ipa = "/lɒŋ/",
            exampleSentence = "The river is very long.",
            exampleTranslation = "这条河很长。",
            levelIndex = 9
        ),
        Word(
            id = 88,
            word = "short",
            category = "Adjectives",
            translation = "短的 / 矮的",
            ipa = "/ʃɔːt/",
            exampleSentence = "Wait for a short time.",
            exampleTranslation = "请等极短时间。",
            levelIndex = 9
        ),
        Word(
            id = 89,
            word = "hot",
            category = "Adjectives",
            translation = "热的 / 辣的",
            ipa = "/hɒt/",
            exampleSentence = "The summer weather is hot.",
            exampleTranslation = "夏天的天气很热。",
            levelIndex = 9
        ),
        Word(
            id = 90,
            word = "cold",
            category = "Adjectives",
            translation = "冷的 / 寒冷的",
            ipa = "/kəʊld/",
            exampleSentence = "I need a cup of hot tea in cold winter.",
            exampleTranslation = "寒冬里我需要一杯热茶。",
            levelIndex = 9
        ),

        // Level 10: Colors & Core Emotions (Adjectives)
        Word(
            id = 91,
            word = "black",
            category = "Adjectives",
            translation = "黑色的",
            ipa = "/blæk/",
            exampleSentence = "The night is black and dark.",
            exampleTranslation = "黑夜漆黑一片。",
            levelIndex = 10
        ),
        Word(
            id = 92,
            word = "white",
            category = "Adjectives",
            translation = "白色的",
            ipa = "/waɪt/",
            exampleSentence = "White snow fell from sky.",
            exampleTranslation = "白雪从天空中飘落。",
            levelIndex = 10
        ),
        Word(
            id = 93,
            word = "red",
            category = "Adjectives",
            translation = "红色的",
            ipa = "/red/",
            exampleSentence = "Apples are sometimes red.",
            exampleTranslation = "苹果有时是红色的。",
            levelIndex = 10
        ),
        Word(
            id = 94,
            word = "blue",
            category = "Adjectives",
            translation = "蓝色的",
            ipa = "/bluː/",
            exampleSentence = "The sky is blue today.",
            exampleTranslation = "今天的蓝天很美。",
            levelIndex = 10
        ),
        Word(
            id = 95,
            word = "green",
            category = "Adjectives",
            translation = "绿色的",
            ipa = "/ɡriːn/",
            exampleSentence = "The park has green trees.",
            exampleTranslation = "公园里有翠绿的树木。",
            levelIndex = 10
        ),
        Word(
            id = 96,
            word = "yellow",
            category = "Adjectives",
            translation = "黄色的",
            ipa = "/ˈjeləʊ/",
            exampleSentence = "Bananas are sweet and yellow.",
            exampleTranslation = "香蕉又甜又黄。",
            levelIndex = 10
        ),
        Word(
            id = 97,
            word = "happy",
            category = "Adjectives",
            translation = "快乐的 / 幸福的",
            ipa = "/ˈhæpi/",
            exampleSentence = "The happy family eats bread.",
            exampleTranslation = "快乐的家人在吃面包。",
            levelIndex = 10
        ),
        Word(
            id = 98,
            word = "sad",
            category = "Adjectives",
            translation = "悲伤的",
            ipa = "/sæd/",
            exampleSentence = "He was sad to see her go.",
            exampleTranslation = "看到她离去，他感到很难过。",
            levelIndex = 10
        ),
        Word(
            id = 99,
            word = "clean",
            category = "Adjectives",
            translation = "干净的",
            ipa = "/kliːn/",
            exampleSentence = "Keep your hands clean.",
            exampleTranslation = "保持双手清洁。",
            levelIndex = 10
        ),
        Word(
            id = 100,
            word = "dirty",
            category = "Adjectives",
            translation = "脏的",
            ipa = "/ˈdɜːti/",
            exampleSentence = "Wash those dirty dishes.",
            exampleTranslation = "把那些脏碟子洗了。",
            levelIndex = 10
        ),

        // Level 11: Time & Space
        Word(
            id = 101,
            word = "time",
            category = "General Nouns",
            translation = "时间",
            ipa = "/taɪm/",
            exampleSentence = "It is time to sleep.",
            exampleTranslation = "睡觉的时间到了。",
            levelIndex = 11
        ),
        Word(
            id = 102,
            word = "day",
            category = "General Nouns",
            translation = "白昼 / 一天",
            ipa = "/deɪ/",
            exampleSentence = "The day is warm and bright.",
            exampleTranslation = "白昼温暖明亮。",
            levelIndex = 11
        ),
        Word(
            id = 103,
            word = "night",
            category = "General Nouns",
            translation = "夜晚",
            ipa = "/naɪt/",
            exampleSentence = "We can see stars in the night.",
            exampleTranslation = "我们可以在晚上看到星星。",
            levelIndex = 11
        ),
        Word(
            id = 104,
            word = "week",
            category = "General Nouns",
            translation = "星期 / 周",
            ipa = "/wiːk/",
            exampleSentence = "I learn English every week.",
            exampleTranslation = "我每周都学习英语。",
            levelIndex = 11
        ),
        Word(
            id = 105,
            word = "month",
            category = "General Nouns",
            translation = "月份",
            ipa = "/mʌnθ/",
            exampleSentence = "Let's meet next month.",
            exampleTranslation = "让我们下个月见。",
            levelIndex = 11
        ),
        Word(
            id = 106,
            word = "year",
            category = "General Nouns",
            translation = "年 / 岁",
            ipa = "/jɪə/",
            exampleSentence = "A year is long.",
            exampleTranslation = "年很漫长。",
            levelIndex = 11
        ),
        Word(
            id = 107,
            word = "morning",
            category = "General Nouns",
            translation = "早晨",
            ipa = "/ˈmɔːnɪŋ/",
            exampleSentence = "Say good morning to your family.",
            exampleTranslation = "对家人说早安。",
            levelIndex = 11
        ),
        Word(
            id = 108,
            word = "evening",
            category = "General Nouns",
            translation = "傍晚 / 晚上",
            ipa = "/ˈiːvnɪŋ/",
            exampleSentence = "I walk in the evening.",
            exampleTranslation = "我在傍晚散步。",
            levelIndex = 11
        ),
        Word(
            id = 109,
            word = "world",
            category = "General Nouns",
            translation = "世界",
            ipa = "/wɜːld/",
            exampleSentence = "The basic language brings the world together.",
            exampleTranslation = "基础性语言能拉近世界的距离。",
            levelIndex = 11
        ),
        Word(
            id = 110,
            word = "earth",
            category = "General Nouns",
            translation = "地球 / 土地",
            ipa = "/ɜːθ/",
            exampleSentence = "The earth is a beautiful place.",
            exampleTranslation = "地球是个美好的地方。",
            levelIndex = 11
        ),

        // Level 12: School, Work & Friends
        Word(
            id = 111,
            word = "money",
            category = "General Nouns",
            translation = "金钱",
            ipa = "/ˈmʌni/",
            exampleSentence = "He has some gold coins as money.",
            exampleTranslation = "他有一些金币作为货币。",
            levelIndex = 12
        ),
        Word(
            id = 112,
            word = "work",
            category = "General Nouns",
            translation = "工作 / 运转",
            ipa = "/wɜːk/",
            exampleSentence = "Do your work well.",
            exampleTranslation = "把工作做好。",
            levelIndex = 12
        ),
        Word(
            id = 113,
            word = "play",
            category = "General Nouns",
            translation = "游戏 / 玩耍",
            ipa = "/pleɪ/",
            exampleSentence = "The kids like to play in the park.",
            exampleTranslation = "孩子们喜欢在公园里玩。",
            levelIndex = 12
        ),
        Word(
            id = 114,
            word = "school",
            category = "General Nouns",
            translation = "学校",
            ipa = "/skuːl/",
            exampleSentence = "Go to school to find knowledge.",
            exampleTranslation = "去学校寻找知识。",
            levelIndex = 12
        ),
        Word(
            id = 115,
            word = "book",
            category = "Picturable Nouns",
            translation = "书本",
            ipa = "/bʊk/",
            exampleSentence = "Open the book on page one.",
            exampleTranslation = "打开书本第一页。",
            levelIndex = 12
        ),
        Word(
            id = 116,
            word = "paper",
            category = "General Nouns",
            translation = "纸张",
            ipa = "/ˈpeɪpə/",
            exampleSentence = "Write down words on the paper.",
            exampleTranslation = "把词汇写在纸上。",
            levelIndex = 12
        ),
        Word(
            id = 117,
            word = "letter",
            category = "General Nouns",
            translation = "字母 / 信件",
            ipa = "/ˈletə/",
            exampleSentence = "He wrote an easy letter today.",
            exampleTranslation = "他今天写了一封简单的信。",
            levelIndex = 12
        ),
        Word(
            id = 118,
            word = "news",
            category = "General Nouns",
            translation = "新闻",
            ipa = "/njuːz/",
            exampleSentence = "I hear some good news.",
            exampleTranslation = "我听到了一些好消息。",
            levelIndex = 12
        ),
        Word(
            id = 119,
            word = "friend",
            category = "General Nouns",
            translation = "朋友",
            ipa = "/frend/",
            exampleSentence = "A true friend is like a brother.",
            exampleTranslation = "真诚的朋友就像亲兄弟。",
            levelIndex = 12
        ),
        Word(
            id = 120,
            word = "love",
            category = "General Nouns",
            translation = "爱 / 热爱",
            ipa = "/lʌv/",
            exampleSentence = "Mothers show deep love to babies.",
            exampleTranslation = "母亲对婴儿展现深切的关爱。",
            levelIndex = 12
        ),

        // Level 13: Nature & Elements
        Word(
            id = 121,
            word = "sun",
            category = "Picturable Nouns",
            translation = "太阳",
            ipa = "/sʌn/",
            exampleSentence = "The sun is hot and bright.",
            exampleTranslation = "太阳又热又亮。",
            levelIndex = 13
        ),
        Word(
            id = 122,
            word = "moon",
            category = "Picturable Nouns",
            translation = "月亮",
            ipa = "/muːn/",
            exampleSentence = "The moon shines at night.",
            exampleTranslation = "月亮在夜晚中闪耀。",
            levelIndex = 13
        ),
        Word(
            id = 123,
            word = "star",
            category = "Picturable Nouns",
            translation = "明星 / 星星",
            ipa = "/stɑː/",
            exampleSentence = "See the star in the dark sky.",
            exampleTranslation = "看黑夜天空中那颗闪亮的星星。",
            levelIndex = 13
        ),
        Word(
            id = 124,
            word = "sky",
            category = "General Nouns",
            translation = "天空",
            ipa = "/skaɪ/",
            exampleSentence = "White clouds fly in blue sky.",
            exampleTranslation = "白云并在蔚蓝的天空中飘落。",
            levelIndex = 13
        ),
        Word(
            id = 125,
            word = "wind",
            category = "General Nouns",
            translation = "风",
            ipa = "/wɪnd/",
            exampleSentence = "The cold wind blows code through windows.",
            exampleTranslation = "寒风从窗户里吹进来。",
            levelIndex = 13
        ),
        Word(
            id = 126,
            word = "rain",
            category = "General Nouns",
            translation = "雨水 / 下雨",
            ipa = "/reɪn/",
            exampleSentence = "The rain falls on the green land.",
            exampleTranslation = "雨落在这绿色的土地上。",
            levelIndex = 13
        ),
        Word(
            id = 127,
            word = "snow",
            category = "General Nouns",
            translation = "雪花 / 下雪",
            ipa = "/snəʊ/",
            exampleSentence = "The white snow makes the world quiet.",
            exampleTranslation = "白雪让世界变得寂静起来。",
            levelIndex = 13
        ),
        Word(
            id = 128,
            word = "fire",
            category = "General Nouns",
            translation = "火 / 烈火",
            ipa = "/ˈfaɪə/",
            exampleSentence = "Keep your hands far from the fire.",
            exampleTranslation = "双手离火堆远一点。",
            levelIndex = 13
        ),
        Word(
            id = 129,
            word = "land",
            category = "General Nouns",
            translation = "陆地 / 土地",
            ipa = "/lænd/",
            exampleSentence = "The ship reaches the land.",
            exampleTranslation = "船到达了陆地。",
            levelIndex = 13
        ),
        Word(
            id = 130,
            word = "sea",
            category = "General Nouns",
            translation = "大海",
            ipa = "/siː/",
            exampleSentence = "The sea is deep and blue.",
            exampleTranslation = "大海深而蓝。",
            levelIndex = 13
        ),

        // Level 14: Conceptual Adjectives
        Word(
            id = 131,
            word = "easy",
            category = "Adjectives",
            translation = "容易的",
            ipa = "/ˈiːzi/",
            exampleSentence = "This English word is easy.",
            exampleTranslation = "这个英文单词很容易。",
            levelIndex = 14
        ),
        Word(
            id = 132,
            word = "hard",
            category = "Adjectives",
            translation = "坚硬的 / 艰难的",
            ipa = "/hɑːd/",
            exampleSentence = "The work seems very hard.",
            exampleTranslation = "这项工作好像很艰难。",
            levelIndex = 14
        ),
        Word(
            id = 133,
            word = "true",
            category = "Adjectives",
            translation = "真实的 / 正确的",
            ipa = "/truː/",
            exampleSentence = "Tell me the true secret.",
            exampleTranslation = "把真实的秘密告诉我。",
            levelIndex = 14
        ),
        Word(
            id = 134,
            word = "false",
            category = "Adjectives",
            translation = "虚假的 / 错误的",
            ipa = "/fɔːls/",
            exampleSentence = "His words are false.",
            exampleTranslation = "他的话是虚假的。",
            levelIndex = 14
        ),
        Word(
            id = 135,
            word = "right",
            category = "Adjectives",
            translation = "右边的 / 对的",
            ipa = "/raɪt/",
            exampleSentence = "That is the right direction.",
            exampleTranslation = "那是正确的方向。",
            levelIndex = 14
        ),
        Word(
            id = 136,
            word = "wrong",
            category = "Adjectives",
            translation = "错误的",
            ipa = "/rɒŋ/",
            exampleSentence = "There is nothing wrong with you.",
            exampleTranslation = "你没有任何问题（你没错）。",
            levelIndex = 14
        ),
        Word(
            id = 137,
            word = "beautiful",
            category = "Adjectives",
            translation = "美丽的",
            ipa = "/ˈbjuːtɪfl/",
            exampleSentence = "What a beautiful face.",
            exampleTranslation = "多么美丽的脸庞。",
            levelIndex = 14
        ),
        Word(
            id = 138,
            word = "angry",
            category = "Adjectives",
            translation = "愤怒的",
            ipa = "/ˈæŋɡri/",
            exampleSentence = "My actions make him angry.",
            exampleTranslation = "我的举动让他很生气。",
            levelIndex = 14
        ),
        Word(
            id = 139,
            word = "quiet",
            category = "Adjectives",
            translation = "安静的",
            ipa = "/ˈkwaɪət/",
            exampleSentence = "The night is clean and quiet.",
            exampleTranslation = "黑夜洁净而安静。",
            levelIndex = 14
        ),
        Word(
            id = 140,
            word = "loud",
            category = "Adjectives",
            translation = "大声的 / 响亮的",
            ipa = "/laʊd/",
            exampleSentence = "He speaks in a loud voice.",
            exampleTranslation = "他用很大的声音说话。",
            levelIndex = 14
        ),

        // Level 15: Household Belongings & Clothing
        Word(
            id = 141,
            word = "clothing",
            category = "General Nouns",
            translation = "衣服 / 穿戴",
            ipa = "/ˈkləʊðɪŋ/",
            exampleSentence = "We wear warm clothing in winter.",
            exampleTranslation = "我们在冬天穿暖和的衣服。",
            levelIndex = 15
        ),
        Word(
            id = 142,
            word = "coat",
            category = "Picturable Nouns",
            translation = "上衣 / 外套",
            ipa = "/kəʊt/",
            exampleSentence = "I have a new black coat.",
            exampleTranslation = "我有一件黑色的新外套。",
            levelIndex = 15
        ),
        Word(
            id = 143,
            word = "hat",
            category = "Picturable Nouns",
            translation = "帽子",
            ipa = "/hæt/",
            exampleSentence = "He wears a small yellow hat.",
            exampleTranslation = "他戴着一顶黄色的小帽子。",
            levelIndex = 15
        ),
        Word(
            id = 144,
            word = "shoe",
            category = "Picturable Nouns",
            translation = "鞋子",
            ipa = "/ʃuː/",
            exampleSentence = "Is your shoe clean or dirty?",
            exampleTranslation = "你的鞋是干净的还是脏的？",
            levelIndex = 15
        ),
        Word(
            id = 145,
            word = "bag",
            category = "Picturable Nouns",
            translation = "包 / 袋子",
            ipa = "/bæɡ/",
            exampleSentence = "Put the apple in your school bag.",
            exampleTranslation = "把苹果放进你的书包里。",
            levelIndex = 15
        ),
        Word(
            id = 146,
            word = "box",
            category = "Picturable Nouns",
            translation = "盒子 / 箱子",
            ipa = "/bɒks/",
            exampleSentence = "A big box is on the table.",
            exampleTranslation = "桌上放着一个大箱子。",
            levelIndex = 15
        ),
        Word(
            id = 147,
            word = "key",
            category = "Picturable Nouns",
            translation = "钥匙 / 点窍",
            ipa = "/kiː/",
            exampleSentence = "I lost the key to my house door.",
            exampleTranslation = "我丢了我家大门的钥匙。",
            levelIndex = 15
        ),
        Word(
            id = 148,
            word = "clock",
            category = "Picturable Nouns",
            translation = "时钟 / 钟表",
            ipa = "/klɒk/",
            exampleSentence = "The room clock says it is morning.",
            exampleTranslation = "房间里的时钟显示现在是早晨了。",
            levelIndex = 15
        ),
        Word(
            id = 149,
            word = "bed",
            category = "Picturable Nouns",
            translation = "床铺",
            ipa = "/bed/",
            exampleSentence = "We sleep on a soft bed.",
            exampleTranslation = "我们在柔软的床上睡觉。",
            levelIndex = 15
        ),
        Word(
            id = 150,
            word = "table",
            category = "Picturable Nouns",
            translation = "桌子",
            ipa = "/ˈteɪbl/",
            exampleSentence = "Put the tea cup on the table.",
            exampleTranslation = "把茶杯放在桌子上。",
            levelIndex = 15
        )
    )

    /** All 850 words: 150 hand-written core words followed by the compact-format ones. */
    val initialWords: List<Word> by lazy { coreWords + ExtraWordData.words }

    const val TOTAL_LEVELS = 85

    private val coreLevelTitles = mapOf(
        1 to "动作动词", 2 to "助词与方向", 3 to "方位介词", 4 to "连词与介词", 5 to "关联代词",
        6 to "家庭与房屋", 7 to "人体感官", 8 to "美食与饮品", 9 to "常用状态", 10 to "色彩与情绪",
        11 to "时空领域", 12 to "学校与工作", 13 to "自然与天气", 14 to "抽象特征", 15 to "穿戴与物属"
    )

    fun levelTitle(level: Int): String =
        coreLevelTitles[level]
            ?: ExtraWordData.levels.firstOrNull { it.index == level }?.titleZh
            ?: "第 $level 关"
}
