package com.example.data

/**
 * Words 151-850 of the 850-word list, stored in a compact text format.
 *
 * A line starting with "##" opens a new level (10 words each): `## 中文标题 | English category`.
 * Every other line is a word: `word|translation|ipa|example sentence|example translation`.
 * Levels are numbered from [FIRST_LEVEL]; ids continue from [FIRST_ID].
 * Do not use `$` or triple quotes in the text below.
 */
object ExtraWordData {
    const val FIRST_ID = 151
    const val FIRST_LEVEL = 16

    private val raw = """
## 代词 | Pronouns
I|我|/aɪ/|I am a student.|我是学生。
you|你 / 你们|/juː/|Are you ready?|你准备好了吗？
he|他|/hiː/|He is my brother.|他是我的哥哥。
she|她|/ʃiː/|She lives in Beijing.|她住在北京。
it|它|/ɪt/|It is a nice day.|今天天气真好。
we|我们|/wiː/|We are friends.|我们是朋友。
they|他们|/ðeɪ/|They are at home.|他们在家。
this|这个|/ðɪs/|This is my phone.|这是我的手机。
that|那个|/ðæt/|That is a good idea.|那是个好主意。
my|我的|/maɪ/|This is my bag.|这是我的包。
## 物主与数量 | Possessives
your|你的|/jɔːr/|Is this your key?|这是你的钥匙吗？
his|他的|/hɪz/|His name is Tom.|他的名字叫汤姆。
her|她的 / 她|/hɜːr/|Her hair is long.|她的头发很长。
our|我们的|/aʊr/|Our school is big.|我们的学校很大。
their|他们的|/ðer/|Their house is old.|他们的房子很旧。
all|所有 / 全部|/ɔːl/|All the children are here.|所有孩子都在这里。
some|一些|/sʌm/|I want some water.|我想要一些水。
any|任何 / 一些|/ˈeni/|Do you have any milk?|你有牛奶吗？
every|每一个|/ˈevri/|I walk every day.|我每天都走路。
much|很多 / 非常|/mʌtʃ/|How much is it?|这个多少钱？
## 常用小词 | Function Words
no|不 / 没有|/noʊ/|No, thank you.|不用了，谢谢。
not|不|/nɑːt/|I am not tired.|我不累。
other|其他的|/ˈʌðər/|Where are the other students?|其他学生在哪里？
same|相同的|/seɪm/|We have the same bag.|我们的包一样。
such|这样的|/sʌtʃ/|It is such a nice day.|今天天气真好。
than|比|/ðæn/|My brother is taller than me.|我哥哥比我高。
then|然后 / 那时|/ðen/|We eat, and then we go home.|我们吃饭，然后回家。
there|那里|/ðer/|The bank is over there.|银行就在那边。
here|这里|/hɪr/|Please sit here.|请坐这里。
only|只有 / 仅仅|/ˈoʊnli/|I have only one sister.|我只有一个姐姐。
## 常用副词（一）| Adverbs
now|现在|/naʊ/|I am busy now.|我现在很忙。
very|非常|/ˈveri/|The soup is very hot.|汤非常烫。
too|太 / 也|/tuː/|This bag is too heavy.|这个包太重了。
also|也 / 而且|/ˈɔːlsoʊ/|I also like tea.|我也喜欢茶。
still|仍然|/stɪl/|Are you still at work?|你还在上班吗？
again|再 / 又|/əˈɡen/|Please say it again.|请再说一遍。
always|总是|/ˈɔːlweɪz/|He is always happy.|他总是很开心。
never|从不|/ˈnevər/|I never drink coffee.|我从不喝咖啡。
often|经常|/ˈɔːfn/|We often eat here.|我们经常在这里吃饭。
just|刚刚 / 只是|/dʒʌst/|I just got home.|我刚到家。
## 常用副词（二）| Adverbs
together|一起|/təˈɡeðər/|Let's go together.|我们一起去吧。
almost|几乎|/ˈɔːlmoʊst/|We are almost there.|我们快到了。
enough|足够的|/ɪˈnʌf/|I have enough money.|我的钱够了。
even|甚至|/ˈiːvn/|Even my mother likes it.|连我妈妈都喜欢它。
quite|相当|/kwaɪt/|It is quite cold today.|今天相当冷。
so|所以 / 这么|/soʊ/|I am so happy.|我太开心了。
soon|很快|/suːn/|See you soon.|回头见。
later|稍后|/ˈleɪtər/|I will call you later.|我稍后给你打电话。
today|今天|/təˈdeɪ/|I am free today.|我今天有空。
tomorrow|明天|/təˈmɑːroʊ/|See you tomorrow.|明天见。
## 方向与礼貌 | Directions & Politeness
yesterday|昨天|/ˈjestərdeɪ/|I was at home yesterday.|我昨天在家。
north|北|/nɔːrθ/|It is cold in the north.|北方很冷。
south|南|/saʊθ/|We live in the south.|我们住在南方。
east|东|/iːst/|The sun comes up in the east.|太阳从东方升起。
west|西|/west/|The wind comes from the west.|风从西边来。
please|请|/pliːz/|Please help me.|请帮帮我。
yes|是的|/jes/|Yes, I can.|是的，我可以。
maybe|也许|/ˈmeɪbi/|Maybe it will rain.|也许会下雨。
thanks|谢谢|/θæŋks/|Thanks for your help.|谢谢你的帮助。
sorry|对不起|/ˈsɑːri/|Sorry, I am late.|对不起，我迟到了。
## 打招呼与求助 | Greetings & Help
hello|你好|/həˈloʊ/|Hello, how are you?|你好，你好吗？
goodbye|再见|/ˌɡʊdˈbaɪ/|Goodbye! See you tomorrow.|再见！明天见。
welcome|欢迎|/ˈwelkəm/|Welcome to my home.|欢迎来我家。
thank|感谢|/θæŋk/|I thank you for the gift.|谢谢你的礼物。
excuse|原谅 / 借口|/ɪkˈskjuːz/|Excuse me, where is the bank?|打扰一下，银行在哪里？
wait|等待|/weɪt/|Please wait here.|请在这里等。
help|帮助|/help/|Can you help me?|你能帮我吗？
ask|问|/æsk/|May I ask a question?|我可以问个问题吗？
answer|回答|/ˈænsər/|Please answer my question.|请回答我的问题。
call|打电话 / 叫|/kɔːl/|I will call you tonight.|我今晚给你打电话。
## 日常动作（一）| Daily Verbs
eat|吃|/iːt/|Let's eat dinner.|我们吃晚饭吧。
sleep|睡觉|/sliːp/|I sleep eight hours.|我睡八个小时。
walk|走路|/wɔːk/|I walk to school.|我走路去学校。
run|跑|/rʌn/|He can run fast.|他跑得很快。
sit|坐|/sɪt/|Please sit down.|请坐下。
stand|站|/stænd/|Please stand up.|请站起来。
open|打开|/ˈoʊpən/|Please open the door.|请把门打开。
close|关上|/kloʊz/|Close the window, please.|请关上窗户。
look|看|/lʊk/|Look at the sky.|看天空。
listen|听|/ˈlɪsn/|Listen to me.|听我说。
## 日常动作（二）| Daily Verbs
speak|说（语言）|/spiːk/|Can you speak English?|你会说英语吗？
talk|交谈|/tɔːk/|Let's talk about it.|我们来谈谈这件事。
think|想 / 认为|/θɪŋk/|I think so.|我也这么认为。
know|知道|/noʊ/|I know your name.|我知道你的名字。
want|想要|/wɑːnt/|I want some tea.|我想要点茶。
need|需要|/niːd/|I need help.|我需要帮助。
like|喜欢|/laɪk/|I like music.|我喜欢音乐。
buy|买|/baɪ/|I want to buy a book.|我想买一本书。
sell|卖|/sel/|They sell fresh fruit.|他们卖新鲜水果。
pay|付钱|/peɪ/|I will pay by card.|我用卡付钱。
## 日常动作（三）| Daily Verbs
cook|做饭|/kʊk/|I cook dinner every day.|我每天做晚饭。
wash|洗|/wɑːʃ/|Wash your hands.|洗手。
wear|穿 / 戴|/wer/|I wear a coat in winter.|我冬天穿外套。
read|读|/riːd/|I read a book every night.|我每晚读书。
write|写|/raɪt/|Please write your name.|请写下你的名字。
learn|学习|/lɜːrn/|I learn English every day.|我每天学英语。
teach|教|/tiːtʃ/|Can you teach me?|你能教我吗？
study|学习 / 研究|/ˈstʌdi/|I study at night.|我晚上学习。
finish|完成|/ˈfɪnɪʃ/|I finish work at six.|我六点下班。
start|开始|/stɑːrt/|The class will start soon.|课马上就要开始了。
## 日常动作（四）| Daily Verbs
begin|开始|/bɪˈɡɪn/|Let's begin the lesson.|我们开始上课吧。
stop|停止|/stɑːp/|Stop here, please.|请在这里停。
try|尝试|/traɪ/|Please try this cake.|请尝尝这个蛋糕。
use|使用|/juːz/|Can I use your phone?|我可以用你的手机吗？
find|找到|/faɪnd/|I cannot find my keys.|我找不到我的钥匙。
lose|丢失|/luːz/|Do not lose your ticket.|别弄丢你的票。
bring|带来|/brɪŋ/|Please bring your book.|请带上你的书。
carry|携带 / 搬|/ˈkæri/|Can you carry this box?|你能搬这个箱子吗？
pull|拉|/pʊl/|Pull the door.|拉门。
push|推|/pʊʃ/|Push the door.|推门。
## 日常动作（五）| Daily Verbs
turn|转 / 轮到|/tɜːrn/|Turn left here.|在这里左转。
move|移动 / 搬家|/muːv/|Please move your car.|请把你的车挪一下。
stay|停留 / 待|/steɪ/|Stay at home today.|今天待在家里。
live|居住|/lɪv/|I live in a small town.|我住在一个小镇。
die|死|/daɪ/|The flowers will die without water.|没有水花会死。
feel|感觉|/fiːl/|I feel tired.|我觉得累。
hear|听见|/hɪr/|I cannot hear you.|我听不见你说话。
tell|告诉|/tel/|Please tell me the truth.|请告诉我真相。
show|展示 / 给…看|/ʃoʊ/|Can you show me the way?|你能给我指路吗？
meet|见面|/miːt/|Nice to meet you.|很高兴见到你。
## 出行与娱乐动词 | Verbs of Movement
visit|拜访|/ˈvɪzɪt/|I visit my parents on Sunday.|我星期天去看我父母。
travel|旅行|/ˈtrævl/|I like to travel.|我喜欢旅行。
drive|开车|/draɪv/|Can you drive?|你会开车吗？
ride|骑|/raɪd/|I ride a bike to work.|我骑自行车上班。
fly|飞|/flaɪ/|Birds can fly.|鸟会飞。
swim|游泳|/swɪm/|Can you swim?|你会游泳吗？
sing|唱歌|/sɪŋ/|She likes to sing.|她喜欢唱歌。
dance|跳舞|/dæns/|Let's dance!|我们跳舞吧！
laugh|笑|/læf/|The joke made us laugh.|这个笑话让我们大笑。
cry|哭|/kraɪ/|Do not cry.|别哭。
## 思想与情感动词 | Verbs of Mind
smile|微笑|/smaɪl/|Please smile.|请笑一笑。
wake|醒来|/weɪk/|I wake up at seven.|我七点醒来。
dream|梦想 / 做梦|/driːm/|I dream in English.|我用英语做梦。
hope|希望|/hoʊp/|I hope you are well.|希望你一切都好。
wish|祝愿|/wɪʃ/|I wish you good luck.|祝你好运。
remember|记得|/rɪˈmembər/|I remember your name.|我记得你的名字。
forget|忘记|/fərˈɡet/|Do not forget your key.|别忘了你的钥匙。
understand|明白|/ˌʌndərˈstænd/|I do not understand.|我不明白。
believe|相信|/bɪˈliːv/|I believe you.|我相信你。
choose|选择|/tʃuːz/|Please choose one.|请选一个。
## 动手动词（一）| Action Verbs
decide|决定|/dɪˈsaɪd/|I cannot decide.|我没法决定。
change|改变 / 零钱|/tʃeɪndʒ/|I want to change my room.|我想换房间。
build|建造|/bɪld/|They build houses here.|他们在这里盖房子。
break|打破 / 弄坏|/breɪk/|Do not break the glass.|别打破玻璃杯。
cut|切 / 剪|/kʌt/|Please cut the bread.|请把面包切开。
fill|装满|/fɪl/|Fill the glass with water.|把杯子装满水。
hold|握住 / 拿着|/hoʊld/|Please hold my bag.|请帮我拿一下包。
hit|打 / 撞|/hɪt/|The ball hit the window.|球打到了窗户。
pick|挑选 / 摘|/pɪk/|Pick any color.|选任何一种颜色。
throw|扔|/θroʊ/|Throw the ball to me.|把球扔给我。
## 动手动词（二）| Action Verbs
catch|接住 / 赶上|/kætʃ/|I must catch the bus.|我得赶上这班公交车。
fall|落下 / 摔倒|/fɔːl/|Leaves fall in autumn.|秋天树叶飘落。
grow|生长|/ɡroʊ/|Children grow fast.|孩子长得快。
win|赢|/wɪn/|Our team will win.|我们队会赢。
count|数数|/kaʊnt/|Count from one to ten.|从一数到十。
drop|掉落|/drɑːp/|Do not drop the glass.|别把杯子掉了。
hurry|赶快|/ˈhɜːri/|Hurry up! We are late.|快点！我们迟到了。
join|加入|/dʒɔɪn/|Please join us.|请加入我们。
mean|意思是|/miːn/|What does this word mean?|这个词是什么意思？
mix|混合|/mɪks/|Mix the milk and eggs.|把牛奶和鸡蛋混合。
## 安排与生活动词 | Everyday Verbs
notice|注意到|/ˈnoʊtɪs/|Did you notice the sign?|你注意到那个标志了吗？
pass|经过 / 传递|/pæs/|Please pass the salt.|请把盐递给我。
plan|计划|/plæn/|We plan to travel in May.|我们计划五月去旅行。
prepare|准备|/prɪˈper/|I prepare dinner at six.|我六点准备晚饭。
repeat|重复|/rɪˈpiːt/|Please repeat that.|请重复一遍。
rest|休息|/rest/|I need to rest.|我需要休息。
return|返回 / 归还|/rɪˈtɜːrn/|I return home at six.|我六点回家。
save|节省 / 救|/seɪv/|I save money every month.|我每个月存钱。
share|分享|/ʃer/|Let's share this cake.|我们分着吃这个蛋糕吧。
spend|花费 / 度过|/spend/|I spend too much money.|我花钱太多了。
## 交流与体验动词 | Verbs of Experience
taste|品尝 / 味道|/teɪst/|Taste this soup.|尝尝这个汤。
touch|触摸|/tʌtʃ/|Do not touch the stove.|别碰炉子。
watch|观看 / 手表|/wɑːtʃ/|Let's watch a movie.|我们看场电影吧。
worry|担心|/ˈwɜːri/|Do not worry.|别担心。
enjoy|享受|/ɪnˈdʒɔɪ/|I enjoy my work.|我喜欢我的工作。
invite|邀请|/ɪnˈvaɪt/|I want to invite you to dinner.|我想邀请你吃晚饭。
order|点餐 / 订购|/ˈɔːrdər/|Can I order now?|我现在可以点餐吗？
explain|解释|/ɪkˈspleɪn/|Please explain it again.|请再解释一遍。
agree|同意|/əˈɡriː/|I agree with you.|我同意你的看法。
allow|允许|/əˈlaʊ/|They do not allow dogs here.|这里不允许带狗。
## 情态与助动词 | Modal & Be Verbs
can|能 / 可以|/kæn/|I can swim.|我会游泳。
could|能 / 可否|/kʊd/|Could you help me?|你能帮我一下吗？
must|必须|/mʌst/|I must go now.|我现在必须走了。
should|应该|/ʃʊd/|You should rest.|你应该休息。
would|愿意 / 会|/wʊd/|Would you like some tea?|你想喝点茶吗？
am|是（我）|/æm/|I am a teacher.|我是老师。
is|是|/ɪz/|She is my friend.|她是我的朋友。
are|是（你们/他们）|/ɑːr/|You are kind.|你很善良。
was|是（过去）|/wʌz/|It was a good day.|那天很美好。
were|是（过去，复数）|/wɜːr/|We were at school.|我们当时在学校。
## 来去与借还 | Coming & Going
leave|离开|/liːv/|I leave home at seven.|我七点出门。
arrive|到达|/əˈraɪv/|We arrive at noon.|我们中午到。
enter|进入|/ˈentər/|Please enter the room.|请进房间。
follow|跟随|/ˈfɑːloʊ/|Follow me.|跟我来。
borrow|借入|/ˈbɑːroʊ/|Can I borrow your pen?|我可以借你的笔吗？
lend|借出|/lend/|Can you lend me some money?|你能借我点钱吗？
marry|结婚|/ˈmæri/|They will marry in June.|他们六月结婚。
shout|喊叫|/ʃaʊt/|Do not shout.|别大喊。
shake|摇 / 握（手）|/ʃeɪk/|Let's shake hands.|我们握个手吧。
point|指 / 点|/pɔɪnt/|Point to the door.|指一下门。
## 人 | People
man|男人|/mæn/|That man is my teacher.|那个男人是我的老师。
woman|女人|/ˈwʊmən/|The woman is a doctor.|那位女士是医生。
boy|男孩|/bɔɪ/|The boy is eight.|这个男孩八岁。
girl|女孩|/ɡɜːrl/|The girl has long hair.|那个女孩留着长发。
child|孩子|/tʃaɪld/|Every child likes games.|每个孩子都喜欢游戏。
person|人|/ˈpɜːrsn/|He is a kind person.|他是个善良的人。
people|人们|/ˈpiːpl/|Many people are here.|很多人在这里。
husband|丈夫|/ˈhʌzbənd/|My husband cooks dinner.|我丈夫做晚饭。
wife|妻子|/waɪf/|His wife is a nurse.|他的妻子是护士。
son|儿子|/sʌn/|My son is six.|我儿子六岁。
## 亲戚与邻里 | Relatives & Neighbors
daughter|女儿|/ˈdɔːtər/|My daughter is five.|我女儿五岁。
uncle|叔叔 / 舅舅|/ˈʌŋkl/|My uncle lives in Shanghai.|我叔叔住在上海。
aunt|阿姨 / 姑姑|/ænt/|My aunt is kind.|我阿姨很善良。
grandfather|祖父 / 外祖父|/ˈɡrænfɑːðər/|My grandfather is old.|我爷爷年纪大了。
grandmother|祖母 / 外祖母|/ˈɡrænmʌðər/|My grandmother cooks well.|我奶奶很会做饭。
parents|父母|/ˈperənts/|My parents live in Wuhan.|我父母住在武汉。
cousin|堂/表兄弟姐妹|/ˈkʌzn/|My cousin is my age.|我表弟和我同岁。
neighbor|邻居|/ˈneɪbər/|My neighbor is friendly.|我的邻居很友好。
guest|客人|/ɡest/|We have a guest tonight.|今晚我们有客人。
stranger|陌生人|/ˈstreɪndʒər/|Do not talk to a stranger.|别和陌生人说话。
## 身体部位 | Body Parts
arm|手臂|/ɑːrm/|My arm hurts.|我的手臂疼。
leg|腿|/leɡ/|My leg is sore.|我的腿酸痛。
finger|手指|/ˈfɪŋɡər/|I cut my finger.|我割到手指了。
back|背 / 后面|/bæk/|My back hurts.|我背疼。
neck|脖子|/nek/|My neck hurts.|我脖子疼。
heart|心|/hɑːrt/|Her heart is kind.|她心地善良。
tooth|牙齿|/tuːθ/|I have a bad tooth.|我有一颗坏牙。
skin|皮肤|/skɪn/|Her skin is soft.|她的皮肤很柔软。
blood|血|/blʌd/|There is blood on my hand.|我手上有血。
bone|骨头|/boʊn/|The dog has a bone.|狗有一根骨头。
## 看病与健康 | Health
doctor|医生|/ˈdɑːktər/|I need to see a doctor.|我需要看医生。
nurse|护士|/nɜːrs/|The nurse is kind.|护士很和善。
hospital|医院|/ˈhɑːspɪtl/|He is in the hospital.|他在住院。
medicine|药|/ˈmedsn/|Take this medicine.|吃这个药。
sick|生病的|/sɪk/|I feel sick.|我觉得不舒服。
pain|疼痛|/peɪn/|I have a pain in my leg.|我腿疼。
fever|发烧|/ˈfiːvər/|She has a fever.|她发烧了。
cough|咳嗽|/kɔːf/|I have a bad cough.|我咳得厉害。
hurt|疼 / 伤害|/hɜːrt/|Does it hurt?|疼吗？
health|健康|/helθ/|Health is important.|健康很重要。
## 房屋各处 | Parts of the Home
kitchen|厨房|/ˈkɪtʃɪn/|She is in the kitchen.|她在厨房。
bathroom|浴室|/ˈbæθruːm/|Where is the bathroom?|洗手间在哪里？
bedroom|卧室|/ˈbedruːm/|My bedroom is small.|我的卧室很小。
garden|花园|/ˈɡɑːrdn/|We have a small garden.|我们有一个小花园。
wall|墙|/wɔːl/|There is a clock on the wall.|墙上有个钟。
floor|地板 / 楼层|/flɔːr/|The floor is clean.|地板很干净。
roof|屋顶|/ruːf/|The roof is red.|屋顶是红色的。
stairs|楼梯|/sterz/|Be careful on the stairs.|上下楼梯要小心。
corner|角落 / 拐角|/ˈkɔːrnər/|The shop is on the corner.|商店在拐角处。
home|家|/hoʊm/|I am at home.|我在家。
## 家具餐具 | Furniture & Tableware
chair|椅子|/tʃer/|Please take a chair.|请坐。
lamp|灯|/læmp/|Turn on the lamp.|把灯打开。
sofa|沙发|/ˈsoʊfə/|The cat is on the sofa.|猫在沙发上。
bowl|碗|/boʊl/|I want a bowl of rice.|我想要一碗米饭。
cup|杯子|/kʌp/|A cup of tea, please.|请来一杯茶。
plate|盘子|/pleɪt/|Put the food on the plate.|把食物放在盘子上。
knife|刀|/naɪf/|Be careful with the knife.|小心刀。
fork|叉子|/fɔːrk/|I need a fork.|我需要一把叉子。
spoon|勺子|/spuːn/|Use a spoon for soup.|喝汤用勺子。
glass|玻璃杯 / 玻璃|/ɡlæs/|A glass of water, please.|请来一杯水。
## 日用品 | Household Items
bottle|瓶子|/ˈbɑːtl/|A bottle of water, please.|请来一瓶水。
towel|毛巾|/ˈtaʊəl/|I need a clean towel.|我需要一条干净的毛巾。
soap|肥皂|/soʊp/|Wash with soap.|用肥皂洗。
mirror|镜子|/ˈmɪrər/|Look in the mirror.|照照镜子。
brush|刷子 / 刷|/brʌʃ/|I need a new brush.|我需要一把新刷子。
pillow|枕头|/ˈpɪloʊ/|My pillow is soft.|我的枕头很软。
blanket|毯子|/ˈblæŋkɪt/|I need a blanket.|我需要一条毯子。
toy|玩具|/tɔɪ/|The child has a new toy.|孩子有一个新玩具。
tool|工具|/tuːl/|This tool is useful.|这个工具很有用。
camera|相机|/ˈkæmərə/|I have a new camera.|我有一台新相机。
## 电子产品 | Technology
phone|手机 / 电话|/foʊn/|My phone is new.|我的手机是新的。
computer|电脑|/kəmˈpjuːtər/|I use a computer at work.|我工作时用电脑。
internet|互联网|/ˈɪntərnet/|The internet is slow today.|今天网速很慢。
email|电子邮件|/ˈiːmeɪl/|I will send you an email.|我会给你发封邮件。
screen|屏幕|/skriːn/|The screen is broken.|屏幕坏了。
radio|收音机|/ˈreɪdioʊ/|I listen to the radio.|我听收音机。
television|电视|/ˈtelɪvɪʒn/|We watch television at night.|我们晚上看电视。
light|光 / 灯 / 轻的|/laɪt/|Turn on the light.|把灯打开。
battery|电池|/ˈbætəri/|My phone battery is low.|我的手机电量低了。
machine|机器|/məˈʃiːn/|This machine is old.|这台机器很旧。
## 衣物 | Clothes
shirt|衬衫|/ʃɜːrt/|I like your shirt.|我喜欢你的衬衫。
dress|连衣裙|/dres/|She has a red dress.|她有一条红裙子。
pants|裤子|/pænts/|These pants are too long.|这条裤子太长了。
skirt|裙子|/skɜːrt/|The skirt is short.|这条裙子很短。
sock|袜子|/sɑːk/|I lost a sock.|我丢了一只袜子。
glove|手套|/ɡlʌv/|I have one glove.|我有一只手套。
scarf|围巾|/skɑːrf/|Wear a scarf, it is cold.|戴条围巾，天冷。
jacket|夹克|/ˈdʒækɪt/|Take your jacket.|带上你的夹克。
glasses|眼镜|/ˈɡlæsɪz/|I wear glasses.|我戴眼镜。
umbrella|雨伞|/ʌmˈbrelə/|Take an umbrella.|带把伞。
## 主食饮品 | Staple Food & Drinks
rice|米饭|/raɪs/|I eat rice every day.|我每天吃米饭。
noodles|面条|/ˈnuːdlz/|I like noodles.|我喜欢面条。
tea|茶|/tiː/|Would you like tea?|你想喝茶吗？
coffee|咖啡|/ˈkɔːfi/|A coffee, please.|请来一杯咖啡。
juice|果汁|/dʒuːs/|I want orange juice.|我想要橙汁。
cake|蛋糕|/keɪk/|This cake is sweet.|这个蛋糕很甜。
sugar|糖|/ˈʃʊɡər/|No sugar, please.|请不要加糖。
salt|盐|/sɔːlt/|Pass the salt, please.|请把盐递给我。
cheese|奶酪|/tʃiːz/|I like cheese.|我喜欢奶酪。
butter|黄油|/ˈbʌtər/|Put butter on the bread.|在面包上涂黄油。
## 肉类蔬菜 | Meat & Vegetables
fish|鱼|/fɪʃ/|We eat fish on Friday.|我们星期五吃鱼。
chicken|鸡肉 / 鸡|/ˈtʃɪkɪn/|I like chicken.|我喜欢鸡肉。
beef|牛肉|/biːf/|Beef is expensive.|牛肉很贵。
pork|猪肉|/pɔːrk/|I do not eat pork.|我不吃猪肉。
vegetable|蔬菜|/ˈvedʒtəbl/|A potato is a vegetable.|土豆是一种蔬菜。
potato|土豆|/pəˈteɪtoʊ/|I want a potato.|我想要一个土豆。
tomato|番茄|/təˈmeɪtoʊ/|The tomato is red.|番茄是红色的。
onion|洋葱|/ˈʌnjən/|Onion makes me cry.|洋葱让我流泪。
carrot|胡萝卜|/ˈkærət/|I need a carrot.|我需要一根胡萝卜。
bean|豆子|/biːn/|This bean is small.|这颗豆子很小。
## 水果零食 | Fruit & Snacks
banana|香蕉|/bəˈnænə/|I eat a banana every morning.|我每天早上吃一根香蕉。
orange|橙子 / 橙色|/ˈɔːrɪndʒ/|I want an orange.|我想要一个橙子。
grape|葡萄|/ɡreɪp/|This grape is sweet.|这颗葡萄很甜。
lemon|柠檬|/ˈlemən/|I want tea with lemon.|我想要加柠檬的茶。
strawberry|草莓|/ˈstrɔːberi/|I love strawberry cake.|我爱草莓蛋糕。
candy|糖果|/ˈkændi/|Children like candy.|孩子们喜欢糖果。
chocolate|巧克力|/ˈtʃɔːklət/|I like chocolate.|我喜欢巧克力。
cookie|饼干|/ˈkʊki/|Have a cookie.|吃块饼干吧。
pizza|披萨|/ˈpiːtsə/|Let's order pizza.|我们点披萨吧。
sandwich|三明治|/ˈsænwɪtʃ/|I want a sandwich.|我想要一个三明治。
## 三餐与点餐 | Meals & Restaurants
breakfast|早餐|/ˈbrekfəst/|I eat breakfast at seven.|我七点吃早餐。
lunch|午餐|/lʌntʃ/|Let's have lunch.|我们去吃午饭吧。
dinner|晚餐|/ˈdɪnər/|Dinner is ready.|晚饭好了。
meal|一餐|/miːl/|This is a good meal.|这顿饭很好。
snack|零食|/snæk/|I want a snack.|我想吃点零食。
menu|菜单|/ˈmenjuː/|Can I see the menu?|我能看看菜单吗？
restaurant|餐厅|/ˈrestərɑːnt/|This restaurant is good.|这家餐厅很不错。
bill|账单|/bɪl/|The bill, please.|请买单。
waiter|服务员|/ˈweɪtər/|Waiter, a glass of water, please.|服务员，请来杯水。
delicious|美味的|/dɪˈlɪʃəs/|This soup is delicious.|这个汤很好喝。
## 味道与饥渴 | Taste & Hunger
sweet|甜的|/swiːt/|The cake is very sweet.|蛋糕很甜。
sour|酸的|/saʊr/|This lemon is sour.|这个柠檬很酸。
bitter|苦的|/ˈbɪtər/|Coffee is bitter.|咖啡是苦的。
spicy|辣的|/ˈspaɪsi/|I like spicy food.|我喜欢吃辣的。
salty|咸的|/ˈsɔːlti/|The soup is too salty.|汤太咸了。
fresh|新鲜的|/freʃ/|The fish is fresh.|鱼很新鲜。
hungry|饿的|/ˈhʌŋɡri/|I am hungry.|我饿了。
thirsty|渴的|/ˈθɜːrsti/|I am thirsty.|我渴了。
full|饱的 / 满的|/fʊl/|I am full.|我吃饱了。
empty|空的|/ˈempti/|The cup is empty.|杯子是空的。
## 城市（一）| In Town
city|城市|/ˈsɪti/|I live in a big city.|我住在大城市。
town|城镇|/taʊn/|My town is small.|我的小镇很小。
village|村庄|/ˈvɪlɪdʒ/|My grandmother lives in a village.|我奶奶住在村里。
street|街道|/striːt/|Cross the street carefully.|过马路要小心。
road|道路|/roʊd/|The road is long.|这条路很长。
bridge|桥|/brɪdʒ/|Walk across the bridge.|走过这座桥。
shop|商店|/ʃɑːp/|The shop is closed.|商店关门了。
store|商店 / 储存|/stɔːr/|I go to the store.|我去商店。
market|市场|/ˈmɑːrkɪt/|I buy fruit at the market.|我在市场买水果。
bank|银行|/bæŋk/|The bank is near here.|银行就在这附近。
## 城市（二）| Places
library|图书馆|/ˈlaɪbreri/|I read at the library.|我在图书馆读书。
park|公园|/pɑːrk/|Let's walk in the park.|我们去公园散步吧。
station|车站|/ˈsteɪʃn/|The station is near.|车站很近。
airport|机场|/ˈerpɔːrt/|I am at the airport.|我在机场。
hotel|酒店|/hoʊˈtel/|The hotel is nice.|这家酒店不错。
office|办公室|/ˈɔːfɪs/|I am in the office.|我在办公室。
farm|农场|/fɑːrm/|My uncle has a farm.|我叔叔有个农场。
factory|工厂|/ˈfæktri/|He works in a factory.|他在工厂工作。
cinema|电影院|/ˈsɪnəmə/|Let's go to the cinema.|我们去电影院吧。
supermarket|超市|/ˈsuːpərmɑːrkɪt/|I shop at the supermarket.|我在超市购物。
## 交通 | Transport
car|汽车|/kɑːr/|My car is blue.|我的车是蓝色的。
bus|公共汽车|/bʌs/|I take the bus to work.|我坐公交车上班。
train|火车|/treɪn/|The train is late.|火车晚点了。
plane|飞机|/pleɪn/|The plane is big.|飞机很大。
ship|轮船|/ʃɪp/|The ship is large.|这艘船很大。
boat|小船|/boʊt/|We sit in a small boat.|我们坐在一条小船里。
bike|自行车|/baɪk/|I ride a bike.|我骑自行车。
taxi|出租车|/ˈtæksi/|Let's take a taxi.|我们打车吧。
ticket|票|/ˈtɪkɪt/|I need a ticket.|我需要一张票。
traffic|交通|/ˈtræfɪk/|The traffic is bad today.|今天交通很糟。
## 旅行与问路 | Travel & Directions
map|地图|/mæp/|I need a map.|我需要一张地图。
trip|旅行|/trɪp/|Have a good trip.|旅途愉快。
passport|护照|/ˈpæspɔːrt/|Show your passport.|请出示护照。
luggage|行李|/ˈlʌɡɪdʒ/|Where is my luggage?|我的行李在哪里？
address|地址|/əˈdres/|What is your address?|你的地址是什么？
left|左边|/left/|Turn left at the corner.|在拐角处左转。
front|前面|/frʌnt/|Sit in the front.|坐在前面。
side|旁边 / 一侧|/saɪd/|The bank is on the left side.|银行在左边。
middle|中间|/ˈmɪdl/|I am in the middle.|我在中间。
edge|边缘|/edʒ/|Do not stand at the edge.|别站在边上。
## 自然物 | Nature
tree|树|/triː/|The tree is tall.|这棵树很高。
flower|花|/ˈflaʊər/|She gave me a flower.|她送了我一朵花。
grass|草|/ɡræs/|The grass is green.|草是绿的。
leaf|叶子|/liːf/|A leaf fell down.|一片叶子掉了下来。
rock|岩石|/rɑːk/|Do not climb the rock.|别爬那块岩石。
stone|石头|/stoʊn/|The stone is heavy.|这块石头很重。
sand|沙子|/sænd/|The sand is hot.|沙子很烫。
cloud|云|/klaʊd/|There is one cloud in the sky.|天上有一朵云。
storm|暴风雨|/stɔːrm/|A storm is coming.|暴风雨要来了。
ice|冰|/aɪs/|Put some ice in the glass.|往杯子里放些冰。
## 地理 | Geography
mountain|山|/ˈmaʊntn/|The mountain is high.|这座山很高。
river|河|/ˈrɪvər/|The river is wide.|这条河很宽。
lake|湖|/leɪk/|We swim in the lake.|我们在湖里游泳。
beach|海滩|/biːtʃ/|Let's go to the beach.|我们去海滩吧。
forest|森林|/ˈfɔːrɪst/|The forest is quiet.|森林很安静。
island|岛|/ˈaɪlənd/|They live on an island.|他们住在岛上。
field|田野|/fiːld/|The cows are in the field.|牛在田野里。
country|国家 / 乡下|/ˈkʌntri/|Which country are you from?|你来自哪个国家？
hill|小山|/hɪl/|We walk up the hill.|我们走上山坡。
ocean|海洋|/ˈoʊʃn/|The ocean is deep.|海洋很深。
## 天气与季节 | Weather & Seasons
weather|天气|/ˈweðər/|The weather is nice.|天气很好。
cloudy|多云的|/ˈklaʊdi/|It is cloudy today.|今天多云。
sunny|晴朗的|/ˈsʌni/|It is sunny today.|今天晴天。
rainy|下雨的|/ˈreɪni/|It is a rainy day.|今天是雨天。
windy|有风的|/ˈwɪndi/|It is windy.|风很大。
warm|温暖的|/wɔːrm/|It is warm today.|今天很暖和。
cool|凉爽的|/kuːl/|The water is cool.|水很凉爽。
spring|春天|/sprɪŋ/|Flowers grow in spring.|春天花会开。
summer|夏天|/ˈsʌmər/|Summer is hot.|夏天很热。
autumn|秋天|/ˈɔːtəm/|Leaves fall in autumn.|秋天树叶飘落。
## 时间表达 | Time Expressions
winter|冬天|/ˈwɪntər/|Winter is cold.|冬天很冷。
hour|小时|/ˈaʊər/|It is one hour away.|离这里一个小时的路程。
minute|分钟|/ˈmɪnɪt/|Wait a minute.|等一下。
second|秒 / 第二|/ˈsekənd/|Wait a second.|稍等一秒。
noon|中午|/nuːn/|We eat lunch at noon.|我们中午吃午饭。
tonight|今晚|/təˈnaɪt/|What are you doing tonight?|你今晚做什么？
weekend|周末|/ˈwiːkend/|Have a nice weekend.|周末愉快。
holiday|假期|/ˈhɑːlədeɪ/|I am on holiday.|我在度假。
birthday|生日|/ˈbɜːrθdeɪ/|Happy birthday!|生日快乐！
moment|片刻|/ˈmoʊmənt/|Just a moment.|稍等片刻。
## 星期 | Days of the Week
Monday|星期一|/ˈmʌndeɪ/|I work on Monday.|我星期一上班。
Tuesday|星期二|/ˈtuːzdeɪ/|I have class on Tuesday.|我星期二有课。
Wednesday|星期三|/ˈwenzdeɪ/|Wednesday is my day off.|星期三我休息。
Thursday|星期四|/ˈθɜːrzdeɪ/|The meeting is on Thursday.|会议在星期四。
Friday|星期五|/ˈfraɪdeɪ/|Friday is my favorite day.|星期五是我最喜欢的一天。
Saturday|星期六|/ˈsætərdeɪ/|We play ball on Saturday.|我们星期六打球。
Sunday|星期日|/ˈsʌndeɪ/|I rest on Sunday.|我星期天休息。
date|日期 / 约会|/deɪt/|What is the date today?|今天几号？
calendar|日历|/ˈkælɪndər/|Look at the calendar.|看看日历。
future|未来|/ˈfjuːtʃər/|I want a good future.|我想要一个美好的未来。
## 月份（一）| Months
January|一月|/ˈdʒænjueri/|It is cold in January.|一月很冷。
February|二月|/ˈfebrueri/|February is short.|二月很短。
March|三月|/mɑːrtʃ/|Spring starts in March.|春天从三月开始。
April|四月|/ˈeɪprəl/|It rains a lot in April.|四月常下雨。
June|六月|/dʒuːn/|My birthday is in June.|我的生日在六月。
July|七月|/dʒuˈlaɪ/|July is very hot.|七月很热。
August|八月|/ˈɔːɡəst/|We travel in August.|我们八月去旅行。
September|九月|/sepˈtembər/|School starts in September.|九月开学。
October|十月|/ɑːkˈtoʊbər/|October is a nice month.|十月是个好月份。
November|十一月|/noʊˈvembər/|It gets cold in November.|十一月天气变冷。
## 数字（一）| Numbers
December|十二月|/dɪˈsembər/|Winter starts in December.|冬天从十二月开始。
zero|零|/ˈzɪroʊ/|The temperature is zero.|气温是零度。
one|一|/wʌn/|I have one brother.|我有一个哥哥。
two|二|/tuː/|I have two cats.|我有两只猫。
three|三|/θriː/|Wait three minutes.|等三分钟。
four|四|/fɔːr/|I have four books.|我有四本书。
five|五|/faɪv/|Give me five!|击个掌！
six|六|/sɪks/|I get up at six.|我六点起床。
seven|七|/ˈsevn/|We eat at seven.|我们七点吃饭。
eight|八|/eɪt/|School starts at eight.|学校八点开始上课。
## 数字（二）| Numbers
nine|九|/naɪn/|I go to bed at nine.|我九点睡觉。
ten|十|/ten/|Count to ten.|数到十。
twenty|二十|/ˈtwenti/|I am twenty years old.|我二十岁。
hundred|百|/ˈhʌndrəd/|One hundred people came.|一百个人来了。
thousand|千|/ˈθaʊznd/|It costs a thousand yuan.|这要一千块钱。
first|第一|/fɜːrst/|This is my first day.|这是我的第一天。
last|最后的 / 上一个|/læst/|This is the last bus.|这是最后一班公交车。
half|一半|/hæf/|I ate half of the cake.|我吃了半个蛋糕。
pair|一双 / 一对|/per/|I need a pair of shoes.|我需要一双鞋。
number|数字 / 号码|/ˈnʌmbər/|What is your phone number?|你的电话号码是多少？
## 常见动物 | Animals
dog|狗|/dɔːɡ/|My dog is friendly.|我的狗很友好。
cat|猫|/kæt/|The cat is sleeping.|猫在睡觉。
bird|鸟|/bɜːrd/|A bird is singing.|一只鸟在唱歌。
horse|马|/hɔːrs/|He can ride a horse.|他会骑马。
cow|奶牛|/kaʊ/|The cow gives milk.|奶牛产奶。
pig|猪|/pɪɡ/|The pig is fat.|这头猪很胖。
sheep|羊|/ʃiːp/|The sheep eat grass.|羊在吃草。
rabbit|兔子|/ˈræbɪt/|The rabbit is white.|兔子是白色的。
mouse|老鼠 / 鼠标|/maʊs/|A mouse is in the kitchen.|厨房里有只老鼠。
duck|鸭子|/dʌk/|The duck is on the lake.|鸭子在湖上。
## 更多动物 | More Animals
tiger|老虎|/ˈtaɪɡər/|The tiger is strong.|老虎很强壮。
lion|狮子|/ˈlaɪən/|The lion is the king.|狮子是兽中之王。
elephant|大象|/ˈelɪfənt/|The elephant is big.|大象很大。
monkey|猴子|/ˈmʌŋki/|The monkey likes bananas.|猴子喜欢香蕉。
bear|熊|/ber/|The bear sleeps in winter.|熊在冬天睡觉。
snake|蛇|/sneɪk/|A snake is long.|蛇很长。
wolf|狼|/wʊlf/|The wolf is in the forest.|狼在森林里。
bee|蜜蜂|/biː/|A bee is on the flower.|一只蜜蜂在花上。
butterfly|蝴蝶|/ˈbʌtərflaɪ/|The butterfly is beautiful.|蝴蝶很美。
animal|动物|/ˈænɪml/|A dog is an animal.|狗是一种动物。
## 职业 | Jobs
teacher|老师|/ˈtiːtʃər/|My teacher is kind.|我的老师很和善。
student|学生|/ˈstuːdnt/|I am a student.|我是学生。
driver|司机|/ˈdraɪvər/|The driver is friendly.|司机很友好。
farmer|农民|/ˈfɑːrmər/|The farmer has a cow.|农民有一头奶牛。
worker|工人|/ˈwɜːrkər/|He is a hard worker.|他是个勤奋的人。
boss|老板|/bɔːs/|My boss is nice.|我老板人很好。
police|警察|/pəˈliːs/|Call the police!|快报警！
artist|艺术家|/ˈɑːrtɪst/|She is an artist.|她是一位艺术家。
singer|歌手|/ˈsɪŋər/|He is a good singer.|他是个好歌手。
engineer|工程师|/ˌendʒɪˈnɪr/|My father is an engineer.|我父亲是工程师。
## 职场 | At Work
job|工作|/dʒɑːb/|I like my job.|我喜欢我的工作。
company|公司|/ˈkʌmpəni/|He works at a big company.|他在一家大公司工作。
meeting|会议|/ˈmiːtɪŋ/|I have a meeting at ten.|我十点有个会议。
customer|顾客|/ˈkʌstəmər/|The customer is happy.|顾客很满意。
manager|经理|/ˈmænɪdʒər/|The manager is in the office.|经理在办公室。
team|团队|/tiːm/|We are a good team.|我们是个好团队。
business|生意|/ˈbɪznəs/|He has a small business.|他有个小生意。
salary|薪水|/ˈsæləri/|My salary is good.|我的薪水不错。
schedule|日程|/ˈskedʒuːl/|What is your schedule today?|你今天的日程怎么安排？
report|报告|/rɪˈpɔːrt/|I must write a report.|我得写一份报告。
## 学校用品 | School
lesson|课|/ˈlesn/|The lesson is easy.|这节课很简单。
class|班 / 课|/klæs/|Class starts at eight.|八点上课。
test|测试|/test/|I have a test today.|我今天有个测验。
homework|作业|/ˈhoʊmwɜːrk/|I do my homework at night.|我晚上做作业。
exam|考试|/ɪɡˈzæm/|The exam is tomorrow.|考试在明天。
pen|钢笔|/pen/|Can I use your pen?|我可以用你的笔吗？
pencil|铅笔|/ˈpensl/|I need a pencil.|我需要一支铅笔。
notebook|笔记本|/ˈnoʊtbʊk/|Write it in your notebook.|把它写在你的笔记本上。
dictionary|词典|/ˈdɪkʃəneri/|Look it up in the dictionary.|查一下词典。
classroom|教室|/ˈklæsruːm/|The classroom is clean.|教室很干净。
## 语言与沟通 | Language
question|问题|/ˈkwestʃən/|I have a question.|我有个问题。
word|单词|/wɜːrd/|I do not know this word.|我不认识这个单词。
name|名字|/neɪm/|My name is Li Ming.|我叫李明。
language|语言|/ˈlæŋɡwɪdʒ/|English is a language.|英语是一种语言。
sentence|句子|/ˈsentəns/|Write one sentence.|写一个句子。
sound|声音|/saʊnd/|I hear a sound.|我听到一个声音。
voice|嗓音|/vɔɪs/|She has a nice voice.|她的嗓音很好听。
story|故事|/ˈstɔːri/|Tell me a story.|给我讲个故事。
message|消息|/ˈmesɪdʒ/|I got your message.|我收到你的消息了。
English|英语|/ˈɪŋɡlɪʃ/|I study English.|我学英语。
## 娱乐 | Fun & Media
picture|图片|/ˈpɪktʃər/|Draw a picture.|画一幅画。
photo|照片|/ˈfoʊtoʊ/|Take a photo, please.|请拍张照。
music|音乐|/ˈmjuːzɪk/|I love music.|我爱音乐。
song|歌|/sɔːŋ/|This song is nice.|这首歌很好听。
movie|电影|/ˈmuːvi/|Let's watch a movie.|我们看电影吧。
game|游戏 / 比赛|/ɡeɪm/|Let's play a game.|我们玩个游戏吧。
sport|运动|/spɔːrt/|My favorite sport is football.|我最喜欢的运动是足球。
ball|球|/bɔːl/|Kick the ball.|踢球。
party|聚会|/ˈpɑːrti/|I have a party tonight.|我今晚有个聚会。
gift|礼物|/ɡɪft/|This gift is for you.|这个礼物是给你的。
## 运动与爱好 | Sports & Hobbies
football|足球|/ˈfʊtbɔːl/|I play football.|我踢足球。
basketball|篮球|/ˈbæskɪtbɔːl/|He plays basketball.|他打篮球。
tennis|网球|/ˈtenɪs/|Let's play tennis.|我们打网球吧。
exercise|锻炼|/ˈeksərsaɪz/|I exercise every morning.|我每天早上锻炼。
hobby|爱好|/ˈhɑːbi/|My hobby is reading.|我的爱好是阅读。
race|比赛 / 赛跑|/reɪs/|I run in the race.|我参加赛跑。
coach|教练|/koʊtʃ/|Our coach is strict.|我们的教练很严格。
player|球员 / 玩家|/ˈpleɪər/|He is a good player.|他是个好球员。
fan|粉丝 / 风扇|/fæn/|I am a big fan.|我是个大粉丝。
prize|奖品|/praɪz/|She won a prize.|她赢了一个奖。
## 购物 | Shopping
price|价格|/praɪs/|What is the price?|价格是多少？
cheap|便宜的|/tʃiːp/|It is cheap.|很便宜。
expensive|贵的|/ɪkˈspensɪv/|This watch is expensive.|这块手表很贵。
cash|现金|/kæʃ/|I pay in cash.|我付现金。
card|卡|/kɑːrd/|Can I pay by card?|我可以刷卡吗？
receipt|收据|/rɪˈsiːt/|Can I have the receipt?|我可以要收据吗？
size|尺寸|/saɪz/|What size do you wear?|你穿什么尺码？
color|颜色|/ˈkʌlər/|What color do you like?|你喜欢什么颜色？
sale|促销|/seɪl/|Everything is on sale.|所有东西都在打折。
wallet|钱包|/ˈwɑːlɪt/|My wallet is in my bag.|我的钱包在包里。
## 抽象名词（一）| Ideas
idea|主意|/aɪˈdiːə/|That is a good idea.|这是个好主意。
problem|问题|/ˈprɑːbləm/|No problem.|没问题。
reason|原因|/ˈriːzn/|What is the reason?|原因是什么？
way|方式 / 路|/weɪ/|Which way is the station?|车站往哪边走？
thing|东西 / 事情|/θɪŋ/|What is that thing?|那个东西是什么？
kind|种类 / 善良的|/kaɪnd/|What kind of food do you like?|你喜欢哪种食物？
part|部分|/pɑːrt/|This is part of my job.|这是我工作的一部分。
place|地方|/pleɪs/|This is a nice place.|这是个好地方。
example|例子|/ɪɡˈzæmpl/|Give me an example.|给我举个例子。
life|生活 / 生命|/laɪf/|Life is good.|生活很美好。
## 抽象名词（二）| Everyday Troubles
rule|规则|/ruːl/|Follow the rule.|遵守规则。
mistake|错误|/mɪˈsteɪk/|I made a mistake.|我犯了个错。
chance|机会|/tʃæns/|Give me a chance.|给我一个机会。
trouble|麻烦|/ˈtrʌbl/|I am in trouble.|我有麻烦了。
noise|噪音|/nɔɪz/|Too much noise!|太吵了！
danger|危险|/ˈdeɪndʒər/|Danger! Do not enter.|危险！禁止入内。
secret|秘密|/ˈsiːkrət/|It is a secret.|这是个秘密。
result|结果|/rɪˈzʌlt/|The result is good.|结果不错。
accident|事故|/ˈæksɪdənt/|There was an accident.|发生了一起事故。
luck|运气|/lʌk/|Good luck!|祝你好运！
## 感受 | Feelings
tired|累的|/ˈtaɪərd/|I am tired.|我累了。
afraid|害怕的|/əˈfreɪd/|I am afraid of dogs.|我怕狗。
shy|害羞的|/ʃaɪ/|He is shy.|他很害羞。
proud|骄傲的|/praʊd/|I am proud of you.|我为你骄傲。
excited|兴奋的|/ɪkˈsaɪtɪd/|I am excited.|我很兴奋。
bored|无聊的|/bɔːrd/|I am bored.|我觉得无聊。
surprised|惊讶的|/sərˈpraɪzd/|I am surprised.|我很惊讶。
lonely|孤独的|/ˈloʊnli/|She is lonely.|她很孤独。
worried|担心的|/ˈwɜːrid/|I am worried.|我很担心。
nervous|紧张的|/ˈnɜːrvəs/|I am nervous.|我很紧张。
## 性格 | Personality
polite|礼貌的|/pəˈlaɪt/|He is very polite.|他很有礼貌。
rude|粗鲁的|/ruːd/|Do not be rude.|别没礼貌。
smart|聪明的|/smɑːrt/|She is smart.|她很聪明。
foolish|愚蠢的|/ˈfuːlɪʃ/|That was foolish.|那很愚蠢。
brave|勇敢的|/breɪv/|The boy is brave.|这个男孩很勇敢。
lazy|懒的|/ˈleɪzi/|I am lazy today.|我今天很懒。
careful|小心的|/ˈkerfl/|Be careful!|小心！
honest|诚实的|/ˈɑːnɪst/|Please be honest.|请诚实一点。
friendly|友好的|/ˈfrendli/|The people are friendly.|这里的人很友好。
funny|有趣的|/ˈfʌni/|He is funny.|他很有趣。
## 形容大小程度 | Size & Degree
tall|高的|/tɔːl/|He is tall.|他很高。
high|高的|/haɪ/|The mountain is high.|这座山很高。
low|低的|/loʊ/|The price is low.|价格很低。
deep|深的|/diːp/|The lake is deep.|这个湖很深。
wide|宽的|/waɪd/|The road is wide.|路很宽。
narrow|窄的|/ˈneroʊ/|The street is narrow.|街道很窄。
thick|厚的|/θɪk/|The book is thick.|这本书很厚。
thin|薄的 / 瘦的|/θɪn/|The paper is thin.|纸很薄。
heavy|重的|/ˈhevi/|The box is heavy.|箱子很重。
fat|胖的|/fæt/|The cat is fat.|这只猫很胖。
## 形容状态 | States
fast|快的|/fæst/|The train is fast.|火车很快。
slow|慢的|/sloʊ/|The bus is slow.|公交车很慢。
early|早的|/ˈɜːrli/|I get up early.|我起得早。
late|晚的 / 迟到|/leɪt/|Sorry, I am late.|对不起，我迟到了。
young|年轻的|/jʌŋ/|She is young.|她很年轻。
strong|强壮的|/strɔːŋ/|He is strong.|他很强壮。
weak|虚弱的|/wiːk/|I feel weak.|我觉得虚弱。
rich|富有的|/rɪtʃ/|He is rich.|他很有钱。
poor|贫穷的|/pʊr/|They are poor.|他们很穷。
busy|忙的|/ˈbɪzi/|I am busy.|我很忙。
## 常用形容词（一）| Adjectives
free|自由的 / 免费的|/friː/|Are you free tonight?|你今晚有空吗？
ready|准备好的|/ˈredi/|I am ready.|我准备好了。
safe|安全的|/seɪf/|This place is safe.|这个地方很安全。
dangerous|危险的|/ˈdeɪndʒərəs/|The road is dangerous.|这条路很危险。
healthy|健康的|/ˈhelθi/|Fruit is healthy.|水果有益健康。
special|特别的|/ˈspeʃl/|Today is a special day.|今天是个特别的日子。
important|重要的|/ɪmˈpɔːrtnt/|This is important.|这很重要。
different|不同的|/ˈdɪfrənt/|We are different.|我们不一样。
similar|相似的|/ˈsɪmələr/|They look similar.|它们看起来很像。
simple|简单的|/ˈsɪmpl/|It is simple.|这很简单。
## 常用形容词（二）| Adjectives
difficult|困难的|/ˈdɪfɪkəlt/|English is not difficult.|英语并不难。
possible|可能的|/ˈpɑːsəbl/|Is it possible?|有可能吗？
certain|确定的|/ˈsɜːrtn/|I am certain.|我很确定。
lucky|幸运的|/ˈlʌki/|You are lucky.|你真幸运。
necessary|必要的|/ˈnesəseri/|Is it necessary?|有必要吗？
useful|有用的|/ˈjuːsfl/|This map is useful.|这张地图很有用。
wonderful|极好的|/ˈwʌndərfl/|It is a wonderful day.|今天真是美好的一天。
terrible|糟糕的|/ˈterəbl/|The weather is terrible.|天气糟透了。
perfect|完美的|/ˈpɜːrfɪkt/|It is perfect.|太完美了。
strange|奇怪的|/streɪndʒ/|That is strange.|那很奇怪。
## 物体特征 | Qualities of Things
dark|黑暗的|/dɑːrk/|It is dark outside.|外面很黑。
bright|明亮的|/braɪt/|The room is bright.|房间很明亮。
soft|柔软的|/sɔːft/|The bed is soft.|床很软。
sharp|锋利的|/ʃɑːrp/|The knife is sharp.|刀很锋利。
smooth|光滑的|/smuːð/|The table is smooth.|桌子很光滑。
rough|粗糙的|/rʌf/|The road is rough.|路很颠簸。
wet|湿的|/wet/|My shoes are wet.|我的鞋湿了。
dry|干的|/draɪ/|The towel is dry.|毛巾是干的。
flat|平的|/flæt/|The road is flat.|路很平。
round|圆的|/raʊnd/|The ball is round.|球是圆的。
## 位置 | Position
inside|里面|/ˌɪnˈsaɪd/|Come inside.|进来吧。
outside|外面|/ˌaʊtˈsaɪd/|It is cold outside.|外面很冷。
above|在…上方|/əˈbʌv/|The lamp is above the table.|灯在桌子上方。
below|在…下方|/bɪˈloʊ/|The cat is below the chair.|猫在椅子下面。
behind|在…后面|/bɪˈhaɪnd/|The shop is behind the bank.|商店在银行后面。
beside|在…旁边|/bɪˈsaɪd/|Sit beside me.|坐在我旁边。
near|近的|/nɪr/|The park is near.|公园很近。
far|远的|/fɑːr/|Is it far?|远吗？
around|周围|/əˈraʊnd/|Look around.|看看四周。
away|离开 / 远离|/əˈweɪ/|Go away.|走开。
## 疑问与连接 | Question Words & Links
what|什么|/wʌt/|What is your name?|你叫什么名字？
which|哪一个|/wɪtʃ/|Which one do you want?|你想要哪一个？
whose|谁的|/huːz/|Whose bag is this?|这是谁的包？
next|下一个|/nekst/|Who is next?|下一个是谁？
until|直到|/ənˈtɪl/|Wait until six.|等到六点。
since|自从|/sɪns/|I have lived here since May.|我从五月起就住在这里。
during|在…期间|/ˈdʊrɪŋ/|Do not talk during the movie.|电影放映期间不要说话。
without|没有|/wɪˈðaʊt/|I drink tea without sugar.|我喝茶不加糖。
both|两者都|/boʊθ/|Both of us are tired.|我们俩都累了。
each|每个|/iːtʃ/|Each child has a book.|每个孩子都有一本书。
## 数量表达 | Quantity
many|许多|/ˈmeni/|I have many friends.|我有很多朋友。
few|很少|/fjuː/|Few people came.|来的人很少。
more|更多|/mɔːr/|I want more water.|我想要更多的水。
most|最多 / 大多数|/moʊst/|Most people like music.|大多数人喜欢音乐。
less|更少|/les/|Eat less sugar.|少吃点糖。
another|另一个|/əˈnʌðər/|Can I have another cup?|我可以再要一杯吗？
own|自己的|/oʊn/|I have my own room.|我有自己的房间。
several|几个|/ˈsevrəl/|I have several friends here.|我在这里有几个朋友。
something|某事|/ˈsʌmθɪŋ/|I want to say something.|我想说点事。
nothing|没有什么|/ˈnʌθɪŋ/|Nothing is wrong.|没什么问题。
## 不定代词与宾格 | Pronouns
everyone|每个人|/ˈevriwʌn/|Everyone is here.|大家都在这里。
someone|某人|/ˈsʌmwʌn/|Someone is at the door.|有人在门口。
anyone|任何人|/ˈeniwʌn/|Is anyone here?|有人在吗？
everything|一切|/ˈevriθɪŋ/|Everything is fine.|一切都好。
anything|任何东西|/ˈeniθɪŋ/|Do you need anything?|你需要什么吗？
me|我（宾格）|/miː/|Please help me.|请帮帮我。
him|他（宾格）|/hɪm/|I know him.|我认识他。
us|我们（宾格）|/ʌs/|Come with us.|跟我们一起来。
them|他们（宾格）|/ðem/|I see them.|我看见他们了。
myself|我自己|/maɪˈself/|I did it myself.|这是我自己做的。
## 生活琐事 | Daily Life
shower|淋浴|/ˈʃaʊər/|I take a shower.|我洗个澡。
bath|洗澡|/bæθ/|I like a hot bath.|我喜欢泡热水澡。
garbage|垃圾|/ˈɡɑːrbɪdʒ/|Take out the garbage.|把垃圾拿出去。
laundry|洗衣|/ˈlɔːndri/|I do laundry on Sunday.|我星期天洗衣服。
appointment|预约|/əˈpɔɪntmənt/|I have an appointment.|我有个预约。
advice|建议|/ədˈvaɪs/|Thank you for your advice.|谢谢你的建议。
favor|帮忙|/ˈfeɪvər/|Can you do me a favor?|你能帮我个忙吗？
habit|习惯|/ˈhæbɪt/|Reading is a good habit.|阅读是个好习惯。
member|成员|/ˈmembər/|I am a member.|我是会员。
visitor|访客|/ˈvɪzɪtər/|We have a visitor.|我们有位访客。
## 日常衔接词 | Everyday Connectors
however|然而|/haʊˈevər/|It is cheap. However, it is old.|它很便宜，不过很旧。
actually|其实|/ˈæktʃuəli/|Actually, I like it.|其实我挺喜欢的。
really|真的|/ˈriːli/|I really like it.|我真的很喜欢。
probably|大概|/ˈprɑːbəbli/|It will probably rain.|很可能会下雨。
usually|通常|/ˈjuːʒuəli/|I usually eat at home.|我通常在家吃饭。
sometimes|有时|/ˈsʌmtaɪmz/|Sometimes I walk to work.|有时我走路上班。
already|已经|/ɔːlˈredi/|I already ate.|我已经吃过了。
yet|还（没）/ 然而|/jet/|I have not eaten yet.|我还没吃饭。
instead|代替|/ɪnˈsted/|Let's walk instead.|我们走路去吧。
anyway|无论如何|/ˈeniweɪ/|Thanks anyway.|还是谢谢你。
""".trimIndent()

