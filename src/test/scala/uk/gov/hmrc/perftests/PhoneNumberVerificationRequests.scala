/*
 * Copyright 2024 HM Revenue & Customs
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

object PhoneNumberVerificationRequests extends ServicesConfiguration {

  val baseUrl: String             = baseUrlFor("phone-number-gateway")
  val testBaseUrl: String         = baseUrlFor("phone-number-verification")
  val route: String               = "/phone-number-gateway"
  val phoneNumber: String         = "07912204199"
  val phoneNumberToVerify: String = "+447912204199"
  val payload: String             = s"""{"phoneNumber" : "$phoneNumber"}"""

  val healthUrl       = s"$baseUrl/ping/ping"
  val sendCodeUrl     = s"$baseUrl$route/send-code"
  val retrieveCodeUrl = s"$testBaseUrl/test-only/retrieve/verification-code"
  val verifyCodeUrl   = s"$baseUrl$route/verify-code"

  val health: HttpRequestBuilder =
    http("Initiate phone number verification")
      .get(healthUrl)
      .body(StringBody(payload))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .check(status.is(200))

  val verifyPhoneNumber: HttpRequestBuilder =
    http("Initiate phone number verification")
      .post(sendCodeUrl)
      .body(StringBody(payload))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .check(status.is(200))

  val getVerificationCode: HttpRequestBuilder =
    http("Retrieve a VerificationCode for the phone number verification")
      .post(retrieveCodeUrl)
      .body(StringBody(s"""{"phoneNumber" : "$phoneNumber"}"""))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .check(status.is(200))
      .check(jsonPath("$.verificationCode").saveAs("verificationCode"))

  val verifyVerificationCode: HttpRequestBuilder =
    http("Verify a VerificationCode for the phone number")
      .post(verifyCodeUrl)
      .body(StringBody(s"""{"phoneNumber" : "$phoneNumberToVerify", "verificationCode": "$${verificationCode}" }"""))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .check(status.is(200))
}
