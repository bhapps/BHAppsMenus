package bhapps.menus.horizontal.horizontalmenu.annotations

import androidx.annotation.Dimension

@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD,
    AnnotationTarget.LOCAL_VARIABLE, AnnotationTarget.EXPRESSION
)
@Dimension(unit = Dimension.DP)
internal annotation class DpAnnotation