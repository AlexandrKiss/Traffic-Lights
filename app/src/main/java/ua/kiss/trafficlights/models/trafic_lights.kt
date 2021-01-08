package ua.kiss.trafficlights.models

import ua.kiss.trafficlights.R

data class ColorLight(
    val name: String,
    val description: String,
    val img: Int,
    val imgInfo: Int)

val greenLight = ColorLight("Зеленый",
    "Свет зеленый впереди?\n" +
            "Смело ты теперь иди.\n" +
            "Вас машины подождут,\n" +
            "Пешеходы все пройдут.",
    R.drawable.rect_green,
    R.drawable.go_dialog)

val yellowLight = ColorLight("Желтый",
    "Желтый светит огонек,\n" +
            "Погоди, постой, дружок.\n" +
            "Ты идти не торопись,\n" +
            "А зеленого дождись.",
    R.drawable.rect_yellow,
    R.drawable.attention_dialog)

val redLight = ColorLight("Красный",
    "Если красный свет горит,\n" +
            "Что тебе он говорит?\n" +
            "Это значит, стой, и жди.\n" +
            "Путь опасный впереди.",
    R.drawable.rect_red,
    R.drawable.stop_dialog)