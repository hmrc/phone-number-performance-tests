/*
 * Copyright 2025 HM Revenue & Customs
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

  val baseUrl: String         = baseUrlFor("phone-number-gateway")
  val route: String           = "/phone-number-gateway"
  val testOnlyBaseUrl: String = baseUrlFor("phone-number-verification")

  def payload(phoneNumber:String)                 =
    s"""
       |{"phoneNumber" : "$phoneNumber" }
      """.stripMargin

  def verifyPhoneNumber(phoneNumber: String): HttpRequestBuilder =
    http("Initiate phone number verification")
      .post(s"$baseUrl$route/send-code": String)
      .body(StringBody(payload(phoneNumber)))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header(HttpHeaderNames.UserAgent, "pni-performance-tests")
      .check(status.is(200))

  def getVerificationCode(phoneNumber: String): HttpRequestBuilder =
    http("Retrieve a VerificationCode for the phone number verification")
      .post(s"$testOnlyBaseUrl/test-only/retrieve/verification-code": String)
      .body(StringBody(s"""{"phoneNumber" : "$phoneNumber"}"""))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header(HttpHeaderNames.UserAgent, "pni-performance-tests")
      .check(status.is(200))
      .check(jsonPath("$.verificationCode").saveAs("verificationCode"))

  def verifyVerificationCode(phoneNumber: String): HttpRequestBuilder =
    http("Verify a VerificationCode for the phone number")
      .post(s"$baseUrl$route/verify-code": String)
      .body(StringBody(s"""{"phoneNumber" : "$phoneNumber", "verificationCode": "$${verificationCode}" }"""))
      .header("Content-Type", "application/json")
      .header("Accept", "application/json")
      .header(HttpHeaderNames.UserAgent, "pni-performance-tests")
      .check(status.is(200))
}
