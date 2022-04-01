/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package androidx.media3.transformer;

import android.util.Size;
import androidx.media3.common.util.UnstableApi;
import java.io.IOException;

/**
 * Manages a GLSL shader program for processing a frame.
 *
 * <p>Methods must be called in the following order:
 *
 * <ol>
 *   <li>The constructor, for implementation-specific arguments.
 *   <li>{@link #setInputSize(int, int)}, to configure based on input dimensions.
 *   <li>{@link #initialize(int)}, to set up graphics initialization.
 *   <li>{@link #updateProgramAndDraw(long)}, to process one frame.
 *   <li>{@link #release()}, upon conclusion of processing.
 * </ol>
 */
@UnstableApi
public interface GlFrameProcessor {
  // TODO(b/213313666): Investigate whether all configuration can be moved to initialize by
  //  using a placeholder surface until the encoder surface is known. If so, convert
  //  configureOutputSize to a simple getter.

  /**
   * Sets the input size of frames processed through {@link #updateProgramAndDraw(long)}.
   *
   * <p>This method must be called before {@link #initialize(int)} and does not use OpenGL, as
   * calling this method without a current OpenGL context is allowed.
   *
   * <p>After setting the input size, the output size can be obtained using {@link
   * #getOutputSize()}.
   */
  void setInputSize(int inputWidth, int inputHeight);

  /**
   * Returns the output {@link Size} of frames processed through {@link
   * #updateProgramAndDraw(long)}.
   *
   * <p>Must call {@link #setInputSize(int, int)} before calling this method.
   */
  Size getOutputSize();

  /**
   * Does any initialization necessary such as loading and compiling a GLSL shader programs.
   *
   * <p>This method may only be called after creating the OpenGL context and focusing a render
   * target.
   */
  void initialize(int inputTexId) throws IOException;

  /**
   * Updates the shader program's vertex attributes and uniforms, binds them, and draws.
   *
   * <p>The frame processor must be {@linkplain #initialize(int) initialized}. The caller is
   * responsible for focussing the correct render target before calling this method.
   *
   * @param presentationTimeUs The presentation timestamp of the current frame, in microseconds.
   */
  void updateProgramAndDraw(long presentationTimeUs);

  /** Releases all resources. */
  void release();
}