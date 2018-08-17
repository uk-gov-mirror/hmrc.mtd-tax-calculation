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
import play.api.libs.json.{Reads, _}

case class TaxableIncome(employments: Option[Employments],
                         selfEmployments: Option[SelfEmployments],
                         ukProperty: Option[UKProperty],
                         ukDividends: Option[UKDividends],
                         totalIncomeReceived: BigDecimal,
                         allowancesAndDeductions: AllowancesAndDeductions,
                         totalTaxableIncome: Option[BigDecimal])

object TaxableIncome {
  implicit val writes: Writes[TaxableIncome] = Json.writes[TaxableIncome]

  implicit val reads: Reads[TaxableIncome] = (
    (__ \ "taxableIncome" \ "incomeReceived" \ "employments").readNestedNullable[JsObject].flatMap{
      case Some(_) => (__ \ "taxableIncome" \ "incomeReceived").readNullable[Employments]
      case None => Reads.pure[Option[Employments]](None)
    } and
       (__ \ "taxableIncome" \ "incomeReceived").readNullable[SelfEmployments] and
      (__ \ "taxableIncome" \ "incomeReceived").readNullable[UKProperty] and
      (__ \ "taxableIncome" \ "incomeReceived").readNullable[UKDividends] and
      (__ \ "taxableIncome" \ "totalIncomeReceived").read[BigDecimal] and
      (__ \ "taxableIncome").read[AllowancesAndDeductions] and
      (__ \ "totalTaxableIncome").readNullable[BigDecimal]
    )(TaxableIncome.apply _)

}
