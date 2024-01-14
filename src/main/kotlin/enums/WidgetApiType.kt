package enums

/**
 * Widget TYPE ENUM to specify which type on widget API needs to be called.
 * This gets added as Path parameter to the request
 */
enum class WidgetApiType(val value: String) {
    ITEM("item"),
    CATEGORY("category"),
    KEYWORD("keyword"),
    PERSONALIZED("personalized"),
    GLOBAL("global"),
    VISUAL_SEARCH("visual/search")
}