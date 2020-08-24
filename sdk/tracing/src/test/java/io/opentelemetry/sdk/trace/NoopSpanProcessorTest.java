/*
 * Copyright 2019, OpenTelemetry Authors
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

package io.opentelemetry.sdk.trace;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/** Unit tests for {@link NoopSpanProcessorTest}. */
class NoopSpanProcessorTest {
  @Mock private ReadableSpan readableSpan;
  @Mock private ReadWriteSpan readWriteSpan;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void noCrash() {
    SpanProcessor noopSpanProcessor = NoopSpanProcessor.getInstance();
    noopSpanProcessor.onStart(readWriteSpan);
    assertThat(noopSpanProcessor.isStartRequired()).isFalse();
    noopSpanProcessor.onEnd(readableSpan);
    assertThat(noopSpanProcessor.isEndRequired()).isFalse();
    noopSpanProcessor.forceFlush();
    noopSpanProcessor.shutdown();
  }
}
