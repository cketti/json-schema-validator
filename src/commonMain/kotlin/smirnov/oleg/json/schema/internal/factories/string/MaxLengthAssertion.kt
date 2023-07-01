package smirnov.oleg.json.schema.internal.factories.string

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.intOrNull
import smirnov.oleg.json.pointer.JsonPointer
import smirnov.oleg.json.schema.ErrorCollector
import smirnov.oleg.json.schema.ValidationError
import smirnov.oleg.json.schema.internal.AssertionContext
import smirnov.oleg.json.schema.internal.JsonSchemaAssertion
import smirnov.oleg.json.schema.internal.LoadingContext
import smirnov.oleg.json.schema.internal.factories.AbstractAssertionFactory

@Suppress("unused")
internal object MaxLengthAssertionFactory : AbstractAssertionFactory("maxLength") {
  override fun createFromProperty(element: JsonElement, context: LoadingContext): JsonSchemaAssertion {
    require(element is JsonPrimitive && !element.isString) { "$property must be an integer" }
    val maxLength = requireNotNull(element.intOrNull) { "$property must be a valid integer" }
    require(maxLength >= 0) { "$property must be a non-negative integer" }
    return LengthAssertion(context.schemaPath, maxLength, "must be less or equal to") { a, b -> a <= b }
  }
}