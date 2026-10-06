# 850 常用英语词

目标：轻松掌握 850 个最常用的英语单词，并能用在日常生活交流中，听、说、读、写都练到。

## 学习方式

- **85 关 × 10 词 = 850 词**，按主题循序渐进：代词和功能词 → 日常动词 → 人物、家、食物、出行、天气 → 职业、学校、购物 → 形容词和日常衔接词。
- **每关两轮练习，覆盖本关全部单词**
  - 认读：看词选义（读）、听音选词（听）
  - 运用：选词填空（读）、听写（听+写）、拼写（写）、拼句子（读+写）、跟读例句（说，调用系统语音识别）
  - 第一次答错的题，会在本关结束前再问一遍。
- **间隔复习（Leitner 盒子）**：每个词按“第一次作答是否答对”升降，间隔 10 分钟 / 1 / 2 / 4 / 7 / 15 / 30 天。到期的词出现在首页“今日复习”。盒子 4 以上才算“已掌握”。
- **AI 伙伴 Lily**：带上下文的英语对话，可语音输入；内置 7 个生活情景（自我介绍、点餐、问路、购物、看病、打电话、订酒店）。
- 词库页可按“未学 / 学习中 / 已掌握 / 易错”筛选，每个词和例句都能朗读。

## 词库维护

- 第 1–15 关（150 词）在 `data/word_list_data.kt`。
- 第 16–85 关（700 词）在 `data/word_list_extra.kt`，用紧凑文本格式维护：`## 关卡标题 | Category`，每个词一行 `word|释义|音标|例句|例句翻译`。
- `WordDataTest` 校验总数 850、无重复、每关 10 词、例句包含目标词。

---

# Run and deploy your AI Studio app

This contains everything you need to run your app locally.

View your app in AI Studio: https://ai.studio/apps/35bca536-4e29-4c22-9188-69b8b505adb6

## Run Locally

**Prerequisites:**  [Android Studio](https://developer.android.com/studio)


1. Open Android Studio
2. Select **Open** and choose the directory containing this project
3. Allow Android Studio to fix any incompatibilities as it imports the project.
4. Create a file named `.env` in the project directory and set `GEMINI_API_KEY` in that file to your Gemini API key (see `.env.example` for an example)
5. Remove this line from the app's `build.gradle.kts` file: `signingConfig = signingConfigs.getByName("debugConfig")`
6. Run the app on an emulator or physical device
