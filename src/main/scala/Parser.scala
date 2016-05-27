//object Parser {
//
//  def parseInstructions(program: String): Vector[Instruction] = {
//    val instructionFormat = """(.*) (a|b) (+|-\d*)""".r
//
//    val parsedInstructions = {
//      program.map { line => line match {
//        case instructionFormat(stringOp, null, null) =>
//          Instruction(findOperation(stringOp), None, None)
//        case instructionFormat(stringOp, register, null) =>
//          Instruction(findOperation(stringOp), /*findRegister(register)*/ None, None)
//        case instructionFormat(stringOp, register, offset) =>
//      }
//        //            val maybeRegister = if (register)
//        //
//        //              Instruction(operation, maybeRegister, maybeOffset)
//        //          //          var benefit: Int = Operation.opCode
//        //          //          if (polarityS == "lose") benefit *= -1
//        //          //          (Set(name1, name2), benefit)
//        //
//        //        }
//      }
//
//      def findOperation(stringOp: Char): Operation = {
//        val operations = List(IncrementR, JumpIfEven, JumpIfOne, JumpOffset, HalfR, TripleR)
//        operations.find(stringOp == _.opCode).get
//      }
//
//      //  def findRegister(register: Char): Option[Register] = register match {
//      //    case 'a' => Some(registerA)
//      //    case 'b' => Some(registerB)
//      //    case _ => None
//      //  }
//    }
//  }
//}
