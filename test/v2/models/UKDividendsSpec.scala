/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v2.models

import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.play.test.UnitSpec
import v2.models.utils.JsonErrorValidators

class UKDividendsSpec extends UnitSpec with JsonErrorValidators {

  import JsonError._

  val ukDividends =
    UKDividends(
      totalIncome = Some(123.45)
    )
  val emptyUkDividends = UKDividends(None)

  val emptyJson: JsValue = Json.parse("{}")

  "reads" should {
    "return correct validation errors" when {
      "the wrong formats are returned for all of the fields" in {
        val json = Json.parse(
          s"""
             |{
             | "ukDividendsIncome":"nan"
             |}
           """.stripMargin)

        val Left(errors) = UKDividends.reads.reads(json).asEither

        multipleJsonErrorValidator(errors)(
          "/ukDividendsIncome" -> NUMBER_FORMAT_EXCEPTION
        )
      }
    }

    "return a correctly read UKDividends model" when {
      "all fields exist" in {
        val json = Json.parse(
          s"""
             |{
             | "ukDividendsIncome": 123.45
             |}
             """.stripMargin)

        UKDividends.reads.reads(json).get shouldBe ukDividends
      }

      "nullable fields don't exist" in {
        UKDividends.reads.reads(emptyJson).get shouldBe emptyUkDividends
      }
    }
  }

  "write" should {
    "return client json" when {
      "all fields exist" in {

        val clientJson = Json.parse(
          s"""
             |{
             |  "totalIncome": 123.45
             |}
           """.stripMargin)

        UKDividends.writes.writes(ukDividends) shouldBe clientJson
      }

      "all fields don't exist" in {
        UKDividends.writes.writes(emptyUkDividends) shouldBe emptyJson
      }
    }
  }
}
