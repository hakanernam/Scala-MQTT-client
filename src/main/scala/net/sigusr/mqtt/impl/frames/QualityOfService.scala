/*
 * Copyright 2014 Frédéric Cabestre
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

package net.sigusr.mqtt.impl.frames

import scodec.Err

import scala.annotation.switch
import scalaz.\/

sealed trait QualityOfService extends CaseEnum

object AtMostOnce extends QualityOfService { val enum = 0 }
object AtLeastOnce extends QualityOfService { val enum = 1 }
object ExactlyOnce extends QualityOfService { val enum = 2 }

object QualityOfService {

  import scala.language.implicitConversions

  implicit def fromEnum(enum : Int): \/[Err, QualityOfService] =
    (enum: @switch) match {
      case 0 => \/.right(AtMostOnce)
      case 1 => \/.right(AtLeastOnce)
      case 2 => \/.right(ExactlyOnce)
      case _ => \/.left(Err("Quality of service encoded value should be in the range [0..2]"))
    }
}