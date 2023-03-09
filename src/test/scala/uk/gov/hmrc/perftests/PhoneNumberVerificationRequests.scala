/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.perftests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import java.lang.reflect.InvocationTargetException

object PhoneNumberVerificationRequests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("cip-phone-number")
  val route: String   = "/customer-insight-platform/phone-number"
  val phoneNumber = "+447912204199"

  val payload =
    s"""
       |{"phoneNumber" : "${phoneNumber}" }
      """.stripMargin

  val verifyPhoneNumber: HttpRequestBuilder =
    http("Verify phone number call")
      .post(s"$baseUrl$route/verify": String)
      .body(StringBody(payload))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header("Authorization", "fake-token")
      .check(status.is(200))

  val passcode = getPasscode
  
  val verifyPasscode: HttpRequestBuilder = {
    http("Verify a Passcode for the email")
      .post(s"$baseUrl$route/verify/passcode": String)
      .body(StringBody(s"""{"phoneNumber" : "${phoneNumber}", "passcode": "${passcode}" }"""))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header("Authorization", "fake-token")
      .check(status.is(200))
  }

  def getPasscode: String = {
    try {
      val testDataHelper = new TestDataHelper
      testDataHelper.getPhoneNumberAndPasscodeData(phoneNumber).get.passcode
    } catch {
      case e: InvocationTargetException => ""
      case e: NoSuchElementException => ""
      case e: NullPointerException => ""
    }
    finally {
      println("done!")
    }
  }
}
