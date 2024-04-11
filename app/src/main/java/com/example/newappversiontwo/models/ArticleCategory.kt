package com.example.newappversiontwo.models
import com.example.newappversiontwo.models.ArticleCategory.*;
enum class ArticleCategory(val categoryName:String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology")
}
fun getAllArticleCategory():List<ArticleCategory>{
    return listOf(
        ArticleCategory.BUSINESS,
        ArticleCategory.ENTERTAINMENT, ArticleCategory.GENERAL,
        ArticleCategory.HEALTH, ArticleCategory.SCIENCE, ArticleCategory.SPORTS,
        ArticleCategory.TECHNOLOGY
    )
}


fun getArticleCategoryFromEnum(category: String): ArticleCategory?{
    val map= values().associateBy(ArticleCategory::categoryName)
    return map[category]
}