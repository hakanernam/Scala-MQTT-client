package net.sigusr

import scodec.codecs._
import scodec.bits._

package object frames {
  val qualityOfServiceCodec = new CaseEnumCodec[QualityOfService](uint2)
  val remainingLengthCodec = new RemainingLengthCodec
  val stringCodec = variableSizeBytes(uint16, utf8)
  val zeroLength = bin"00000000"
}