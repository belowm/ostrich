/*
 * Copyright 2011 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twitter.ostrich
package stats

import scala.collection.immutable
import com.twitter.conversions.string._
import com.twitter.conversions.time._
import com.twitter.logging.{Level, Logger}
import com.twitter.util.Time
import org.specs.Specification

object StatsReporterSpec extends Specification {
  "StatsReporter" should {
    val collection = new StatsCollection()
    val reporter = new StatsReporter(collection)

    doBefore {
    }

    "delta" in {
      reporter.delta(0, 5) mustEqual 5
      reporter.delta(Long.MaxValue - 10, Long.MaxValue) mustEqual 10
      reporter.delta(-4000, -3000) mustEqual 1000
      reporter.delta(Long.MaxValue, Long.MinValue) mustEqual 1
      reporter.delta(Long.MaxValue - 5, Long.MinValue + 3) mustEqual 9
    }
  }
}
