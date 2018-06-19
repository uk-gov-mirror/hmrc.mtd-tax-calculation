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

package v2.httpparsers

import uk.gov.hmrc.http.{HttpReads, HttpResponse}
import v2.models._
import v2.outcomes.TaxCalcOutcome.TaxCalcOutcome

object TaxCalcHttpParser extends HttpParser {
  implicit val taxCalcHttpReads: HttpReads[TaxCalcOutcome] = new HttpReads[TaxCalcOutcome] {
    override def read(method: String, url: String, response: HttpResponse): TaxCalcOutcome = {
      response.status match {
        case _ => Right(TestTaxCalc.taxCalc)
        //      case OK => validateJson[TaxCalculation](response) match {
        //        case Some(taxCalc) => Right(taxCalc)
        //        case None => Left(TaxCalcOutcome.NotFound)
        //      }
      }
    }
  }
}

object TestTaxCalc {
  val taxCalc =
    TaxCalculation(
      year = "2016-17",
      intentToCrystallise = Some(false),
      crystallised = Some(false),
      validationMessageCount = Some(10),
      incomeTaxAndNicYTD = Some(1000.25),
      nationalRegime = Some("UK"),
      taxableIncome = TaxableIncome(
        employments = Employments(
          totalIncome = Some(1000.25),
          totalPay = Some(1000.25),
          totalBenefitsAndExpenses = Some(1000.25),
          totalAllowableExpenses = Some(1000.25),
          employment = Seq(
            Employment(
              employmentId = Some("ABIS10000000001"),
              netPay = Some(1000.25),
              benefitsAndExpenses = Some(1000.25),
              allowableExpenses = Some(1000.25)
            )
          )
        ),
        selfEmployments = SelfEmployments(
          totalIncome = Some(1000.25),
          selfEmployment = Seq(
            SelfEmployment(
              selfEmploymentId = "XKIS00000000988",
              taxableIncome = Some(1000.25),
              finalised = Some(true),
              losses = Some(1000.25)
            )
          )
        ),
        ukProperty = UKProperty(
          totalIncome = Some(1000.25),
          nonFurnishedHolidayLettingsTaxableProfit = Some(1000.25),
          nonFurnishedHolidayLettingsLoss = Some(1000.25),
          furnishedHolidayLettingsTaxableProfit = Some(1000.25),
          furnishedHolidayLettingsLoss = Some(1000.25),
          finalised = Some(true)
        ),
        ukDividends = UKDividends(
          totalIncome = Some(1000.25)
        ),
        totalIncomeReceived = Some(1000.25),
        allowancesAndDeductions = AllowancesAndDeductions(
          totalAllowancesAndDeductions = Some(1000.25),
          giftOfInvestmentsAndPropertyToCharity = Some(1000.25),
          apportionedPersonalAllowance = Some(1000.25)
        ),
        totalTaxableIncome = Some(1000.25)
      ),
      incomeTax = IncomeTax(
        payPensionsProfit = IncomeTaxItem(
          totalAmount = Some(1000.25),
          band = Seq(
            Band(
              name = "ZRT",
              rate = 99.99,
              threshold = 99999999,
              apportionedThreshold = 99999999,
              income = 1000.25,
              amount = 1000.25
            )
          ),
          personalAllowanceUsed = Some(1000.25),
          taxableIncome = Some(1000.25)
        ),
        savingsAndGains = IncomeTaxItem(
          totalAmount = Some(1000.25),
          band = Seq(
            Band(
              name = "BRT",
              rate = 99.99,
              threshold = 99999999,
              apportionedThreshold = 99999999,
              income = 1000.25,
              amount = 1000.25
            )
          ),
          personalAllowanceUsed = Some(1000.25),
          taxableIncome = Some(1000.25)
        ),
        dividends = IncomeTaxItem(
          totalAmount = Some(1000.25),
          band = Seq(
            Band(
              name = "BRT",
              rate = 99.99,
              threshold = 99999999,
              apportionedThreshold = 99999999,
              income = 1000.25,
              amount = 1000.25
            )
          ),
          personalAllowanceUsed = Some(1000.25),
          taxableIncome = Some(1000.25)
        ),
        totalBeforeReliefs = Some(1000.25),
        allowancesAndReliefs = AllowancesAndReliefs(
          propertyFinanceRelief = Some(1000.25),
          totalAllowancesAndReliefs = Some(1000.25)
        ),
        totalAfterReliefs = Some(1000.25),
        giftAid = GiftAid(
          paymentsMade = Some(1000.25),
          rate = 99.99,
          taxableAmount = Some(1000.25)
        ),
        totalAfterGiftAid = Some(1000.25),
        totalIncomeTax = Some(1000.25)
      ),
      nic = Nic(
        totalNic = Some(1000.25),
        class2 = Class2Nic(
          amount = Some(1000.25),
          weekRate = Some(1000.25),
          weeks = Some(1.1),
          limit = Some(99999999),
          apportionedLimit = Some(2)
        ),
        class4 = Class4Nic(
          totalAmount = Some(1000.25),
          band = Seq(
            Band(
              name = "BRT",
              rate = 99.99,
              threshold = 99999999,
              apportionedThreshold = 99999999,
              income = 1000.25,
              amount = 1000.25
            )
          )
        )
      ),
      totalBeforeTaxDeducted = Some(1000.25),
      taxDeducted = TaxDeducted(
        ukLandAndProperty = Some(1000.25),
        totalTaxDeducted = Some(1000.25)
      ),
      eoyEstimate = EoyEstimate(
        employments = Seq(
          EoyEmployment(
            employmentId = Some("ABIS10000000001"),
            taxableIncome = Some(99999999.99),
            supplied = Some(false),
            finalised = Some(false)
          )
        ),
        selfEmployments = Seq(
          EoySelfEmployment(
            selfEmploymentId = "XKIS00000000988",
            taxableIncome = Some(99999999.99),
            supplied = Some(false),
            finalised = Some(false)
          )
        ),
        ukProperty = EoyItem(
          taxableIncome = Some(99999999.99),
          supplied = Some(false),
          finalised = Some(false)
        ),
        ukDividends = EoyItem(
          taxableIncome = Some(99999999.99),
          supplied = Some(false),
          finalised = Some(false)
        ),
        totalTaxableIncome = Some(99999999.99),
        incomeTaxAmount = Some(99999999.99),
        nic2 = Some(99999999),
        nic4 = Some(99999999),
        totalNicAmount = Some(99999999.99),
        incomeTaxAndNicAmount = Some(99999999.99)
      ),
      calculationMessageCount = Some(1),
      calculationMessages = Seq(
        CalculationMessage(
          `type`= "warning",
          text = Some("abcdefghijklm")
        )
      ),
      annualAllowances = AnnualAllowances(
        personalAllowance = Some(99999999),
        personalAllowanceThreshold = Some(99999999),
        reducedPersonalisedAllowance = Some(99999999),
        giftAidExtender = Some(99999999)
      )
    )
}