    data class Level(val index: Int, val titleZh: String, val category: String)

    private val parsed: Pair<List<Word>, List<Level>> by lazy { parse() }

    /** Words 151..850. */
    val words: List<Word> get() = parsed.first

    /** Levels 16 and up. */
    val levels: List<Level> get() = parsed.second

    private fun parse(): Pair<List<Word>, List<Level>> {
        val wordList = mutableListOf<Word>()
        val levelList = mutableListOf<Level>()
        var level = FIRST_LEVEL - 1
        var category = ""
        var id = FIRST_ID
        for (line in raw.lineSequence()) {
            val text = line.trim()
            if (text.isEmpty()) continue
            if (text.startsWith("##")) {
                level++
                val parts = text.removePrefix("##").split("|")
                val titleZh = parts[0].trim()
                category = parts.getOrElse(1) { titleZh }.trim()
                levelList.add(Level(level, titleZh, category))
                continue
            }
            val f = text.split("|")
            require(f.size == 5) { "Bad word line: $text" }
            wordList.add(
                Word(
                    id = id++,
                    word = f[0],
                    category = category,
                    translation = f[1],
                    ipa = f[2],
                    exampleSentence = f[3],
                    exampleTranslation = f[4],
                    levelIndex = level
                )
            )
        }
        return wordList to levelList
    }
}
