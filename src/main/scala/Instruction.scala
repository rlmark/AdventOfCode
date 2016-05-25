
case class Instruction(op: Operation, register: Option[Register], offset: Option[Offset])
/// / Note we don't need to pass in actual register, just the reference to the register.
case class Memory(contents: Map[Register, Value])
case class Register(name: String)
case class Value(value: Int)

case class Offset(value: Int)

case class Program(instructions: Vector[Instruction])

case class VirtualMachine(currentInstructionIndex: Int, memory: Memory){
  def executeOneInstruction(program: Program) = {
    val currentInstruction = program.instructions(currentInstructionIndex)
    currentInstruction.op.execute(this, currentInstruction.register, currentInstruction.offset)
  }
  
  def runToCompletion(program: Program, maxSteps: Int) = {
    helper(this, program, maxSteps)
  }
  
  def helper(vm: VirtualMachine, program: Program, remainingSteps: Int):VirtualMachine = {
    println(vm)
    if (remainingSteps == 0) throw new RuntimeException("Too many steps!")

    if (vm.currentInstructionIndex < 0 || vm.currentInstructionIndex > program.instructions.length - 1) {
      vm
    } else {
      val nextState = vm.executeOneInstruction(program)
      helper(nextState, program, remainingSteps - 1)
    }
  }
}