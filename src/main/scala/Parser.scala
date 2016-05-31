

object Parser {

  def parseInstructions(program: String): Vector[Instruction] = {
    val groupInstructions = program.split("[\\r\\n]+").map(_.split(" "))

    def findOperation(stringOp: String): Operation = {
      val operations = List(IncrementR, JumpIfEven, JumpIfOne, JumpOffset, HalfR, TripleR)
      operations.find(stringOp == _.opCode).get
    }

    def findRegister(register: String): Option[Register] =
      if (register == "a") Some(Register("a")) else Some(Register("b"))

    val parsedInstructions: Array[Instruction] = {
      groupInstructions.map { line => line match {
        case op if line.length == 1 =>
          Instruction(findOperation(op(0)), None, None)
          //these cases are incorrect! wither operation with register or operation with offset
        case opWithReg if line.length == 2 && line(1) == "a" | line(1) == "b" =>
          Instruction(findOperation(opWithReg(0)), findRegister(opWithReg(1)), None)
        case opWithOffset if line.length == 2 =>
          Instruction(findOperation(opWithOffset(0)), None, Some(Offset(opWithOffset(1).toInt)))
        case opWithOffset =>
          Instruction(findOperation(opWithOffset(0)), findRegister(opWithOffset(1)), Some(Offset(opWithOffset(2).toInt)))
      }
      }
    }

    parsedInstructions.toVector
  }
}