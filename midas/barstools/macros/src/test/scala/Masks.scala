package barstools.macros

import mdf.macrolib._

// Test the ability of the compiler to deal with various mask combinations.

trait MasksTestSettings {
  this: MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator =>
    override lazy val memDepth = 2048
    override lazy val libDepth = 1024
}

// Try all four different kinds of mask config:
/**
 *
 *                    Non-masked mem     Masked mem
 *                  ---------------------------------
 *  Non-masked lib  |                |              |
 *                  ---------------------------------
 *  Masked lib      |                |              |
 *                  ---------------------------------
 */

class Masks_FourTypes_NonMaskedMem_NonMaskedLib extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = None
  override lazy val libWidth = 8 
  override lazy val libMaskGran = None

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_NonMaskedMem_MaskedLib extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = None
  override lazy val libWidth = 8
  override lazy val libMaskGran = Some(2)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_MaskedMem_NonMaskedLib extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = Some(8)
  override lazy val libWidth = 8
  override lazy val libMaskGran = None

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_MaskedMem_NonMaskedLib_SmallerMaskGran extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = Some(4)
  override lazy val libWidth = 8
  override lazy val libMaskGran = None

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_MaskedMem_MaskedLib extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = Some(8)
  override lazy val libWidth = 16
  override lazy val libMaskGran = Some(4)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_MaskedMem_MaskedLib_SameMaskGran extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 32
  override lazy val memMaskGran = Some(8)
  override lazy val libWidth = 16
  override lazy val libMaskGran = Some(8)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_FourTypes_MaskedMem_MaskedLib_SmallerMaskGran extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 64
  override lazy val memMaskGran = Some(4)
  override lazy val libWidth = 32
  override lazy val libMaskGran = Some(8)

  compileExecuteAndTest(mem, lib, v, output)
}

// Bit-mask memories to non-masked libs whose width is larger than 1.

class Masks_BitMaskedMem_NonMaskedLib extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleWidthTestGenerator {
  override lazy val depth = 1024
  override lazy val memWidth = 16
  override lazy val memMaskGran = Some(1)
  override lazy val libWidth = 8
  override lazy val libMaskGran = None

  compileExecuteAndTest(mem, lib, v, output)
}

// FPGA-style byte-masked memories.

class Masks_FPGAStyle_32_8 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 32
  override lazy val memMaskGran = Some(32)
  override lazy val libMaskGran = Some(8)

  compileExecuteAndTest(mem, lib, v, output)
}

// Simple powers of two with bit-masked lib.

class Masks_PowersOfTwo_8_1 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 64
  override lazy val memMaskGran = Some(8)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_PowersOfTwo_16_1 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 64
  override lazy val memMaskGran = Some(16)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_PowersOfTwo_32_1 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 64
  override lazy val memMaskGran = Some(32)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_PowersOfTwo_64_1 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 64
  override lazy val memMaskGran = Some(64)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

// Simple powers of two with non bit-masked lib.

class Masks_PowersOfTwo_32_4 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 128
  override lazy val memMaskGran = Some(32)
  override lazy val libMaskGran = Some(4)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_PowersOfTwo_32_8 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 128
  override lazy val memMaskGran = Some(32)
  override lazy val libMaskGran = Some(8)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_PowersOfTwo_8_8 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 128
  override lazy val memMaskGran = Some(8)
  override lazy val libMaskGran = Some(8)

  compileExecuteAndTest(mem, lib, v, output)
}

// Width as a multiple of the mask, bit-masked lib

class Masks_IntegerMaskMultiple_20_10 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 20
  override lazy val memMaskGran = Some(10)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_21_7 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 21
  override lazy val memMaskGran = Some(21)
  override lazy val libMaskGran = Some(7)

  it should "be enabled when non-power of two masks are supported" is (pending)
  //~ compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_21_21 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 21
  override lazy val memMaskGran = Some(21)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_84_21 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 84
  override lazy val memMaskGran = Some(21)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_92_23 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 92
  override lazy val memMaskGran = Some(23)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_117_13 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 117
  override lazy val memMaskGran = Some(13)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_160_20 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 160
  override lazy val memMaskGran = Some(20)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

class Masks_IntegerMaskMultiple_184_23 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 184
  override lazy val memMaskGran = Some(23)
  override lazy val libMaskGran = Some(1)

  compileExecuteAndTest(mem, lib, v, output)
}

// Width as an non-integer multiple of the mask, bit-masked lib

class Masks_NonIntegerMaskMultiple_32_3 extends MacroCompilerSpec with HasSRAMGenerator with HasSimpleDepthTestGenerator with MasksTestSettings {
  override lazy val width = 32
  override lazy val memMaskGran = Some(3)
  override lazy val libMaskGran = Some(1)

  it should "be enabled when non-power of two masks are supported" is (pending)
  //~ compileExecuteAndTest(mem, lib, v, output)
}
