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

import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json.{Json, _}

case class NicBand(name: String,
                   rate: BigDecimal,
                   threshold: Int,
                   apportionedThreshold: Int,
                   income: BigDecimal,
                   amount: BigDecimal)

object NicBand {
  implicit val writes: Writes[NicBand] = Json.writes[NicBand]

  implicit val reads: Reads[NicBand] = (
    (__ \ "name").read[String] and
      (__ \ "rate").read[BigDecimal] and
      (__ \ "threshold").read[Int] and
      (__ \ "apportionedThreshold").read[Int] and
      (__ \ "income").read[BigDecimal] and
      (__ \ "amount").read[BigDecimal]
    ) (NicBand.apply _)

}