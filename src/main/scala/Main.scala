
object Main {
  def main(args: Array[String]): Unit = {
    val registerA = Register("a")
    val registerB = Register("b")
    val memory: Memory = Memory(contents = Map(registerA -> Value(0), registerB -> Value(0)))

    val instructions = Vector(Instruction(IncrementR, Some(registerA), None),
                              Instruction(IncrementR, Some(registerB), None),
                              Instruction(IncrementR, Some(registerA), None),
                              Instruction(JumpOffset, None, Some(Offset(-2))))
    val program = Program(instructions = instructions)


    val vm = VirtualMachine(0, memory)

    val result = vm.runToCompletion(program, 10)
    println(program)
    printState(vm)
    printState(result)

    def printState(virtualMachine: VirtualMachine): Unit = {
      println("_________")
      println(virtualMachine)
    }


  }
}
