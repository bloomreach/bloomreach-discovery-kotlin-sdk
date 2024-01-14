package enums

/**
 * VisitorType ENUM to provide hitcount based on new or returning user
 */
enum class VisitorType(val hitCount: Int) {
    NEW_USER(1),
    RETURNING_USER(2)
}