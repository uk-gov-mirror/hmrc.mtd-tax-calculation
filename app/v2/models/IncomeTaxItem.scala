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

case class IncomeTaxItem(totalAmount: Option[BigDecimal],
                         band: Seq[IncomeTaxBand],
                         personalAllowanceUsed: Option[BigDecimal],
                         taxableIncome: Option[BigDecimal])

object IncomeTaxItem {
  implicit val writes: Writes[IncomeTaxItem] = Json.writes[IncomeTaxItem]

  implicit val reads: Reads[IncomeTaxItem] = (
    (__ \ "totalAmount").readNullable[BigDecimal].orElse(Reads.pure(None)) and
      (__ \ "band").read[Seq[IncomeTaxBand]] and
      (__ \ "personalAllowanceUsed").readNullable[BigDecimal].orElse(Reads.pure(None)) and
      (__ \ "taxableIncome").readNullable[BigDecimal].orElse(Reads.pure(None))
    )(IncomeTaxItem.apply _)

}